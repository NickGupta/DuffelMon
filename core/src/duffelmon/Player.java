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
    
    private Mon[] mons;
    private Item[] items;
    private int money;
    
    public Player() {
        mons = new Mon[3];
        items = new Item[5];
        money = 0;
    }
    
    public Mon[] getMons() {
        return mons;
    }
    
    public Mon getMon(int i) {
        return mons[i];
    }
    
    public Item[] getItems() {
        return items;
    }
    
    public Item getItem(int i) {
        return items[i];
    }
    
    public int getMoney() {
        return money;
    }
    
    /**
     * Attempts to add a mon to the player's party.
     * @param m Mon to be added
     * @return Whether the addition was successful
     */
    public boolean addMon(Mon m) {
        for(int i = 0; i < mons.length; i++) {
            if (mons[i] == null) {
                mons[i] = m;
                return true;
            }
        }
        return false;
    }
}
