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
public class Floor{
    
    private final int floorID;
    private ArrayList<Person> peopleWaitingOnFloor;
    private ArrayList<Person> completedPassengers;
    private ArrayList<Elevator> availableElevators;
   
    public Floor(int floorIDIn, int elevatorCount, ArrayList<Person>peopleIn){
       floorID = floorIDIn;    
       peopleWaitingOnFloor = peopleIn;
       completedPassengers = new ArrayList<Person>();
    }
    
    public void sendRequest(){
     /*   Person testPerson = peopleWaitingOnFloor.get(0); 
        testPerson.sendRequest(); */
    }
    public void addPerson(Person personIn){
        peopleWaitingOnFloor.add(personIn);
    }
    public int getNumberofPeopleWaiting(){
        return peopleWaitingOnFloor.size();
    }
    public ArrayList<Person> getPeopleWaiting(){
        return peopleWaitingOnFloor;
    }
    public void addCompletedPersons(Person p){
        completedPassengers.add(p);
    }
    public int getFloorID(){
        return floorID;
    }
}
