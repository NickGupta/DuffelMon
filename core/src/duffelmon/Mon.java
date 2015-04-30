/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.TreeMap;

/**
 *
 * @author csstudent
 */
public class Mon {
    
    private static TreeMap<Integer,Move> moveMap = new TreeMap<Integer,Move>();
    
    private String name;
    private Species species;
    private Stats stats;
    private int level;
    private double xp;
    private double health;
    private Attack attack1;
    private Attack attack2;
    private Attack attack3;
    private Attack attack4;
    
    public Mon(String n, Species s, int l) {
        name = n;
        species = s;
        stats = Stats.generateStats(s, l);
        health = stats.getHealth();
        level = l;
        xp = 0;
    }
    
    public Mon(String n, Species s, int l, Stats st) {
        name = n;
        species = s;
        stats = st;
        health = stats.getHealth();
        level = l;
        xp = 0;
    }
    
    public static Mon makeDefaultMon() {
        return new Mon(null, Species.getDefaultSpecies(), 1);
    }
    
    public String getName() {
        if (name == null) {
            return species.getName();
        }
        return name;
    }
    
    public double getHealth(){
        return health;
    }
    
    public double getMaxHealth() {
        return stats.getHealth();
    }
    
    public int getLevel() {
        return level;
    }
    
    public double getXP() {
        return xp;
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
    
    public Attack getAttack1(){
        return attack1;
    }
    
    public Attack getAttack2(){
        return attack2;
    }
    
    public Attack getAttack3(){
        return attack3;
    }
    
    public Attack getAttack4(){
        return attack4;
    }
}
