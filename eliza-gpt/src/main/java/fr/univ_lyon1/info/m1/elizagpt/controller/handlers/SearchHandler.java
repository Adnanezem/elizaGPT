package fr.univ_lyon1.info.m1.elizagpt.controller.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.Chat;
import fr.univ_lyon1.info.m1.elizagpt.model.Search;

/**
 * Handler class.
 * Cette class définit le handler
 */
public class SearchHandler implements Handler {
    private Handler nextHandler;
    private Search search;
    private Chat chat;

    /**
     * Constructeur de SearchHandler.
     * @param search la recherche
     * @param chat le chat
     */
    public SearchHandler(final Search search, final Chat chat) {
        this.search = search;
        this.chat = chat;
    }

    /**
     * Cette fonction définit le prochain handler.
     * @param h handler suivant
     */
    @Override
    public void setNext(final Handler h) {
        this.nextHandler = h;
    }

    /**
     * Cette fonction traite l'action.
     * @param actionName l'action à traité
     */
    @Override
    public void handle(final String actionName, final String userChat) {
        if (actionName.equals("onSearch")) {
            handleSearch(userChat);
        } else if (actionName.equals("onUndo")) {
            handleUndo();
        } else if (nextHandler != null) {
            nextHandler.handle(actionName, userChat);
        }
    }

    /**
     * Cette fonction traite l'action onSearch.
     */
    private void handleSearch(final String userChat) {
        System.out.println("Handling OnSearch...");
        search.searchText(userChat, chat);
    }

    /**
     * Cette fonction traite l'action onUndo.
     */
    private void handleUndo() {
        System.out.println("Handling OnUndo...");
        search.undoSearch();
    }
}


