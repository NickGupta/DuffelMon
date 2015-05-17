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
    
    private Species species = null;
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
    
    public BaseStats getCopy() {
        BaseStats b = new BaseStats(attack, defense, speed, attitude);
        b.setSpecies(species);
        return b;
    }
    
    public Species getSpecies() {
        return species;
    }
    
    public void setSpecies(Species s) {
        species = s;
    }
    
    public double getStatTotal() {
        return attack + defense + speed + attitude;
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
