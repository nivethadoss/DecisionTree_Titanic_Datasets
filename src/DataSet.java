/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nivetha
 */
public class DataSet {
    
    List<Characteristics> characteristicList;
    List<RowData> rows;
    Characteristics targetCharteristics;
    
    
    public DataSet(){
        
        
    }
    // To hold list of characters object
    public void setCharacteristicList(List<Characteristics> characteristicList){
        this.characteristicList = characteristicList;
    }
    
    // to hold the RowData list
   public void setRowDataList(List<RowData> rows){
       this.rows = rows;
   }
   
   
  // This method helps to build a single tree for a characteristic given 
   public SingleTree createTree(Characteristics Character, Characteristics Target){
       // for each unique values in target, the count are mapped
       //  
       // Survived : 
       //                    male : Count / totalSurvived   
       //                    female : Count / totalSurvived
       // Non Survived:
       //                    male : Count / totalDead 
       //                   female: Count / totalDead 
       
        HashMap<TrainingValue, Map<TrainingValue, Double>> map = new HashMap<>();
        SingleTree tree = new SingleTree(Character.name);
       
        // get profile and target characteristics 
        Characteristics profileCharteristics = Character;
        Characteristics targetCharteristics = Target;
        
        // get unique values in each column of profile and target 
        ArrayList<TrainingValue> uniqueTargteValues = targetCharteristics.getUniqueList();
        ArrayList<TrainingValue> uniqueProfileValues = profileCharteristics.getUniqueList();
        
        // for each unique value initialize with unique values in profile 
        for(TrainingValue unique : uniqueTargteValues){              
               map.put(unique, new HashMap<>());
               for(TrainingValue profileUnique : uniqueProfileValues){
                  HashMap<TrainingValue,Double> targetmap = (HashMap<TrainingValue,Double>) map.get(unique);
                  targetmap.put(profileUnique, 0.0); 
               }
        }
        
       // for each row in profile obtain the value and increase the count accordingly
       for(RowData r : this.rows){                  
           for(TrainingValue unique : uniqueTargteValues){
               TrainingValue profileValue = r.rowMap.get(profileCharteristics);
               TrainingValue targetValue = r.rowMap.get(targetCharteristics);
                if(profileValue != null && targetValue !=null){
                     if(unique.equals(targetValue)){
                         HashMap<TrainingValue,Double> targetmap = (HashMap<TrainingValue,Double>) map.get(unique);
                         for(TrainingValue prvalue : uniqueProfileValues  ){            
                             if(prvalue.equals(profileValue)){
                                 Double count = targetmap.get(prvalue);
                                 count = count +1;
                                 targetmap.put(prvalue, count);
                             }                    
                         }

                     } 
                }
           }   
       }
       
       for( Map.Entry<TrainingValue, Map<TrainingValue, Double>> entry : map.entrySet()){ 
           Map<TrainingValue, Double> scoremap = entry.getValue();
           double tcount = 0;
              //  for (Map.Entry <TrainingValue, Double> ele : scoremap.entrySet()){
              //      tcount = tcount + ele.getValue();
              //  }         
               // dividing by total count to normalize the value 
                TrainingValue maxItem = new TrainingValue();
                for (Map.Entry <TrainingValue, Double> ele : scoremap.entrySet()){
                    double count = scoremap.get(ele.getKey());
                    if (count > tcount){
                        maxItem = ele.getKey();
                    }
                    tcount = count;
                  //  count = count/tcount;
                  //  scoremap.put(ele.getKey(),count);
                }
                for (Map.Entry <TrainingValue, Double> ele : scoremap.entrySet()){
                    if(ele.getKey() == maxItem){
                        scoremap.put(ele.getKey(),1.0);
                    }
                    else{ 
                        scoremap.put(ele.getKey(),0.0);
                    }
                  //  count = count/tcount;
                  //  scoremap.put(ele.getKey(),count);
                }
                
                
                
            //    System.err.println(maxItem.toString());
               
       }
      tree.setTargetmap(map);
   
    return tree;
}
   
   // This method takes all the features from the data set and creates trees for each columns and return a classification model
   public Model trainTheModel(String FeatureName){
       Model classificationModel = new Model();
       classificationModel.TargetName = FeatureName;
       for(Characteristics ch : this.characteristicList){
           System.err.println(ch.name);
           if(ch.name.equals(FeatureName)){
               this.targetCharteristics = ch;
           }
       }
       classificationModel.targetCharacter= this.targetCharteristics;
       classificationModel.setTargetName(this.targetCharteristics.name);
       
       for(Characteristics ch : this.characteristicList){
        if(!ch.name.equals(FeatureName)){
            Characteristics profileCharteristics = ch;
            SingleTree tree = this.createTree(ch, targetCharteristics);
           // System.err.println(tree);
            classificationModel.addTree(ch, tree);
            classificationModel.trainCharacters.add(ch);
            classificationModel.addFeaturesTrained(ch.name);
        }  
           
       }
       return classificationModel;
   }
}