/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.interfaces;

/**
 *
 * @author johnberry
 */
public interface Request {

    //public Requestable sendRequest(int senderID, int destinationID, String directionOfTravel, int sectorID, String requestType); 
        //Add Timestamp class to process waiting time
    
    //public void handleRequest(Request r);
    
    public int getID();
    public String getDirection();
    public int getDestinationID();
    public String getCallSender();

}
