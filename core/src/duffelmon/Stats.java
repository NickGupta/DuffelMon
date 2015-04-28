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
public class Stats {
    
    private int level;
    private double xp;
    private double health;
    private double attack;
    private double speed;
    private double attitude;
    
    public Stats(int l, double x, double h, double a, double s, double tude) {
        level = l;
        xp = x;
        health = h;
        attack = a;
        speed = s;
        attitude = tude;
    }
    
    public static Stats generateStats(Species s, int l) {
        return new Stats(1, 0, 50, 50, 50, 50);
    }
    
    public int getLevel() {
        return level;
    }
    
    public double getXP() {
        return xp;
    }
    
    public double getHealth() {
        return health;
    }
    
    public double getAttack() {
        return attack;
    }
    
    public double getSpeed() {
        return speed;
    }
    
    public double getAttitude() {
        return attitude;
    }
}