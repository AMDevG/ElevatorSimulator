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
    private long durationTime = 60000;
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
        while(simulationTimeElapsedMillis <= durationTime){ 
            simulationTimeElapsedMillis = simulationTimeElapsedMillis + timeStep;

            //IN FINAL SUBMISSION RIDERS ARE PLACED ON FLOOR AT CREATION OF BUILDING
            if (simulationTimeElapsedMillis == 2000){
                Building.getInstance().getFloors().get(5).addPerson(new Person(1,6,1));
                Building.getInstance().floorButtonPress(3, 6, "DOWN");
                
                Building.getInstance().getFloors().get(15).addPerson(new Person(12,16,19));
                Building.getInstance().floorButtonPress(1, 16, "UP");
                
                Building.getInstance().getFloors().get(15).addPerson(new Person(12,16,2));
                Building.getInstance().floorButtonPress(4, 16, "DOWN");
            }
            
            if (simulationTimeElapsedMillis == 4000){
                Building.getInstance().getFloors().get(1).addPerson(new Person(2,2,10));
                Building.getInstance().floorButtonPress(2, 2, "UP");
                
                Building.getInstance().getFloors().get(9).addPerson(new Person(3,10,18));
                Building.getInstance().floorButtonPress(1, 10, "UP");
            }
            
            if (simulationTimeElapsedMillis == 6000){
                Building.getInstance().getFloors().get(10).addPerson(new Person(4,11,1));
                Building.getInstance().floorButtonPress(4, 11, "DOWN");
                
                Building.getInstance().getFloors().get(3).addPerson(new Person(5,4,10));
                Building.getInstance().floorButtonPress(2, 4, "UP");
            }
            if (simulationTimeElapsedMillis == 8000){
                Building.getInstance().getFloors().get(16).addPerson(new Person(6,17,18));
                Building.getInstance().floorButtonPress(2, 17, "UP");
                
                Building.getInstance().getFloors().get(8).addPerson(new Person(7,9,14));
                Building.getInstance().floorButtonPress(3, 9, "UP");
                
                Building.getInstance().getFloors().get(9).addPerson(new Person(8,10,3));
                Building.getInstance().floorButtonPress(1, 10, "DOWN");
                
                Building.getInstance().getFloors().get(9).addPerson(new Person(7,10,14));
                Building.getInstance().floorButtonPress(3, 10, "UP");
            }

            Building.getInstance().update(timeStep);
            
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
