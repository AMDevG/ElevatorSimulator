/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.elevatorsimulator.factories;
import com.jberry.elevatorsimulator.domain.Person;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author johnberry
 */
public class PersonFactory {
    
    private static ArrayList<Person> peopleInBuilding;
    private final static int LOBBY_FLOOR = 1;
    private static int TOP_FLOOR;
    
    //CREATES ALL PEOPLE IN BUILDING; REQUIRES NUMBER OF PPL
    //REQUIRES MAX NUMBER OF FLOORS TO DISTRIBUTE
    public static ArrayList<Person> createPeople(int numberPeopleToCreate, int maxFloors){
        int randomStartingFloor;
        int randomDestinationFloor;
        
        TOP_FLOOR = maxFloors; 
        peopleInBuilding = new ArrayList<Person>();
        Random rn = new Random();

        //ASSIGNS ID (1 -> TOTAL NUMBER OF PEOPLE)
        //ASSIGNS RANDOM STARTING & DESTINATION FLOORS
        
        for(int i=1; i<= 10; i++){
            randomStartingFloor = rn.nextInt((TOP_FLOOR - LOBBY_FLOOR) + 1) + LOBBY_FLOOR;
            
            //IF PERSONS CURRENT FLOOR IS TOP FLOOR; CALCULATE RANDOM INT
            //SHRINK MAX RANGE TO BE TOTAL FLOORS - 1
            
            if (randomStartingFloor == TOP_FLOOR){
                int maxFloor = TOP_FLOOR - 1;
                randomDestinationFloor = rn.nextInt(((maxFloor - LOBBY_FLOOR) - LOBBY_FLOOR) + 1) + 1;
            }
            else if(randomStartingFloor == LOBBY_FLOOR)
            {
                int minFloor = LOBBY_FLOOR + 1;
                randomDestinationFloor = rn.nextInt((TOP_FLOOR - minFloor) + 1) + minFloor;      
            }
            else{
               randomDestinationFloor = rn.nextInt((TOP_FLOOR - LOBBY_FLOOR) + 1) + LOBBY_FLOOR;
                if (randomDestinationFloor == randomStartingFloor)
                    randomDestinationFloor++;       
            }
            
            
            System.out.println(" ------------");
            System.out.println("Person ID: " + i);
            System.out.println("Starting Floor: " + randomStartingFloor);
            System.out.println("Destination Floor: " + randomDestinationFloor);
            
           // Person newPerson = new Person(i,randomFloor);
            //peopleInBuilding.add(newPerson);
            
        }
       return peopleInBuilding; 
    }
}
