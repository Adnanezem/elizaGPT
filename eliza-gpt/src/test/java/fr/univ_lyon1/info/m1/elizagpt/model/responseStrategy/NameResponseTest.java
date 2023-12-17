package fr.univ_lyon1.info.m1.elizagpt.model.responseStrategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class NameResponseTest {

    private NameResponse nameResponse;

    @BeforeEach
    void setUp() {
        nameResponse = new NameResponse();
    }

    @Test
    void shouldGenerateCorrectResponseForNameStatement() {
        String result = nameResponse.generateResponse("User", "Je m'appelle John.");
        assertThat(result, is(equalTo("Bonjour John.")));
    }

    @Test
    void shouldReturnNullForNonNameStatement() {
        String result = nameResponse.generateResponse("User", "Bonjour.");
        assertThat(result, is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenNameIsNull() {
        String result = nameResponse.generateResponse(null, "Je m'appelle John.");
        assertThat(result, is(equalTo("Bonjour John.")));
    }

    @Test
    void shouldReturnNullWhenStatementIsNull() {
        String result = nameResponse.generateResponse("User", null);
        assertThat(result, is(nullValue()));
    }
}
