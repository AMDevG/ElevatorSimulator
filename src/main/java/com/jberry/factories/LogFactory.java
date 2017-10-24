/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.factories;
import com.jberry.elevatorsimulator.domain.Building;
import com.jberry.interfaces.Log;
import com.jberry.interfaces.Loggable;
import com.jberry.simulator.logging.ElevatorLog;

/**
 *
 * @author johnberry
 */
public class LogFactory implements Loggable {
    
    private static String timeStamp;
    private static String logFrom;
    private static String logDetail;
    private static String logMessage;
    private static Log log;

    public LogFactory(){}
    ///CTOR WONT WORK FOR ALL EVENT TYPES
    ///DIFFERENT EVENTS REQUIRE DIFFERENT PARAMS
    ///OVERLOAD CTOR IN FIRST SUBMISSION
    
    public static Log createNewLog(int senderID, int senderCurrentFloor,int senderDestFloor, String timeStampIn, Event eventIn){
        switch(eventIn){
            case HANDLING_RIDER_REQUEST:
                timeStamp = timeStampIn;
                logFrom = "    Elevator "+senderID;
                logDetail = "is moving from " +senderCurrentFloor +"\n to Floor "+senderDestFloor + " (Floor Stops " +
                               Building.getInstance().getElevators().get(senderID - 1).getFloorStops() + " Rider Stops: "+
                               Building.getInstance().getElevators().get(senderID - 1).getRiderStops()+")";
                logMessage = timeStamp + logFrom + logDetail;
                log = new ElevatorLog(logMessage);
                break;
                
            case DOORS_OPEN:
                timeStamp = timeStampIn;
                logFrom = "     Elevator "+senderID;
                logDetail = " doors are open on floor " +senderCurrentFloor + " (Floor Stops " + 
                               Building.getInstance().getElevators().get(senderID - 1).getFloorStops() + " Rider Stops: "+
                               Building.getInstance().getElevators().get(senderID - 1).getRiderStops() +")";
                logMessage = timeStamp + logFrom + logDetail;
                log = new ElevatorLog(logMessage);
                break;    
                
            case DOORS_CLOSED:
                timeStamp = timeStampIn;
                logFrom = "     Elevator "+senderID;
                logDetail = " doors are closed on floor " +senderCurrentFloor + " (Floor Stops " +
                               Building.getInstance().getElevators().get(senderID - 1).getFloorStops() +" Rider Stops: "+
                               Building.getInstance().getElevators().get(senderID - 1).getRiderStops() +")";
                logMessage = timeStamp + logFrom + logDetail;
                log = new ElevatorLog(logMessage);
                break; 
                
            case MOVING:
                timeStamp = timeStampIn;
                logFrom = "     Elevator "+senderID;
                logDetail = " moving from Floor " +senderCurrentFloor + " to Floor " +senderDestFloor + " (Floor Stops " +
                               Building.getInstance().getElevators().get(senderID - 1).getFloorStops() + " Rider Stops: "+
                               Building.getInstance().getElevators().get(senderID - 1).getRiderStops() + ")";
                logMessage = timeStamp + logFrom + logDetail;
                log = new ElevatorLog(logMessage);
                break; 
 
            }
        return log;
    }
    
    public static Log createNewLog(int senderID, int senderCurrentFloor,int senderDest, String timeStampIn, String directionIn, Event eventIn){
        switch(eventIn){
              case PERSON_CREATED:
                timeStamp = timeStampIn;
                logFrom = "     Person "+senderID;
                logDetail = "  created on floor "+ senderCurrentFloor + " wants to go " + directionIn + " to Floor " + senderDest; 
                logMessage = timeStamp + logFrom + logDetail;
                log = new ElevatorLog(logMessage);
                break;
                
            case PERSON_ENTERING_ELEVATOR:
                timeStamp = timeStampIn;
                logFrom = "     Person "+senderID;
                logDetail = "  entered Elevator "+ senderDest + " Riders: " + 
                            Building.getInstance().getElevators().get(senderDest - 1).getRiderStops();
                logMessage = timeStamp + logFrom + logDetail;
                log = new ElevatorLog(logMessage);
        }
        return log;
    } 
}
