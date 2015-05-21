/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

/**
 *
 * @author csstudent
 */
public class StatusEffect {
    
    private StatusEffectType type;
    private double strength;
    private int turnsLeft;
    
    public StatusEffect(StatusEffectType t, double s, int tu) {
        type = t;
        strength = s;
        turnsLeft = tu;
    }
    
    public StatusEffectType getType() {
        return type;
    }
    
    public double attackEffect() {
        return type.attackEffect();
    }
    
    public double defenseEffect() {
        return type.defenseEffect();
    }
    
    public double speedEffect() {
        return type.speedEffect();
    }
    
    public double attitudeEffect() {
        return type.attitudeEffect();
    }
    
    public double accuracyEffect() {
        return type.accuracyEffect();
    }
    
    public double evasionEffect() {
        return type.evasionEffect();
    }
    
    public double getStrength() {
        return strength;
    }
    
    public int getTurnsLeft() {
        return turnsLeft;
    }
    
    public void decrementTurnsLeft() {
        if (turnsLeft > 0) {
            turnsLeft--;
        }
    }
    
    public void endEffect() {
        turnsLeft = 0;
    }
    
}
