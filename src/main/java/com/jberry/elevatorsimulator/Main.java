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
        SimulatorSetting simulationSettings = SimulationSettingsReader.getInstance().parseSimulationSettings(settingsFilePath);
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
    
  /*  public static void getElevatorLocations(){
        for(Elevator e : building.getElevators()){
            System.out.println("Elevator: "+e.getElevatorID()+
                                " Is on Floor "+e.getCurrentFloor()+
                                " and has "+e.getCurrentCapacity()+
                                " people in it. "+
                                "------------------------------------");
            }
    }*/
   
    private static void moveElevator(int elevNum, int fromFloor, int toFloor) throws InterruptedException {
        int numRiders = (int) (11.0 * Math.random()) + 1;

        ElevatorDisplay.getInstance().closeDoors(elevNum);
        if (fromFloor < toFloor) {
            for (int i = fromFloor; i <= toFloor; i++) {
                ElevatorDisplay.getInstance().updateElevator(elevNum, i, numRiders, UP);
                Thread.sleep(80);
            }
        } else {
            for (int i = fromFloor; i >= toFloor; i--) {
                ElevatorDisplay.getInstance().updateElevator(elevNum, i, numRiders, DOWN);
                Thread.sleep(80);
            }
        }
        ElevatorDisplay.getInstance().openDoors(elevNum);
    }
}
