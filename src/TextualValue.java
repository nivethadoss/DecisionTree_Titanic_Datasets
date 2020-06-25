/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontree;

import java.util.Objects;

/**
 *
 * @author Nivetha
 */
public class TextualValue extends TrainingValue{
    
      String value;
    
    public TextualValue(String val){
        
        this.value = val;
    }

    @Override
    public String toString() {
        return "TextualValue{" + "value=" + value + '}';
    }
    
     @Override
    public boolean equals(Object obj) {
        boolean check = false;
        final TextualValue other = (TextualValue) obj;
        if(this.value.equals(other.value)){
            check = true;
        }
        return check;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.value);
        return hash;
    }
    
}
