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
    
    private Mon[] monsInStorage = new Mon[64];
    private Item[] itemsInStorage = new Item[64];
    private int money;
    
    public Player(String n) {
        super(n, "Trainer", 0, null);
        money = 0;
    }
    
    public int getMoney() {
        return money;
    }
    
    public Mon[] getStorageMons() {
        return monsInStorage;
    }
    
    public Mon getStorageMon(int pos) {
        return monsInStorage[pos];
    }
    
    public Item[] getStorageItems() {
        return itemsInStorage;
    }
    
    public Item getStorageItem(int pos) {
        return itemsInStorage[pos];
    }
    
    public int giveMoney(int m) {
        int given = Math.min(m, 1000000 - money);
        money += given;
        return given;
    }
    
    public int takeMoney(int m) {
        int taken = Math.max(m, -money);
        money -= taken;
        return taken;
    }
    
    public Mon takeStorageMon(int pos) {
        Mon m = monsInStorage[pos];
        monsInStorage[pos] = null;
        return m;
    }
    
    public boolean addStorageMon(Mon m) {
        for(int i = 0; i < monsInStorage.length; i++) {
            if (monsInStorage[i] == null) {
                monsInStorage[i] = m;
                return true;
            }
        }
        return false;
    }
    
    public Item takeStorageItem(int pos) {
        Item i = itemsInStorage[pos];
        itemsInStorage[pos] = null;
        return i;
    }
    
    public boolean addStorageItem(Item it) {
        for(int i = 0; i < itemsInStorage.length; i++) {
            if (itemsInStorage[i] == null) {
                itemsInStorage[i] = it;
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int getMoneyToGive() {
        return money/2;
    }
    
}
