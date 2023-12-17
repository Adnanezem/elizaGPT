package fr.univ_lyon1.info.m1.elizagpt.model.responseStrategy;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ConjugaisonResponseTest {

    private ConjugaisonResponse conjugaisonResponse;

    @BeforeEach
    void setUp() {
        conjugaisonResponse = new ConjugaisonResponse();
    }

    @Test
    void shouldConvertFirstToSecondPerson() {
        String result = conjugaisonResponse.firstToSecondPerson("Je suis content.");
        assertThat(result, is(equalTo("vous êtes content.")));
    }

    @Test
    void shouldReturnRandomElementFromArray() {
        String[] array = {"element1", "element2", "element3"};
        String result = conjugaisonResponse.pickRandom(array);
        assertThat(result, anyOf(is("element1"), is("element2"), is("element3")));
    }

    @Test
    void shouldGenerateResponseForFirstPersonStatement() {
        String result = conjugaisonResponse.generateResponse("User", "Je suis content.");
        assertThat(result, anyOf(
                is(equalTo("Pourquoi dites-vous que vous êtes content ?")),
                is(equalTo("Pourquoi pensez-vous que vous êtes content ?")),
                is(equalTo("Êtes-vous sûr que vous êtes content ?"))
        ));
    }

    @Test
    void shouldReturnNullForNonFirstPersonStatement() {
        String result = conjugaisonResponse.generateResponse("User", "Il est content.");
        assertThat(result, is(nullValue()));
    }
}
