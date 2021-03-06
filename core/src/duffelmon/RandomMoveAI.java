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
            setOutput("MOVE0");
        } else {
            int random = (int)(positions.size()*Math.random());
            setOutput("MOVE"+positions.get(random));
        }
    }
    
    @Override
    public void chooseMon(Combatant self, Combatant opponent) {
        String output = null;
        int i = self.getMonsPos();
        while (output == null) {
            i++;
            if (i > self.getMons().length) {
                i = 0;
            }
            if (self.getMon(i).getHealth() > 0) {
                output = "MONS" + i;
            }
        }
        setOutput(output);
    }
    
}
