/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.simulator;

import com.jberry.simulator.logging.ActivityLogger;
import com.jberry.elevatorsimulator.Main;
import com.jberry.elevatorsimulator.domain.Building;


/**
 *
 * @author johnberry
 */
public class Simulator {
    
    private final Building building;
    
    private final ActivityLogger logger;
    private final long timeStepMills;
    /* TIMER DATA */
    
    private final SystemTimer timer;
    private boolean simulatorRunning;
    private long simulatorRunTime;
    private String timeStamp;
    private String activityLogUpdate;
   
    
    public Simulator(ActivityLogger loggerIn, SystemTimer timerIn){
        logger = loggerIn;
        building = Main.getBuilding(); //MOVE OUT OF MAIN
        timer = timerIn;
        simulatorRunning = false;
        timeStepMills = timer.getTimeStepMills();
    }
    
    public void step() throws InterruptedException{
        System.out.println("Beginning Step Code");
        
        while(simulatorRunning){
            //update objects
            System.out.println("Updating object movement");
            
            //activityLogUpdate = logger.updateLog();
            //String test = logger.logTimeStep(timeStamp);
            
            //System.out.println(test);
            
            System.out.println("Going to Sleep now");
            
            Thread.sleep(timeStepMills);
        }
    }
    
    public void startSimulation() throws InterruptedException{
        simulatorRunning = true;
        System.out.println("Starting simulation; Simulator Running");
        timer.startTimer();
        System.out.println("Started timer.");
        
        System.out.println("Starting time step.");
        timer.step();
        System.out.println("Time step started. Simulation fully running."+"\n"+
                           " Heading back to Main");
    }
    
    public void stopSimulation(){
        simulatorRunning = false;
        timer.stopTimer();
        //timeStamp = timer.formatTimeStamp(timer.getTimeElapsed());
       // logger.logSimulatorStop(timeStamp);
        
    }
   
    public void updateSimulation(){
        System.out.println("Updating Simulation Objects");
    }
   }
    

