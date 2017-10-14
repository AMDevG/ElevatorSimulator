/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.elevatorsimulator.factories;

import com.jberry.elevatorsimulator.domain.Floor;
import java.util.ArrayList;

/**
 *
 * @author johnberry
 */
public class FloorFactory {
    
    private static ArrayList<Floor> buildingFloors;
    
    public FloorFactory(){
        buildingFloors = new ArrayList<Floor>();
    }
    
    
    public static ArrayList<Floor> createFloors(int numberOfElevatorsIn, int numberOfFloorsIn){
        for(int i=1; i<=numberOfFloorsIn; i++){
            new Floor(numberOfElevatorsIn, i);
        }
        
        return buildingFloors;
    }
    
}
