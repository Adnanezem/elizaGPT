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
        if (pattern == null || pattern.isEmpty()) {
            System.out.println("CompleteWordSearchStrategy: pattern is null or empty");
        } else {
            System.out.println("CompleteWordSearchStrategy: pattern = " + pattern);
            Pattern p = Pattern.compile("\\b" + pattern + "\\b"); // \b pour les mots complets
            for (Message msg : list) {
                String str = msg.normalize();
                Matcher m = p.matcher(str);
                if (m.find()) {
                    results.add(msg);
                }

            }
        }
    }
}
