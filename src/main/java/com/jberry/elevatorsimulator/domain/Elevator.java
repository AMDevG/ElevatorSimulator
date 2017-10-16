/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.elevatorsimulator.domain;
import com.jberry.factories.LogFactory;
import com.jberry.interfaces.Request;
import com.jberry.interfaces.ElevatorInterface;
import com.jberry.simulator.SystemTimer;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import com.jberry.interfaces.Log;
import com.jberry.interfaces.Loggable;
import com.jberry.simulator.Simulator;
import com.jberry.simulator.logging.ActivityLogger;

/**
 *
 * @author johnberry
 */
public class Elevator implements ElevatorInterface, Loggable {
    private ElevatorProcessor processor;
    private int elevatorID;
    private int maxNumberPeople;
  
    
    private ArrayList<Person> peopleInElevator;
    private ArrayList<Integer> floorsToVisit;
    
    private boolean doorsOpen;
    private int currentFloor;
    
    private long travelTimeMills;
    private long doorActionTimesMills;
    private long idleTimeMills;
 
    private String direction;
    private boolean available;
    private int sectorID;

    public Elevator(int elevatorIDIn, long travelTimeMillsIn,
                                   long doorActionTimeMillsIn, int maxNumberPeopleIn, long idleTimeMillsIn){
        elevatorID = elevatorIDIn;
        doorsOpen = false;
        available = true;
        travelTimeMills = travelTimeMillsIn;
        currentFloor = 1;
        idleTimeMills = idleTimeMillsIn;
        floorsToVisit = new ArrayList<Integer>();
        direction = UP_DIRECTION;  
        peopleInElevator = new ArrayList<Person>();
    }
    
    // SIMULATING MOVEMENT
    public void move(int destFloor){
        int movingFromFloor = currentFloor;
        
        //SIMULATE TIME; MOVING INSTANTLY NOW
        
        currentFloor = destFloor;
        System.out.println("Moving elevator "+getElevatorID()+
                " from Floor: "+movingFromFloor+
                " now at floor "+getCurrentFloor());  
    }
    
    
    public void sendRequest(Request r){
        System.out.println("Senging Request");     
     //Pass Off Request to Processor
    }
    
    public void addFloorToVisit(int floorID){
        floorsToVisit.add(floorID);
        System.out.println("Updated stops for elevator " + elevatorID);
        System.out.println("List of stops:  ");
        
        for(int i : floorsToVisit){
            System.out.println("Floor: " + i);
        } 
    }
    public void openDoors(){
        String timeStamp = SystemTimer.getTimeStamp();
        System.out.println("In OpenDoors received timestamp:  "+timeStamp);
        Log logToSend = LogFactory.createNewLog(getElevatorID(), getCurrentFloor(), timeStamp, Event.DOORS_OPEN);
        ActivityLogger.displayLog(logToSend);
    }
    
    public void closeDoors(){
    }
    
    public int getCurrentFloor(){
        return currentFloor;
    }
    
    public int getElevatorID(){
        return elevatorID;
    }
    
    public long getIdleTime(){
        return idleTimeMills;
    }
    public int getCurrentCapacity(){
        return peopleInElevator.size();
    }
    
    public ArrayList<Integer> getFloorsToVisit(){
        ArrayList<Integer>copyOfFloorsToVisit = new ArrayList<Integer>();   
        for(int floorIDOut : floorsToVisit)
            copyOfFloorsToVisit.add(floorIDOut);
        return copyOfFloorsToVisit;
    }
    
    public void reAssignSector(int sectorIDIn)
    {
        sectorID = sectorIDIn;
    }
    
    public void update(){
        
        /*
        UPDATE CURRENT FLOOR FROM TIME STEP
        UPDATE PASSENGER LIST
        UPDATE DIRECTION
        UPDATE FLOORS TO VISIT
        UPDATE 
        
        
        */
        
        
        
    }
}
