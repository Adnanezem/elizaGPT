package fr.univ_lyon1.info.m1.elizagpt.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

/**
 *  Message class.
 *  Cette class définit une réponse à un message
 */
public interface ResponseStrategy {

    /**
     * Cette fonction génère une réponse à un message.
     * @param name le nom de l'utilisateur
     * @param normalizedText le message
     * @return la réponse
     */
    String generateResponse(String name, String normalizedText);
}

/**
 *  Message class.
 *  Cette class définit une réponse au message "Au revoir"
 */
class GoodbyeResponse implements ResponseStrategy {
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


/**
 *  Message class.
 *  Cette class définit une réponse au message "Je m'appelle ..."
 */
class NameResponse implements ResponseStrategy {
    @Override
    public String generateResponse(final String name, final String normalizedText) {
        Pattern pattern = Pattern.compile("Je m'appelle (.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            String newName = matcher.group(1);
            return "Bonjour " + newName;
        }
        return null;
    }
}


/**
 *  Message class.
 *  Cette class définit une réponse à la question "Qui est le plus ..."
 */
class WhoIsTheBestResponse implements ResponseStrategy {
    @Override
    public String generateResponse(final String name, final String normalizedText) {
        Pattern pattern = Pattern.compile("Qui est le plus (.*) \\?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            return "Le plus " + matcher.group(1)
                    + " est bien sûr votre enseignant de MIF01 !";
        }
        return null;
    }
}


/**
 *  Message class.
 *  Cette class définit une réponse à la question "Quel est mon nom ?"
 */
class WhatMyNameResponse implements ResponseStrategy {
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



/**
 *  Message class.
 *  Cette class définit une réponse au jeu "Devinez le nombre"
 */
class ResponseNumberGame implements ResponseStrategy {
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

/**
 *  Message class.
 *  Cette class définit une réponse à un message
 */
class ConjugaisonResponse implements ResponseStrategy {
    private final Random random = new Random();

    /**
     * Information about conjugation of a verb.
     */
    protected static final List<Verb> VERBS = Arrays.asList(
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
        for (Verb v : VERBS) {
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

    public String generateResponse(final String name, final String normalizedText) {
        Pattern pattern = Pattern.compile("(Je .*)\\.", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(normalizedText);
            if (matcher.matches()) {
            final String startQuestion = pickRandom(new String[]{
                    "Pourquoi dites-vous que ",
                    "Pourquoi pensez-vous que ",
                    "Êtes-vous sûr que ",
            });
            return startQuestion + firstToSecondPerson(matcher.group(1)) + " ?";
        }
        return null;
    }
}

/**
 * Liste des stratégies de réponse.
 */

final class ResponseStrategies {
    private static final ArrayList<ResponseStrategy> RESPONSE_STRATEGIES = new ArrayList<>();
    private String name;

    // Private constructor to prevent instantiation
    private ResponseStrategies() {
        throw new UnsupportedOperationException("ResponseStrategies doit pas etre instancié");
    }

    static {
        RESPONSE_STRATEGIES.add(new GoodbyeResponse());
        RESPONSE_STRATEGIES.add(new NameResponse());
        RESPONSE_STRATEGIES.add(new WhoIsTheBestResponse());
        RESPONSE_STRATEGIES.add(new WhatMyNameResponse());
        RESPONSE_STRATEGIES.add(new ResponseNumberGame());
        RESPONSE_STRATEGIES.add(new ConjugaisonResponse());
    }

    /**
     * Getter de la liste des stratégies de réponse.
     * @return de la liste des stratégies de réponse
     */
    public static ArrayList<ResponseStrategy> getResponseStrategies() {
        return RESPONSE_STRATEGIES;
    }

}


