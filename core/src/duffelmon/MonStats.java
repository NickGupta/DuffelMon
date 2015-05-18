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
public class MonStats {
    
    private Species species;
    private Chromosome chromosome1;
    private Chromosome chromosome2;
    private double attack;
    private double defense;
    private double speed;
    private double attitude;
    private int level;
    
    public MonStats(Species s, Chromosome c1, Chromosome c2, double a, double d, double spd, double tude) {
        species = s;
        chromosome1 = c1;
        chromosome2 = c2;
        attack = a;
        defense = d;
        speed = spd;
        attitude = tude;
        level = MonStats.getLevelFromStatTotal(s, attack + defense + speed + attitude);
    }
    
    public MonStats(Species s, double a, double d, double spd, double tude) {
        this(s, new Chromosome(), new Chromosome(), a, d, spd, tude);
    }
    
    public MonStats(Species s, Chromosome c1, Chromosome c2, int l) {
        this(s, c1, c2, MonStats.getLevel1Attack(s, c1, c2),
                        MonStats.getLevel1Defense(s, c1, c2),
                        MonStats.getLevel1Speed(s, c1, c2),
                        MonStats.getLevel1Attitude(s, c1, c2));
        while (level < l) {
            privateGainExperience(this.privateGetCopy(), 0.001);
        }
    }
    
    public MonStats(Species s, int l) {
        this(s, new Chromosome(), new Chromosome(), l);
    }
    
    public static int getLevelFromStatTotal(Species s, double t) {
        return (int)Math.sqrt((Math.max(t - 25, 0))) + 1;
    }
    
    public static double getLevel1Attack(Species s, Chromosome c1, Chromosome c2) {
        return s.getAttack()*(25/s.getStatTotal())*c1.getAttack()*c2.getAttack();
    }
    
    public static double getLevel1Defense(Species s, Chromosome c1, Chromosome c2) {
        return s.getDefense()*(25/s.getStatTotal())*c1.getDefense()*c2.getDefense();
    }
    
    public static double getLevel1Speed(Species s, Chromosome c1, Chromosome c2) {
        return s.getSpeed()*(25/s.getStatTotal())*c1.getSpeed()*c2.getSpeed();
    }
    
    public static double getLevel1Attitude(Species s, Chromosome c1, Chromosome c2) {
        return s.getAttitude()*(25/s.getStatTotal())*c1.getAttitude()*c2.getAttitude();
    }
    
    private MonStats privateGetCopy() {
        return new MonStats(species, chromosome1.getCopy(), chromosome2.getCopy(), attack, defense, speed, attitude);
    }
    
    public MonStats getCopy() {
        return privateGetCopy();
    }
    
    public Species getSpecies() {
        return species;
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
    
    public int getLevel() {
        return level;
    }
    
    private void privateGainExperience(MonStats m, double gainFactor) {
        double speciesStatRatio = getStatTotal()/species.getStatTotal();
        attack += gainFactor*(species.getAttack()*speciesStatRatio + m.getAttack()*chromosome1.getAttack()*chromosome2.getAttack());
        defense += gainFactor*(species.getDefense()*speciesStatRatio + m.getDefense()*chromosome1.getDefense()*chromosome2.getDefense());
        speed += gainFactor*(species.getSpeed()*speciesStatRatio + m.getSpeed()*chromosome1.getSpeed()*chromosome2.getSpeed());
        attitude += gainFactor*(species.getAttitude()*speciesStatRatio + m.getAttitude()*chromosome1.getAttitude()*chromosome2.getAttitude());
        level = MonStats.getLevelFromStatTotal(species, getStatTotal());
    }
    
    private void privateGainExperience(MonStats m) {
        privateGainExperience(m, 0.02);
    }
    
    public void gainExperience(MonStats m) {
        privateGainExperience(m);
    }
}
