package fr.univ_lyon1.info.m1.elizagpt;

/**
 * Interface for the controller.
 */
public interface MessageNormalizerInterface {
    /**
     * Normalize a message.
     *
     * @param text The message to normalize.
     * @return The normalized message.
     */
    String normalize(String text);

    /**
     * Normalize a message.
     *
     * @param text The message to normalize.
     * @return The normalized message.
     */
    String firstToSecondPerson(String text);

}
