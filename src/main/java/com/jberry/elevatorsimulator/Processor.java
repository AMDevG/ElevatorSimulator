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
public interface Processor {
    
    void processFloorRequest(String direction, int elevatorID);
    void processElevatorRequest(String direction, int floorID);
    void sendDestinationFloor(int destFloor, int elevatorID);
   
}
