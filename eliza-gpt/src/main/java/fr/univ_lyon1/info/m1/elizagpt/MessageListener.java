package fr.univ_lyon1.info.m1.elizagpt;

/**
 * Interface for a message listener.
 */
public interface MessageListener {

    /**
     * notify the listener that a message has been received.
     * @param message the message
     */
    void onMessage(String message);
}
