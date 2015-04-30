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
    private Move[] moves = {null, null, null, null};
    private int level;
    private double xp;
    private double health;
    
    public Mon(String n, Species s, int l) {
        name = n;
        species = s;
        Move[] m = Species.generateMoves(s, l);
        for (int i = 0; i < Math.min(moves.length, m.length); i++) {
            moves[i] = m[i];
        }
        stats = Stats.generateStats(s, l);
        health = stats.getHealth();
        level = l;
        xp = 0;
    }
    
    public Mon(String n, Species s, int l, Move[] m) {
        name = n;
        species = s;
        for (int i = 0; i < Math.min(moves.length, m.length); i++) {
            moves[i] = m[i];
        }
        stats = Stats.generateStats(s, l);
        health = stats.getHealth();
        level = l;
        xp = 0;
    }
    
    public Mon(String n, Species s, int l, Move[] m, Stats st) {
        name = n;
        species = s;
        for (int i = 0; i < Math.min(moves.length, m.length); i++) {
            moves[i] = m[i];
        }
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
    
    public Move getMove(int i) {
        return moves[i];
    }

}
