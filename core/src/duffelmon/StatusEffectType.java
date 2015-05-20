/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.HashMap;

/**
 *
 * @author csstudent
 */
public class StatusEffectType {
    
    private static HashMap<String,StatusEffectType> effectTypeMap = new HashMap<String,StatusEffectType>();
    
    private String name;
    
    public StatusEffectType(String n) {
        name = n;
    }
    
    public static StatusEffectType makeEffectType(StatusEffectType s) {
        effectTypeMap.put(s.getName(), s);
        return s;
    }
    
    public static StatusEffectType getEffectType(String s) {
        return effectTypeMap.get(s);
    }
    
    public String getName() {
        return name;
    }
    
}
