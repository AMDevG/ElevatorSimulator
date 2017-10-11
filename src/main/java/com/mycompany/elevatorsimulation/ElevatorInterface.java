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
public interface ElevatorInterface {    
    void move(int destFloor);
    
    void openDoors();
    void closeDoors();
    int getCurrentFloor();
    int getElevatorID();
}
