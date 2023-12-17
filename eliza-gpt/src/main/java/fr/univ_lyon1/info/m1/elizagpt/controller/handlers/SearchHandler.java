package fr.univ_lyon1.info.m1.elizagpt.controller.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.Chat;
import fr.univ_lyon1.info.m1.elizagpt.model.search.Search;

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
        if (actionName.equals("onSearchRegex")) {
            handleSearch(userChat, "regexStrategy");
        } else if (actionName.equals("onSearchSubString")) {
            handleSearch(userChat, "subStringStrategy");
        } else if (actionName.equals("onUndo")) {
            handleUndo();
        } else if (nextHandler != null) {
            nextHandler.handle(actionName, userChat);
        }
    }

    /**
     * Cette fonction traite l'action onSearch.
     */
    private void handleSearch(final String userChat, final String strategy) {
        System.out.println("Handling OnSearch " + strategy);
        search.setIsSearch(true);
        search.searchText(userChat, strategy, chat);
    }

    /**
     * Cette fonction traite l'action onUndo.
     */
    private void handleUndo() {
        System.out.println("Handling OnUndo...");
        search.undoSearch();
        search.setIsSearch(false);
    }
}


