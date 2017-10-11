/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.elevatorsimulation;

import java.util.ArrayList;
import java.util.Random;
import gui.ElevatorDisplay;
import static gui.ElevatorDisplay.Direction.DOWN;
import static gui.ElevatorDisplay.Direction.IDLE;
import static gui.ElevatorDisplay.Direction.UP;

/**
 *
 * @author johnberry
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    //REPLACE WITH XML TO FEED
    public static int NUMBER_OF_FLOORS = 10;
    public static int NUMBER_OF_ELEVATORS = 6;
    public static int NUMBER_OF_PEOPLE = 20;
    public static int MAX_ELEVATOR_CAPACITY = 20;
    
    public static long TRAVEL_TIME_MILLIS = 3000;
    public static long DOOR_TIME_MILLIS = 4000;
    
    
    
    public static ArrayList<Floor> tempFloorArray = new ArrayList<Floor>();
    public static ArrayList<Elevator> tempElevatorArray = new ArrayList<Elevator>();

    
    public static void main(String[] args) {
        
        //Building building = BuildingFactory.createBuilding("Standard", NUMBER_OF_FLOORS, NUMBER_OF_ELEVATORS);
 
        for(int i=1; i<=NUMBER_OF_FLOORS; i++){
            Floor newFloor = FloorFactory.createFloor(NUMBER_OF_ELEVATORS, i);
            tempFloorArray.add(newFloor);
        }
        
        for(int i=1; i<=NUMBER_OF_ELEVATORS; i++){
            Elevator newElevator = ElevatorFactory.createElevator(i, TRAVEL_TIME_MILLIS, DOOR_TIME_MILLIS, MAX_ELEVATOR_CAPACITY);
            tempElevatorArray.add(newElevator);
        }
        
        ElevatorProcessor.getInstance().addElevators(tempElevatorArray);
        
        ElevatorProcessor.getInstance().sendDestinationFloor(9, 2);
        ElevatorProcessor.getInstance().sendDestinationFloor(6, 2);
        
    }
    
    public void startSimulation(int floorNumIn, int elevatorNumIn, int peopleNumIn){
        ElevatorDisplay.getInstance().initialize(floorNumIn);
        for (int i = 1; i <= 23; i++) {
            ElevatorDisplay.getInstance().addElevator(i, 1);
        }
        
    }
    
    public void setUpBuilding(){
        
    }
    
    
    
    
    
    
    
    public Person createNewPersonObj(int ID, int floor){
        Person newPersonObj = new Person(ID, floor);
        return newPersonObj;
    }
    
    public ElevatorProcessor createNewElevatorProcessor(){
        ElevatorProcessor newElevatorProcessor = ElevatorProcessor.getInstance();
        return newElevatorProcessor;
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
