package fr.univ_lyon1.info.m1.elizagpt.controller;


import fr.univ_lyon1.info.m1.elizagpt.model.Chat;
import fr.univ_lyon1.info.m1.elizagpt.model.search.Search;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ControllerTest {

    private Controller controller;
    private Chat chat;
    private Search search;

    @BeforeEach
    void setUp() {
        chat = new Chat();
        search = new Search();
        controller = new Controller(chat, search);
    }

    @Test
    void shouldPerformAction() {
        controller.performAction("textFieldEnter", "Je m'appelle Wail");
        assertThat(chat.getMessageList(), hasSize(3));
        assertThat(chat.getMessageList().get(0).getAuthor(), is(equalTo("Bot")));
        assertThat(chat.getMessageList().get(0).getMessage(),
                    is(equalTo("Bonjour, je suis Eliza, votre psychologue virtuel. ")));
        assertThat(chat.getMessageList().get(1).getAuthor(), is(equalTo("User")));
        assertThat(chat.getMessageList().get(1).getMessage(), is(equalTo("Je m'appelle Wail")));
        assertThat(chat.getMessageList().get(2).getAuthor(), is(equalTo("Bot")));
        assertThat(chat.getMessageList().get(2).getMessage(), is(equalTo("Bonjour Wail.")));
    }

    @Test
    void shouldGetChat() {
        Chat result = controller.getChat();
        assertThat(result, is(equalTo(chat)));
    }

    @Test
    void shouldGetSearch() {
        Search result = controller.getSearch();
        assertThat(result, is(equalTo(search)));
    }
}
