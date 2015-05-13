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

    private double health;
    private double attack;
    private double speed;
    private double attitude;
    
    public Stats(double h, double a, double s, double tude) {
        health = h;
        attack = a;
        speed = s;
        attitude = tude;
    }
    
    public Stats getCopy() {
        return new Stats(health, attack, speed, attitude);
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
