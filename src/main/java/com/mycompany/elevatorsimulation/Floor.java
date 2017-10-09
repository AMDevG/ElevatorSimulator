/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.elevatorsimulation;
import java.util.ArrayList;

/**
 *
 * @author johnberry
 */
public class Floor {
    private int floorID;
    private int numberOfElevators;
    private int numberOfPeopleWaiting;
    //Array List Holding person Objects
    
    public ArrayList<Person> peopleOnFloor;
   
    public Floor(int numberOfElevatorsIn, int floorIDIn){
       numberOfElevators = numberOfElevatorsIn;
       floorID = floorIDIn;      
       peopleOnFloor = new ArrayList<Person>();
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
}
