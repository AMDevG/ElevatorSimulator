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
    private long idleStart = (long) 0.0;
    private long timeIdle;
 
    public String direction;
    public String pendingDirection;
    
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
        riderStops = new HashMap<>();

        direction = IDLE_STATUS;  
        peopleInElevator = new ArrayList<Person>();
    }
    
    //REAL SIMULATION: floorsPerUpdate = timeStep/travelTime
    /*MOVE LOGGER CODE INTO FACADE? LOGGER.LOG */
    public void move(long time){
       
       if(!floorsToVisit.isEmpty()){
            System.out.println("Elevator " + this.getElevatorID() + " needs to visit "+ floorsToVisit.get(pendingDirection));  

          if(direction != IDLE_STATUS){
            double distance = time/travelTimeMills;
            
            if(direction.equals(UP_DIRECTION)){
                
                currentFloor = currentFloor + distance;
                ElevatorDisplay.getInstance().updateElevator(getElevatorID(), (int) getCurrentFloor(), 0, ElevatorDisplay.Direction.UP);
            }
            else{
                currentFloor = currentFloor - distance;
                ElevatorDisplay.getInstance().updateElevator(getElevatorID(), (int) getCurrentFloor(), 0, ElevatorDisplay.Direction.DOWN); 
            }
            
            //FLOOR CALL REACHED, REMOVE FLOOR CALL FROM LIST
            //OPEN DOORS & PROCESS FLOOR
            if(currentFloor % 1 == 0 && floorsToVisit.get(pendingDirection).contains((int) currentFloor)){
                floorsToVisit.get(pendingDirection).remove(new Integer((int) currentFloor));
                
                direction = pendingDirection;
                
                if(floorsToVisit.get(direction).isEmpty()){
                    floorsToVisit.remove(direction);
                }
                openDoors();
             }
          } 
      }
        
       
      else if (!riderStops.isEmpty()){
          
          System.out.println("Elevator "+getElevatorID()+" has riderStops at "+riderStops.keySet());
          
          if(direction != IDLE_STATUS){
            double distance = time/travelTimeMills;
           
          
            if(direction.equals(UP_DIRECTION)){
                currentFloor = currentFloor + distance;
                System.out.println("Elevator is at floor " + getCurrentFloor());
                ElevatorDisplay.getInstance().updateElevator(getElevatorID(), (int) getCurrentFloor(), getNumRiders(), ElevatorDisplay.Direction.UP);
            }
            else{
                currentFloor = currentFloor - distance;
                ElevatorDisplay.getInstance().updateElevator(getElevatorID(), (int) getCurrentFloor(), getNumRiders(), ElevatorDisplay.Direction.DOWN);
                System.out.println("Elevator is at floor " + getCurrentFloor());
            }
            
            if(currentFloor % 1 == 0 && riderStops.containsKey((int) currentFloor)){
                openDoors();
            }
   
        }

       }
       else{
            direction = IDLE_STATUS;
            System.out.println("Elevator: " + this.getElevatorID());
            System.out.println("Status: " + this.getDirection());
        }
    }
    
    public void update(long timeIn){ 
        move(timeIn);   
    }
    
    public int getNumRiders(){
        return 1;
    }
    public void sendRequest(Request r){
        //UNUSED PART OF ELEVATOR INTERFACE 
    }
    
    public void openDoors(){
        String timeStamp = SystemTimer.getTimeStamp();
        ElevatorDisplay.getInstance().openDoors(getElevatorID());
        Log logToSend = LogFactory.createNewLog(getElevatorID(), (int) getCurrentFloor(),0, timeStamp, Event.DOORS_OPEN);
        ActivityLogger.displayLog(logToSend);
        
        processFloor();
    }
    
    public void closeDoors(){
        ElevatorDisplay.getInstance().closeDoors(getElevatorID());
        System.out.println("Elevator " + getElevatorID() + " cloosing doors");
    }
    
    public void addFloorToVisit(int floorID, String dirIn){
        if(!floorsToVisit.containsKey(dirIn)){
            floorsToVisit.put(dirIn, new ArrayList<Integer>());
        }
        floorsToVisit.get(dirIn).add(floorID);
        
        if(direction.equals(IDLE_STATUS)){
            direction = getDirection((int) getCurrentFloor(), floorID);
            pendingDirection = dirIn;
        }
        
        System.out.println("Elevator " + getElevatorID() + " status "+getDirection());
        //START WAIT TIME FOR EACH PERSON
        Floor floor = Building.getInstance().getFloors().get(floorID - 1);
        for(Person p : floor.getPeopleWaiting()){   
            if(p.getDirection().equals(pendingDirection)){
               p.startWaitTime();
            }
        }

        System.out.println("Updated stops for elevator " + elevatorID);
        System.out.println("List of floor stops:  " + floorsToVisit.get(dirIn));
    }
    
    public String getDirection(int currentLocation, int floorID){
        
        if(floorID > currentLocation){
            return "UP";
        }
        else{
            return "DOWN";
        } 
    }
    
    public void processFloor(){
        
        Floor floor = Building.getInstance().getFloors().get((int)this.getCurrentFloor() - 1);
        
        //ADDS PEOPLE TO ELEVATOR
        System.out.println("Num people waiting " + floor.getNumberofPeopleWaiting());
        System.out.println("Elevator Direction: "+direction);
        
        for(Person p : floor.getPeopleWaiting()){
            if(p.getDirection().equals(direction)){
                System.out.println("Adding Person " + p.getID() + " dest floor " + p.getDestinationFloor());
                if(!riderStops.containsKey(p.getDestinationFloor())){
                    riderStops.put(p.getDestinationFloor(), new ArrayList<Person>());
                    riderStops.get(p.getDestinationFloor()).add(p);
                }
                else{
                    riderStops.get(p.getDestinationFloor()).add(p);
                } 
            }
        }
        
       if(riderStops.containsKey(floor.getFloorID())){
            
           for(Person p : riderStops.get(floor.getFloorID())){ 
                System.out.println("Removing person: " + p.getID() + " arrived at :"+ p.getDestinationFloor());
                    floor.addCompletedPersons(p);
                    p.calculateWaitTime();
                }   
                riderStops.remove(floor.getFloorID());
           }
       
       System.out.println("Elevator " + getElevatorID()+" now has riderstops at " + riderStops.keySet());
 
       closeDoors();
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
