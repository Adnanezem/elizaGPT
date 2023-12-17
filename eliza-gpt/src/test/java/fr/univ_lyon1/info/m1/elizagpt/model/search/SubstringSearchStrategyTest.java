package fr.univ_lyon1.info.m1.elizagpt.model.search;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class SubstringSearchStrategyTest {

    private SubstringSearchStrategy substringSearchStrategy;
    private List<Message> list;
    private List<Message> results;

    @BeforeEach
    void setUp() {
        substringSearchStrategy = new SubstringSearchStrategy();
        list = new ArrayList<>();
        results = new ArrayList<>();
        list.add(new Message("User", "Hello, my name is John."));
        list.add(new Message("User", "Goodbye, my name is John."));
    }

    @Test
    void shouldReturnCorrectMessageWhenSubstringExists() {
        substringSearchStrategy.search(list, results, "my name is");
        assertThat(results, hasSize(2));
        assertThat(results.get(0).getMessage(), is(equalTo("Hello, my name is John.")));
        assertThat(results.get(1).getMessage(), is(equalTo("Goodbye, my name is John.")));
    }

    @Test
    void shouldReturnEmptyListWhenSubstringDoesNotExist() {
        substringSearchStrategy.search(list, results, "Good morning");
        assertThat(results, is(empty()));
    }

    @Test
    void shouldReturnEmptyListWhenInputListIsEmpty() {
        list.clear();
        substringSearchStrategy.search(list, results, "my name is");
        assertThat(results, is(empty()));
    }

   /*@Test
    void shouldReturnEmptyListWhenSubstringIsNull() {
        substringSearchStrategy.search(list, results, null);
        assertThat(results, is(empty()));
    }*/
}
