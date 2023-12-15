package fr.univ_lyon1.info.m1.elizagpt.model;

import java.util.ArrayList;
import java.util.List;

public class Chat {

    private List<Message> messageList;

    public Chat(){
        this.messageList = new ArrayList<Message>();
    }

    public void addMessage(final Message mesg){

    }

    public void removeMessage(int index){

    }

    public List<Message> getMessageList(){
        return messageList;
    }

}
