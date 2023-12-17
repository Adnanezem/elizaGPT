package fr.univ_lyon1.info.m1.elizagpt.model.search;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class CompleteWordSearchStrategyTest {

    private CompleteWordSearchStrategy completeWordSearchStrategy;
    private List<Message> list;
    private List<Message> results;

    @BeforeEach
    void setUp() {
        completeWordSearchStrategy = new CompleteWordSearchStrategy();
        list = new ArrayList<>();
        results = new ArrayList<>();
        list.add(new Message("User", "Hello my name is John."));
        list.add(new Message("User", "Goodbye my name is John."));
    }

    @Test
    void shouldReturnCorrectMessageWhenPatternMatches() {
        completeWordSearchStrategy.search(list, results, "Hello");
        assertThat(results, hasSize(1));
        assertThat(results.get(0).getMessage(), is(equalTo("Hello my name is John.")));
    }

    @Test
    void shouldReturnEmptyListWhenPatternDoesNotMatch() {
        completeWordSearchStrategy.search(list, results, "Good morning my name is John.");
        assertThat(results, is(empty()));
    }

    @Test
    void shouldReturnEmptyListWhenInputListIsEmpty() {
        list.clear();
        completeWordSearchStrategy.search(list, results, "Hello my name is John.");
        assertThat(results, is(empty()));
    }

    @Test
    void shouldReturnEmptyListWhenPatternIsNull() {
        completeWordSearchStrategy.search(list, results, null);
        assertThat(results, is(empty()));
    }
}
