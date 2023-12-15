package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.controller.handlers.DialogHandler;
import fr.univ_lyon1.info.m1.elizagpt.controller.handlers.Handler;
import fr.univ_lyon1.info.m1.elizagpt.controller.handlers.SearchHandler;

public class Controller {
    private Handler handler;

    public Controller(){
        Handler dialogHandler = new DialogHandler();
        Handler searchHandler = new SearchHandler();

        dialogHandler.setNext(searchHandler);

        this.handler = dialogHandler;//first administrator
    }

    public void performAction(String actionName){
        handler.handle(actionName);
    }
}
