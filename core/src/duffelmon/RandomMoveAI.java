/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class RandomMoveAI extends BattleAI {
    
    public RandomMoveAI() {}
    
    @Override
    public void chooseAction(Combatant self, Combatant opponent) {
        ArrayList<Integer> positions = self.getCurrentMon().getUsableMoves();
        if (positions.isEmpty()) {
            setOutput("MOVE1");
        } else {
            int random = (int)(positions.size()*Math.random()) + 1;
            setOutput("MOVE"+positions.get(random));
        }
    }
}
