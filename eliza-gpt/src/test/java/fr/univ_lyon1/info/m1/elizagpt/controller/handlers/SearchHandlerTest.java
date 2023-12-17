package fr.univ_lyon1.info.m1.elizagpt.controller.handlers;


import fr.univ_lyon1.info.m1.elizagpt.model.Chat;
import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.search.Search;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class SearchHandlerTest {

    private SearchHandler searchHandler;
    private Chat chat;
    private Search search;

    @BeforeEach
    void setUp() {
        chat = new Chat();
        search = new Search();
        searchHandler = new SearchHandler(search, chat);
    }

    @Test
    void shouldHandleOnSearchRegexAction() {
        chat.addMessage(new Message("User", "Hello, my name is John."));
        searchHandler.handle("onSearchRegex", "Hello, my name is John.");
        assertThat(search.getListSearch(), hasSize(1));
        assertThat(search.getListSearch().get(0).getMessage(),
                is(equalTo("Hello, my name is John.")));
    }

    @Test
    void shouldHandleOnSearchSubStringAction() {
        chat.addMessage(new Message("User", "Hello, my name is John."));
        searchHandler.handle("onSearchSubString", "my name is");
        assertThat(search.getListSearch(), hasSize(1));
        assertThat(search.getListSearch().get(0).getMessage(),
                is(equalTo("Hello, my name is John.")));
    }

    /*@Test
    void shouldHandleOnSearchCompleteWordAction() {
        chat.addMessage(new Message("User", "Bonjour Eliza"));
        searchHandler.handle("onSearchCompleteWord", "Bonjour");
        assertThat(search.getListSearch(), hasSize(1));
        assertThat(search.getListSearch().get(0).getMessage(), is(equalTo("Bonjour")));
    }*/

    @Test
    void shouldHandleOnUndoAction() {
        chat.addMessage(new Message("User", "Hello, my name is John."));
        searchHandler.handle("onSearchRegex", "Hello, my name is John.");
        searchHandler.handle("onUndo", "");
        assertThat(search.getListSearch(), is(empty()));
    }

    @Test
    void shouldNotHandleUnknownAction() {
        chat.addMessage(new Message("User", "Hello, my name is John."));
        searchHandler.handle("unknownAction", "Hello, my name is John.");
        assertThat(search.getListSearch(), is(empty()));
    }
}
