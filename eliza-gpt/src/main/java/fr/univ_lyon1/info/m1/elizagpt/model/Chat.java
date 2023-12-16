package fr.univ_lyon1.info.m1.elizagpt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Chat class.
 *  Cette class définit un chat
 */
public class Chat extends Observable {

    private List<Message> messageList;
    private String name;

    /**
     * Constructeur de la classe Chat.
     */
    public Chat() {
        this.messageList = new ArrayList<Message>();
        this.name = null;
    }

    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Ajoute un message au chat.
     * @param mesg le message
     */
    public void addMessage(final Message mesg) {
        System.out.println("Adding message : " + mesg.getMessage());
        messageList.add(mesg);
        notifyObservers();
    }

    /**
     * Supprime un message du chat.
     * @param index l'index du message
     */
    public void removeMessage(final int index) {
        messageList.remove(index);
        notifyObservers();
    }

    /**
     * Getter de la liste des messages.
     * @return la liste des messages
     */
    public List<Message> getMessageList() {
        return messageList;
    }

    /**
     * Getter du dernier message  du bot.
     * @return le dernier message
     */
    public Message getLastMessagesBot() {
        if (!messageList.isEmpty()) {
            Message lastMessage = messageList.get(messageList.size() - 1);

            int size = messageList.size();

            while (!(lastMessage.getAuthor().equals("Bot")) && size > 1) {
                lastMessage = messageList.get(size - 2);
                size--;
            }
            if (lastMessage.getAuthor().equals("Bot")) {
                //System.out.println(lastMessage.getAuthor()+" : "+lastMessage.getMessage());
                return lastMessage;
            }
            throw new RuntimeException("No bot message found");
        }
        throw new RuntimeException("No message found");
    }

    /**
     * Getter du dernier message de l'utilisateur.
     * @return le dernier message
     */
    public Message getLastMessagesUser() {
        if (!messageList.isEmpty()) {
            Message lastMessage = messageList.get(messageList.size() - 1);

            int size = messageList.size();

            while (!(lastMessage.getAuthor().equals("User")) && size > 1) {
                lastMessage = messageList.get(size - 2);
                size--;
            }
            if (lastMessage.getAuthor().equals("User")) {
                //System.out.println(lastMessage.getAuthor()+" : "+lastMessage.getMessage());
                return lastMessage;
            }
            throw new RuntimeException("No user message found");
        }
        throw new RuntimeException("No message found");
    }

    /**
     * Extract the name of the user from the dialog.
     * TODO: this totally breaks the MVC pattern, never, ever, EVER do that.
     * @return
     */
    public String getName() {
        for (Message msg : messageList) {
            if (msg.getAuthor().equals("User")) {
                String text = msg.getMessage();
                Pattern pattern = Pattern.compile("Je m'appelle (.*)\\.",
                        Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(text);
                if (matcher.matches()) {
                    return matcher.group(1);
                }
            }
        }
        return null;
    }

    /**
     * Affiche le chat.
     */
    public void displayChat() {
        for (Message mesg : messageList) {
            System.out.println(mesg.getAuthor() + " : " + mesg.getMessage());
        }
    }
}
