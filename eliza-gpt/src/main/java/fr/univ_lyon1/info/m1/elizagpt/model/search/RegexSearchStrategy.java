package fr.univ_lyon1.info.m1.elizagpt.model.search;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  RegexSearchStrategy class.
 *  Cette class définit une stratégie de recherche par expression régulière
 */
public class RegexSearchStrategy implements SearchStrategy {

    @Override
    public void search(final List<Message> list,
                        final List<Message> results,
                        final String pattern) {
        Pattern regex = Pattern.compile(pattern);
        for (Message msg : list) {
            Matcher matcher = regex.matcher(msg.getMessage());
            if (matcher.find()) {
                results.add(msg); // Ajoute la sous-chaîne trouvée à la liste des résultats
            }
        }
    }
}
