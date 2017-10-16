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
public class FloorRequest implements Request {
    private final String REQUEST_TYPE = "FLOOR";

    private int floorID;
    private int destinationFloorID;
    private String directionOfTravel;
    private int sectorID;

    public FloorRequest(int floorIDIn, int destinationIDin, String directionOfTravelIn){
        floorID = floorIDIn;
        destinationFloorID = destinationIDin;
        directionOfTravel = directionOfTravelIn;       
    }
    
    public int getID(){
        return floorID;
    }
    public int getDestinationID(){
        return destinationFloorID;
    }
    public String getDirection(){
        return directionOfTravel;
    }
    public String getCallSender(){
        return REQUEST_TYPE;
    }
    
}
