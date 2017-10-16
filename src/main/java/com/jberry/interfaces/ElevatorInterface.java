/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.interfaces;

import com.jberry.elevatorsimulator.domain.CarRequest;

/**
 *
 * @author johnberry
 */
public interface ElevatorInterface {    
    void move(int destFloor);
    
    public final String UP_DIRECTION = "UP";
    public final String DOWN_DIRECTION = "DOWN";
    public final String IDLE_STATUS = "IDLE";
    
    public final String EVENT_TYPE = "ELEVATOR";
    
    
    void openDoors();
    void closeDoors();
    int getCurrentFloor();
    int getElevatorID();
    void sendRequest(Request r); //** CREATE REQUESTABLE INTERFACE FOR THIS METHOD
    
}
