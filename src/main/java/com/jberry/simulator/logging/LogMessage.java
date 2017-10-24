/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.simulator.logging;

/**
 *
 * @author johnberry
 */
public class LogMessage {
    
    private String timeStamp;
    private String message;

    public LogMessage(String messageIn){
        message = messageIn;
    }
    public String getSenderID(){
        return timeStamp;
    }
    public String getMessage(){
        return message;
    }
    
    
}
