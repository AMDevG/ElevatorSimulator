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
public class ActivityLogger{
    
    public ActivityLogger(){}
    
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
    
}
