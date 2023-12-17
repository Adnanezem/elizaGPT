package fr.univ_lyon1.info.m1.elizagpt.model.search;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;

import java.util.List;

/**
 *  SubstringSearchStrategy class.
 *  Cette class définit une stratégie de recherche par sous-chaîne
 */
public class SubstringSearchStrategy implements SearchStrategy {

    @Override
    public void search(final List<Message> list,
                       final List<Message> results,
                       final String pattern) {

        if (pattern == null || pattern.isEmpty()) {
            System.out.println("SubstringSearchStrategy: pattern is null or empty");
        } else {
            for (Message msg : list) {
                if (msg.getMessage().contains(pattern)) {
                    results.add(msg); // Ajoute la sous-chaîne trouvée à la liste des résultats
                }
            }
        }
    }
}
