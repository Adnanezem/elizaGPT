package fr.univ_lyon1.info.m1.elizagpt;

import fr.univ_lyon1.info.m1.elizagpt.model.Chat;

/**
 * Observer interface.
 * Cette interface définit un observer
 */
public interface Observer {
    /**
     * Cette fonction met à jour le chat.
     * @param chat le chat
     */
    void update(Chat chat);

}
