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
public class Battle {
    
    private static Battle battle;
    
    private Combatant combatant1;
    private Combatant combatant2;
    private HealthDisplay h1;
    private HealthDisplay h2;
    
    private Battle(Combatant c1, Combatant c2) {
        combatant1 = c1;
        combatant2 = c2;
        h1 = new HealthDisplay(combatant1.getCurrentMon(), 400, 100);
        GlobalData.getStage().addActor(h1);
        h2 = new HealthDisplay(combatant2.getCurrentMon(), 100, 400);
        GlobalData.getStage().addActor(h2);
    }
    
    public static Battle startBattle(Combatant c1, Combatant c2) {
        battle = new Battle(c1, c2);
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
}
