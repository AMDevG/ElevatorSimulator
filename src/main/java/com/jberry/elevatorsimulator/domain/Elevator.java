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

import com.jberry.interfaces.Log;
import com.jberry.interfaces.Loggable;
import com.jberry.simulator.Simulator;
import com.jberry.simulator.logging.ActivityLogger;
import gui.ElevatorDisplay;
import java.util.HashMap;

/**
 *
 * @author johnberry
 */
public class Elevator implements ElevatorInterface {
    private ElevatorProcessor processor;
    private int elevatorID;
    private int maxNumberPeople;
  
    private ArrayList<Person> peopleInElevator;
    private HashMap<Integer, ArrayList<Person>> riderStops;
    private HashMap<String, ArrayList<Integer>> floorsToVisit;
    
    private boolean doorsOpen;
    private double currentFloor;
    
    private long travelTimeMills;
    private long doorActionTimesMills;
    private long idleTimeMills;
 
    public String direction;
    private boolean available;
    private int sectorID;
    
    private int testStop = 10;

    public Elevator(int elevatorIDIn, long travelTimeMillsIn,
                                   long doorActionTimeMillsIn, int maxNumberPeopleIn, long idleTimeMillsIn){
        elevatorID = elevatorIDIn;
        doorsOpen = false;
        available = true;
        travelTimeMills = travelTimeMillsIn;
        currentFloor = 1;
        idleTimeMills = idleTimeMillsIn;
        
        floorsToVisit = new HashMap<>();
        //floorsToVisit.put(UP_DIRECTION, new ArrayList<Integer>());
        //floorsToVisit.put(DOWN_DIRECTION, new ArrayList<Integer>());
        
        direction = IDLE_STATUS;  
        peopleInElevator = new ArrayList<Person>();
    }
    
    //REAL SIMULATION: floorsPerUpdate = timeStep/travelTime
    /*MOVE LOGGER CODE INTO FACADE? LOGGER.LOG */
    public void move(long time){
        
        if(!floorsToVisit.isEmpty()){
            System.out.println("Elevator " + this.getElevatorID() + " needs to visit "+ floorsToVisit.get(UP_DIRECTION));  
            this.direction = UP_DIRECTION;
 
          if(this.direction != IDLE_STATUS){
           
            double distance = time/travelTimeMills;
            System.out.println("Distance for this step: "+ distance);
            
            if(direction.equals(UP_DIRECTION)){
                currentFloor = currentFloor + distance;
                
                ElevatorDisplay.getInstance().updateElevator(getElevatorID(), (int) getCurrentFloor(), 0, ElevatorDisplay.Direction.UP);
                System.out.println("Current Floor is " + getCurrentFloor());
            }
            else{
                currentFloor = currentFloor - distance;
            }
            
  
            
            if(currentFloor % 1 == 0 && floorsToVisit.get(direction).contains((int) currentFloor)){
                floorsToVisit.get(direction).remove(new Integer((int) currentFloor));
                
                if(floorsToVisit.get(direction).isEmpty()){
                    floorsToVisit.remove(direction);
                    direction = IDLE_STATUS;
                }
                
                openDoors();
                
                System.out.println("Elevator "+ this.getElevatorID() + " reached floor "+ this.getCurrentFloor() +
                    " floors To Visit " + this.getFloorsToVisit(UP_DIRECTION));
            }
        }
            
      }
        
        else if (!riderStops.isEmpty()){
 
        }
        
        else{
            System.out.println("Elevator: " + this.getElevatorID());
            System.out.println("Status: " + this.getDirection());
        }
                
        //String timeStamp = SystemTimer.getTimeStamp();
        //Log logToSend = LogFactory.createNewLog(getElevatorID(), getCurrentFloor(),destFloor, timeStamp, Event.HANDLING_RIDER_REQUEST);
        //ActivityLogger.displayLog(logToSend);
        
    }
    
    public void update(long timeIn){ 
        System.out.println("Updating elevators");
        move(timeIn);
        
    }
       
    
    public void sendRequest(Request r){
        //UNUSED PART OF ELEVATOR INTERFACE 
    }
    
    public void openDoors(){
        String timeStamp = SystemTimer.getTimeStamp();
        ElevatorDisplay.getInstance().openDoors(getElevatorID());
        Log logToSend = LogFactory.createNewLog(getElevatorID(), (int) getCurrentFloor(),0, timeStamp, Event.DOORS_OPEN);
        ActivityLogger.displayLog(logToSend);
    }
    
    public void closeDoors(){
        ElevatorDisplay.getInstance().closeDoors(getElevatorID());
    }
    
    public void addFloorToVisit(int floorID, String dirIn){
        if(!floorsToVisit.containsKey(dirIn)){
            floorsToVisit.put(dirIn, new ArrayList<Integer>());
        }
        
        floorsToVisit.get(dirIn).add(floorID);
        System.out.println("Updated stops for elevator " + elevatorID);
        System.out.println("List of stops:  " + floorsToVisit.get(dirIn));
    }
    
    public double getCurrentFloor(){
        return currentFloor;
    }
    
    public int getElevatorID(){
        return elevatorID;
    }
    
    public String getDirection(){
        return direction;
    }
    
    public long getIdleTime(){
        return idleTimeMills;
    }
    public int getCurrentCapacity(){
        return peopleInElevator.size();
    }
    
    public ArrayList<Integer> getFloorsToVisit(String dirIn){
        //ArrayList<Integer>copyOfFloorsToVisit = new ArrayList<Integer>();  
        return floorsToVisit.get(dirIn);
    }   
    //public void reAssignSector(int sectorIDIn){sectorID = sectorIDIn;}

    
}
