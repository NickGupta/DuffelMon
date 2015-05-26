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
public class Trainer {
    
    private Mon[] mons;
    private Item[] items;
    private int moneyToGive;
    
    public Trainer(int m) {
        mons = new Mon[3];
        items = new Item[5];
        moneyToGive = m;
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
    
    public int getMoneyToGive() {
        return moneyToGive;
    }
    
    /**
     * Attempts to add a mon to the trainer's party.
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
    
    /**
     * Attempts to add an item to the trainer's item list.
     * @param it Item to be added
     * @return Whether the addition was successful
     */
    public boolean addItem(Item it) {
        for(int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = it;
                return true;
            }
        }
        return false;
    }
    
}
