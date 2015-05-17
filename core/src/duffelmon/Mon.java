/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 *
 * @author csstudent
 */
public class Mon {
    
    private String name;
    private Species species;
    private MonTextures textures;
    private MonStats stats;
    private Move[] moves = new Move[4];
    private int[] powerPoints = new int[4];
    private double health;
    
    public Mon(String n, Species s, Move[] m, MonStats st) {
        name = n;
        species = s;
        textures = s.getTextures();
        for (int i = 0; i < Math.min(moves.length, m.length); i++) {
            privateSetMove(m[i], i);
        }
        stats = st;
        health = 100;
    }
    
    public Mon(String n, Species s, int l) {
        this(n, s, s.generateMoves(l), new MonStats(s, l));
    }
    
    public Mon(String n, Species s, int l, Move[] m) {
        this(n, s, m, new MonStats(s, l));
    }
    
    public String getName() {
        if (name == null) {
            return species.getName();
        }
        return name;
    }
    
    public Type[] getTypes() {
        return species.getTypes();
    }
    
    public double getHealth(){
        return health;
    }
    
    public double getMaxHealth() {
        return 100;
    }
    
    public int getLevel() {
        return stats.getLevel();
    }
    
    public double getStatTotal() {
        return stats.getStatTotal();
    }
    
    public double getAttack() {
        return stats.getAttack();
    }
    
    public double getDefense() {
        return stats.getDefense();
    }
    
    public double getSpeed() {
        return stats.getSpeed();
    }
    
    public double getAttitude() {
        return stats.getAttitude();
    }
    
    public double getAccuracy() {
        double acc = 1;
        return acc;
    }
    
    public double getEvasion() {
        double ev = 1;
        return ev;
    }
    
    public MonTextures getMonTextures() {
        return textures;
    }
    
    public Texture getFrontTexture() {
        return textures.getFrontTexture();
    }
    
    public Texture getBackTexture() {
        return textures.getBackTexture();
    }
    
    public Move getMove(int i) {
        return moves[i];
    }
    
    public String getMoveName(int i) {
        if (moves[i] == null) {
            return "---";
        }
        return moves[i].getName();
    }
    
    public int getPowerPoints(int i) {
        return powerPoints[i];
    }
    
    public void decreaseHealth(double d){
        health = Math.max(health - d, 0);
    }
    
    public void increaseHealth(double h) {
        health = Math.min(health + h, getMaxHealth());
    }
    
    /**
     * Returns an ArrayList containing all of the positions in this mon's list
     * of moves that contain currently usable moves.
     * @return An ArrayList of positions of usable moves
     */
    public ArrayList<Integer> getUsableMoves() {
        ArrayList<Integer> positions = new ArrayList<Integer>();
        for(int i = 0; i < moves.length; i++) {
            if (moves[i] != null && powerPoints[i] != 0) {
                positions.add(i);
            }
        }
        return positions;
    }
    
    private void privateSetMove(Move m, int i) {
        moves[i] = m;
        if (moves[i] != null) {
            powerPoints[i] = moves[i].getPowerPoints();
        }
    }
    
    /**
     * Inserts a move into a particular move slot, regardless of what was in there before.
     * @param m Move to be inserted
     * @param i Move slot to insert it at
     */
    public void setMove(Move m, int i) {
        privateSetMove(m, i);
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
    
    public void drawInfo(Batch batch, float alpha, BitmapFont font, Color color, float x, float y) {
        int healthToDraw = (int)Math.ceil(getHealth());
        font.setColor(color);
        font.draw(batch, getName() + " lv. " + getLevel(), x, y);
        font.draw(batch, "Health: " + healthToDraw + "%", x, y - 20);
    }
}
