/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.ArrayList;

/**
 *
 * @author csstudent
 */
public class Combatant {
    
    private Mon[] mons;
    private int currentMon = 0;
    private BattleAI ai = null;
    
    public Combatant(Mon m, BattleAI a) {
        mons = new Mon[1];
        mons[0] = m;
        ai = a;
    }
    
    public Combatant(Mon[] m, BattleAI a) {
        mons = m;
        ai = a;
    }
    
    public Mon[] getMons() {
        return mons;
    }
    
    public Mon getCurrentMon() {
        return mons[currentMon];
    }
    
    public boolean isPlayer() {
        return ai == null;
    }
    
    public BattleAI getAI() {
        return ai;
    }
}
