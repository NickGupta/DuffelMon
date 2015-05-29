/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

/**
 *
 * @author Andrew
 */
public abstract class BattleAI {
    
    private String output = null;
    
    public BattleAI() {}
    
    public String getOutput() {
        return output;
    }
    
    public void setOutput(String o) {
        output = o;
    }
    
    public abstract void chooseAction(Combatant self, Combatant opponent);
    
    public abstract void chooseMon(Combatant self, Combatant opponent);
    
}
