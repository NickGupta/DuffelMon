/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;
import java.util.*;

/**
 *
 * @author Andrew
 */
public class Battle {
    
    private Combatant combatant1;
    private Combatant combatant2;
    
    public Battle(Combatant c1, Combatant c2) {
        combatant1 = c1;
        combatant2 = c2;
    }
    
    public Combatant getCombatant1() {
        return combatant1;
    }
    
    public Combatant getCombatant2() {
        return combatant2;
    }
}
