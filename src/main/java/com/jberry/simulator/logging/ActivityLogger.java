/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.simulator.logging;

import com.jberry.interfaces.Log;
import com.jberry.simulator.SystemTimer;

/**
 *
 * @author johnberry
 */
public class ActivityLogger{
    
    public ActivityLogger(){
    }
    
    public static void displayLog(Log l){
        System.out.println(l.getMessage());   
    }
    
//    public String getActivityLog(){
//        String timeStamp = SystemTimer.formatTimeStamp(SystemTimer.getTimeElapsed());
//        return "Probably going to delete this";
//    }
//    public String getTimeStamp(){
//        String timeStamp = SystemTimer.formatTimeStamp(SystemTimer.getTimeElapsed());
//        return timeStamp;
//    }
//    
//    public String logSimulatorStop(String timeStampIn){
//        //PUBLISH STATS ABOUT SIM RUN
//        String logMessage = timeStampIn + "  simulator Ended";             
//        return logMessage;   
//    }
//    
    
    /*CREAT INTERFACE FOR HANDLING UPDATE LOGS FOR TYPES:
    
    Floor:
    
        Floor Calls:
            00:00:01 Person P1 presses DOWN button on Floor 10
    
    
    Elevator:
        Elevator Dispatched:
            00:00:47 Elevator 1 is going to Floor 9 for UP request [Current Floor Requests: 8, 9][Current Rider Requests:5]
            
        Rider Request Sent:
            00:00:37 Elevator 3 Rider Request made for Floor 16 [Current Floor Requests: 2][ Current Rider Requests:16]
            
        
        Handling Rider Request:
            00:00:58 Elevator 3 moving from Floor 10 to Floor 11 [Current Floor Requests: 0][ Current Rider Requests: 16]
    
        
        Handled Request (Floor or Rider)
            00:00:27 Elevator 2 has arrived at Floor 12 for Floor Request [Current Floor Requests: 14][Current Rider Requests: 16]
        
        Doors Open/Close
            00:03:09 Elevator 3 Doors Open           
            00:03:12 Elevator 3 Doors Close
        
        PersonEnteringElevator
            00:00:06 Person P12 entered Elevator 1 [Riders: P3, P5, P12]
            00:07:22 Person P43 entered Elevator 4 [Riders: P22, P43]
        
        PersonExitingElevator
            00:04:52 Person P18 has left Elevator 4 [Riders: P13] 
   */
    
}
