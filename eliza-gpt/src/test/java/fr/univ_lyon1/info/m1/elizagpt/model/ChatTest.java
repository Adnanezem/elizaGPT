package fr.univ_lyon1.info.m1.elizagpt.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ChatTest {

    private Chat chat;

    @BeforeEach
    void setUp() {
        chat = new Chat();
    }

    @Test
    void shouldAddMessageToChat() {
        Message message = new Message("User", "Hello, my name is John.");
        chat.addMessage(message);
        List<Message> messageList = chat.getMessageList();
        assertThat(messageList, hasSize(1));
        assertThat(messageList.get(0), is(equalTo(message)));
    }

    @Test
    void shouldRemoveMessageFromChat() {
        Message message = new Message("User", "Hello, my name is John.");
        chat.addMessage(message);
        chat.removeMessage(0);
        List<Message> messageList = chat.getMessageList();
        assertThat(messageList, is(empty()));
    }

    @Test
    void shouldReturnLastMessageFromBot() {
        Message message1 = new Message("Bot", "Hello, my name is Bot.");
        Message message2 = new Message("User", "Hello, my name is John.");
        chat.addMessage(message1);
        chat.addMessage(message2);
        Message lastMessage = chat.getLastMessagesBot();
        assertThat(lastMessage, is(equalTo(message1)));
    }

    @Test
    void shouldReturnLastMessageFromUser() {
        Message message1 = new Message("Bot", "Hello, my name is Bot.");
        Message message2 = new Message("User", "Hello, my name is John.");
        chat.addMessage(message1);
        chat.addMessage(message2);
        Message lastMessage = chat.getLastMessagesUser();
        assertThat(lastMessage, is(equalTo(message2)));
    }

    @Test
    void shouldReturnUserName() {
        Message message = new Message("User", "Je m'appelle John.");
        chat.addMessage(message);
        String name = chat.getName();
        assertThat(name, is(equalTo("John.")));
    }

    @Test
    void shouldReturnNullWhenNoUserName() {
        Message message = new Message("User", "Hello, my name is.");
        chat.addMessage(message);
        String name = chat.getName();
        assertThat(name, is(nullValue()));
    }
}
