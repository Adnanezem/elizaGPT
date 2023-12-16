package fr.univ_lyon1.info.m1.elizagpt.view;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.elizagpt.controller.Controller;
import fr.univ_lyon1.info.m1.elizagpt.model.MessageProcessor;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;
import java.util.regex.PatternSyntaxException;


/**
 * Main class of the View (GUI) of the application.
 */
public class JfxView {
    private final VBox dialog;

    private List<Node> dialogOriginal;
    private boolean actualsearch;
    private TextField text = null;
    private TextField searchText = null;
    private Label searchTextLabel = null;
    private MessageProcessor processor = new MessageProcessor();
    private final Random random = new Random();

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
        replyToUser("Bonjour");


        // Everything's ready: add it to the scene and display it
        final Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        text.requestFocus();
        stage.show();
    }

    static final String BASE_STYLE = "-fx-padding: 8px; "
            + "-fx-margin: 5px; "
            + "-fx-background-radius: 5px;";
    static final String USER_STYLE = "-fx-background-color: #A0E0A0; " + BASE_STYLE;
    static final String ELIZA_STYLE = "-fx-background-color: #A0A0E0; " + BASE_STYLE;

    private void replyToUser(final String text) {
        HBox hBox = new HBox();
        final Label label = new Label(text);
        hBox.getChildren().add(label);
        label.setStyle(USER_STYLE);
        hBox.setAlignment(Pos.BASELINE_LEFT);
        dialog.getChildren().add(hBox);

        hBox.setOnMouseClicked(e -> {
            dialog.getChildren().remove(hBox);

        });

    }


    private void sendMessage(final String text) {
        HBox hBox = new HBox();
        final Label label = new Label(text);
        hBox.getChildren().add(label);
        label.setStyle(ELIZA_STYLE);
        hBox.setAlignment(Pos.BASELINE_RIGHT);
        dialog.getChildren().add(hBox);
        hBox.setOnMouseClicked(e -> {
            dialog.getChildren().remove(hBox);

        });
    }

    private Pane createSearchWidget() {
        final HBox firstLine = new HBox();
        final HBox secondLine = new HBox();
        firstLine.setAlignment(Pos.BASELINE_LEFT);
        secondLine.setAlignment(Pos.BASELINE_LEFT);
        searchText = new TextField();
        searchText.setOnAction(e -> {
            searchText(searchText);
            //controller.performAction("onSearch");
        });
        firstLine.getChildren().add(searchText);
        final Button send = new Button("Search");
        send.setOnAction(e -> {
            searchText(searchText);
            //controller.performAction("onSearch");
        });
        searchTextLabel = new Label();
        final Button undo = new Button("Undo search");
        undo.setOnAction(e -> undoSearch());

        secondLine.getChildren().addAll(send, searchTextLabel, undo);
        final VBox input = new VBox();
        input.getChildren().addAll(firstLine, secondLine);
        return input;
    }

    private void searchText(final TextField text) {
        String currentSearchText = text.getText();
        if (currentSearchText.isEmpty()) {
            searchTextLabel.setText("No active search");
            return;
        }

        Pattern pattern;
        try {
            pattern = Pattern.compile(currentSearchText, Pattern.CASE_INSENSITIVE);
        } catch (PatternSyntaxException e) {
            searchTextLabel.setText("Invalid regular expression");
            return;
        }

        // Store the original state of the dialog only if a search hasn't been performed
        if (!actualsearch) {
            dialogOriginal = new ArrayList<>(dialog.getChildren());
            actualsearch = true;
        }

        searchTextLabel.setText("Searching for: " + currentSearchText);

        List<HBox> toDelete = new ArrayList<>();

        for (Node hBox : dialog.getChildren()) {
            for (Node label : ((HBox) hBox).getChildren()) {
                String msg = ((Label) label).getText();
                Matcher matcher = pattern.matcher(msg);

                if (!matcher.find()) {
                    toDelete.add((HBox) hBox);
                }
            }
        }

        dialog.getChildren().removeAll(toDelete);
        text.setText("");
    }

    private void undoSearch() {
        // Restore the original state of the dialog
        if (actualsearch && dialogOriginal != null) {
            dialog.getChildren().setAll(dialogOriginal);
            actualsearch = false;
            searchTextLabel.setText("Undo search");
        } else {
            searchTextLabel.setText("No search to undo");
        }
    }
    private Pane createInputWidget() {
        final Pane input = new HBox();
        text = new TextField();
        text.setOnAction(e -> {
            //sendMessage(text.getText());
            //text.setText("");
            controller.performAction("textFieldEnter", text.getText());
            displayUserMessage(controller.getChat().getLastMessagesUser().getMessage());
            displayChatMessage(controller.getChat().getLastMessagesBot().getMessage());
        });

        final Button send = new Button("Send");
        send.setOnAction(e -> {
            //sendMessage(text.getText());
            //text.setText("");
            controller.performAction("onButtonClick", text.getText());
            displayUserMessage(controller.getChat().getLastMessagesUser().getMessage());
            displayChatMessage(controller.getChat().getLastMessagesBot().getMessage());
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
        final Label label = new Label(text);
        hBox.getChildren().add(label);
        label.setStyle(style);
        hBox.setAlignment(alignment);
        dialog.getChildren().add(hBox);
        hBox.setOnMouseClicked(e -> dialog.getChildren().remove(hBox));
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

}
