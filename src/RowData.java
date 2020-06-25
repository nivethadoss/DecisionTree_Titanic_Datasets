/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontree;

import java.util.HashMap;

/**
 *
 * @author Nivetha
 */
public class RowData {
    
    HashMap<Characteristics,TrainingValue> rowMap;
    int rowId;
    
    // Class to store a single row with all the characteristics
    
    public RowData(int count){
        this.rowId = count;
        this.rowMap = new HashMap<>();
        
    }

    
    // Creation of data with respect to data type
    public void setData(Characteristics ch,String val){
        
        if(!this.rowMap.containsKey(ch)){
            if("Numerical".equals(ch.datatype)){
                NumericalValue valObj = new NumericalValue(val);
                ch.addToUnique(valObj);
                ch.increaseCount();
                this.rowMap.put(ch,valObj);
                }
                    
            
            else if("Categorical".equals(ch.datatype)){
                TextualValue valObj = new TextualValue(val);
                ch.addToUnique(valObj);
                ch.increaseCount();
                this.rowMap.put(ch,valObj);
            }
        }
    }
    
    public HashMap<Characteristics,TrainingValue>  getRowMap(){
        return this.rowMap;
    }
    
    public TrainingValue getValue(Characteristics ch){
        return this.rowMap.get(ch);
    }
    
    public void removeData(Characteristics ch){
        this.rowMap.remove(ch);
    }
        
}
