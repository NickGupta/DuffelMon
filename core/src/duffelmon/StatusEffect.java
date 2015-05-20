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
    private int turnsLeft;
    
    public StatusEffect(StatusEffectType t, int tu) {
        type = t;
        turnsLeft = tu;
    }
    
    public StatusEffectType getType() {
        return type;
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
