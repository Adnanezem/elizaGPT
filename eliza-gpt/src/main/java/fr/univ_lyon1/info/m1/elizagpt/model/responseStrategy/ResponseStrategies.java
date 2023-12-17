package fr.univ_lyon1.info.m1.elizagpt.model.responseStrategy;



import java.util.ArrayList;

/**
 * Liste des stratégies de réponse.
 */
public final class ResponseStrategies {
    public static final ArrayList<ResponseStrategy> RESPONSE_STRATEGIES = new ArrayList<>();

    // Constructeur privé pour empêcher l'instanciation de la classe
    private ResponseStrategies() {
        throw new UnsupportedOperationException("Vous ne pouvez pas instancier cette classe");
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
