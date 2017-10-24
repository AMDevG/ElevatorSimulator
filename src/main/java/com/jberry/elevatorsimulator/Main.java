/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.elevatorsimulator;

import java.util.ArrayList;

import com.jberry.simulator.SimulationSettingsReader;
import com.jberry.simulator.SimulatorSetting;
import com.jberry.elevatorsimulator.domain.Building;
import com.jberry.elevatorsimulator.domain.Person;
import com.jberry.elevatorsimulator.domain.Elevator;
import com.jberry.elevatorsimulator.domain.Floor;
import com.jberry.factories.BuildingFactory;
import com.jberry.factories.ElevatorFactory;
import com.jberry.factories.FloorFactory;
import com.jberry.factories.PersonFactory;
import com.jberry.simulator.logging.ActivityLogger;
import com.jberry.simulator.Simulator;
import com.jberry.simulator.SystemTimer;

import gui.ElevatorDisplay;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File; 

/**
 *
 * @author johnberry
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    
    private static String currentDir = System.getProperty("user.dir");
    private static String SETTINGS_FILE_PATH;
    
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
        String USER_OS = System.getProperty("os.name");
        
        //IF USER IS ON MAC, CHANGE FILE PATH STRING
        if(!USER_OS.contains("Window")){
            SETTINGS_FILE_PATH = currentDir + "//simulationSpecs.json";  
        }
        else{
            SETTINGS_FILE_PATH = currentDir + "\\simulationSpecs.json";  
        }
        
        createSettings();
        setUpBuilding();
        createSimulatorComponents();
        simulator.startSimulation();   
    }   
    public static void setUpBuilding(){
       ArrayList<Person> peopleEnteringBuilding = PersonFactory.createPeople(NUMBER_OF_PEOPLE, NUMBER_OF_FLOORS);
       ArrayList<Floor> buildingFloors = FloorFactory.createFloors(NUMBER_OF_FLOORS, NUMBER_OF_ELEVATORS, peopleEnteringBuilding);
       ArrayList<Elevator> buildingElevators = ElevatorFactory.createElevators(NUMBER_OF_ELEVATORS, TRAVEL_TIME_MILLIS, DOOR_TIME_MILLIS, MAX_ELEVATOR_CAPACITY, IDLE_TIME_MILLIS);
       building = BuildingFactory.createBuilding("Standard",buildingFloors, buildingElevators); 
    }
    private static void createSettings(){
        SimulatorSetting simulationSettings = SimulationSettingsReader.getInstance().parseSimulationSettings(SETTINGS_FILE_PATH);
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
        
        ElevatorDisplay.getInstance().initialize(NUMBER_OF_FLOORS);
        for (int i = 1; i <= NUMBER_OF_ELEVATORS; i++) {
            ElevatorDisplay.getInstance().addElevator(i, 1);
        }  
    }
 
}
