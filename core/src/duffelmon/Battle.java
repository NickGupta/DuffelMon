/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 *
 * @author Andrew
 */
public class Battle extends GameObject {
    
    private static Battle battle;
    
    private Combatant combatant1;
    private Combatant combatant2;
    private MonDisplay m1;
    private MonDisplay m2;
    private MonInfoDisplay i1;
    private MonInfoDisplay i2;
    private BattleMenu menu;
    
    private Battle(Combatant c1, Combatant c2) {
        combatant1 = c1;
        combatant2 = c2;
        m1 = new MonDisplay(combatant1.getCurrentMon(), true, 200, 100);
        m2 = new MonDisplay(combatant2.getCurrentMon(), false, 300, 400);
        i1 = new MonInfoDisplay(combatant1.getCurrentMon(), 400, 100);
        i2 = new MonInfoDisplay(combatant2.getCurrentMon(), 100, 400);
        menu = new BattleMenu();
    }
    
    public static Battle startBattle(Combatant c1, Combatant c2) {
        battle = new Battle(c1, c2);
        GameObject.makeIndependent(battle);
        return battle;
    }
    
    public static Battle getBattle() {
        return battle;
    }
    
    public Combatant getCombatant1() {
        return combatant1;
    }
    
    public Combatant getCombatant2() {
        return combatant2;
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        m1.draw(batch, alpha);
        m2.draw(batch, alpha);
        i1.draw(batch, alpha);
        i2.draw(batch, alpha);
        menu.draw(batch, alpha);
    }
    
    @Override
    public void doFrame() {
        m1.doFrame();
        m2.doFrame();
        menu.doFrame();
    }
}
