/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontree;

import java.util.ArrayList;

/**
 *
 * @author Nivetha
 */
public class Characteristics {
    /* Class to store the column name and type of each dataset */
    // This class acts like an id to each column from data set, it contains the Null value information 
    // It also contains unique values from each column
    
    int id;
    int isUnique;
    String name;
    String datatype;
    int isNumerical = 0;
    int isCategorical = 0;
    TrainingValue uniVal;
    int totalDataCount = 0;
    ArrayList<Integer> nullId = new ArrayList<>();
    ArrayList<TrainingValue> UniqueValues = new ArrayList<>();

    
    public Characteristics(int id, String name){
        this.id = id;
        this.name = name;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getName() {
        return name;
    }

    public void setNullId(Integer nullId) {
        this.nullId.add(nullId);
    }
    
     public void addToUnique(TrainingValue valObj){
         
        if(this.UniqueValues.size() == 0){
             this.UniqueValues.add(valObj);
         }
        
        else{
            isUnique = 1;
            for(TrainingValue val: this.UniqueValues){
                if (val.equals(valObj) == true){
                    isUnique = 0;
                    break;
                }
            }
            
            if (isUnique == 1){
               //System.err.println(valObj);
               this.UniqueValues.add(valObj);        
        }
        }
     }
     
    public void increaseCount(){
        totalDataCount = totalDataCount +1;
    }
    public ArrayList<TrainingValue> getUniqueList(){
        
        return this.UniqueValues;
    }
     
}


