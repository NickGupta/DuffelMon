/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.*;

/**
 *
 * @author csstudent
 */
public class Combatant {
    
    private ArrayList<Mon> mons;
    
    public Combatant(Mon m) {
        mons = new ArrayList<Mon>();
        mons.add(m);
    }
    
    public Combatant(ArrayList<Mon> m) {
        mons = m;
    }
    
    public ArrayList<Mon> getMonList() {
        return mons;
    }
    
}
