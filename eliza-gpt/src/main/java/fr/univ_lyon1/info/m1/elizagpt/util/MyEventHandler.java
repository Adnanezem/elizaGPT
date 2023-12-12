package fr.univ_lyon1.info.m1.elizagpt.util;

import fr.univ_lyon1.info.m1.elizagpt.view.JfxView;
import javafx.scene.control.TextField;

/**
 * Classe pour gérer les événements.
 */
public class MyEventHandler {


    private final JfxView view;

    /**
     * Constructeur de la classe.
     * @param view la vue
     */
    public MyEventHandler(final JfxView view) {
        this.view = view;
    }

    public void onTextFieldEnter(final Object event) {
        processEvent();
    }

    public void onButtonClick(final Object event) {
        processEvent();
    }


    /**
     * Méthode pour l'evennement de la touche entrée et click.
     */
    public void processEvent() {
        String message = view.getTextField().getText();
        view.displayUserMessage(message);
        view.getTextField().clear();
    }


    /**
     * Méthode pour le bouton search.
     * @param event l'événement
     */
    public void onSearch(final Object event) {
        TextField message = (TextField) view.getSearchTextField();
        view.searchText(message);
        view.getSearchTextField().clear();
    }

    /**
     * Méthode pour le bouton undo.
     * @param event l'événement
     */
    public void onUndo(final Object event) {
        view.undoSearch();
    }




}
