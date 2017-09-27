/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.elevatorsimulation;

/**
 *
 * @author johnberry
 */
public final class ElevatorProcessor {
    
    private static ElevatorProcessor instance;
    
    private ElevatorProcessor(){
    }
    
    public static ElevatorProcessor getInstance(){
        if(instance == null)
            instance = new ElevatorProcessor();
        return instance;
    }
}
