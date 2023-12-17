package fr.univ_lyon1.info.m1.elizagpt.model.search;

import fr.univ_lyon1.info.m1.elizagpt.model.Chat;
import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class SearchTest {

    private Search search;
    private Chat chat;

    @BeforeEach
    void setUp() {
        search = new Search();
        chat = new Chat();
        chat.addMessage(new Message("User", "Hello, my name is John."));
        chat.addMessage(new Message("User", "Goodbye, my name is John."));
    }

    @Test
    void shouldReturnCorrectMessageWhenPatternMatches() {
        search.setSearchStrategy(new RegexSearchStrategy());
        search.searchText("Hello, my name is John.", chat);
        List<Message> result = search.getListSearch();
        assertThat(result, hasSize(1));
        assertThat(result.get(0).getMessage(), is(equalTo("Hello, my name is John.")));
    }

    @Test
    void shouldReturnEmptyListWhenPatternDoesNotMatch() {
        search.setSearchStrategy(new RegexSearchStrategy());
        search.searchText("Good morning, my name is John.", chat);
        List<Message> result = search.getListSearch();
        assertThat(result, is(empty()));
    }

    @Test
    void shouldReturnEmptyListWhenInputListIsEmpty() {
        chat.getMessageList().clear();
        search.setSearchStrategy(new RegexSearchStrategy());
        search.searchText("Hello, my name is John.", chat);
        List<Message> result = search.getListSearch();
        assertThat(result, is(empty()));
    }

    /*
    @Test
    void shouldReturnEmptyListWhenPatternIsNull() {
        search.setSearchStrategy(new RegexSearchStrategy());
        search.searchText(null, chat);
        List<Message> result = search.getListSearch();
        assertThat(result, is(empty()));
    }

    @Test
    void shouldReturnEmptyListWhenChatIsNull() {
        search.setSearchStrategy(new RegexSearchStrategy());
        search.searchText("Hello, my name is John.", null);
        List<Message> result = search.getListSearch();
        assertThat(result, is(empty()));
    }
*/
    @Test
    void shouldClearSearchListWhenUndoSearchIsCalled() {
        search.setSearchStrategy(new RegexSearchStrategy());
        search.searchText("Hello, my name is John.", chat);
        search.undoSearch();
        List<Message> result = search.getListSearch();
        assertThat(result, is(empty()));
    }

    @Test
    void shouldSetIsSearchToFalseWhenUndoSearchIsCalled() {
        search.setSearchStrategy(new RegexSearchStrategy());
        search.searchText("Hello, my name is John.", chat);
        search.undoSearch();
        assertThat(search.getIsSearch(), is(false));
    }
}
