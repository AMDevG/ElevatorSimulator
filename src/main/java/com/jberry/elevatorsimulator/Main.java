/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.elevatorsimulator;

import com.jberry.simulator.SimulationSettingsReader;
import com.jberry.simulator.SimulatorSetting;
import com.jberry.elevatorsimulator.domain.Building;
import com.jberry.elevatorsimulator.domain.Person;
import com.jberry.elevatorsimulator.domain.Elevator;
import com.jberry.elevatorsimulator.domain.Floor;
import com.jberry.elevatorsimulator.domain.ElevatorProcessor;
import com.jberry.factories.BuildingFactory;
import com.jberry.factories.ElevatorFactory;
import com.jberry.factories.FloorFactory;
import com.jberry.factories.PersonFactory;
import com.jberry.simulator.logging.ActivityLogger;
import com.jberry.simulator.Simulator;
import com.jberry.simulator.SystemTimer;

import java.util.ArrayList;

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
    
    private static String settingsFilePath = "/Users/johnberry/NetBeansProjects/ElevatorSimulatorSE450/src/main/java/simulationsettings/simulationSpecs.json";
    private static final int LOBBY_FLOOR = 1;
    
    private static Building building;

    private static Simulator simulator;
    
    private static int NUMBER_OF_FLOORS;
    private static int NUMBER_OF_ELEVATORS;
    private static int NUMBER_OF_PEOPLE;
    private static int MAX_ELEVATOR_CAPACITY;
    
    private static long TRAVEL_TIME_MILLIS;
    private static long DOOR_TIME_MILLIS;
    private static long IDLE_TIME_MILLIS;
    private static long TIME_STEP_MILLIS;
    
    
    public static void main(String[] args) throws InterruptedException {
        createSettings();
        setUpBuilding();
        System.out.println("Settings processed."+"\n"+"Building and components constructed");
        
        createSimulatorComponents();
        System.out.println(" Simulator and its components built"+"\n"+
                           " Starting simulation now.");

        simulator.startSimulation();
        
        System.out.println("Back in main. About to call TestLoggin.");
        
        testLoggin();
 
        
        //Thread.sleep(10000); //run simulator for ten seconds
        //simulator.stopSimulation();
             
//        testDispatcher(10);
//        testDispatcher(3);
//        testDispatcher(16);
//        testDispatcher(12);
//        testDispatcher(5);
//        testDispatcher(9);
//        
//        getElevatorLocations();

          
        
    } 
    
    public static void setUpBuilding(){
       
       ArrayList<Person> peopleEnteringBuilding = PersonFactory.createPeople(NUMBER_OF_PEOPLE, NUMBER_OF_FLOORS);
       ArrayList<Floor> buildingFloors = FloorFactory.createFloors(NUMBER_OF_FLOORS, NUMBER_OF_ELEVATORS, peopleEnteringBuilding);
       ArrayList<Elevator> buildingElevators = ElevatorFactory.createElevators(NUMBER_OF_ELEVATORS, TRAVEL_TIME_MILLIS, DOOR_TIME_MILLIS, MAX_ELEVATOR_CAPACITY, IDLE_TIME_MILLIS);
       building = BuildingFactory.createBuilding("Standard",buildingFloors, buildingElevators); 

    }
    
    
    private static void createSettings(){
        SimulatorSetting simulationSettings = SimulationSettingsReader.parseSimulationSettings(settingsFilePath); //ACCEPT PARAMS OF FILE PATH
        NUMBER_OF_FLOORS = simulationSettings.getSettingsFloorCount();
        NUMBER_OF_ELEVATORS = simulationSettings.getSettingsElevatorCount();
        NUMBER_OF_PEOPLE = simulationSettings.getSettingsPeopleCount();
        MAX_ELEVATOR_CAPACITY = simulationSettings.getSettingsElevatorCapacity();
        TRAVEL_TIME_MILLIS = simulationSettings.getSettingsElevTravelTime();
        DOOR_TIME_MILLIS = simulationSettings.getSettingsDoorFunctionTime();
        IDLE_TIME_MILLIS = simulationSettings.getSettingsElevIdleTime();
        TIME_STEP_MILLIS = simulationSettings.getTimeStepMills();  
    }
    
    public static void createSimulatorComponents(){
        ActivityLogger logger = new ActivityLogger();
        SystemTimer timer = new SystemTimer(TIME_STEP_MILLIS);
        simulator = new Simulator(logger, timer);
    }
    
    public static Building getBuilding(){ //TEST CODE FIGURE OUT HOW TO SOLVE
        return building;
    }
 
    
    public static void testDispatcher(int floorIn){
        
        ArrayList<Floor> floors = building.getFloors();
        Floor callFloor = floors.get(floorIn);
        if(!(callFloor.getNumberofPeopleWaiting() == 0)){
            callFloor.sendRequest();
        }
        else{
            while(callFloor.getNumberofPeopleWaiting() == 0){
                callFloor = floors.get(floorIn++); 
            }
            callFloor.sendRequest();
        }    
    }
    
    public static void getElevatorLocations(){
        
        for(Elevator e : building.getElevators()){
            System.out.println("Elevator: "+e.getElevatorID()+
                                " Is on Floor "+e.getCurrentFloor()+
                                " and has "+e.getCurrentCapacity()+
                                " people in it. "+
                                "------------------------------------");
        }
    }
   
    public static void testLoggin(){
        
        ArrayList<Elevator> elevators = building.getElevators();
        
        Elevator e1 = elevators.get(0);
        Elevator e2 = elevators.get(1);
        Elevator e3 = elevators.get(2);
        
        e1.move(10);
        e1.openDoors();
        
        
    }

}
