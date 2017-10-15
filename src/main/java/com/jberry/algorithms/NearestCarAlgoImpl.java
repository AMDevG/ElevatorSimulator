/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.algorithms;

import com.jberry.elevatorsimulator.Main;
import com.jberry.elevatorsimulator.domain.Building;
import com.jberry.elevatorsimulator.domain.Elevator;
import com.jberry.elevatorsimulator.domain.Floor;
import com.jberry.interfaces.Request;
import com.jberry.interfaces.SchedulingAlgorithm;
import java.util.ArrayList;

/**
 *
 * @author johnberry
 */
public class NearestCarAlgoImpl implements SchedulingAlgorithm {
       
    private String callSender;
    private int ID;
    private int destinationFloorID;
    private String travelDirection;
    
    /** TEST CODE FOR BUILDING CHANGE!!!!!*/
    private Building building;
    
    private ArrayList<Floor> floors;
    private ArrayList<Elevator> elevatorBank;
    
    public NearestCarAlgoImpl(){ 
        building = Main.getBuilding();
        elevatorBank = building.getElevators();  
    }
    
    public void handleCall(Request r){
        
        System.out.println("Handling Call in Delegate");
        System.out.println("Received Request from: "+r.getCallSender() +" "+r.getID()+
                            " to go to Destination: "+r.getDestinationID()+
                            " Direction of Call:    " +r.getDirection()+"\n"+
                            " -----------------------------");
        System.out.println("Looking for available elevator");
 
        callSender = r.getCallSender();
        ID = r.getID();
        destinationFloorID = r.getDestinationID();
        travelDirection = r.getDirection();
        
        //**** CHECK TO SEE IF ELEVATOR ALREADY ON FLOOR
        
        if(callSender.equals("FLOOR")){
            int nearestCar = findNearestCar(ID);
            dispatchElevator(nearestCar,ID);
        }
        if(callSender.equals("CAR")){
            System.out.println("Call is from Car");
        }
    }
    
    public void dispatchElevator(int elevatorToDispatch, int floorID){
        Elevator elevatorToSend = null;
        for(Elevator e : elevatorBank){
            int currentID = e.getElevatorID();
            if(currentID == elevatorToDispatch){
                System.out.println("Dispatching Elevator "+currentID);
                elevatorToSend = e;
                break;
            }
            else{
                System.out.println("Could Not Find Elevator");
            }
        }
        elevatorToSend.move(floorID);  
    }
    
    public int findNearestCar(int callFloorID){
        int elevatorWithMinDistance = 0;
        int currentMinDistance = 0;
        int currentFloor;

        for(Elevator elevator : elevatorBank){
            
            int distance = Math.abs(elevator.getCurrentFloor() - callFloorID);

            if (elevatorWithMinDistance == 0){
                elevatorWithMinDistance = elevator.getElevatorID();
                currentMinDistance = distance;
                }
            else{ 
                if(distance < currentMinDistance){
                    currentMinDistance = distance;
                    elevatorWithMinDistance = elevator.getElevatorID();
                    }   
                }
            }
        return elevatorWithMinDistance;             
    }
}