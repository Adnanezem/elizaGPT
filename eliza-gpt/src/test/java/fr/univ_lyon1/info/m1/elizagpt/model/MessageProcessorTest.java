package fr.univ_lyon1.info.m1.elizagpt.model;

import fr.univ_lyon1.info.m1.elizagpt.util.MessageNormalizer;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for MessageProcessor.
 */
public class MessageProcessorTest {
    @Test
    void testFirstToSecondPerson() {
        // Given
        MessageNormalizer n = new MessageNormalizer();

        // Then
        assertThat(n.firstToSecondPerson("Je pense à mon chien."),
                // TODO: Obviously wrong, it should be chien
                is("vous pensez à votre chien."));

        assertThat(n.firstToSecondPerson("Je suis heureux."),
                is("vous êtes heureux."));

        assertThat(n.firstToSecondPerson("Je dis bonjour."),
                is("vous dites bonjour."));

        assertThat(n.firstToSecondPerson("Je vais à la mer."),
                is("vous allez à la mer."));

        assertThat(n.firstToSecondPerson("Je finis mon travail."),
                is("vous finissez votre travail."));
    }

    /**
     * Not so relevant test, but here to give an example of non-trivial
     * hamcrest assertion.
     */
    @Test
    void testVerbList() {
        assertThat(MessageProcessor.VERBS, hasItem(
                allOf(
                        hasProperty("firstSingular", is("suis")),
                        hasProperty("secondPlural", is("êtes")))));
    }
}
