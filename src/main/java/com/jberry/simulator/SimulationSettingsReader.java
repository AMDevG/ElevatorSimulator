/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jberry.simulator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author johnberry
 */
public final class SimulationSettingsReader {
    
    private static SimulatorSetting newSetting; 
    private static SimulationSettingsReader instance;
    
    private SimulationSettingsReader(){}   

    public static SimulationSettingsReader getInstance(){  
        if(instance == null){
            instance = new SimulationSettingsReader();
            return instance;
        }
        return instance; 
    }
    
    public static SimulatorSetting parseSimulationSettings(String filePathIn){
       try{
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(filePathIn));
            JSONObject jsonObject = (JSONObject) obj;
           
            long numberOfFloors = (long)jsonObject.get("numberOfFloors");
            long numberOfElevators = (long) jsonObject.get("numberOfElevators");
            long numberOfPeople = (long) jsonObject.get("numberOfPeople");
            long maxCapacity = (long) jsonObject.get("maxElevatorCapacity");
            long doorFunctionTimeMills = (long) jsonObject.get("doorFunctionTimeMills");
            long travelTimeMills = (long) jsonObject.get("travelTimeMills");
            long idleTimeMills = (long) jsonObject.get("idleTimeMills");
            long timeStepMills = (long) jsonObject.get("timeStepMills");
            
            int intMaxCapacity = (int) maxCapacity;
            int intFloors = (int) numberOfFloors;
            int intElevators = (int) numberOfElevators;
            int intPeople = (int) numberOfPeople;
            
            newSetting = new SimulatorSetting(intFloors, intElevators, intPeople,intMaxCapacity,
                    doorFunctionTimeMills, travelTimeMills, idleTimeMills, timeStepMills);  
           }catch(FileNotFoundException e){System.out.println("No file found.");}
            catch(IOException | ParseException e){System.out.println("IOExceptionCaught");}
       
        return newSetting;
    }
}