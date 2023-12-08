package fr.univ_lyon1.info.m1.elizagpt;

/**
 * Interface for the messageProcessor.
 */
public interface MessageProcessorInterface {

    /**
     * Process a message.
     *
     * @param array The message to process.
     * @param <T> The type of the array.
     * @return The response.
     */
    <T> T pickRandom(T[] array);
}
