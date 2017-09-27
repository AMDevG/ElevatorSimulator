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
public class ElevatorController {
    
    private static ElevatorController instance;
    
    private ElevatorController(){}
    
    public static ElevatorController getInstance(){
        if (instance == null)
            instance = new ElevatorController();
        return instance;
    }
    
    
    
}
