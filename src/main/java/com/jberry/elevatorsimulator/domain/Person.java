/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.elevatorsimulator.domain;

import com.jberry.interfaces.Request;
import java.util.Random;

/**
 *
 * @author johnberry
 */
public class Person {
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
