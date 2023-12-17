package fr.univ_lyon1.info.m1.elizagpt.model.search;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  CompleteWordSearchStrategy class.
 *  Cette class définit une stratégie de recherche par mot complet
 */
public class CompleteWordSearchStrategy implements SearchStrategy {
    @Override
    public void search(final List<Message> list,
                       final List<Message> results,
                       final String pattern) {

        Pattern p = Pattern.compile("\\b" + pattern + "\\b"); // \b pour les mots complets
        for (Message msg : list) {
            if (msg.getMessage().equals(pattern)) {
                Matcher m = p.matcher(msg.getMessage());
                if (m.find()) {
                    results.add(msg);
                }
            }
        }
    }
}
