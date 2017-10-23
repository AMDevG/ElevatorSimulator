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
    //HAVE TO OVERLOAD CTOR
    
    public static Log createNewLog(int senderID, int senderCurrentFloor,int senderDestFloor, String timeStampIn, Event eventIn){
        switch(eventIn){
            case HANDLING_RIDER_REQUEST:
                timeStamp = timeStampIn;
                logFrom = "    Elevator "+senderID;
                logDetail = "is moving from " +senderCurrentFloor +"\n to Floor "+senderDestFloor;
                logMessage = timeStamp + logFrom + logDetail;
                log = new ElevatorLog(logMessage);
                break;
                
            case DOORS_OPEN:
                timeStamp = timeStampIn;
                logFrom = "    Elevator "+senderID;
                logDetail = " doors are open on floor " +senderCurrentFloor;
                logMessage = timeStamp + logFrom + logDetail;
                log = new ElevatorLog(logMessage);
                break;    
                
            case DOORS_CLOSED:
                timeStamp = timeStampIn;
                logFrom = "     Elevator "+senderID;
                logDetail = " doors are closed on floor " +senderCurrentFloor;
                logMessage = timeStamp + logFrom + logDetail;
                log = new ElevatorLog(logMessage);
                break; 
                
            case MOVING:
                timeStamp = timeStampIn;
                logFrom = "     Elevator "+senderID;
                logDetail = " moving from Floor " +senderCurrentFloor + " to Floor " +senderDestFloor;
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
                logDetail = "  created "+ senderDest + " wants to go " + directionIn + " to Floor " + senderDest; 
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
    
    /*CREAT INTERFACE FOR HANDLING UPDATE LOGS FOR TYPES:
    
    Floor:
    
        Floor Calls:
            00:00:01 Person P1 presses DOWN button on Floor 10
    
    
    Elevator:
        Elevator Dispatched:
            00:00:47 Elevator 1 is going to Floor 9 for UP request [Current Floor Requests: 8, 9][Current Rider Requests:5]
            
        Rider Request Sent:
            00:00:37 Elevator 3 Rider Request made for Floor 16 [Current Floor Requests: 2][ Current Rider Requests:16]
            
        
        Handling Rider Request:
            00:00:58 Elevator 3 moving from Floor 10 to Floor 11 [Current Floor Requests: 0][ Current Rider Requests: 16]
    
        
        Handled Request (Floor or Rider)
            00:00:27 Elevator 2 has arrived at Floor 12 for Floor Request [Current Floor Requests: 14][Current Rider Requests: 16]
        
        Doors Open/Close
            00:03:09 Elevator 3 Doors Open           
            00:03:12 Elevator 3 Doors Close
        
        PersonEnteringElevator
            00:00:06 Person P12 entered Elevator 1 [Riders: P3, P5, P12]
            00:07:22 Person P43 entered Elevator 4 [Riders: P22, P43]
        
        PersonExitingElevator
            00:04:52 Person P18 has left Elevator 4 [Riders: P13] 
   */ 
    
}
