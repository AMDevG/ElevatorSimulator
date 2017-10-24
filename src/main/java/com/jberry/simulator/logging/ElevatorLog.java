/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.simulator.logging;

import com.jberry.interfaces.Log;

/**
 *
 * @author johnberry
 */
public class ElevatorLog implements Log {
    
    private String logMessage;
    
    public ElevatorLog(String messageIn){
        logMessage = messageIn;
    }
    public String getMessage(){
        return logMessage;
    }
    
}
