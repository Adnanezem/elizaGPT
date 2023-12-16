package fr.univ_lyon1.info.m1.elizagpt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 *  Search class.
 *  Cette class définit une recherche
 */
public class Search extends Observable {
    private List<Message> searchList;

    /**
     * Constructeur de la classe Search.
     */
    public Search() {
        this.searchList = new ArrayList<>();
    }

    /**
     * Getter de la liste des messages.
     * @return la liste des messages
     */
    public List<Message> getListSearch() {
        return searchList;
    }

    /**
     * fonction qui recherche un texte dans un chat.
     * @param currentSearchText le texte à rechercher
     * @param chat le chat
     */
    public void searchText(final String currentSearchText, final Chat chat) {

        if (!searchList.isEmpty()) {
            searchList.clear();
        }

        if (currentSearchText.isEmpty()) {
            System.out.println("No active search");
        }

        Pattern pattern;
        try {
            pattern = Pattern.compile(currentSearchText, Pattern.CASE_INSENSITIVE);
        } catch (PatternSyntaxException e) {
            System.out.println("Invalid regular expression");
            return;
        }

        for (Message msg : chat.getMessageList()) {
            Matcher matcher = pattern.matcher(msg.getMessage());

            if (matcher.find()) {
                searchList.add(msg);
            }

        }
        notifyObservers();
    }

    /**
     * fonction qui annule la recherche.
     */
    public void undoSearch() {
        searchList.clear();
        notifyObservers();
    }

    /**
     * fonction qui affiche la recherche.
     */
    public void displaySearch() {
        for (Message msg : searchList) {
            System.out.println(msg.getMessage());
        }
    }
}
