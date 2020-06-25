/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontree;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Nivetha
 */
public class DecisionTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        DAO decision = new DAO();
        
        // Extracting Train data from CSV
        
        String trainPath = "data//train_new.csv";
        String seperator = ":";
        DataExtractor rawData = decision.extractData(trainPath,seperator);
        ArrayList<Characteristics> availableCharacterList = rawData.getlistofCharacteristics();
        ArrayList<RowData> availableData  = rawData.getRowData(availableCharacterList);
//        
//         // Extracting Test data from CSV
//        
        String testPath = "data//test_new.csv";
        DataExtractor testData = decision.extractData(testPath,seperator);
        ArrayList<Characteristics> availabletestCharacterList = testData.getlistofCharacteristics();
        ArrayList<RowData> availabletestData  = testData.getRowData(availabletestCharacterList);
//        
//        
//         // Creating list of features to extract from raw data
        ArrayList<String> featuresList = new ArrayList<>();
        featuresList.add("Fare");
        featuresList.add("Sex");
        featuresList.add("Survived");
        featuresList.add("Pclass");
        featuresList.add("SibSp");
        featuresList.add("Parch");
        featuresList.add("Ticket");
        featuresList.add("Cabin");
        featuresList.add("Embarked");
//        
//        
//        // Creating train dataset
        DataSet trainDataSet = decision.createTrainingDataSet(availableCharacterList,availableData,featuresList);
//        
//        // Creating test dataset
        DataSet testDataSet = decision.createTrainingDataSet(availabletestCharacterList,availabletestData,featuresList);
//        
//        
//        // Training the classification model
        Model classificationModel = decision.TrainClassificationModel(trainDataSet, "Survived");
//        
//        // Print summary of the train model
        classificationModel.summary();
//        
//        // Testing the model with test data 
        classificationModel.test(testDataSet, 1);
        
        
//     UNCOMMENT TO PREDICT TWITTER TWEET REWEETS
/*        
        DAO decision = new DAO();
        
        // Extracting Train data from CSV
        
        String trainPath = "data//Train_tweet.csv";
        String seperator = ",";
        DataExtractor rawData = decision.extractData(trainPath,seperator);
        ArrayList<Characteristics> availableCharacterList = rawData.getlistofCharacteristics();
        ArrayList<RowData> availableData  = rawData.getRowData(availableCharacterList);
        
         // Extracting Test data from CSV
        
        String testPath = "data//Test_tweet.csv";
        DataExtractor testData = decision.extractData(testPath,seperator);
        ArrayList<Characteristics> availabletestCharacterList = testData.getlistofCharacteristics();
        ArrayList<RowData> availabletestData  = testData.getRowData(availabletestCharacterList);
        
        
//         // Creating list of features to extract from raw data
        ArrayList<String> featuresList = new ArrayList<>();
        featuresList.add("Number_of_followers");
        featuresList.add("Number_of_friend");
        featuresList.add("Total_of_tweets");
        featuresList.add("Contain_Picture");
        featuresList.add("Contain_hashtag");
        featuresList.add("Listed_count");
        featuresList.add("User_mentions");
        featuresList.add("Contain_url");
        featuresList.add("Retweet");
//        
//        
//        // Creating train dataset
        DataSet trainDataSet = decision.createTrainingDataSet(availableCharacterList,availableData,featuresList);
//        
//        // Creating test dataset
        DataSet testDataSet = decision.createTrainingDataSet(availabletestCharacterList,availabletestData,featuresList);
//        
//        
//        // Training the classification model
        Model classificationModel = decision.TrainClassificationModel(trainDataSet, "Retweet");
//        
//        // Print summary of the train model
        classificationModel.summary();
        
        // Testing the model with test data 
        classificationModel.test(testDataSet, 1);
        
*/        
    }
    
}
