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
public class Player {
    
    private static ArrayList<Mon> mons = new ArrayList<Mon>();
    private static int money = 0;
    
    private static ArrayList<Mon> getMonList() {
        return mons;
    }
    
    /**
     * Attempts to add a mon to the player's party.
     * @param m Mon to be added
     * @return Whether the addition was successful
     */
    private static boolean addMon(Mon m) {
        if (mons.size() < 3) {
            mons.add(m);
            return true;
        }
        return false;
    }
}
