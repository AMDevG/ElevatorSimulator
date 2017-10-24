/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.simulator;

import com.jberry.simulator.logging.ActivityLogger;
/**
 *
 * @author johnberry
 */
public class Simulator {
   
    private final SystemTimer timer;
    private final ActivityLogger logger;
    
    private final long timeStepMills;
    private boolean simulatorRunning;
    private long simulatorRunTime;
    private String timeStamp;

    public Simulator(ActivityLogger loggerIn, SystemTimer timerIn){
        logger = loggerIn;
        timer = timerIn;
        timeStepMills = timer.getTimeStepMills();
        simulatorRunning = false;
    }
    public void startSimulation() throws InterruptedException{
        simulatorRunning = true;
        timer.startTimer();
        timer.step();
    }
    public void stopSimulation(){
        simulatorRunning = false;
        timer.stopTimer();
    }
}
    

