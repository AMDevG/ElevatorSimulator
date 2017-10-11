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
public class BuildingFactory {
    
    private BuildingFactory(){}
    
    public static Building createBuilding(String type, int numFloorsIn, int numElevatorsIn){
            if (type.equals("Standard")){
                return new Building(numFloorsIn, numElevatorsIn);
            }
            else return null;
        }  
    }
  
