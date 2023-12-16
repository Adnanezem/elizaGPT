package fr.univ_lyon1.info.m1.elizagpt.model;

/**
 *  Cette class définit un verbe.
 */
public class Verb {
    private final String firstSingular;
    private final String secondPlural;

    /**
     * @return the firstSingular
     */
    public String getFirstSingular() {
        return firstSingular;
    }

    /**
     * @return the secondPlural
     */
    public String getSecondPlural() {
        return secondPlural;
    }

    /**
     * Constructeur de la classe Verb.
     * @param firstSingular le premier singulier
     * @param secondPlural le second pluriel
     */
    Verb(final String firstSingular, final String secondPlural) {
        this.firstSingular = firstSingular;
        this.secondPlural = secondPlural;
    }
}
