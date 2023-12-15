package fr.univ_lyon1.info.m1.elizagpt.controller.handlers;

import javax.swing.*;

public class SearchHandler implements Handler{
    private Handler nextHandler;
    @Override
    public void setNext(Handler h) {
        this.nextHandler = h;
    }

    @Override
    public void handle(String actionName) {
        if (actionName.equals("onSearch")) {
            handleSearch();
        } else if (actionName.equals("onUndo")) {
            handleUndo();
        } else if (nextHandler != null) {
            nextHandler.handle(actionName);
        }
    }

    private void handleSearch() {
        System.out.println("Handling OnSearch...");
    }

    private void handleUndo() {
        System.out.println("Handling OnUndo...");
    }
}
