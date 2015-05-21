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
    private float front; //If front > 0, you're front-facing; if front < 0, back-facing
    //This can be used as a multiplier for anything that flips when the mon is facing the other way
    private Sprite sprite = null;
    boolean visible = true;
    private Move currentMove = null;
    private MonDisplay moveTarget = null;
    private int moveStep = 0;
    private double damageDealt = 0;
    private boolean hitTarget = false;
    private String moveMessage = null;
    private boolean moveFinished = false;
    private double[] customMoveVars = new double[3];
    private enum States {
        ALIVE, FAINTING, FAINTED
    }
    private States state = States.ALIVE;
    
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
    
    public Move getCurrentMove() {
        return currentMove;
    }
    
    public void setCurrentMove(Move c) {
        currentMove = c;
    }
    
    public MonDisplay getMoveTarget() {
        return moveTarget;
    }
    
    public void setMoveTarget(MonDisplay m) {
        moveTarget = m;
    }
    
    public int getMoveStep() {
        return moveStep;
    }
    
    public void setMoveStep(int m) {
        moveStep = m;
    }
    
    public double getDamageDealt() {
        return damageDealt;
    }
    
    public boolean getHitTarget() {
        return hitTarget;
    }
    
    public void registerHit(double d) {
        damageDealt += d;
        hitTarget = true;
    }
    
    public boolean getMoveFinished() {
       return moveFinished; 
    }
    
    public void setMoveFinished(boolean m) {
       moveFinished = m;
    }
    
    public double getMoveVar(int pos) {
        return customMoveVars[pos];
    }
    
    public void setMoveVar(int pos, int val) {
        customMoveVars[pos] = val;
    }
    
    public String readMoveMessage() {
        String m = moveMessage;
        moveMessage = null;
        return m;
    }
    
    public void resetMoveVars() {
        currentMove = null;
        moveTarget = null;
        moveStep = 0;
        damageDealt = 0;
        hitTarget = false;
        customMoveVars = new double[customMoveVars.length];
    }
    
    public void faint() {
        state = States.FAINTING;
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        if (visible && sprite != null) {
            sprite.setPosition(getX(), getY());
            sprite.draw(batch);
        }
    }
    
    @Override
    public void frameActions() {
        if (state == States.FAINTING) {
            visible = false;
            state = States.FAINTED;
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
            currentMove.doMoveStep(this, moveTarget, moveStep);
        }
    }
}

