package fr.univ_lyon1.info.m1.elizagpt.view;

import java.util.List;

import fr.univ_lyon1.info.m1.elizagpt.controller.Controller;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class of the View (GUI) of the application.
 */
public class JfxView implements Observer {
    private final VBox dialog;
    private TextField text = null;
    private TextField searchText = null;

    private ComboBox<String> comboBox = null;
    private Label searchTextLabel = null;
    private Controller controller;
    /**
     * Create the main view of the application.
     */
    // TODO: style error in the following line. Check that checkstyle finds it, and then fix it.
    public JfxView(final Controller control, final Stage stage, final int width, final int height) {
        stage.setTitle("Eliza GPT");

        final VBox root = new VBox(10);
        this.controller = control;
        final Pane search = createSearchWidget();
        root.getChildren().add(search);



        ScrollPane dialogScroll = new ScrollPane();
        dialog = new VBox(10);
        dialogScroll.setContent(dialog);
        // scroll to bottom by default:
        dialogScroll.vvalueProperty().bind(dialog.heightProperty());
        root.getChildren().add(dialogScroll);
        dialogScroll.setFitToWidth(true);

        final Pane input = createInputWidget();
        root.getChildren().add(input);

        // Everything's ready: add it to the scene and display it
        final Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        text.requestFocus();
        stage.show();
        update();
    }

    static final String BASE_STYLE = "-fx-padding: 8px; "
            + "-fx-margin: 5px; "
            + "-fx-background-radius: 5px;";
    static final String USER_STYLE = "-fx-background-color: #A0E0A0; " + BASE_STYLE;
    static final String ELIZA_STYLE = "-fx-background-color: #A0A0E0; " + BASE_STYLE;

    private Pane createSearchWidget() {
        final HBox firstLine = new HBox();
        final HBox secondLine = new HBox();
        firstLine.setAlignment(Pos.BASELINE_LEFT);
        secondLine.setAlignment(Pos.BASELINE_LEFT);
        searchText = new TextField();
        ComboBox<String> comboBox = new ComboBox<>();
        ObservableList<String> list; // Création de l'ObservableList
        list = FXCollections.observableArrayList();

        list.add("SubString");
        list.add("Regex");
        list.add("Mot complet");

        comboBox.setItems(list);
        comboBox.getSelectionModel().select(1);
        searchText.setOnAction(e -> {
            if (searchText.getText().isEmpty()) {
                searchTextLabel.setText("No search to perform");
            } else {
                if (comboBox.getValue().equals("SubString")) {
                    controller.performAction("onSearchSubString", searchText.getText());
                } else if (comboBox.getValue().equals("Regex")) {
                    controller.performAction("onSearchRegex", searchText.getText());
                } else if (comboBox.getValue().equals("Mot complet")) {
                    controller.performAction("onSearchCompleteWord", searchText.getText());
                }
                searchTextLabel.setText("Search: " + searchText.getText());
                searchText.setText("");
            }
        });

        firstLine.getChildren().addAll(searchText, comboBox);
        final Button send = new Button("Search");
        send.setOnAction(e -> {
            if (searchText.getText().isEmpty()) {
                searchTextLabel.setText("No search to perform");
            } else {
                if (comboBox.getValue().equals("subString")) {
                    controller.performAction("onSearchSubString", searchText.getText());
                } else {
                    controller.performAction("onSearchRegex", searchText.getText());
                }
                searchTextLabel.setText("Search: " + searchText.getText());
                searchText.setText("");
            }
        });
        searchTextLabel = new Label();
        final Button undo = new Button("Undo search");
        undo.setOnAction(e -> {
            controller.performAction("onUndo", searchText.getText());
        });

        secondLine.getChildren().addAll(send, searchTextLabel, undo);
        final VBox input = new VBox();
        input.getChildren().addAll(firstLine, secondLine);
        return input;
    }

    private void searchText(final TextField text) {
        if (text.getText().isEmpty()) {
            text.setText("No search to perform");
            return;
        }
        for (Message msg : controller.getSearch().getListSearch()) {
            if (msg.getMessage().equals(text.getText())) {
                if (msg.getAuthor().equals("Bot")) {
                    displayChatMessage(msg.getMessage());
                } else {
                    displayUserMessage(msg.getMessage());
                }
            }
        }
    }

    private Pane createInputWidget() {
        final Pane input = new HBox();
        text = new TextField();
        text.setOnAction(e -> {
            controller.performAction("textFieldEnter", text.getText());
            text.setText("");
        });

        final Button send = new Button("Send");
        send.setStyle("-fx-background-color: green; ");
        send.setOnAction(e -> {
            controller.performAction("onButtonClick", text.getText());
            text.setText("");
        });
        input.getChildren().addAll(text, send);
        return input;
    }

    /**
     * Display a message in the chat.
     * @param text The message to display.
     */
    private void displayMessage(final String text, final String style, final Pos alignment) {
        HBox hBox = new HBox();
        HBox hBoxDelete = new HBox();
        Button deleteButton = new Button("x");
        final Label label = new Label(text);
        hBoxDelete.getChildren().addAll(label, deleteButton);
        hBox.getChildren().add(hBoxDelete);


        deleteButton.setOnAction(event -> {
            int index = dialog.getChildren().indexOf(hBox);
            controller.performAction("removeMessage", Integer.toString(index));
        });

        label.setStyle(style);
        deleteButton.setStyle("-fx-background-color: brown; " + BASE_STYLE);
        hBox.setAlignment(alignment);
        dialog.getChildren().addAll(hBox);

    }

    /**
     * Display a message from the user in the chat.
     *
     * @param text The message to display.
     */
    public void displayUserMessage(final String text) {
        displayMessage(text, USER_STYLE, Pos.BASELINE_RIGHT);
    }

    /**
     * Display a message from Eliza in the chat.
     *
     * @param text The message to display.
     */
    public void displayChatMessage(final String text) {
        displayMessage(text, ELIZA_STYLE, Pos.BASELINE_LEFT);
    }

    /**
     * Display une liste de message.
     *
     * @param list la liste de message
     */
    public void displayList(final List<Message> list) {

        for (Message msg : list) {
            if (msg.getAuthor().equals("Bot")) {
                displayChatMessage(msg.getMessage());
            } else {
                displayUserMessage(msg.getMessage());
            }
        }
    }

    /**
     * Display a message from Eliza in the chat.
     *
     */
    @Override
    public void update() {
        dialog.getChildren().clear();

        if (controller.getSearch().getIsSearch()) {
            displayList(controller.getSearch().getListSearch());
        } else {
            displayList(controller.getChat().getMessageList());
        }
    }
}
