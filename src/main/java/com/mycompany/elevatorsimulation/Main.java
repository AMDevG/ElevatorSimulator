/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.elevatorsimulation;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author johnberry
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static int NUMBER_OF_FLOORS = 10;
    public static int NUMBER_OF_ELEVATORS = 4;
    public static int NUMBER_OF_PEOPLE = 20;
    
    public static long TRAVEL_TIME_PER_FLOOR_MILLIS = 3000;
    public static ArrayList<Floor> tempFloorArray;

    
    public static void main(String[] args) {
        
        tempFloorArray = new ArrayList<Floor>();
        
        //CREATE BUILDING AND SET NUMBER OF ELEVATORS AND FLOORS
        //CREATE PEOPLE
        ArrayList<Person> testPersonArray = testPeopleCreator();
        Building building = Building.getInstance();
        
        //CREATE FLOORS AND POPULATE THEIR PEOPLEWAITINGARRAYS WITH PPL
        
        for(int i=1; i <= NUMBER_OF_FLOORS; i++){
            Floor currentFloor = new Floor(NUMBER_OF_ELEVATORS, i);
            //ADD PEOPLE TO FLOORS
            for(Person p : testPersonArray){
                int currentID = p.getCurrentFloor();
                System.out.println("Current FloorID: " + currentID + " for Person: " + p.getID());
                if (i == currentID){
                    currentFloor.addPerson(p);
                }
            }
            tempFloorArray.add(currentFloor);
        }
        building.floorArray = tempFloorArray; //Setting Building owns array of floors
        building.setBuildingSpecs(NUMBER_OF_FLOORS, NUMBER_OF_ELEVATORS); //LIKELY REDUNDANT

        for(Floor f : building.floorArray){
          System.out.println("Floor: " + f.getFloorID() + " has # people:  " + f.getNumberofPeopleWaiting());
          System.out.println(" ");
            
            for(Person p : f.peopleOnFloor){
                System.out.println("Person: " + p.getID() + " is waiting on floor: " + p.getCurrentFloor());
                System.out.println(" ");
            }
        }
    }
    
    public Person createNewPersonObj(int ID, int floor){
        Person newPersonObj = new Person(ID, floor);
        return newPersonObj;
    }
    
    public ElevatorController createNewElevatorController(){
        ElevatorController newElevatorController = ElevatorController.getInstance();
        return newElevatorController;
    }

    public static ArrayList testPeopleCreator(){
        //CREATES NEW PERSON OBJECT AND ADDS THEM TO FLOOR
        ArrayList<Person> personArray = new ArrayList<Person>();
        
        for(int i=1; i <= NUMBER_OF_PEOPLE; i++){
            //RANDOM GENERATOR FOR FLOOR
            Random rn = new Random();
            int floorRandom = rn.nextInt(NUMBER_OF_FLOORS - 1 + 1) + 1;
            Person newPers = new Person(i, floorRandom);
            personArray.add(newPers);
        }
        return personArray;
    }
}
