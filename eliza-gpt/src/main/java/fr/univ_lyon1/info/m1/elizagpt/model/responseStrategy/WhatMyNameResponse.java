package fr.univ_lyon1.info.m1.elizagpt.model.responseStrategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Message class.
 *  Cette class définit une réponse à la question "Quel est mon nom ?"
 */
public class WhatMyNameResponse implements ResponseStrategy {
    @Override
    public String generateResponse(final String name, final String normalizedText) {
        Pattern pattern = Pattern.compile("Quel est mon nom \\?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            if (name != null) {
                return "Votre nom est " + name + ".";
            } else {
                return "Je ne connais pas votre nom.";
            }
        }
        return null;
    }

}
