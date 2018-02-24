/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.View.Bags;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import jdk.nashorn.api.scripting.JSObject;

/**
 *
 * @author halse
 */
public class ViewBag{
    
    private final HashMap<String,Object> bindings = new HashMap<String,Object>();
    
    public ViewBag(){}
    
    /**
     * Set a member of this bag
     * @param name
     * @param value 
     */
    public void set(String name, Object value){
        this.bindings.put(name, value);
    }
    
    /**
     * Remove a member from this bag
     * @param name 
     */
    public void remove(String name){
        this.bindings.remove(name);
    }
    
    /**
     * Get a member's value from this bag
     * @param name
     * @return 
     */
    public Object get(String name){
        if(this.bindings.containsKey(name))
            return this.bindings.get(name); 
        return null;
    }
    
    /**
     * Clear all entries in this bag
     */
    public void clear(){
        this.bindings.clear();
    }
    
}
