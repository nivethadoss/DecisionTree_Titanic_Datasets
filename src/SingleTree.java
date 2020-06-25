/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontree;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nivetha
 */
public class SingleTree {
    
    //This class holds the targetmap between a profile and a target
    String name;
    HashMap<TrainingValue, Map<TrainingValue, Double>> targetmap;
    
    public SingleTree(String name){
        this.name = name;    
    }

    public void setTargetmap(HashMap<TrainingValue, Map<TrainingValue, Double>> targetmap) {
        this.targetmap = targetmap;
    }
    
    
    
}
