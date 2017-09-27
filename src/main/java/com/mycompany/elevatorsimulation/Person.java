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
public class Person {
    
    private int ID;
    private int waitTimeMills;
    private int currentFloor;
    private int destFloor;
    
    public Person(int IDIn, int currentFloorIn){
        ID = IDIn;
        currentFloor = currentFloorIn;
    }
    
    public int getID(){
        return ID;
    }
    public int getCurrentFloor(){
        return currentFloor;
    }
    
}
