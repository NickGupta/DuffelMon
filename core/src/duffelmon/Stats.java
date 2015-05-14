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
    
    private double attack;
    private double defense;
    private double speed;
    private double attitude;
    private int level;
    private double xp;
    
    public Stats(double a, double d, double s, double tude, int l, double x) {
        attack = a;
        defense = d;
        speed = s;
        attitude = tude;
        level = l;
        xp = x;
    }
    
    public Stats(double a, double d, double s, double tude) {
        this(a, d, s, tude, 1, 0);
    }
    
    public Stats getCopy() {
        return new Stats(attack, defense, speed, attitude, level, xp);
    }
    
    public double getAttack() {
        return attack;
    }
    
    public double getDefense() {
        return defense;
    }
    
    public double getSpeed() {
        return speed;
    }
    
    public double getAttitude() {
        return attitude;
    }
    
    public int getLevel() {
        return level;
    }
    
    public double getXP() {
        return xp;
    }
}
