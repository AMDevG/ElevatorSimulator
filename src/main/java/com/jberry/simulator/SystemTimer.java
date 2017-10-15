/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.simulator;

/**
 *
 * @author johnberry
 */
public class SystemTimer {
    public static final long MILLISECONDS_PER_SECOND = 1000;
    public static final long MILLISECONDS_PER_MINUTE = 60000;
  
    private long timeSimulated;
    private long timeStart;
    private long timeNow;
    private long timeStep;
    private long timeElapsed;
    private boolean simulationOver;
    
    private String stepTimeStamp;
    
    public SystemTimer(long timeStepIn){
        timeSimulated = 0;
        timeStep = timeStepIn;
        System.out.println("Time step is "+timeStep);
        simulationOver = false;
    }
 
    public void startTimer() throws InterruptedException{
        timeStart = System.currentTimeMillis();
   
        while(!simulationOver){
            if (getTimeElapsed()>10000){stopTimer();}
            step();
            System.out.println("Going to sleep");
            Thread.sleep(timeStep);
        }
    }
    
    public long getTimeElapsed(){
       timeNow = System.currentTimeMillis();
       timeElapsed = timeNow - timeStart;
       return timeElapsed;
    }
    
    
    public void step(){
        timeSimulated = timeSimulated + timeStep;
        System.out.println("Time elapsed " + getTimeElapsed());
        stepTimeStamp = formatTimeStamp(timeSimulated);
    }
    
    public void stopTimer(){
        timeNow = System.currentTimeMillis();
        timeElapsed = timeStart - timeNow;
        simulationOver = true;
        System.out.println("Simulator Ended ");
        formatTimeStamp(timeElapsed);
        
    }

    public String formatTimeStamp(long timeInMills){
        
        double timeSeconds = timeInMills/MILLISECONDS_PER_SECOND;
        double hoursElapsed = timeSeconds / (60.0 * 60.0);
        double minsElapsed = 60 * (hoursElapsed - (int)hoursElapsed);
        double secsElapsed = 60 * (minsElapsed - (int)minsElapsed);
        
        int hour = (int)hoursElapsed;
        int min = (int)minsElapsed;
        int sec = (int)secsElapsed;
        
        return (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min) + ":" + (sec < 10 ? "0" + sec : sec);
    }
}
