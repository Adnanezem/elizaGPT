package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.controller.handlers.DialogHandler;
import fr.univ_lyon1.info.m1.elizagpt.controller.handlers.Handler;
import fr.univ_lyon1.info.m1.elizagpt.controller.handlers.SearchHandler;
import fr.univ_lyon1.info.m1.elizagpt.model.Chat;

/**
 * Controller class.
 * Cette class définit le controller
 */
public class Controller {
    private Handler handler;
    private Chat chat;

    /**
     * Constructeur de la classe Controller.
     */
    public Controller() {
        this.chat = new Chat();
        Handler dialogHandler = new DialogHandler(chat);
        Handler searchHandler = new SearchHandler();

        dialogHandler.setNext(searchHandler);

        this.handler = dialogHandler; //first administrator
    }

    /**
     * Cette fonction définit l'action à effectuer.
     * @param actionName l'action à effectuer
     */
    public void performAction(final String actionName, final String userChat) {
        handler.handle(actionName, userChat);
    }

    /**
     * Cette fonction retourne le chat.
     * @return le chat
     */
    public Chat getChat() {
        return chat;
    }
}
