/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nivetha
 */
public class Model {
    // Model hold the information of who he was trained with 
    // the information on the characters and target name to ease testing 
    
    
    HashMap<Characteristics,SingleTree> globalTargetMap = new HashMap<>();
    String TargetName;
    ArrayList<Characteristics> trainCharacters = new ArrayList<>();
    Characteristics targetCharacter;
    Characteristics testtargetCharacter;
    ArrayList<String> TrainedFeatures = new ArrayList<>();
    Characteristics Testtarget;
    SingleTree tree;
    HashMap<Characteristics,Double> treeCount;
    HashMap<Characteristics,Double> realCount;
    
    public Model(){
        
        
    }
    
    public void addTree(Characteristics ch, SingleTree tree){
        globalTargetMap.put(ch,tree);
    }
    
    
    
    
    // test takes a dataset and goes through each rows and score the row with respect to the tree built while training
    // once score is available a threshold value is set by user to help fix the Precision and recall
    public void test(DataSet testdata, double Threshold) throws IOException{
 
        System.err.println("Printing Test Results: ");
        for(Characteristics ch : testdata.characteristicList){
           if(ch.name.equals(TargetName)){
               testtargetCharacter = ch;
           }
        }  
        // Loop to go through unique target values
        for(TrainingValue trainVal : targetCharacter.UniqueValues){
            int modelCount = 0;
            int trueCount = 0;
              //TrainingValue trainVal = targetCharacter.UniqueValues.get(0);
            treeCount = new HashMap<>();
            realCount = new HashMap<>();
            String text = trainVal.toString();
            System.err.println("Testing for  Target : " + text + " With Threshold = "+ Threshold);

            // Loop to go thorugh all test data 
            for(RowData t : testdata.rows){
                double tscore = 0;
                double score = 0;
                int count = 0;
                TrainingValue testtargetVal = t.rowMap.get(testtargetCharacter);
                // Loop to go through all the test data characters
                for(Characteristics ch : testdata.characteristicList){
                    if(!ch.name.equals(TargetName)){
                        
                        Characteristics tectCh = ch;
                           // Loop to identify the train character with respect to the test character
                            for (Characteristics tch:  trainCharacters){
                                    if(tch.name.equals(ch.name)  ){
                                            try{
                                                    Characteristics trainch = tch;
                                                    TrainingValue testVal = t.rowMap.get(tectCh);
                                                    // Getting the decision tree 
                                                    SingleTree tree = globalTargetMap.get(trainch);
                                                    Map<TrainingValue, Double> scoremap = tree.targetmap.get(trainVal);
                                                    
                                                    for(Map.Entry<TrainingValue, Double> entry : scoremap.entrySet()){
                                                        if(testVal.equals(entry.getKey())){
                                                                tscore = tscore + entry.getValue();
                                                                score = entry.getValue();
                                                                if(score == 1){
                                                                    if(!treeCount.containsKey(tectCh))
                                                                    {
                                                                        treeCount.put(tectCh, 1.0);
                                                                    }
                                                                    else{
                                                                        double counter = treeCount.get(tectCh);
                                                                        counter = counter + 1;
                                                                        treeCount.put(tectCh,counter);
                                                                    }
                                                                    if(trainVal.equals(testtargetVal)){
                                                                        if(!realCount.containsKey(tectCh))
                                                                    {
                                                                        realCount.put(tectCh, 1.0);
                                                                    }
                                                                    else{
                                                                        double counter = realCount.get(tectCh);
                                                                        counter = counter + 1;
                                                                        realCount.put(tectCh,counter);
                                                                    }
                                                                    }
                                                                    else{
                                                                    }
                                                                }
                                                            }
                                                        }   
                                                }
                                                catch(Exception e)
                                                {
                                                     //System.err.println(t.rowId);
                                                }
                                    }
                            }     
                            
                    }  
               
                    
            }  

            // Identifying with a threshold value
            if(tscore > Threshold){
                modelCount = modelCount + 1;
                if(testtargetVal.equals(trainVal)){
                    trueCount = trueCount + 1;
                }
            }   
            }     
            System.err.println("---------Result---------------------------");  
            System.err.println("Target Model Predicted " + modelCount);
            System.err.println("Target in Test data " + trueCount);
            double acc = ((double) trueCount / (double) modelCount)*100; 
            System.err.println("Model accuracy : " + acc);
            System.err.println("------------------------------------------------------");
            System.err.println("Best Tree for the Test set : ");
            for(Characteristics ch : testdata.characteristicList){
                if(!ch.name.equals(TargetName)){
                    
                if(realCount.get(ch) != null && treeCount.get(ch) != null)
                {
                    double bestTree = (realCount.get(ch)/treeCount.get(ch))*100;
                    System.err.println(ch.name +" "+ bestTree +" %");
                }
               
                }
        
            } 
    
        } 
        
    }
    
    public void setTargetName(String Name){
        this.TargetName = Name;
        
    }
    
    public void addFeaturesTrained(String Name){
        this.TrainedFeatures.add(Name);
    }
    
    
    public void summary(){
        System.err.println("The Model was trained with");
        for (String s: this.TrainedFeatures){
            System.err.println(s);
        }
        System.err.println("Target of the model");
        System.err.println(this.TargetName);
         
        for (Characteristics ch : trainCharacters){
            SingleTree tree = globalTargetMap.get(ch);
            for (TrainingValue trainVal : targetCharacter.UniqueValues){
                Map<TrainingValue, Double> scoremap = tree.targetmap.get(trainVal);          
            }   
        }    
    }
}
