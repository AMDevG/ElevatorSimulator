/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
/* FSO Algo is an implementation of the classic 'fixed sectoring, bidirectional 
   sector' elevator controller algorithm. FSO divides the building into sectors
   based on the number of floors and elevators. Sectors are comprised of a 
   range of consecutive floors. An elevator assigned to a sector will only 
   service calls in that sector. An elevator can be re-assigned to a different
   sector depending on a variety of conditions. This algo is best suited for 
   'balanced, inter-floor' traffic.

   Reference: www.diva-portal.se/smash/get/diva2:812034/FULLTEXT01.pdf
*/ 

package com.jberry.algorithms;

import com.jberry.interfaces.SchedulingAlgorithm;
import com.jberry.elevatorsimulator.domain.CarRequest;
import com.jberry.interfaces.Request;

/**
 *
 * @author johnberry
 */
public class FSOAlgoImpl implements SchedulingAlgorithm {
    
    public FSOAlgoImpl(){}
    
    public void handleCall(Request r){
         
    }
    
}
