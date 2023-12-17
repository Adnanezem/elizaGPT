package fr.univ_lyon1.info.m1.elizagpt.model.responseStrategy;


/**
 *  Message class.
 *  Cette class définit une réponse à un message
 */
public interface ResponseStrategy {

    /**
     * Cette fonction génère une réponse à un message.
     * @param name le nom de l'utilisateur
     * @param normalizedText le message
     * @return la réponse
     */
    String generateResponse(String name, String normalizedText);
}




















