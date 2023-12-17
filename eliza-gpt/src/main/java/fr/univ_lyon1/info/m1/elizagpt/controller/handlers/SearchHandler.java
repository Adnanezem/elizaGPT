package fr.univ_lyon1.info.m1.elizagpt.controller.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.Chat;
import fr.univ_lyon1.info.m1.elizagpt.model.search.CompleteWordSearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.model.search.RegexSearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.model.search.Search;
import fr.univ_lyon1.info.m1.elizagpt.model.search.SubstringSearchStrategy;

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
            handleSearchRegex(userChat);
        } else if (actionName.equals("onSearchSubString")) {
            handleSearchsubString(userChat);
        } else if (actionName.equals("onSearchCompleteWord")) {
            handleSearchCompleteWord(userChat);
        } else if (actionName.equals("onUndo")) {
            handleUndo();
        } else if (nextHandler != null) {
            nextHandler.handle(actionName, userChat);
        }
    }

    /**
     * Cette fonction traite l'action onSearchRegex.
     */
    private void handleSearchRegex(final String userChat) {
        System.out.println("Handling OnSearchRegex...");
        search.setIsSearch(true);
        search.setSearchStrategy(new RegexSearchStrategy());
        search.searchText(userChat, chat);
    }

    /**
     * Cette fonction traite l'action onSearchsubstring.
     */
    private void handleSearchsubString(final String userChat) {
        System.out.println("Handling OnSearchRegex...");
        search.setIsSearch(true);
        search.setSearchStrategy(new SubstringSearchStrategy());
        search.searchText(userChat, chat);
    }

    private void handleSearchCompleteWord(final String userChat) {
        System.out.println("Handling OnSearchRegex...");
        search.setIsSearch(true);
        search.setSearchStrategy(new CompleteWordSearchStrategy());
        search.searchText(userChat, chat);
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


