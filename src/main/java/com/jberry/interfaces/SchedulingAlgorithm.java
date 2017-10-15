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
public interface SchedulingAlgorithm {
    
    //NEED TO MAKE REQUESTS GENERIC
    void handleCall(Request r);
    
}
