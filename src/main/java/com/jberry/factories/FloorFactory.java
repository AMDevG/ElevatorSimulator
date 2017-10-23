/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.factories;

import com.jberry.elevatorsimulator.domain.Floor;
import com.jberry.elevatorsimulator.domain.Person;
import java.util.ArrayList;

/**
 *
 * @author johnberry
 */
public class FloorFactory {
    
    private static ArrayList<Floor> buildingFloors;
    private static ArrayList<Person> peopleToAddToFloor;
    
    public static ArrayList<Floor> createFloors(int numberOfFloorsIn, int numberOfElevatorsIn, ArrayList<Person> peopleInBuilding){
        buildingFloors = new ArrayList<Floor>();
        
        for(int i=1; i<=numberOfFloorsIn; i++){
            
            peopleToAddToFloor = new ArrayList<Person>();
            
          if(peopleInBuilding.isEmpty()){
            for (Person person : peopleInBuilding){
                if(person.getCurrentFloor()==i){  
                    peopleToAddToFloor.add(person);
                }   
            }
          }
            Floor newFloor = new Floor(i, numberOfElevatorsIn, peopleToAddToFloor); //IF FLOOR ID MATCHES PERSON ID, ADD TO ARRAY)
            buildingFloors.add(newFloor);   
        }
        
        return buildingFloors;
    }
    
}
