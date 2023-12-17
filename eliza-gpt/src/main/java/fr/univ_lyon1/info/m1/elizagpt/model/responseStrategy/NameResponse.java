package fr.univ_lyon1.info.m1.elizagpt.model.responseStrategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Message class.
 *  Cette class définit une réponse au message "Je m'appelle ..."
 */
public class NameResponse implements ResponseStrategy {
    @Override
    public String generateResponse(final String name, final String normalizedText) {
        if (normalizedText == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("Je m'appelle (.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            String newName = matcher.group(1);
            return "Bonjour " + newName;
        }
        return null;
    }
}
