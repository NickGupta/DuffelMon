/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author csstudent
 */
public class Mon {
    
    private String name;
    private Species species;
    private MonSprite sprite;
    private Stats stats;
    private Move[] moves = new Move[4];
    private int[] powerPoints = new int[4];
    private int level;
    private double xp;
    private double health;
    
    private void initialize(String n, Species s, int l, Move[] m, Stats st) {
        name = n;
        species = s;
        for (int i = 0; i < Math.min(moves.length, m.length); i++) {
            setMove(m[i], i);
        }
        stats = st;
        health = stats.getHealth();
        level = l;
        xp = 0;
    }
    
    public Mon(String n, Species s, int l) {
        initialize(n, s, l, Species.generateMoves(s, l), Stats.generateStats(s, l));
    }
    
    public Mon(String n, Species s, int l, Move[] m) {
        initialize(n, s, l, m, Stats.generateStats(s, l));
    }
    
    public Mon(String n, Species s, int l, Move[] m, Stats st) {
        initialize(n, s, l, m, st);
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
    
    public MonSprite getMonSprite() {
        return sprite;
    }
    
    public Sprite getFrontSprite() {
        return sprite.getFrontSprite();
    }
    
    public Sprite getBackSprite() {
        return sprite.getBackSprite();
    }
    
    public Move getMove(int i) {
        return moves[i];
    }
    
    /**
     * Inserts a move into a particular move slot, regardless of what was in there before.
     * @param m Move to be inserted
     * @param i Move slot to insert it at
     */
    public void setMove(Move m, int i) {
        moves[i] = m;
        if (moves[i] != null) {
            powerPoints[i] = moves[i].getPowerPoints();
        }
    }
    
    /**
     * Attempts to naturally learn a move.
     * @param m Move to be learned
     * @return Whether the move was successfully learned
     */
    public boolean learnMove(Move m) {
        for(int i = 0; i < moves.length; i++) {
            if (moves[i] == null) {
                setMove(m, i);
                return true;
            }
        }
        return false;
    }
}
