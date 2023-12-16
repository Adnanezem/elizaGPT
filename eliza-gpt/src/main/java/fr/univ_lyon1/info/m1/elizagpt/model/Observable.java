package fr.univ_lyon1.info.m1.elizagpt.model;

import fr.univ_lyon1.info.m1.elizagpt.view.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable class.
 * Cette class définit un observable
 */
public class Observable {
    private List<Observer> observers = new ArrayList<>();

    /**
     * Cette fonction ajoute un observer.
     * @param observer l'observer à ajouter
     */
    public void addObserver(final Observer observer) {
        observers.add(observer);
    }

    /**
     * Cette fonction supprime un observer.
     * @param observer l'observer à supprimer
     */
    public void removeObserver(final Observer observer) {
        observers.remove(observer);
    }

    /**
     * Cette fonction notifie les observers.
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
