/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author csstudent
 */

public class MonDisplay extends GameObject {
    
    private Combatant combatant = null;
    private Mon mon = null;
    private MonDisplay opponent = null;
    private float front; //If front > 0, you're front-facing; if front < 0, back-facing
    //This can be used as a multiplier for anything that flips when the mon is facing the other way
    private Sprite sprite = null;
    private Move currentMove = null;
    private int moveStep = 0;
    private boolean moveFinished = false;
    private int[] customMoveVars = new int[3];
    
    public MonDisplay(Combatant c, boolean f, float x, float y){
        combatant = c;
        if (f) {
            front = 1;
        } else {
            front = -1;
        }
        setX(x);
        setY(y);
        privateSetMon(c.getCurrentMon());
    }
    
    public Combatant getCombatant() {
        return combatant;
    }
    
    public Mon getMon() {
        return mon;
    }
    
    private void privateSetMon(Mon m) {
        mon = m;
        Texture t;
        if (front > 0) {
            t = mon.getFrontTexture();
        } else {
            t = mon.getBackTexture();
        }
        sprite = new Sprite(t);
        sprite.setScale(2);
        if (front > 0) {
            sprite.setFlip(true, false);
        }
        sprite.setOrigin(t.getWidth(), 0);
    }
    
    public void setMon(Mon m) {
        privateSetMon(m);
    }
    
    public MonDisplay getOpponent() {
        return opponent;
    }
    
    public void setOpponent(MonDisplay o) {
        opponent = o;
    }
    
    public int getMoveStep() {
        return moveStep;
    }
    
    public void setMoveStep(int m) {
        moveStep = m;
    }
    
    public boolean getMoveFinished() {
       return moveFinished; 
    }
    
    public void setMoveFinished(boolean m) {
       moveFinished = m;
    }
    
    public int getMoveVar(int pos) {
        return customMoveVars[pos];
    }
    
    public void setMoveVar(int pos, int val) {
        customMoveVars[pos] = val;
    }
    
    public void resetMoveVars() {
        currentMove = null;
        moveStep = 0;
        moveFinished = false;
        customMoveVars = new int[customMoveVars.length];
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        if (sprite != null) {
            sprite.setX(getX());
            sprite.setY(getY());
            sprite.draw(batch);
        }
    }
    
    @Override
    public void doFrame() {
        handleTimers();
        frameActions();
        setX(getX() - front*getXSpeed());
        setY(getY() + getYSpeed());
    }
    
    @Override
    public void triggerTimer(String s) {
        if (s.equals("MoveStep")) {
            currentMove.doMoveStep(this, opponent, moveStep);
        }
    }
}

