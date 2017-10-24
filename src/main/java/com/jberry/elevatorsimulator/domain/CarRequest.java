/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.elevatorsimulator.domain;

import com.jberry.interfaces.Request;

/**
 *
 * @author johnberry
 */
public class CarRequest implements Request{
    private final String REQUEST_TYPE = "CAR";

    private int elevatorID;
    private int requestedFloorID;
    private String directionOfTravel;
        
    public CarRequest(int elevatorIDIn, int requestedFloorIn, String directionOfTravelIn){
        elevatorID = elevatorIDIn;
        requestedFloorID = requestedFloorIn;
        directionOfTravel = directionOfTravelIn;
    }
    public int getRequestedFloorID(){
        return requestedFloorID;
    }
    @Override
    public int getID(){
        return elevatorID;
    }
    @Override
    public int getDestinationID(){
        return requestedFloorID;
    }
    @Override
    public String getDirection(){
        return directionOfTravel;
    }
    @Override
    public String getCallSender(){
        return REQUEST_TYPE;
    }
}
