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
    
    private ArrayList<Mon> mons;
    private int currentMon = 0;
    
    public Combatant(Mon m) {
        mons = new ArrayList<Mon>();
        mons.add(m);
    }
    
    public Combatant(ArrayList<Mon> m) {
        if (m.size() > 0) {
            mons = m;
        } else {
            mons = new ArrayList<Mon>();
            mons.add(Mon.makeDefaultMon());
        }
    }
    
    public ArrayList<Mon> getMonList() {
        return mons;
    }
    
    public Mon getCurrentMon() {
        return mons.get(currentMon);
    }
    
}
