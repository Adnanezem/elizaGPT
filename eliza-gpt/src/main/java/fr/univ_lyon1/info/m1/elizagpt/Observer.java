package fr.univ_lyon1.info.m1.elizagpt;

import fr.univ_lyon1.info.m1.elizagpt.model.Chat;

public interface Observer {
    void update(Chat chat);

}
