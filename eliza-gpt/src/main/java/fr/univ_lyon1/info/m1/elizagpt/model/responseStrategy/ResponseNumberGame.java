package fr.univ_lyon1.info.m1.elizagpt.model.responseStrategy;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Message class.
 *  Cette class définit une réponse au jeu "Devinez le nombre"
 */
public class ResponseNumberGame implements ResponseStrategy {
    private final Random random = new Random();
    private Integer secretNumber = null;

    @Override
    public String generateResponse(final String name, final String normalizedText) {
        Pattern pattern = Pattern.compile(".*jeu.*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(normalizedText);
        if (matcher.find()) {
            secretNumber = random.nextInt(100) + 1;
            return "D'accord, je vais penser à un nombre entre 1 et 100, à vous de le deviner !";
        }

        if (secretNumber != null) {
            try {
                int guessedNumber = Integer.parseInt(normalizedText);
                if (guessedNumber == secretNumber) {
                    secretNumber = null;
                    return "Félicitations, vous avez trouvé le bon numéro !";
                } else if (guessedNumber < secretNumber) {
                    return "Le numéro que vous avez deviné est trop petit. Essayez encore !";
                } else {
                    return "Le numéro que vous avez deviné est trop grand. Essayez encore !";
                }
            } catch (NumberFormatException e) {
                return "Je n'ai pas compris votre réponse. Veuillez entrer un nombre.";
            }
        }

        return null;
    }
}
