package fr.univ_lyon1.info.m1.elizagpt.model.responseStrategy;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class GoodbyeResponseTest {

    private GoodbyeResponse goodbyeResponse;

    @BeforeEach
    void setUp() {
        goodbyeResponse = new GoodbyeResponse();
    }

    @Test
    void shouldGenerateGoodbyeResponse() {
        String result = goodbyeResponse.generateResponse("User", "Au revoir.");
        assertThat(result, anyOf(is(equalTo("Oh non, c'est trop triste de se quitter !")),
                is(equalTo("Au revoir User.")), is(equalTo("Au revoir."))));
    }

    @Test
    void shouldReturnNullForNonGoodbyeStatement() {
        String result = goodbyeResponse.generateResponse("User", "Bonjour.");
        assertThat(result, is(nullValue()));
    }

    @Test
    void shouldGenerateGoodbyeResponseWithoutName() {
        String result = goodbyeResponse.generateResponse(null, "Au revoir.");
        assertThat(result, anyOf(is(equalTo("Oh non, c'est trop triste de se quitter !")),
                is(equalTo("Au revoir."))));
    }
}
