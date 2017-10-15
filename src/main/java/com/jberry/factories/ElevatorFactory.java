/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.factories;

import com.jberry.elevatorsimulator.domain.Elevator;
import java.util.ArrayList;

/**
 *
 * @author johnberry
 */
public class ElevatorFactory {
    
    private static ArrayList<Elevator> elevatorsCreated;
    
    public static ArrayList<Elevator> createElevators(int numberOfElevatorsToCreate, long travelTimeMillsIn,
                                   long doorActionTimeMillsIn, int maxNumberPeopleIn, long idleTimeMillsIn){
      elevatorsCreated = new ArrayList<Elevator>();
      
      for(int i=1; i<=numberOfElevatorsToCreate; i++){
            Elevator newElevator = new Elevator(i, travelTimeMillsIn, 
                        doorActionTimeMillsIn, maxNumberPeopleIn, idleTimeMillsIn);
            elevatorsCreated.add(newElevator);
        } 
      return elevatorsCreated;
    }  
}
