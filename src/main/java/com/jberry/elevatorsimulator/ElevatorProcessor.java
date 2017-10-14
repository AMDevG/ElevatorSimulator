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
public final class ElevatorProcessor{
    
    private Processor processorDelegate = new StandardElevatorProcessorImpl();
    private static ElevatorProcessor instance;
    
    private static ArrayList<Elevator> elevatorBank;
    // CONVERT TO HASHMAP
    //MOVE TO IMPL???
    
    private ElevatorProcessor(){}
    
    public static ElevatorProcessor getInstance(){
        if(instance == null){
            instance = new ElevatorProcessor();
            elevatorBank = new ArrayList<Elevator>();
            return instance;
        }
        return instance;   
    }
    
    public void processElevatorRequest(String directionIn, int elevatorIDIn){
        processorDelegate.processElevatorRequest(directionIn, elevatorIDIn);
    }
 
    public void processFloorRequest(String directionIn, int floorIDIn){
        processorDelegate.processFloorRequest(directionIn, floorIDIn);
    }
    
    public void addElevators(ArrayList<Elevator> elevators){
        for(Elevator elevator : elevators){
            System.out.println("Adding elevator  " + elevator.getElevatorID());
            elevatorBank.add(elevator);
        }
    }
    
    public void sendDestinationFloor(int destFloorIn, int elevatorIDIn){
        //NEED TO UPDATE WITH DELEGATE CODE
        Elevator selectedElev = elevatorBank.get(elevatorIDIn);
        selectedElev.addFloorToVisit(destFloorIn);
    }
}
