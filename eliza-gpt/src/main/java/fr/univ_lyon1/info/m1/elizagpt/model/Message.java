package fr.univ_lyon1.info.m1.elizagpt.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Message class.
 *  Cette class définit un message
 */
public class Message {

    private String author;
    private String message;
    private final Random random = new Random();

    /**
     * Constructeur de la classe Message.
     * @param author l'auteur du message
     * @param message le message
     */
    public Message(final String author, final String message) {
        this.author = author;
        this.message = message;
    };

    /**
     * Getter de l'auteur du message.
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Setter de l'auteur du message.
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter du message.
     * @param message le message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Normlize the text: remove extra spaces, add a final dot if missing.
     * @return normalized text.
     */
    public String normalize() {
        return message.replaceAll("\\s+", " ")
                .replaceAll("^\\s+", "")
                .replaceAll("\\s+$", "")
                .replaceAll("[^\\.!?:]$", "$0.");
    }

    /**
     * Information about conjugation of a verb.
     */
    protected static final List<MessageProcessor.Verb> VERBS = Arrays.asList(
            new MessageProcessor.Verb("suis", "êtes"),
            new MessageProcessor.Verb("vais", "allez"),
            new MessageProcessor.Verb("dis", "dites"),
            new MessageProcessor.Verb("ai", "avez"),
            new MessageProcessor.Verb("fais", "faites"),
            new MessageProcessor.Verb("sais", "savez"),
            new MessageProcessor.Verb("dois", "devez"),
            //new Verb("pense","pensez"),
            new MessageProcessor.Verb("peux", "pouvez")
    );

    /**
     * Turn a 1st-person sentence (Je ...) into a plural 2nd person (Vous ...).
     * The result is not capitalized to allow forming a new sentence.
     *
     * TODO: does not deal with all 3rd group verbs.
     *
     * @param text
     * @return The 2nd-person sentence.
     */
    public String firstToSecondPerson(final String text) {
        String processedText = text
                .replaceAll("[Jj]e ([a-z]*)e ", "vous $1ez ");
        for (MessageProcessor.Verb v : VERBS) {
            processedText = processedText.replaceAll(
                    "[Jj]e " + v.getFirstSingular(),
                    "vous " + v.getSecondPlural());
        }
        processedText = processedText
                .replaceAll("[Jj]e ([a-z]*)s ", "vous $1ssez ")
                .replace("mon ", "votre ")
                .replace("ma ", "votre ")
                .replace("mes ", "vos ")
                .replace("moi", "vous");
        return processedText;
    }

    /** Pick an element randomly in the array. */
    public <T> T pickRandom(final T[] array) {
        return array[random.nextInt(array.length)];
    }

    /**
     * Répondre à l'utilisateur.
     */
    public String botResponse(final String name) {
        final String normalizedText = normalize();
        Pattern pattern;
        Matcher matcher;

        // First, try to answer specifically to what the user said
        pattern = Pattern.compile(".*Je m'appelle (.*)\\.", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            return "Bonjour " + matcher.group(1) + ".";
        }

        pattern = Pattern.compile("Quel est mon nom \\?", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            if (name != null) {
                return "Votre nom est " + name + ".";
            } else {
                return "Je ne connais pas votre nom.";
            }
        }

        pattern = Pattern.compile("Qui est le plus (.*) \\?", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            return "Le plus " + matcher.group(1)
                    + " est bien sûr votre enseignant de MIF01 !";
        }

        pattern = Pattern.compile("(Je .*)\\.", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            final String startQuestion = pickRandom(new String[]{
                    "Pourquoi dites-vous que ",
                    "Pourquoi pensez-vous que ",
                    "Êtes-vous sûr que ",
            });
            return startQuestion + firstToSecondPerson(matcher.group(1)) + " ?";
        }

        // Nothing clever to say, answer randomly
        if (random.nextBoolean()) {
            return "Il faut beau aujourd'hui, vous ne trouvez pas ?";
        }
        if (random.nextBoolean()) {
            return "Je ne comprends pas.";
        }
        if (random.nextBoolean()) {
            return "Hmmm, hmm ...";
        }
        // Default answer
        if (name != null) {
            return "Qu'est-ce qui vous fait dire cela, " + name + " ?";
        } else {
            return "Qu'est-ce qui vous fait dire cela ?";
        }
    }
}
