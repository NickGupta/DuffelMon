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
    private ArrayList<StatusEffect> statusEffects = new ArrayList<StatusEffect>();
    
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
    
    public String getNickname() {
        return name;
    }
    
    public Species getSpecies() {
        return species;
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
    
    public MonStats getStats() {
        return stats;
    }
    
    public double getStatTotal() {
        return stats.getStatTotal();
    }
    
    public double getAttack() {
        double r = stats.getAttack();
        for(StatusEffect s : statusEffects) {
            double effect = s.attackEffect() - 1;
            double attRatio = getAttitude()/s.getStrength();
            if (effect > 0) {
                effect *= attRatio;
            } else if (effect < 0) {
                effect /= attRatio;
            }
            r *= Math.max(effect + 1, 0);
        }
        return r;
    }
    
    public double getDefense() {
        double r = stats.getDefense();
        for(StatusEffect s : statusEffects) {
            double effect = s.defenseEffect() - 1;
            double attRatio = getAttitude()/s.getStrength();
            if (effect > 0) {
                effect *= attRatio;
            } else if (effect < 0) {
                effect /= attRatio;
            }
            r *= Math.max(effect + 1, 0);
        }
        return r;
    }
    
    public double getSpeed() {
        double r = stats.getSpeed();
        for(StatusEffect s : statusEffects) {
            double effect = s.speedEffect() - 1;
            double attRatio = getAttitude()/s.getStrength();
            if (effect > 0) {
                effect *= attRatio;
            } else if (effect < 0) {
                effect /= attRatio;
            }
            r *= Math.max(effect + 1, 0);
        }
        return r;
    }
    
    public double getAttitude() {
        double r = stats.getAttitude();
        for(StatusEffect s : statusEffects) {
            r *= Math.max(s.attitudeEffect(), 0);
        }
        return r;
    }
    
    public double getAccuracy() {
        double r = 1;
        for(StatusEffect s : statusEffects) {
            double effect = s.accuracyEffect() - 1;
            double attRatio = getAttitude()/s.getStrength();
            if (effect > 0) {
                effect *= attRatio;
            } else if (effect < 0) {
                effect /= attRatio;
            }
            r *= Math.max(effect + 1, 0);
        }
        return r;
    }
    
    public double getEvasion() {
        double r = 1;
        for(StatusEffect s : statusEffects) {
            double effect = s.evasionEffect() - 1;
            double attRatio = getAttitude()/s.getStrength();
            if (effect > 0) {
                effect *= attRatio;
            } else if (effect < 0) {
                effect /= attRatio;
            }
            r *= Math.max(effect + 1, 0);
        }
        return r;
    }
    
    public void addStatusEffect(StatusEffect s) {
        statusEffects.add(s);
    }
    
    public void removeStatusEffect(StatusEffectType s) {
        for(int i = 0; i < statusEffects.size(); i++) {
            if (statusEffects.get(i).getType() == s) {
                statusEffects.remove(i);
                i--;
            }
        }
    }
    
    public void removeStatusEffects() {
        statusEffects = new ArrayList<StatusEffect>();
    }
    
    public void decrementStatusEffects() {
        for(int i = 0; i < statusEffects.size(); i++) {
            statusEffects.get(i).decrementTurnsLeft();
            if (statusEffects.get(i).getTurnsLeft() == 0) {
                statusEffects.remove(i);
                i--;
            }
        }
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
            return null;
        }
        return moves[i].getName();
    }
    
    public int getPowerPoints(int i) {
        return powerPoints[i];
    }
    
    public void decrementPowerPoints(int pos) {
        powerPoints[pos] = Math.max(powerPoints[pos] - 1, 0);
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
    
    public void drawMoveInfo(Batch batch, float alpha, BitmapFont font, Color color, float x, float y, int moveSlot) {
        font.setColor(color);
        font.draw(batch, moves[moveSlot].getName(), x, y);
        font.draw(batch, "Type: " + moves[moveSlot].getType().getName(), x, y - 20);
        font.draw(batch, "PP: " + powerPoints[moveSlot] + " / " + moves[moveSlot].getPowerPoints(), x, y - 40);
    }
}
