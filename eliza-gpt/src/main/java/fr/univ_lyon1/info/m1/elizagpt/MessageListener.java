package fr.univ_lyon1.info.m1.elizagpt;


/**
 * Interface for the controller.
 */
public interface MessageListener {
    /**
     * Process a message.
     *
     * @param message The message to process.
     */
    void onMessage(String message);

}
