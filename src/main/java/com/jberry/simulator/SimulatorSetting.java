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
    
    private final int floors;
    private final int elevators;
    private final int people;
    private final int elevatorCapacity;
    private final long doorTimeMills;
    private final long travelTimeMills;
    private final long idleTimeMills;
    private final long timeStepMills;
    
    
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
    
    public int getSettingsFloorCount(){return floors;}
    public int getSettingsElevatorCount(){return elevators;}
    public int getSettingsPeopleCount(){return people;}
    public int getSettingsElevatorCapacity(){return elevatorCapacity;}
    public long getSettingsDoorFunctionTime(){return doorTimeMills;}
    public long getSettingsElevTravelTime(){return travelTimeMills;}
    public long getSettingsElevIdleTime(){return idleTimeMills;}
    public long getTimeStepMills(){return timeStepMills;}
     
}
