package fr.univ_lyon1.info.m1.elizagpt.view;

import fr.univ_lyon1.info.m1.elizagpt.EventInterface;
import fr.univ_lyon1.info.m1.elizagpt.MessageListener;
import fr.univ_lyon1.info.m1.elizagpt.MyEventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Main class of the View (GUI) of the application.
 */
public class JfxView {

    private VBox dialog;
    private final Stage stage;
    private List<Node> dialogOriginal;
    private boolean actualSearch;
    private TextField text = null;
    private TextField searchText = null;

    /**
     * The label that displays the current search.
     */
    private final Label searchTextLabel;

    private final List<MessageListener> messageListeners = new ArrayList<>();

    /**
     * Add a listener to the view.
     *
     * @param listener The listener to add.
     */
    public void addMessageListener(final MessageListener listener) {
        messageListeners.add(listener);
    }

    /**
     * Notify all the listeners that a message has been received.
     * @param message The message.
     */
    public void notifyMessageListeners(final String message) {
        for (MessageListener listener : messageListeners) {
            listener.onMessage(message);
        }
    }

    /**
     * Create the main view of the application.
     */
    public JfxView(final Stage stage, final int width, final int height) {
        this.stage = stage;
        stage.setTitle("Eliza GPT");

        VBox root = new VBox(10);
        root.getChildren().addAll(createSearchWidget(), createDialogPane(), createInputWidget());

        displayChatMessage("Bonjour");

        stage.setScene(new Scene(root, width, height));
        text.requestFocus();
        stage.show();
        searchTextLabel = null;
    }


    /**
     * Create the dialog pane of the application.
     *
     * @return The dialog pane.
     */
    private ScrollPane createDialogPane() {
        dialog = new VBox(10);
        ScrollPane dialogScroll = new ScrollPane(dialog);
        dialogScroll.vvalueProperty().bind(dialog.heightProperty());
        dialogScroll.setFitToWidth(true);
        return dialogScroll;
    }


    static final String BASE_STYLE = "-fx-padding: 8px; "
            + "-fx-margin: 5px; "
            + "-fx-background-radius: 5px;";
    static final String USER_STYLE = "-fx-background-color: #A0E0A0; " + BASE_STYLE;
    static final String ELIZA_STYLE = "-fx-background-color: #A0A0E0; " + BASE_STYLE;

    /**
     * Display a message in the chat.
     *
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


    /**
     * Create the search widget of the application.
     *
     * @return The search widget.
     */
    private Pane createSearchWidget() {
        EventInterface handler = new MyEventHandler(this);

        HBox firstLine = new HBox();
        firstLine.setAlignment(Pos.BASELINE_LEFT);
        searchText = createTextField(handler::onSearch);
        firstLine.getChildren().add(searchText);

        HBox secondLine = new HBox();
        secondLine.setAlignment(Pos.BASELINE_LEFT);
        secondLine.getChildren().addAll(createButton("Search",
                                        handler::onSearch),
                                        createButton("Undo search", handler::onUndo));

        return new VBox(firstLine, secondLine);
    }

    /**
     * Create a text field.
     *
     * @param event The event to trigger when the user presses enter.
     * @return The text field.
     */
    private TextField createTextField(final EventHandler<ActionEvent> event) {
        TextField textField = new TextField();
        textField.setOnAction(event);
        return textField;
    }

    /**
     * Create a button.
     *
     * @param text  The text of the button.
     * @param event The event to trigger when the user presses the button.
     * @return The button.
     */
    private Button createButton(final String text, final EventHandler<ActionEvent> event) {
        Button button = new Button(text);
        button.setOnAction(event);
        return button;
    }


    /**
     * Search for a text in the dialog.
     *
     * @param text The text to search.
     */
    public void searchText(final TextField text) {
        String currentSearchText = text.getText();
        if (currentSearchText.isEmpty()) {
            searchTextLabel.setText("No active search");
            return;
        }

        Pattern pattern;
        pattern = Pattern.compile(currentSearchText, Pattern.CASE_INSENSITIVE);

        // Store the original state of the dialog only if a search hasn't been performed
        if (!actualSearch) {
            dialogOriginal = new ArrayList<>(dialog.getChildren());
            actualSearch = true;
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

    /**
     * Undo the search.
     * If no search has been performed, display a message.
     */
    public void undoSearch() {
        // Restore the original state of the dialog
        if (actualSearch && dialogOriginal != null) {
            dialog.getChildren().setAll(dialogOriginal);
            actualSearch = false;
            searchTextLabel.setText("Undo search");
        } else {
            searchTextLabel.setText("No search to undo");
        }
    }


    /**
     * Create the input widget of the application.
     *
     * @return The input widget.
     */
    private Pane createInputWidget() {
        final Pane input = new HBox();
        text = new TextField();
        Button send = new Button("Send");

        EventInterface handler = new MyEventHandler(this);

        text.setOnAction(handler::onTextFieldEnter);
        send.setOnAction(handler::onButtonClick);

        input.getChildren().addAll(text, send);
        return input;
    }


    /**
     * Get the dialog.
     *
     * @return The dialog.
     */
    public List<Node> getDialog() {
        return dialog.getChildren();
    }


    /**
     * Show the stage.
     */
    public void showStage() {
        stage.show();
    }

    public TextField getTextField() {
        return text;
    }

    public TextInputControl getSearchTextField() {
        return searchText;
    }

}
