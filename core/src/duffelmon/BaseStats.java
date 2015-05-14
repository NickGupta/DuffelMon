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
public class BaseStats {
    
    private double attack;
    private double defense;
    private double speed;
    private double attitude;
    
    public BaseStats(double a, double d, double s, double tude) {
        attack = a;
        defense = d;
        speed = s;
        attitude = tude;
    }
    
    public Stats getInitialStats() {
        return new Stats(attack/10, defense/10, speed/10, attitude/10);
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
    
}
