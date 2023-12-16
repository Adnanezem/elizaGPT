package fr.univ_lyon1.info.m1.elizagpt.controller.handlers;

/**
 * Handler class.
 * Cette class définit le handler
 */
public class SearchHandler implements Handler {
    private Handler nextHandler;

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
            handleSearch();
        } else if (actionName.equals("onUndo")) {
            handleUndo();
        } else if (nextHandler != null) {
            nextHandler.handle(actionName, userChat);
        }
    }

    /**
     * Cette fonction traite l'action onSearch.
     */
    private void handleSearch() {
        System.out.println("Handling OnSearch...");
    }

    /**
     * Cette fonction traite l'action onUndo.
     */
    private void handleUndo() {
        System.out.println("Handling OnUndo...");
    }
}
