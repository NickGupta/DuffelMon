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
public class Player extends Trainer {
    
    private Mon[] mons;
    private Item[] items;
    private int money;
    
    public Player(String n) {
        super(n, "Trainer", 0, null);
        money = 0;
    }
    
    public int getMoney() {
        return money;
    }
    
    public void giveMoney(int m) {
        money += m;
    }
    
    public void takeMoney(int m) {
        money = Math.max(money - m, 0);
    }
}
