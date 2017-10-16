/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.simulator;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johnberry
 */
public class SystemTimer {
    public static final long MILLISECONDS_PER_SECOND = 1000;
    public static final long MILLISECONDS_PER_MINUTE = 60000;
    
    private static String logMessage; //Create class for?
    private static long simulationTimeElapsedMillis;
    public long objectElapsedMillis;
    
    private final long timeStep;
    
        
    private static long timeStart;
    private long timeNow;
    private boolean simulatorRunning; 
    private static String stepTimeStamp;
    
    public SystemTimer(long timeStepIn){   
        
        simulationTimeElapsedMillis = 0;
        timeStep = timeStepIn;
        System.out.println("Created Timer with timeInterval of "+timeStep+" millis.");
        simulatorRunning = false;
        objectElapsedMillis = (long) 0.0;
    }
 
    public void startTimer() throws InterruptedException{
        System.out.println("Started system timer");
        timeStart = System.currentTimeMillis();
        simulatorRunning = true;
    }

    public void step(){
        
        while(simulatorRunning){
            //UPDATE CODE
            simulationTimeElapsedMillis = simulationTimeElapsedMillis + timeStep;
            System.out.println("Performed Step. Total Simulation Time: "+ (simulationTimeElapsedMillis/1000.0));
            System.out.println("Going to sleep for: "+timeStep+" seconds.");
            
            try {
                Thread.sleep(timeStep);
            } catch (InterruptedException ex) {
                Logger.getLogger(SystemTimer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //System.out.println("Time elapsed " + getTimeElapsed());    
    }
    
    public void stopTimer(){
        System.out.println("Timer stopped.");
        timeNow = System.currentTimeMillis();
        simulatorRunning = false;
        simulationTimeElapsedMillis = timeStart - timeNow;
    }

    public long getTimeStepMills(){
        return timeStep;
    }
    
        
    public static long getElapsedTimeMills(){
       return simulationTimeElapsedMillis;
    }
    
    //logger sends request for time related logs
    public static String getTimerLog(){
        logMessage = formatTimeStamp(getElapsedTimeMills());
        return logMessage;
    }
    
    public static String getTimeStamp(){
        
        long now = System.currentTimeMillis();
        long start = SystemTimer.timeStart;
 
        long t = now - start;
        
        System.out.println("ellapsed mills = " + t);
        
        return "";
        //return timeStampToReturn;
    }
             
    public static String formatTimeStamp(long elapsedMillisIn){
        //ERROR ERROR ERROR formatter receiving 0
        System.out.println("Formatter received " + elapsedMillisIn);
        double timeSeconds = elapsedMillisIn/MILLISECONDS_PER_SECOND;
        double hoursElapsed = timeSeconds / (60.0 * 60.0);
        double minsElapsed = 60 * (hoursElapsed - (int)hoursElapsed);
        double secsElapsed = 60 * (minsElapsed - (int)minsElapsed);
        
        System.out.println("Formatting, Double Seconds are " +secsElapsed);
        
        int hour = (int)hoursElapsed;
        int min = (int)minsElapsed;
        int sec = (int)secsElapsed;
        System.out.println("Formatting, Int Seconds are " +sec);
        stepTimeStamp = (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min) + ":" + (sec < 10 ? "0" + sec : sec);
        
        return stepTimeStamp;
    }
}
