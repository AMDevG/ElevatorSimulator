/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.factories;

import java.util.ArrayList;

import com.jberry.elevatorsimulator.domain.Building;
import com.jberry.elevatorsimulator.domain.Floor;
import com.jberry.elevatorsimulator.domain.Elevator;

/**
 *
 * @author johnberry
 */
public class BuildingFactory {
    
    private ArrayList<Floor> buildingFloors;
   
    public static Building createBuilding(String type, ArrayList<Floor> floorsIn, ArrayList<Elevator> elevatorsIn){
        Building building = Building.getInstance();
        building.setFloors(floorsIn, elevatorsIn);
        return building; 
    }
    
}
