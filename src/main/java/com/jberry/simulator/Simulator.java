/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.simulator;

import com.jberry.elevatorsimulator.Main;
import com.jberry.elevatorsimulator.domain.Building;

/**
 *
 * @author johnberry
 */
public class Simulator {
    
    private final Building building;
    
    public Simulator(){
        building = Main.getBuilding(); //MOVE OUT OF MAIN
    }
    public void advanceTime(){
        
    }
        
   }
    

