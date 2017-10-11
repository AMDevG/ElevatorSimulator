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
public class Building {
    
    private static int numberOfFloors;
    private static int numberOfElevators;
    
    private static ArrayList<Floor> floorArray;
    private static ArrayList<Elevator> elevatorArray;

    public Building(int numberOfFloorsIn, int numberOfElevatorsIn){
        numberOfFloors = numberOfFloorsIn;
        numberOfElevators = numberOfElevatorsIn;
    }
   
    public int getFloorCount(){
        return numberOfFloors;
    }
    public int getElevatorCount(){
        return numberOfElevators;
    }
}
