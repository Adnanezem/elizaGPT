package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.controller.handlers.DialogHandler;
import fr.univ_lyon1.info.m1.elizagpt.controller.handlers.Handler;
import fr.univ_lyon1.info.m1.elizagpt.controller.handlers.SearchHandler;
import fr.univ_lyon1.info.m1.elizagpt.model.Chat;
import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.search.Search;

/**
 * Controller class.
 * Cette class définit le controller
 */
public class Controller {
    private Handler handler;
    private Chat chat;
    private Search search;

    /**
     * Constructeur de la classe Controller.
     */
    public Controller(final Chat chat, final Search search) {
        this.chat = chat;
        this.search = search;
        Handler dialogHandler = new DialogHandler(search, chat);
        Handler searchHandler = new SearchHandler(search, chat);
        chat.addMessage(new Message("Bot", "Bonjour, je suis Eliza, votre psychologue virtuel. "));
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

    /**
     * Cette fonction retourne la recherche.
     * @return la recherche
     */
    public Search getSearch() {
        return search;
    }
}
