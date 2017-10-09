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
    public static ArrayList<Floor> floorArray;
    
    private static Building instance;
    
    //BUILDING SHOULD OWN FLOORS?
    
    private Building(){}
    
    public static Building getInstance(){
        if (instance!=null){
            instance = new Building();
            floorArray = new ArrayList<Floor>();
        }
        return instance;
    }
    
    //MAY DELETE; NEED TO FIGURE OUT CONSTRUCTOR FOR SINGLETON
    public static void setBuildingSpecs(int numberOfFloorsIn, int numberOfElevatorsIn){
        numberOfFloors = numberOfFloorsIn;
        numberOfElevators = numberOfElevatorsIn;
    }
 
    public static int getFloorCount(){
        return numberOfFloors;
    }
    public static int getElevCount(){
        return numberOfElevators;
    }
}
