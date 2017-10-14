/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.elevatorsimulator;
import java.util.ArrayList;

/**
 *
 * @author johnberry
 */
public class Floor {
    private int floorID;
    private int numberOfElevators;
    private int numberOfPeopleWaiting;

    private ElevatorProcessor processor = ElevatorProcessor.getInstance();
    
    private ArrayList<Person> peopleOnFloor;
    private ArrayList<Elevator> availableElevators;
   
    public Floor(int numberOfElevatorsIn, int floorIDIn){
       numberOfElevators = numberOfElevatorsIn;
       floorID = floorIDIn;    
       
       peopleOnFloor = new ArrayList<Person>();
       availableElevators = new ArrayList<Elevator>();
    }
    
    public void addPerson(Person personIn){
        peopleOnFloor.add(personIn);
        numberOfPeopleWaiting += 1;
    }
   
    public int getNumberofPeopleWaiting(){
        return numberOfPeopleWaiting;
    }
    
    public int getFloorID(){
        return floorID;
    }
    
    public void sendRequest(String directionIn, int floorIDIn){
        processor.processFloorRequest(directionIn, floorID);
    }
    
    
}
