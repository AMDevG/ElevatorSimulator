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
public interface Loggable{
    
        
    public enum Event{
        ELEVATOR_DISPATCHED,
        RIDER_REQUEST,
        HANDLING_RIDER_REQUEST,
        HANDLED_REQUEST,
        MOVING,
        DOORS_OPEN,
        DOORS_CLOSED,
        PERSON_ENTERING_ELEVATOR,
        PERSON_EXITING_ELEVATOR,
        FLOOR_REQUEST, 
        PERSON_CREATED
    }
    
}
