/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.elevatorsimulator.factories;

import com.jberry.elevatorsimulator.domain.Building;
import com.jberry.elevatorsimulator.domain.Person;
import com.jberry.elevatorsimulator.domain.Floor;


import java.util.ArrayList;

/**
 *
 * @author johnberry
 */
public class BuildingFactory {
    
    private ElevatorFactory elevatorFactory;
    private PersonFactory personFactory;
    private ArrayList<Floor> buildingFloors;
    
    public static Building createBuilding(String type, ArrayList<Floor> floorsIn, ArrayList<Elevator> elevatorsIn){
        
        
        
        
    }
//        if (type.equals("Standard")){
//                return new Building(floorsIn, elevatorsIn);
//            }
//            else return null;
//        }
    
}
  
