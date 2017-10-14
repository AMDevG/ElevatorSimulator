/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.elevatorsimulator.domain;
import java.util.ArrayList;

/**
 *
 * @author johnberry
 */
public class Floor {
    private final int floorID;
    private ElevatorProcessor processor = ElevatorProcessor.getInstance();
    
    private ArrayList<Person> peopleWaitingOnFloor;
    private ArrayList<Person> peopleCompletedTrips;
    
    private ArrayList<Elevator> availableElevators;
   
    //REPLACE elevatorCount with ArrayList<Elevator>elevatorsIn 
    public Floor(int floorIDIn, int elevatorCount, ArrayList<Person>peopleIn){
       floorID = floorIDIn;    
       peopleWaitingOnFloor = peopleIn;
       peopleCompletedTrips = new ArrayList<Person>();
       //availableElevators = elevatorsIn;
    }
    
    public void addPerson(Person personIn){
        peopleWaitingOnFloor.add(personIn);
    }
   
    public int getNumberofPeopleWaiting(){
        return peopleWaitingOnFloor.size();
    }
    
    public int getFloorID(){
        return floorID;
    }
    
    public void sendRequest(String directionIn, int floorIDIn){
        processor.processFloorRequest(directionIn, floorID);
    }
    
    public void getDestinationFloorsOfPeopleWaiting(){
        //RETURN ARRAY OF INT DESTINATIONS ??
        for(Person p : peopleWaitingOnFloor){
            System.out.println("Dest Floor" + p.getDestinationFloor()+"\n");
        }
        
    }
    
    
}