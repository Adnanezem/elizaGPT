package fr.univ_lyon1.info.m1.elizagpt.controller.handlers;

/**
 * Handler interface.
 * Cette Interface définit le handler
 */
public interface Handler {
    /**
     * Cette fonction définit le prochain handler.
     * @param h handler suivant
     */
    void setNext(Handler h);

    /**
     * Cette fonction traite l'action.
     * @param actionName l'action à traité
     */
    void handle(String actionName, String userChat);
}
