package fr.univ_lyon1.info.m1.elizagpt.util;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 * Classe pour créer les widgets.
 */
public class WidgetFactory {

    /**
     * Méthode pour créer un textfield.
     */
    public TextField createTextField(final EventHandler<ActionEvent> event) {
        TextField textField = new TextField();
        textField.setOnAction(event);
        return textField;
    }

    /**
     * Méthode pour créer un bouton.
     */
    public Button createButton(final String text, final EventHandler<ActionEvent> event) {
        Button button = new Button(text);
        button.setOnAction(event);
        return button;
    }
}
