/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.elevatorsimulator.domain;

import com.jberry.factories.LogFactory;
import com.jberry.interfaces.Log;
import com.jberry.interfaces.Loggable;
import com.jberry.interfaces.Request;
import com.jberry.simulator.SystemTimer;
import com.jberry.simulator.logging.ActivityLogger;
import java.util.Random;

/**
 *
 * @author johnberry
 */
public class Person implements Loggable{
    private int personID;
    private ElevatorProcessor processor;
 
    private long waitTime;
    private long startTime;
    private long endTime;
    private boolean FINISHED;
    
    private boolean waiting;
    
    private int currentFloor;
    private int destinationFloor;
    
    private String directionOfTravel;
    
    public Person(int IDIn, int currentFloorIn, int destFloorIn){
        personID = IDIn;
        currentFloor = currentFloorIn;
        destinationFloor = destFloorIn;
        FINISHED = false;
        
        if(destinationFloor>currentFloor)
            directionOfTravel = "UP";
        else
            directionOfTravel = "DOWN";

       Log logToDisplay = LogFactory.createNewLog(personID, currentFloor, destinationFloor, SystemTimer.getTimeStamp(), directionOfTravel, Loggable.Event.PERSON_CREATED);
       ActivityLogger.displayLog(logToDisplay);
       // createNewLog(int senderID, int senderCurrentFloor,int senderDestFloor, String timeStampIn, String directionIn, Loggable.Event eventIn)
    }

    public void sendRequest(){
        processor = ElevatorProcessor.getInstance();
        Request r = new FloorRequest(currentFloor, destinationFloor, directionOfTravel);
        processor.handleRequest(r);
    }
            
    public void exitElevator(){
        waiting = false;
        calculateWaitTime();
    }
    
    public void enterElevator(){
    }
    
    public int getID(){ // PUT INTO INTERFACE?
        return personID;
    }
    
    public int getCurrentFloor(){
        return currentFloor;
    }
    public int getDestinationFloor(){
        return destinationFloor;
    }
    public String getDirection(){
        return directionOfTravel;
    }
    public void startWaitTime(){
        startTime = System.currentTimeMillis();
    }
    public void calculateWaitTime(){
        endTime = System.currentTimeMillis();
        waitTime = endTime - startTime;
    }
}
