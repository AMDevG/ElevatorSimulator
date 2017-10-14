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
public class StandardElevatorProcessorImpl implements Processor {
    
    private ArrayList<Elevator> elevatorBank;
    
    public StandardElevatorProcessorImpl(){}
    
    public void processElevatorRequest(String direction, int elevatorID){
        System.out.println("Processing elevator request from standard processor");
    }
   
    public void processFloorRequest(String direction, int floorID){
        System.out.println("Processing request from floor" + floorID +" Requested: " + direction);
    }
    
    public void sendDestinationFloor(int destFloor, int elevatorID){
        System.out.println("Adding destination  " + destFloor + " to elevator " + elevatorID);
    }
}
