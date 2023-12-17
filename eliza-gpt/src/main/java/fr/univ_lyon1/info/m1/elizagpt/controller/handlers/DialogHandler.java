package fr.univ_lyon1.info.m1.elizagpt.controller.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.Chat;
import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.search.Search;

/**
 * DialogHandler class.
 * Cette class définit le handler du dialog
 */
public class DialogHandler implements Handler {

    private Handler nextHandler;
    private Chat chat;
    private Search search;

    /**
     * Constructeur de DialogHandler.
     * @param chat le chat
     */
    public DialogHandler(final Search search, final Chat chat) {
        this.chat = chat;
        this.search = search;
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
    public void handle(final String actionName, final String str) {
        if (actionName.equals("textFieldEnter")) {
            handleValiderChat(str);
        } else if (actionName.equals("onButtonClick")) {
            handleValiderChat(str);
        } else if (actionName.equals("removeMessage")) {
            handleRemoveMessage(str);
        } else if (nextHandler != null) {
            nextHandler.handle(actionName, str);
        }
    }

    /**
     * Cette fonction traite l'action textfieldEnter et l'action onButtonClick.
     */
    private void handleValiderChat(final String userChat) {
        System.out.println("Handling Dialog Action...");
        search.setIsSearch(false);
        chat.addMessage(new Message("User", userChat));
        Message lastMsgUser = chat.getLastMessagesUser();
        chat.addMessage(new Message("Bot",
                lastMsgUser.botResponse(chat.getName())));
    }

    /**
     * Cette fonction traite l'action removeMessage.
     */
    private void handleRemoveMessage(final String index) {
        System.out.println("Remove Message " + index);
        chat.removeMessage(Integer.parseInt(index));
    }

}
