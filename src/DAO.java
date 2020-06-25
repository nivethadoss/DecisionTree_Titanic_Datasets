/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nivetha
 */
public class DAO {
    
    
    
    
    public DAO(){
    
}
    
    
    // Method to extract the data from a CSV file
    
    public DataExtractor extractData(String path, String sep) throws IOException{
        
        DataExtractor db = new DataExtractor(path, sep);   
        return db;
    }
    
    
    // Create a data set from the user given list of characters/features
    public DataSet createTrainingDataSet(ArrayList<Characteristics> avcharaters, ArrayList<RowData> data, ArrayList<String> userCharacterlist) throws IOException{ 
        DataSet traindataset = new DataSet();
        ArrayList<Characteristics> removeCharacter = new ArrayList<>();
        ArrayList<RowData> newrowData = new ArrayList<>();
        ArrayList<Characteristics> characterisList = avcharaters;
        ArrayList<Characteristics> characteristicListChoosen = new ArrayList<>();
        
            for (Characteristics charaters : characterisList){ 
                    if(userCharacterlist.contains(charaters.name))
                    {
                       
                        characteristicListChoosen.add(charaters);
                    }
                    else{
                         removeCharacter.add(charaters);                     
                    }
            }

            for(RowData row : data){
                for(Characteristics c : removeCharacter){
                    row.removeData(c);
                }
                newrowData.add(row);         
            }
        
            traindataset.setCharacteristicList(characteristicListChoosen);
            traindataset.setRowDataList(newrowData);
       return traindataset;
    }
    
    
    // Train the dataset with the target feature name 
    public Model TrainClassificationModel(DataSet traindataset, String FeatureName){
        DataSet trainingDataSet = traindataset;
        Model classificationModel = trainingDataSet.trainTheModel(FeatureName);
        return classificationModel;
        
    }

}
   