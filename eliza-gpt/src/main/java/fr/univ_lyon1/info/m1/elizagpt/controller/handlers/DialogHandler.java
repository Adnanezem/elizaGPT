package fr.univ_lyon1.info.m1.elizagpt.controller.handlers;

public class DialogHandler implements Handler{

    private Handler nextHandler;

    @Override
    public void setNext(Handler h) {
        this.nextHandler = h;
    }

    @Override
    public void handle(String actionName) {
        if (actionName.equals("textfieldEnter")) {
            handleTextFieldEnter();
        } else if (actionName.equals("onButtonClick")) {
            handleButtonClick();
        } else if (nextHandler != null) {
            nextHandler.handle(actionName);
        }
    }

    private void handleTextFieldEnter() {
        System.out.println("Handling TextFieldEnter...");
    }

    private void handleButtonClick() {
        System.out.println("Handling OnButtonClick...");
    }
}
