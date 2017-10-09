/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.elevatorsimulation;

import java.util.Random;

/**
 *
 * @author johnberry
 */
public class Person {
    
    private int ID;
    private long waitTime;
    private long startTime;
    private long endTime;
    private boolean waiting;
    private int currentFloor;
    private int destFloor;
    
    public Person(int IDIn, int currentFloorIn){
        ID = IDIn;
        currentFloor = currentFloorIn;
        waiting = true;
    }
    
    public void exitElevator(){
        waiting = false;
        calculateWaitTime();
    }
    
    public void enterElevator(){
    }
    
    public int getID(){ // PUT INTO INTERFACE?
        return ID;
    }
    
    public int getCurrentFloor(){
        return currentFloor;
    }
    
    private void callElevator(){
        System.out.println("Just called elevator");
        Random rand = new Random();
        int randomNum = rand.nextInt((10 - 1) + 1) + 1;
        destFloor = randomNum;
        startWaitTime();
    }
    
    private void startWaitTime(){
        startTime = System.currentTimeMillis();
    }
    private void calculateWaitTime(){
        endTime = System.currentTimeMillis();
        waitTime = endTime - startTime;
    }
}
