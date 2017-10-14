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
    private ArrayList<Elevator> availableElevators;
   
    public Floor(int numberOfElevatorsIn, int floorIDIn){
       floorID = floorIDIn;    
       peopleWaitingOnFloor = new ArrayList<Person>();
       availableElevators = new ArrayList<Elevator>();
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
    
    
}
