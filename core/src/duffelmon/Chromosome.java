/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

/**
 *
 * @author Andrew
 */
public class Chromosome {
    
    private double attack;
    private double defense;
    private double speed;
    private double attitude;
    
    public Chromosome(double a, double d, double s, double tude) {
        attack = a;
        defense = d;
        speed = s;
        attitude = tude;
    }
    
    public Chromosome() {
        attack = Chromosome.getRandomizedGene();
        defense = Chromosome.getRandomizedGene();
        speed = Chromosome.getRandomizedGene();
        attitude = Chromosome.getRandomizedGene();
    }
    
    public static double getRandomizedGene() {
        return Math.random() * 0.2 + 0.9;
    }
    
    public Chromosome getCopy() {
        return new Chromosome(attack, defense, speed, attitude);
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
