/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Nivetha
 */
public class DataExtractor {

    private final String filepath;
    private final String separator;
    private BufferedReader br; 
    RowData row;
    
    
    public DataExtractor(String path, String separator) throws FileNotFoundException, IOException{
        this.filepath = path;
        this.separator = separator;
        
    
}
     // This method helps to extract the header name and create Characteristic objects and identify the datatype
    public ArrayList<Characteristics> getlistofCharacteristics() throws IOException{
        ArrayList<Characteristics> characteristiclist = new  ArrayList<>() ;
        this.br = new BufferedReader(new FileReader(this.filepath));
        String line = br.readLine();
        String[] header = line.split(this.separator);
        for (int i=0;i<=header.length-1;i++){
            Characteristics ch = new Characteristics(i+1,header[i]);
            characteristiclist.add(ch);                 
        }
        
        int count = 1;
         // The last column of reader doesnt identify the null values. So a array of size equi to Header name is created to avoid it
        while ((line = br.readLine()) != null) {
                int length = characteristiclist.size();
                String[] var = line.split(this.separator);
                ArrayList<String> nullArray = new ArrayList<>();
                for (int i=0; i<=length-1;i++){
                    nullArray.add("x");
                }
                for (int i=0; i<=var.length-1;i++){
                    if (!"".equals(var[i]) || !var[i].isEmpty()){
                            nullArray.set(i, var[i]);
                    }
                    
                    String value = nullArray.get(i);
                    Characteristics chars = characteristiclist.get(i);                         
                    if (!"".equals(value) && !value.isEmpty() && !"x".equals(value) && !"x".equals(value)){
                                try{
                                    int counter = chars.isNumerical;
                                    Float val = Float.parseFloat(value);
                                    chars.isNumerical = counter+ 1;
                                }
                                catch(NumberFormatException e) {
                                    int counter = chars.isCategorical;
                                    String val = value;
                                    chars.isCategorical = counter + 1;
                                } 
                    }   
                    else{     
                        chars.setNullId(count);
                    }
                }                 
            count = count + 1;
           }       
         
        // Identification of datatype
        characteristiclist.forEach((ch) -> {
            if ((ch.isNumerical != 0 && ch.isCategorical != 0) || ch.isCategorical !=0){
                ch.datatype = "Categorical";
            }
            else{
                ch.datatype = "Numerical";
            }
        });
        
         br.close();
      return characteristiclist; 
     }
     
     
  // method to extract the data row by row to create RowData object and store it in List
public ArrayList<RowData> getRowData(ArrayList<Characteristics> characteristiclist) throws IOException{
        ArrayList<RowData> rowData = new ArrayList<>();
        this.br = new BufferedReader(new FileReader(this.filepath));
        String line = br.readLine();           
        // The last column of reader doesnt identify the null values. So a array of size equi to Header name is created to avoid it
        int count = 1;
        while ((line = br.readLine()) != null) {
                int length = characteristiclist.size();
                String[] var = line.split(this.separator);
                row = new RowData(count);
                ArrayList<String> nullArray = new ArrayList<>();
                for (int i=0; i<=length-1;i++){
                    nullArray.add("x");
                }
                for (int i=0; i<=var.length-1;i++){
                    if (!"".equals(var[i]) && !var[i].isEmpty()){
                            nullArray.set(i, var[i]);
                    }
                    
                    String value = nullArray.get(i);                
                    Characteristics chars = characteristiclist.get(i);
                  if (!"".equals(value) && !value.isEmpty() && !"x".equals(value) && !"x".equals(value)){
                    row.setData(chars, value);
                   }           
                }
            rowData.add(row);
            count = count + 1;
        }
        br.close();
        return rowData;
       }
}
