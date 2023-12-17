package fr.univ_lyon1.info.m1.elizagpt.model.search;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;

import java.util.List;

/**
 *  Cette interface definie une stratégie de recherche.
 */
public interface SearchStrategy {
    /**
     * Cette fonction recherche une sous-chaîne dans une liste de messages.
     * @param list la liste de messages
     * @param results la liste des résultats
     * @param pattern la sous-chaîne à rechercher
     */
    void search(List<Message> list, List<Message> results, String pattern);
}
