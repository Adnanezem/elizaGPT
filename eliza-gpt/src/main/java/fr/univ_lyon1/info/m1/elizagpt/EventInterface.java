package fr.univ_lyon1.info.m1.elizagpt;

/**
 * Interface des evenements de l application Eliza.
 */
public interface EventInterface {

    /**
     * event when the user press enter in the text field.
     * @param event the event
     */
    void onTextFieldEnter(Object event);

    /**
     * event when the user press the button.
     * @param event the event
     */
    void onButtonClick(Object event);

    /**
     * event when the user press the search button.
     * @param event the event
     */
    void onSearch(Object event);

    /**
     * event when the user press the undo button.
     * @param event the event
     */
    void onUndo(Object event);

}
