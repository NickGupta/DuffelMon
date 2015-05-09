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
public class RandomMoveAI extends BattleAI {
    
    public RandomMoveAI() {}
    
    @Override
    public void chooseAction(Combatant self, Combatant opponent) {
        int random = (int)(4*Math.random()) + 1;
        setOutput("MOVE"+random);
    }
}
