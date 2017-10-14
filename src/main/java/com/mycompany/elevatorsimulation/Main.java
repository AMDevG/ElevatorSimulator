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
    
    private static String settingsFilePath = "/Users/johnberry/NetBeansProjects/ElevatorSimulation/src/main/java/com/mycompany/elevatorsimulation/simulationSpecs.json";
    private static int NUMBER_OF_FLOORS;
    private static int NUMBER_OF_ELEVATORS;
    private static int NUMBER_OF_PEOPLE;
    private static int MAX_ELEVATOR_CAPACITY;
    
    private static long TRAVEL_TIME_MILLIS;
    private static long DOOR_TIME_MILLIS;
    
    public static ArrayList<Floor> tempFloorArray = new ArrayList<Floor>();
    public static ArrayList<Elevator> tempElevatorArray = new ArrayList<Elevator>();

    public static void main(String[] args) {
        SimulatorSetting simulationSettings = SimulationSettingsReader.parseSimulationSettings(settingsFilePath); //ACCEPT PARAMS OF FILE PATH

        NUMBER_OF_FLOORS = simulationSettings.getSettingsFloorCount();
        NUMBER_OF_ELEVATORS = simulationSettings.getSettingsElevatorCount();
        NUMBER_OF_PEOPLE = simulationSettings.getSettingsPeopleCount();
        MAX_ELEVATOR_CAPACITY = simulationSettings.getSettingsElevatorCapacity();
        
        TRAVEL_TIME_MILLIS = simulationSettings.getSettingsElevTravelTime();
        DOOR_TIME_MILLIS = simulationSettings.getSettingsDoorFunctionTime();
        
        System.out.println(" Floors: "+NUMBER_OF_FLOORS+
                           " Elevs:  "+NUMBER_OF_ELEVATORS+
                           " PPL:    "+NUMBER_OF_PEOPLE+
                           " CAPAC:  "+MAX_ELEVATOR_CAPACITY+
                           " Travel: "+TRAVEL_TIME_MILLIS+
                           " DOOR:   "+DOOR_TIME_MILLIS);
 
        //Building building = BuildingFactory.createBuilding("Standard", NUMBER_OF_FLOORS, NUMBER_OF_ELEVATORS);
 
//        for(int i=1; i<=NUMBER_OF_FLOORS; i++){
//            Floor newFloor = FloorFactory.createFloor(NUMBER_OF_ELEVATORS, i);
//            tempFloorArray.add(newFloor);
//        }
//        
//        for(int i=1; i<=NUMBER_OF_ELEVATORS; i++){
//            Elevator newElevator = ElevatorFactory.createElevator(i, TRAVEL_TIME_MILLIS, DOOR_TIME_MILLIS, MAX_ELEVATOR_CAPACITY);
//            tempElevatorArray.add(newElevator);
//        }
//        
//        ElevatorProcessor.getInstance().addElevators(tempElevatorArray);
//        
//        ElevatorProcessor.getInstance().sendDestinationFloor(9, 2);
//        ElevatorProcessor.getInstance().sendDestinationFloor(6, 2);   
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
