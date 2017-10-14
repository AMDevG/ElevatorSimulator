/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.elevatorsimulator;

/**
 *
 * @author johnberry
 */
public class FloorFactory {
    
    public static Floor createFloor(int numberOfElevatorsIn, int floorIDIn){      
        return new Floor(numberOfElevatorsIn, floorIDIn);
    }
    
}
