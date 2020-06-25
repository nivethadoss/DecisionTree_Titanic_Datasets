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
public class NumericalValue extends TrainingValue{
    
    Float value;
    
    public NumericalValue(String val){
        
        this.value = Float.parseFloat(val);
    }

    @Override
    public String toString() {
        return "NumericalValue{" + "value=" + value + '}';
    }
    
    
    
    @Override
    public boolean equals(Object obj) {
        final NumericalValue other = (NumericalValue) obj;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.value);
        return hash;
    }
    
}
