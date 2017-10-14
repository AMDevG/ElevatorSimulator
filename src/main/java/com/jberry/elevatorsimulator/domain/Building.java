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
public class Building {
    
    private static int numberOfFloors;
    private static int numberOfElevators;
    
    private static ArrayList<Floor> floors;
    private static ArrayList<Elevator> elevators;

    public Building(ArrayList<Floor>floorsIn, ArrayList<Elevator>elevatorsIn){
        floors = floorsIn;
        elevators = elevatorsIn;
    }
   
    public int getFloorCount(){
        return floors.size();
    }
    public int getElevatorCount(){
        return elevators.size();
    }
}
