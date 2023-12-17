package fr.univ_lyon1.info.m1.elizagpt.model;

import java.util.ArrayList;
import java.util.Random;

import static fr.univ_lyon1.info.m1.elizagpt.model.ResponseStrategies.getResponseStrategies;

/**
 *  Message class.
 *  Cette class définit un message
 */
public class Message {

    /**
     * Liste des stratégies de réponse.
     */
    private final ArrayList<ResponseStrategy> responseStrategies = getResponseStrategies();


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
        String normalized = message.replaceAll("\\s+", " ")
                .replaceAll("^\\s+", "")
                .replaceAll("\\s+$", "");

        // Check if the normalized text is an integer
        if (!normalized.matches("\\d+")) {
            // If not, add a period at the end if there isn't one
            normalized = normalized.replaceAll("[^\\.!?:]$", "$0.");
        }

        return normalized;
    }

    /**
     * Répondre à l'utilisateur.
     */
    public String botResponse(final String name) {
        final String normalizedText = normalize();

        for (ResponseStrategy strategy : responseStrategies) {
            String response = strategy.generateResponse(name, normalizedText);
            if (response != null) {
                return response;
            }
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
