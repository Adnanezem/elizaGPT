package fr.univ_lyon1.info.m1.elizagpt.controller.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.Chat;
import fr.univ_lyon1.info.m1.elizagpt.model.Message;

/**
 * DialogHandler class.
 * Cette class définit le handler du dialog
 */
public class DialogHandler implements Handler {

    private Handler nextHandler;
    private Chat chat;

    /**
     * Constructeur de DialogHandler.
     * @param chat le chat
     */
    public DialogHandler(final Chat chat) {
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
        if (actionName.equals("textfieldEnter")) {
            handleValiderChat(userChat);
        } else if (actionName.equals("onButtonClick")) {
            handleValiderChat(userChat);
        } else if (nextHandler != null) {
            nextHandler.handle(actionName, userChat);
        }
    }

    /**
     * Cette fonction traite l'action textfieldEnter et l'action onButtonClick.
     */
    private void handleValiderChat(final String userChat) {
        System.out.println("Handling TextFieldEnter...");
        chat.addMessage(new Message("User", userChat));
        //chat.DisplayChat();
        chat.addMessage(new Message("Bot",
                chat.getLastMessagesUser().botResponse(chat.getName())));
    }

}
