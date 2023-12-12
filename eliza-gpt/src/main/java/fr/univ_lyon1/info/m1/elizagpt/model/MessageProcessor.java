package fr.univ_lyon1.info.m1.elizagpt.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;



/**
 * Logic to process a message (and probably reply to it).
 */
public class MessageProcessor {
    /** Random number generator. */
    private final Random random = new Random();



    /**
     * Information about conjugation of a verb.
     */
    public static class Verb {
        private final String firstSingular;
        private final String secondPlural;

        public String getFirstSingular() {
            return firstSingular;
        }

        public String getSecondPlural() {
            return secondPlural;
        }

        Verb(final String firstSingular, final String secondPlural) {
            this.firstSingular = firstSingular;
            this.secondPlural = secondPlural;
        }
    }

    /**
     * List of 3rd group verbs and their correspondance from 1st person signular
     * (Je) to 2nd person plural (Vous).
     * @see Verb
     */
    public static final List<Verb> VERBS = Arrays.asList(
            new Verb("suis", "êtes"),
            new Verb("vais", "allez"),
            new Verb("dis", "dites"),
            new Verb("ai", "avez"),
            new Verb("fais", "faites"),
            new Verb("sais", "savez"),
            new Verb("dois", "devez"),
            //new Verb("pense","pensez"),
            new Verb("peux", "pouvez")
            );



    /** Pick an element randomly in the array.
     *  @param <T> The type of the array.
     * */
    public <T> T pickRandom(final T[] array) {
        return array[random.nextInt(array.length)];
    }

}
