package fr.univ_lyon1.info.m1.elizagpt.controller.handlers;


import fr.univ_lyon1.info.m1.elizagpt.model.Chat;
import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.search.Search;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class DialogHandlerTest {

    private DialogHandler dialogHandler;
    private Chat chat;
    private Search search;

    @BeforeEach
    void setUp() {
        chat = new Chat();
        search = new Search();
        dialogHandler = new DialogHandler(search, chat);
    }

    @Test
    void shouldHandleTextFieldEnterAction() {
        dialogHandler.handle("textFieldEnter", "Hello, my name is John.");
        assertThat(chat.getMessageList(), hasSize(2));
        assertThat(chat.getMessageList().get(0).getAuthor(), is(equalTo("User")));
        assertThat(chat.getMessageList().get(0).getMessage(),
                is(equalTo("Hello, my name is John.")));
        assertThat(chat.getMessageList().get(1).getAuthor(), is(equalTo("Bot")));
    }

    @Test
    void shouldHandleOnButtonClickAction() {
        dialogHandler.handle("onButtonClick", "Goodbye, my name is John.");
        assertThat(chat.getMessageList(), hasSize(2));
        assertThat(chat.getMessageList().get(0).getAuthor(), is(equalTo("User")));
        assertThat(chat.getMessageList().get(0).getMessage(),
                is(equalTo("Goodbye, my name is John.")));
        assertThat(chat.getMessageList().get(1).getAuthor(), is(equalTo("Bot")));
    }

    @Test
    void shouldHandleRemoveMessageAction() {
        chat.addMessage(new Message("User", "Hello, my name is John."));
        chat.addMessage(new Message("Bot", "Hello, John."));
        dialogHandler.handle("removeMessage", "0");
        assertThat(chat.getMessageList(), hasSize(1));
        assertThat(chat.getMessageList().get(0).getAuthor(), is(equalTo("Bot")));
    }

    @Test
    void shouldNotHandleUnknownAction() {
        dialogHandler.handle("unknownAction", "Hello, my name is John.");
        assertThat(chat.getMessageList(), is(empty()));
    }
}
