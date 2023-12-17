package fr.univ_lyon1.info.m1.elizagpt.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Unit tests for Verb class.
 */
public class VerbTest {

    /**
     * Verb to test.
     */
    private Verb verb;


    /**
     * Set up the verb to test.
     */
    @BeforeEach
    public void setUp() {
        verb = new Verb("mange", "mangent");
    }


    /**
     * Test the constructor.
     */
    @Test
    public void shouldReturnCorrectFirstSingular() {
        assertThat(verb.getFirstSingular(), is(equalTo("mange")));
    }


    /**
     * Test the constructor.
     */
    @Test
    public void shouldReturnCorrectSecondPlural() {
        assertThat(verb.getSecondPlural(), is(equalTo("mangent")));
    }


    /**
     * Test the constructor.
     */
    @Test
    public void shouldReturnNullForNonExistentFirstSingular() {
        Verb verb = new Verb(null, "mangent");
        assertThat(verb.getFirstSingular(), is(nullValue()));
    }


    /**
     * Test the constructor.
     */
    @Test
    public void shouldReturnNullForNonExistentSecondPlural() {
        Verb verb = new Verb("mange", null);
        assertThat(verb.getSecondPlural(), is(nullValue()));
    }
}
