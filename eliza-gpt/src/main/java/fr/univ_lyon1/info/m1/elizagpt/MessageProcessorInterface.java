package fr.univ_lyon1.info.m1.elizagpt;

import javafx.scene.Node;

import java.util.List;

/**
 * Interface for the messageProcessor.
 */
public interface MessageProcessorInterface {
    /**
     * Process a message.
     *
     * @param dialog The message to process.
     *               @return The response.
     */
    String getName(List<Node> dialog);

    /**
     * Process a message.
     *
     * @param array The message to process.
     * @param <T> The type of the array.
     * @return The response.
     */
    <T> T pickRandom(T[] array);
}
