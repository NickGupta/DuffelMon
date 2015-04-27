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
public class Mon {
    
    private String name;
    private Species species;
    private Stats stats;
    private double health;
    
    public Mon(String n, Species s, Stats st) {
        name = n;
        species = s;
        stats = st;
        health = st.getHealth();
    }
    
    public double getHealth(){
        return health;
    }
    
    public double getMaxHealth() {
        return stats.getHealth();
    }
    
    public double getAttack() {
        return stats.getAttack();
    }
    
    public double getSpeed() {
        return stats.getSpeed();
    }
    
    public double getAttitude() {
        return stats.getAttitude();
    }
}
