/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.simulator;

/**
 *
 * @author johnberry
 */
public class SimulatorSetting{
    
    private static int floors;
    private static int elevators;
    private static int people;
    private static int elevatorCapacity;
    private static long doorTimeMills;
    private static long travelTimeMills;
    private static long idleTimeMills;
    private static long timeStepMills;
    
    
    public SimulatorSetting(int floorsIn, int elevatorsIn, int peopleIn, int elevCapacityIn,
                            long doorTimeMillsIn, long travelTimeMillsIn, long idleTimeMillsIn,
                            long timeStepMillsIn){
        floors = floorsIn;
        elevators = elevatorsIn;
        people = peopleIn;
        elevatorCapacity = elevCapacityIn;
        doorTimeMills = doorTimeMillsIn;
        travelTimeMills = travelTimeMillsIn;
        idleTimeMills = idleTimeMillsIn;
        timeStepMills = timeStepMillsIn;
    }
    
    public static int getSettingsFloorCount(){return floors;}
    public static int getSettingsElevatorCount(){return elevators;}
    public static int getSettingsPeopleCount(){return people;}
    public static int getSettingsElevatorCapacity(){return elevatorCapacity;}
    public static long getSettingsDoorFunctionTime(){return doorTimeMills;}
    public static long getSettingsElevTravelTime(){return travelTimeMills;}
    public static long getSettingsElevIdleTime(){return idleTimeMills;}
    public static long getTimeStepMills(){return timeStepMills;}
     
}
