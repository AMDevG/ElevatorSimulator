/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.elevatorsimulator.domain;

import com.jberry.interfaces.Processor;
import com.jberry.algorithms.*;
import com.jberry.interfaces.Request;
import com.jberry.interfaces.SchedulingAlgorithm;
import java.util.ArrayList;

/**
 *
 * @author johnberry
 */
public final class ElevatorProcessor{
    
    private static ElevatorProcessor instance;
    private final SchedulingAlgorithm scheduler = new NearestCarAlgoImpl();

    private ArrayList<Floor> floors;
    private ArrayList<Elevator> elevatorBank;
    
    private ElevatorProcessor(){}
    
    public static ElevatorProcessor getInstance(){
        if(instance == null){
            instance = new ElevatorProcessor();
            //elevatorBank = new ArrayList<Elevator>();
            return instance;
        }
        return instance;   
    }
    
    public void handleRequest(Request r){
        scheduler.handleCall(r);
    }
    
    
    
    
    
    
}
