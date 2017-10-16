/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.simulator;

import com.jberry.simulator.logging.ActivityLogger;
import com.jberry.elevatorsimulator.Main;
import com.jberry.elevatorsimulator.domain.Building;
import com.jberry.elevatorsimulator.domain.Elevator;
import com.jberry.elevatorsimulator.domain.Floor;
import java.util.ArrayList;


/**
 *
 * @author johnberry
 */
public class Simulator {
    
    private final Building building;
    private final SystemTimer timer;
    private final ActivityLogger logger;
    
    private final long timeStepMills;
    private boolean simulatorRunning;
    private long simulatorRunTime;
    private String timeStamp;
    
    private static ArrayList<Floor> floors;
    private static ArrayList<Elevator> elevators;

    public Simulator(ActivityLogger loggerIn, SystemTimer timerIn){
        logger = loggerIn;
        timer = timerIn;
        timeStepMills = timer.getTimeStepMills();
        building = Main.getBuilding(); //MOVE OUT OF MAIN
        floors = building.getFloors();
        elevators = building.getElevators();
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
   
    public static void updateSimulation(){
        for(Elevator e : elevators){
            e.update();
        } 
    }
}
    

