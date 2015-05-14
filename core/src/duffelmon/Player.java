/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

/**
 *
 * @author csstudent
 */
public class Player {
    
    private static Mon[] mons = new Mon[3];
    private static int money = 0;
    
    public static Mon[] getMons() {
        return mons;
    }
    
    public static Mon getMon(int i) {
        return mons[i];
    }
    
    public static int getMoney() {
        return money;
    }
    
    /**
     * Attempts to add a mon to the player's party.
     * @param m Mon to be added
     * @return Whether the addition was successful
     */
    public static boolean addMon(Mon m) {
        for(int i = 0; i < mons.length; i++) {
            if (mons[i] == null) {
                mons[i] = m;
                return true;
            }
        }
        return false;
    }
}
