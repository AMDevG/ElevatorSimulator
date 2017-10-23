/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.elevatorsimulator.domain;

import com.jberry.factories.FloorFactory;
import com.jberry.simulator.SimulatorSetting;
import java.util.ArrayList;

/**
 *
 * @author johnberry
 */
public final class Building {
    
    private static int numberOfFloors = SimulatorSetting.getSettingsFloorCount();
    private static int numberOfElevators = SimulatorSetting.getSettingsElevatorCount();
    private static long timeStep = SimulatorSetting.getTimeStepMills();
    
    private static Building instance;
    
    private static ArrayList<Floor> floors;
    private static ArrayList<Elevator> elevators;
    
    private Building(){}
    
    public static Building getInstance(){
        
        if(instance == null){
            instance = new Building();
            return instance;
        }
        return instance; 
    }
    
    public static void update(long time){
        for (Elevator e : elevators){
            e.update(time);
        }
    }
    
    public ArrayList<Elevator> getElevators(){
        return elevators;
    }
    public ArrayList<Floor> getFloors(){
        return floors;
    }
    public int getFloorCount(){
        return floors.size();
    }
    public int getElevatorCount(){
        return elevators.size();
    }
    public int getPeopleInBuildingCount(){
        int totalPeople = 0;
        for(Floor f: floors){
            int pplWaiting = f.getNumberofPeopleWaiting();
            totalPeople = totalPeople + pplWaiting;
        }
        return totalPeople; 
    }
    
    public static void setFloors(ArrayList<Floor> floorsIn, ArrayList<Elevator> elevatorsIn){
        floors = floorsIn;
        elevators = elevatorsIn;
    }
    
    public void floorButtonPress(int elevatorID, int floorID, String direction){
        elevators.get(elevatorID - 1).addFloorToVisit(floorID, direction);
    }
    
    public void selectRandomCall(){
        
        
        
    }
    
}
