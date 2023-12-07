package fr.univ_lyon1.info.m1.elizagpt.model;

import fr.univ_lyon1.info.m1.elizagpt.MessageProcessorInterface;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import java.util.List;
import java.util.Random;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Logic to process a message (and probably reply to it).
 */
public class MessageProcessor implements MessageProcessorInterface {
    /** Random number generator. */
    private final Random random = new Random();



    /**
     * Get the name of the user from the dialog.
     * @param dialog The dialog.
     * @return The name of the user, or null if not found.
     */
    @Override
    public String getName(final List<Node> dialog) {
        for (Node hBox : dialog) {
            for (Node label : ((HBox) hBox).getChildren()) {
                if ("-fx-background-color: #A0E0A0;".equals(label.getStyle())) {
                    String text = ((Label) label).getText();
                    String name = getMatchedGroup(text);
                    if (name != null) {
                        return name;
                    }
                }
            }
        }
        return null;
    }

    /**
     * get matched group from text and pattern.
     *
     * @param text text
     * @return matched group
     */
    private String getMatchedGroup(final String text) {
        Pattern p = Pattern.compile("Je m'appelle (.*)\\.", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(text);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }


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
    @Override
    public <T> T pickRandom(final T[] array) {
        return array[random.nextInt(array.length)];
    }

}
