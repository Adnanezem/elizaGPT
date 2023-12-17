package fr.univ_lyon1.info.m1.elizagpt.model.search;

import fr.univ_lyon1.info.m1.elizagpt.model.Chat;
import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 *  Search class.
 *  Cette class définit une recherche
 */
public class Search extends Observable {
    private List<Message> searchList;
    private SearchStrategy searchStrategy;
    private boolean isSearch;

    /**
     * Constructeur de la classe Search.
     */
    public Search() {
        this.searchList = new ArrayList<>();
        this.searchStrategy = new RegexSearchStrategy();
        this.isSearch = false;
    }

    /**
     * Getter de la liste des messages.
     * @return la liste des messages
     */
    public List<Message> getListSearch() {
        return searchList;
    }

    /**
     * Getter de la recherche.
     * @return la recherche
     */
    public boolean getIsSearch() {
        return isSearch;
    }

    /**
     * Setter de la recherche.
     * @param isSearch la recherche
     */
    public void setIsSearch(final boolean isSearch) {
        this.isSearch = isSearch;
    }

    /**
     * Setter de la stratégie de recherche.
     * @param searchStrategy la stratégie de recherche
     */
    public void setSearchStrategy(final SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    /**
     * fonction qui recherche un avec la strategy regex dans un chat.
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

        searchStrategy.search(chat.getMessageList(), searchList, currentSearchText);
        notifyObservers();
    }



    /**
     * fonction qui annule la recherche.
     */
    public void undoSearch() {
        searchList.clear();
        isSearch = false;
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
