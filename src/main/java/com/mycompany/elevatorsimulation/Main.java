/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.elevatorsimulation;
import java.util.ArrayList;

/**
 *
 * @author johnberry
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Person> testPersonArray = testPeopleCreator();
        for(Person p : testPersonArray){
          System.out.println("Person ID: "+ p.getID() + "is currently waiting on floor " + p.getCurrentFloor());
        }
     
    }
    
    
    public Person createNewPersonObj(int ID, int floor){
        Person newPersonObj = new Person(ID, floor);
        return newPersonObj;
    }
    
    public ElevatorController createNewElevatorController(){
        ElevatorController newElevatorController = ElevatorController.getInstance();
        return newElevatorController;
    }

    public static ArrayList testPeopleCreator(){
        
        ArrayList<Person> personArray = new ArrayList<Person>();
        
        for(int i=0; i<10; i++){
            Person newPers = new Person(i, i);
            personArray.add(newPers);
        }
        return personArray;
    }
}
