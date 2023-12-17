package fr.univ_lyon1.info.m1.elizagpt.model.responseStrategy;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Message class.
 *  Cette class définit une réponse à la question "Qui est le plus ..."
 */
public class WhoIsTheBestResponse implements ResponseStrategy {
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
