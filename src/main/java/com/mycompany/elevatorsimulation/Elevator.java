/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.elevatorsimulation;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author johnberry
 */
public class Elevator {
    private ArrayList<Person> peopleInElevator;
    private ArrayList<Integer> floorsToVisit;
    private int elevatorID;
    private boolean doorsOpen;
    private long travelTime;
    
    private String direction;
    private boolean idle;
    
    
    public Elevator(int idIn, long travelTimeIn){
        doorsOpen = false;
        travelTime = travelTimeIn;
    }
    
    private void updateFloorsToVisit(){
        
    }
    
    private void move(){
        //GET MESSAGE FROM ELEVATORCONTROLLER
        
    }
    
    private void openDoors() throws InterruptedException{
        doorsOpen = true;
        TimeUnit.SECONDS.sleep(2);
        doorsOpen = false;
    }
}
