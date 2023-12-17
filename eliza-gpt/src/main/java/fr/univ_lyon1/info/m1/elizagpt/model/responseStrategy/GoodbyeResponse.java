package fr.univ_lyon1.info.m1.elizagpt.model.responseStrategy;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Message class.
 *  Cette class définit une réponse au message "Au revoir"
 */
public class GoodbyeResponse implements ResponseStrategy {
    private final Random random = new Random();

    @Override
    public String generateResponse(final String name, final String normalizedText) {
        Pattern pattern = Pattern.compile("Au revoir.", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            if (random.nextBoolean()) {
                return "Oh non, c'est trop triste de se quitter !";
            } else {
                if (name != null) {
                    return "Au revoir " + name + ".";
                } else {
                    return "Au revoir.";
                }
            }

        }
        return null;
    }
}
