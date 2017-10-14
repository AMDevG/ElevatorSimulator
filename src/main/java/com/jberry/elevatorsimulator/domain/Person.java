/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.elevatorsimulator.domain;

import java.util.Random;

/**
 *
 * @author johnberry
 */
public class Person {
    private int personID;
    
    private long waitTime;
    private long startTime;
    private long endTime;
    
    private boolean waiting;
    
    private int currentFloor;
    private int destinationFloor;
    
    public Person(int IDIn, int currentFloorIn, int destFloorIn){
        personID = IDIn;
        currentFloor = currentFloorIn;
        destinationFloor = destFloorIn;
        waiting = true;
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
    
    private void startWaitTime(){
        startTime = System.currentTimeMillis();
    }
    private void calculateWaitTime(){
        endTime = System.currentTimeMillis();
        waitTime = endTime - startTime;
    }
}
