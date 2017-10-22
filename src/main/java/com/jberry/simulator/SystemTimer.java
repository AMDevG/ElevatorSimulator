/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.simulator;

import com.jberry.elevatorsimulator.domain.Building;
import com.jberry.elevatorsimulator.domain.Floor;
import com.jberry.elevatorsimulator.domain.Person;
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
    public static long tCalc;
    
    private final long timeStep;
  
    private static long timeStart;
    private long timeNow;
    private boolean simulatorRunning; 
    private static String stepTimeStamp;
    
    public SystemTimer(long timeStepIn){   
        
        simulationTimeElapsedMillis = 0;
        timeStep = timeStepIn;
        simulatorRunning = false;
    }
 
    public void startTimer() throws InterruptedException{
        System.out.println("Started system timer");
        timeStart = System.currentTimeMillis();
        simulatorRunning = true;
    }

    public void step(){
        while(simulatorRunning){
            
            simulationTimeElapsedMillis = simulationTimeElapsedMillis + timeStep;
            
            if (simulationTimeElapsedMillis == 4000){

                //SELECTS RANDOM FLOOR THAT IS NOT EMPTY
                //RIDERS ARE PLACED ON FLOOR AT CREATION OF BUILDING
  
                
                Building.getInstance().floorButtonPress(2, 6, "UP");
                Building.getInstance().floorButtonPress(1, 7, "DOWN");
                
            }
            
            Building.getInstance().update(timeStep);
            
            System.out.println("Performed Step. Total Simulation Time: "+ (simulationTimeElapsedMillis/1000.0));
            System.out.println("-----------------------------------");
            
            try {
                Thread.sleep(timeStep);
            } catch (InterruptedException ex) {
                Logger.getLogger(SystemTimer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
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
    
    public static String getTimeStamp(){
        long now = System.currentTimeMillis();
        long start = SystemTimer.timeStart;
        tCalc = (long) now - start;

        return formatTimeStamp(tCalc);
    }
             
    public static String formatTimeStamp(long elapsedMillisIn){

        double timeSeconds = elapsedMillisIn/MILLISECONDS_PER_SECOND;
        double hoursElapsed = timeSeconds / (60.0 * 60.0);
        double minsElapsed = 60 * (hoursElapsed - (int)hoursElapsed);
        double secsElapsed = 60 * (minsElapsed - (int)minsElapsed);
        
        int hour = (int)hoursElapsed;
        int min = (int)minsElapsed;
        int sec = (int)secsElapsed;
        stepTimeStamp = (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min) + ":" + (sec < 10 ? "0" + sec : sec);
        
        return stepTimeStamp;
    }
}
