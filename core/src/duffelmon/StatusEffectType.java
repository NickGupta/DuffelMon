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
    
    public String getApplicationMessage(MonDisplay tDisplay) {
        return null;
    }
    
    public double attackEffect() {
        return 1;
    }
    
    public double defenseEffect() {
        return 1;
    }
    
    public double speedEffect() {
        return 1;
    }
    
    public double attitudeEffect() {
        return 1;
    }
    
    public double accuracyEffect() {
        return 1;
    }
    
    public double evasionEffect() {
        return 1;
    }
    
    /**
     * Actions to be performed when a mon afflicted with this status effect
     * begins its turn.
     * @param effect StatusEffect object afflicting the mon
     * @param mDisplay MonDusplay object representing the mon
     * @return Whether the mon should be allowed to continue with its turn
     */
    public boolean beginTurnEffect(StatusEffect effect, MonDisplay mDisplay) {
        return true;
    }
    
    /**
     * Actions to be performed when a mon afflicted with this status effect
     * ends its turn.
     * @param effect StatusEffect object afflicting the mon
     * @param mDisplay MonDusplay object representing the mon
     */
    public void endTurnEffect(StatusEffect effect, MonDisplay mDisplay) {}
    
}
