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
        
    if(riderStops.isEmpty() && floorsToVisit.isEmpty()){
            direction = IDLE_STATUS;
            ElevatorDisplay.getInstance().setIdle(getElevatorID());
        }
      
    if(riderStops.isEmpty() && !floorsToVisit.isEmpty()){
        
        if(direction != IDLE_STATUS){
            double distance = time/travelTimeMills;
            if(direction.equals(UP_DIRECTION)){  
                currentFloor = currentFloor + distance;
                ElevatorDisplay.getInstance().updateElevator(getElevatorID(), (int) getCurrentFloor(), getNumRiders(), ElevatorDisplay.Direction.UP);
                
                Log logToDisplay = LogFactory.createNewLog(getElevatorID(), (int) getCurrentFloor(), (int) getCurrentFloor() + 1, SystemTimer.getTimeStamp(), Event.MOVING);
                ActivityLogger.displayLog(logToDisplay);
             //   System.out.println(SystemTimer.getTimeStamp() + " Elevator " + getElevatorID() + "is at floor " + getCurrentFloor());
            }
            else{
                currentFloor = currentFloor - distance;
                ElevatorDisplay.getInstance().updateElevator(getElevatorID(), (int) getCurrentFloor(), getNumRiders(), ElevatorDisplay.Direction.DOWN);
                Log logToDisplay = LogFactory.createNewLog(getElevatorID(), (int) getCurrentFloor(), (int) getCurrentFloor() - 1, SystemTimer.getTimeStamp(), Event.MOVING);
                ActivityLogger.displayLog(logToDisplay);
              //  System.out.println(SystemTimer.getTimeStamp() + " Elevator " + getElevatorID() + "is at floor " + getCurrentFloor());
            }
            
            //FLOOR CALL REACHED, REMOVE FLOOR CALL FROM LIST
            //OPEN DOORS & PROCESS FLOOR
            
            if(currentFloor % 1 == 0 && floorsToVisit.get(pendingDirection).contains((int) currentFloor)){
                
               // System.out.println(SystemTimer.getTimeStamp() + " Elevator " + getElevatorID() + "reached floor stop " + getCurrentFloor() + " removing from floorstop");
                floorsToVisit.get(pendingDirection).remove(new Integer((int) currentFloor));
                direction = pendingDirection;
                
                if(floorsToVisit.get(pendingDirection).isEmpty()){
                    //System.out.println("Last of " + pendingDirection + " calls removing");
                    floorsToVisit.remove(pendingDirection);
                }
                
                openDoors();
            }
        } 
    }
       
    else if (!riderStops.isEmpty()){
          //System.out.println("Elevator "+getElevatorID()+" has riderStops at "+riderStops.keySet());
          
        if(direction != IDLE_STATUS){
            double distance = time/travelTimeMills;
            String timeStamp = SystemTimer.getTimeStamp();
            
            if(direction.equals(UP_DIRECTION)){
                currentFloor = currentFloor + distance;
              //  System.out.println(timeStamp + " Elevator " + getElevatorID() + "is at floor " + getCurrentFloor());
                ElevatorDisplay.getInstance().updateElevator(getElevatorID(), (int) this.getCurrentFloor(), getNumRiders(), ElevatorDisplay.Direction.UP);
            }
            else{
                currentFloor = currentFloor - distance;
                ElevatorDisplay.getInstance().updateElevator(getElevatorID(), (int) this.getCurrentFloor(), getNumRiders(), ElevatorDisplay.Direction.DOWN);
               // System.out.println(timeStamp + " Elevator "+ getElevatorID() + " is at floor " + getCurrentFloor());
            }
            if(currentFloor % 1 == 0 && riderStops.containsKey((int) currentFloor)){
                openDoors();
            }
        }
    }
      
    else{
        direction = IDLE_STATUS;
        ElevatorDisplay.getInstance().setIdle(getElevatorID());
        //  System.out.println("Elevator: " + this.getElevatorID());
        //  System.out.println("Status: " + this.getCurrentDirection());
    }
   }
    
    public void update(long timeIn){ 
        move(timeIn);   
    }
   
    public void processFloor(){
        Floor floor = Building.getInstance().getFloors().get((int)this.getCurrentFloor() - 1);
        //ADDS PEOPLE TO ELEVATOR
//       / System.out.println("Num people waiting " + floor.getNumberofPeopleWaiting());
       // System.out.println("Elevator Direction: "+direction);
        for(Person p : floor.getPeopleWaiting()){
            if(p.getDirection().equals(direction)){
               // System.out.println(SystemTimer.getTimeStamp() + " Adding Person " + p.getID() + " dest floor " + p.getDestinationFloor());
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
            }
        }
 
       if(riderStops.containsKey(floor.getFloorID())){ 
           for(Person p : riderStops.get(floor.getFloorID())){ 
               // System.out.println(SystemTimer.getTimeStamp() + " Removing person: " + p.getID() + " arrived at :"+ p.getDestinationFloor());
                    floor.addCompletedPersons(p);
                    p.calculateWaitTime();
                }   
                riderStops.remove(floor.getFloorID());
                }
       //CHECK TO SEE IF THERE ARE ANYMORE RIDERSTOPS
            if(riderStops.isEmpty() && !floorsToVisit.isEmpty()){
               // System.out.println("Rider Stops empty for elv " + getElevatorID());
               // System.out.println("Handling next floor request");
                
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
            else{
             //   System.out.println("Elevator " + getElevatorID()+" now has riderstops at " + riderStops.keySet());
              }
            
            closeDoors();
    }
    
    
    public void addFloorToVisit(int floorID, String dirIn){
        //IF CALLS FOR THAT DIRECTION DONT EXIST; CREATE ARRAY LIST
        if(!floorsToVisit.containsKey(dirIn)){
            floorsToVisit.put(dirIn, new ArrayList<Integer>());
        }
        
        floorsToVisit.get(dirIn).add(floorID);
       // System.out.println("Adding Floor Stop for Dir " + dirIn + "for Elevator "+ getElevatorID());
       // System.out.println(dirIn + " floor stops are now "+ floorsToVisit.get(dirIn));
        
        //IF IDLE, SET DIRECTION TO WHATEVER CALL IS
        if(direction.equals(IDLE_STATUS) && !floorsToVisit.isEmpty()){
            direction = getDirection((int) getCurrentFloor(), floorID);
            pendingDirection = dirIn;
        }
        else{
            //CHECK TO SEE IF NEW CALL WILL INTERUPT OTHER CALLS PENDING
            if (dirIn!=pendingDirection){
                //System.out.println("Elevator " + getElevatorID() + " got call for "+ dirIn + " has to wait to complete current call of dir " + pendingDirection);
            }
            else{
                pendingDirection = dirIn;
            }
        }
        
        //System.out.println("Elevator " + getElevatorID() + " status "+ getCurrentDirection());
       
        //START WAIT TIME FOR EACH PERSON
        Floor floor = Building.getInstance().getFloors().get(floorID - 1);
        for(Person p : floor.getPeopleWaiting()){   
            if(p.getDirection().equals(pendingDirection)){
               p.startWaitTime();
            }
        }
       // System.out.println("Updated stops for elevator " + elevatorID);
       // System.out.println("List of floor stops:  " + floorsToVisit.get(dirIn));
    }
    
    
    
    public int getNumRiders(){
        int counter = 0;
        for(int key : riderStops.keySet()){
            counter = counter + riderStops.get(key).size();
        }
        return counter;
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
    
    public ArrayList<Integer> getRiderStops(){
        ArrayList<Integer> riderStopsDisplay = new ArrayList<Integer>();
        for(int key : riderStops.keySet()){
            riderStopsDisplay.add(key);
        }
        return riderStopsDisplay;
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
    //public void reAssignSector(int sectorIDIn){sectorID = sectorIDIn;}
}
