/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.factories;
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

    public static ArrayList<Person> createPeople(int numberPeopleToCreate, int maxFloors){
        peopleInBuilding = new ArrayList<Person>();
        int randomStartingFloor;
        int randomDestinationFloor;
        
        TOP_FLOOR = maxFloors; 
        Random rn = new Random();

        //ASSIGNS ID (1 -> TOTAL NUMBER OF PEOPLE)
        //ASSIGNS RANDOM STARTING & DESTINATION FLOORS
        
        for(int i=1; i<= numberPeopleToCreate; i++){
            randomStartingFloor = rn.nextInt((TOP_FLOOR - LOBBY_FLOOR) + 1) + LOBBY_FLOOR;
            int newID = i;
            
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
            Person newPerson = new Person(newID,randomStartingFloor, randomDestinationFloor);
            peopleInBuilding.add(newPerson);
        }
       return peopleInBuilding; 
    }
}
