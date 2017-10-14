/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.elevatorsimulator;

/**
 *
 * @author johnberry
 */
public class SimulatorSetting{
    
    private int floors;
    private int elevators;
    private int people;
    private int elevatorCapacity;
    private long doorTimeMills;
    private long travelTimeMills;
    
    public SimulatorSetting(int floorsIn, int elevatorsIn, int peopleIn,
                            int elevCapacityIn, long doorTimeMillsIn, long travelTimeMillsIn){
        floors = floorsIn;
        elevators = elevatorsIn;
        people = peopleIn;
        elevatorCapacity = elevCapacityIn;
        doorTimeMills = doorTimeMillsIn;
        travelTimeMills = travelTimeMillsIn;
    }
    
    public int getSettingsFloorCount(){return floors;}
    public int getSettingsElevatorCount(){return floors;}
    public int getSettingsPeopleCount(){return floors;}
    public int getSettingsElevatorCapacity(){return floors;}
    public long getSettingsDoorFunctionTime(){return doorTimeMills;}
    public long getSettingsElevTravelTime(){return travelTimeMills;}
     
}
