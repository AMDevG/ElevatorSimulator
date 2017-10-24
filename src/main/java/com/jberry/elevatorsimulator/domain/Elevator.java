/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.elevatorsimulator.domain;

import java.util.HashMap;
import java.util.ArrayList;

import com.jberry.factories.LogFactory;
import com.jberry.interfaces.Request;
import com.jberry.interfaces.ElevatorInterface;
import com.jberry.simulator.SystemTimer;
import com.jberry.interfaces.Log;
import com.jberry.simulator.logging.ActivityLogger;

import gui.ElevatorDisplay;

/**
 *
 * @author johnberry
 */
public class Elevator implements ElevatorInterface {
    
    private final int elevatorID;
    private int maxNumberPeople;

    private HashMap<Integer, ArrayList<Person>> riderStops;
    private HashMap<String, ArrayList<Integer>> floorsToVisit;
    
    private final long travelTimeMills;
    private final long doorActionTimeMills;
    private final long idleTimeMills;
 
    public String direction;
    public String pendingDirection;
    private double currentFloor;
   
    public Elevator(int elevatorIDIn, long travelTimeMillsIn,
                                   long doorActionTimeMillsIn, int maxNumberPeopleIn, long idleTimeMillsIn){
        elevatorID = elevatorIDIn;
        travelTimeMills = travelTimeMillsIn;
        currentFloor = 1;
        idleTimeMills = idleTimeMillsIn;
        doorActionTimeMills = doorActionTimeMillsIn;
        direction = IDLE_STATUS; 
        floorsToVisit = new HashMap<>();
        riderStops = new HashMap<>(); 
    }
    
    public void move(long time){  
      
        if(riderStops.isEmpty() && floorsToVisit.isEmpty()){
            direction = IDLE_STATUS;
            ElevatorDisplay.getInstance().setIdle(getElevatorID());
        }
      
        if(riderStops.isEmpty() && !floorsToVisit.isEmpty()){
        
            if(direction != IDLE_STATUS){
                double distance = time/travelTimeMills;
            
                if(direction.equals(UP_DIRECTION)){  
                    Log logToDisplay = LogFactory.createNewLog(getElevatorID(), (int) getCurrentFloor(), (int) getCurrentFloor() + 1, SystemTimer.getTimeStamp(), Event.MOVING);
                    ActivityLogger.displayLog(logToDisplay);
                
                    currentFloor = currentFloor + distance;
                    ElevatorDisplay.getInstance().updateElevator(getElevatorID(), (int) getCurrentFloor(), getNumRiders(), ElevatorDisplay.Direction.UP);
                }
                
                else if(direction.equals(DOWN_DIRECTION)) {
                    Log logToDisplay = LogFactory.createNewLog(getElevatorID(), (int) getCurrentFloor(), (int) getCurrentFloor() - 1, SystemTimer.getTimeStamp(), Event.MOVING);
                    ActivityLogger.displayLog(logToDisplay);
                
                    currentFloor = currentFloor - distance;
                    ElevatorDisplay.getInstance().updateElevator(getElevatorID(), (int) getCurrentFloor(), getNumRiders(), ElevatorDisplay.Direction.DOWN);
                }
            
            //FLOOR CALL REACHED, REMOVE FLOOR CALL FROM LIST
                if(currentFloor % 1 == 0 && floorsToVisit.get(pendingDirection).contains((int) currentFloor)){
                    floorsToVisit.get(pendingDirection).remove(new Integer((int) currentFloor));
                    direction = pendingDirection;
                
                    if(floorsToVisit.get(pendingDirection).isEmpty()){
                        floorsToVisit.remove(pendingDirection);
                    }
                    openDoors();
                }
            } 
        }
       
        else if (!riderStops.isEmpty()){
            if(!direction.equals(IDLE_STATUS)){
                double distance = time/travelTimeMills;
                String timeStamp = SystemTimer.getTimeStamp();
            
                if(direction.equals(UP_DIRECTION)){
                    Log logToDisplay = LogFactory.createNewLog(getElevatorID(), (int) getCurrentFloor(), (int) getCurrentFloor() + 1, SystemTimer.getTimeStamp(), Event.MOVING);
                    ActivityLogger.displayLog(logToDisplay);
                
                    currentFloor = currentFloor + distance;
                    ElevatorDisplay.getInstance().updateElevator(getElevatorID(), (int) getCurrentFloor(), getNumRiders(), ElevatorDisplay.Direction.UP);
                }
                else{
                    Log logToDisplay = LogFactory.createNewLog(getElevatorID(), (int) getCurrentFloor(), (int) getCurrentFloor() - 1, SystemTimer.getTimeStamp(), Event.MOVING);
                    ActivityLogger.displayLog(logToDisplay);
                
                    currentFloor = currentFloor - distance;
                    ElevatorDisplay.getInstance().updateElevator(getElevatorID(), (int) getCurrentFloor(), getNumRiders(), ElevatorDisplay.Direction.DOWN);
                }
            }
            
            if(currentFloor % 1 == 0 && riderStops.containsKey((int) currentFloor)){
                openDoors();
            }
        }
        
        else{
            direction = IDLE_STATUS;
            ElevatorDisplay.getInstance().setIdle(getElevatorID());
        }
    }

    public void processFloor(){
        Floor floor = Building.getInstance().getFloors().get((int)this.getCurrentFloor() - 1);
        
        for(Person p : floor.getPeopleWaiting()){
            if(p.getDirection().equals(direction)){
                
                if(!riderStops.containsKey(p.getDestinationFloor())){
                    riderStops.put(p.getDestinationFloor(), new ArrayList<Person>());
                    riderStops.get(p.getDestinationFloor()).add(p);
                    Log enterLog = LogFactory.createNewLog(p.getID(), (int) getCurrentFloor(), getElevatorID(), SystemTimer.getTimeStamp(), " ",Event.PERSON_ENTERING_ELEVATOR);
                    ActivityLogger.displayLog(enterLog);
                } 
                else{
                    riderStops.get(p.getDestinationFloor()).add(p);
                    Log logToDisplay = LogFactory.createNewLog(p.getID(), (int) getCurrentFloor(), getElevatorID(), SystemTimer.getTimeStamp()," ",  Event.PERSON_ENTERING_ELEVATOR);
                    ActivityLogger.displayLog(logToDisplay);
                }
                p.calculateWaitTime();
                p.startRideTime();
            }
        }
        
       if(riderStops.containsKey(floor.getFloorID())){ 
           for(Person p : riderStops.get(floor.getFloorID())){ 
               p.calculateRideTime();
               String rideTime = SystemTimer.formatTimeStamp(p.getRideTime());
               String waitTime = SystemTimer.formatTimeStamp(p.getWaitTime());
               
               System.out.println(SystemTimer.getTimeStamp() + "     Person " + p.getID() + " completed trip at Floor "+ p.getDestinationFloor() 
                                + " (Wait time: " +  waitTime + " , Ride time: " + rideTime+ ")");
                    floor.addCompletedPersons(p); 
                }   
                riderStops.remove(floor.getFloorID());
                }
       //CHECK TO SEE IF THERE ARE ANYMORE RIDERSTOPS
        if(riderStops.isEmpty() && !floorsToVisit.isEmpty()){
            if(floorsToVisit.containsKey("UP")){
                //GETS FIRST FLOOR REQUEST
                int nextFloor = floorsToVisit.get("UP").get(0);
                pendingDirection = UP_DIRECTION;
                direction = getDirection((int) getCurrentFloor(),nextFloor); 
            }
            else if(floorsToVisit.containsKey("DOWN")){
                int nextFloor = floorsToVisit.get("DOWN").get(0);
                pendingDirection = DOWN_DIRECTION;
                direction = getDirection((int) getCurrentFloor(),nextFloor);   
            }
        }
        closeDoors();
    }

    public void addFloorToVisit(int floorID, String dirIn){
        //IF CALLS FOR THAT DIRECTION DONT EXIST; CREATE ARRAY LIST
        if(!floorsToVisit.containsKey(dirIn)){
            floorsToVisit.put(dirIn, new ArrayList<Integer>());
        }
        
        floorsToVisit.get(dirIn).add(floorID);
        System.out.println(SystemTimer.getTimeStamp() + "     Floor Call made from Floor " + floorID);
        
        //IF IDLE, SET DIRECTION TO WHATEVER NEXT CALL IS
        if(direction.equals(IDLE_STATUS) && !floorsToVisit.isEmpty()){
            direction = getDirection((int) getCurrentFloor(), floorID);
            pendingDirection = dirIn;
        }
        //START WAIT TIME FOR EACH PERSON
        Floor floor = Building.getInstance().getFloors().get(floorID - 1);
        for(Person p : floor.getPeopleWaiting()){   
            p.startWaitTime();
        }
    }
    
    public void update(long timeIn){ 
        move(timeIn);   
    }
    public int getNumRiders(){
        int counter = 0;
        for(int key : riderStops.keySet()){
            counter = counter + riderStops.get(key).size();}
        return counter;
    }       
    public void sendRequest(Request r){
        //UNUSED PART OF ELEVATOR INTERFACE; IMPLEMENT NEXT BUILD 
    }
    public void openDoors(){
        String timeStamp = SystemTimer.getTimeStamp();
        ElevatorDisplay.getInstance().openDoors(getElevatorID());
        
        Log logToSend = LogFactory.createNewLog(getElevatorID(), (int) getCurrentFloor(),0, timeStamp, Event.DOORS_OPEN);
        ActivityLogger.displayLog(logToSend); 
        processFloor();
    }
    public ArrayList<Integer> getRiderStops(){
        ArrayList<Integer> riderStopsDisplay = new ArrayList<Integer>();
        for(int key : riderStops.keySet()){
            riderStopsDisplay.add(key);}
        return riderStopsDisplay;
    }
    public ArrayList<Integer> getFloorStops(){
        ArrayList<Integer> floorStopsDisplay = new ArrayList<Integer>();  
        for(String key : floorsToVisit.keySet()){
            for(int stop : floorsToVisit.get(key)){
                floorStopsDisplay.add(stop);}
        }   
        return floorStopsDisplay;
    }
    public void closeDoors(){
        String timeStamp = SystemTimer.getTimeStamp();
        ElevatorDisplay.getInstance().closeDoors(getElevatorID());
        Log logToSend = LogFactory.createNewLog(getElevatorID(), (int) getCurrentFloor(),0, timeStamp, Event.DOORS_CLOSED);
        ActivityLogger.displayLog(logToSend);
    }
    public String getDirection(int currentLocation, int floorID){
        if(floorID > currentLocation){return "UP";}
        else{return "DOWN";} 
    }
    public double getCurrentFloor(){
        return currentFloor;
    }
    public int getElevatorID(){
        return elevatorID;
    }
    public String getCurrentDirection(){
        return direction;
    }
    public long getIdleTime(){
        return idleTimeMills;
    }
    public ArrayList<Integer> getFloorsToVisit(String dirIn){
        //ArrayList<Integer>copyOfFloorsToVisit = new ArrayList<Integer>();  
        return floorsToVisit.get(dirIn);
    }   
}
