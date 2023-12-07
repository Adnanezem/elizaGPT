package fr.univ_lyon1.info.m1.elizagpt;

/**
 * Interface for the controller.
 * This interface is used to process messages.
 */
public interface ControllerInterface extends MessageListener {
    /**
     * Process a message.
     *
     * @param message The message to process.
     */
    void processMessage(String message);

}
