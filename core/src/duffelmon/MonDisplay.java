/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.ArrayList;
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
    boolean ranAway = false;
    private Move currentMove = null;
    private MonDisplay moveTarget = null;
    private int moveStep = 0;
    private double damageDealt = 0;
    private boolean hitTarget = false;
    private ArrayList<String> moveMessages = new ArrayList<String>();
    private boolean moveFinished = false;
    private double[] customMoveVars = new double[3];
    
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
    
    public void becomeDuffelBag() {
        combatant.showMonDisplay();
        combatant.hideInfoDisplay();
        Texture t;
        if (front > 0) {
            t = GlobalData.getTexture("duffelBagFront");
        } else {
            t = GlobalData.getTexture("duffelBagBack");
        }
        sprite = new Sprite(t);
        sprite.setScale(2);
        sprite.setOrigin(t.getWidth(), 0);
    }
    
    public boolean getRanAway() {
        return ranAway;
    }
    
    public void runAway() {
        combatant.hideMonDisplay();
        combatant.hideInfoDisplay();
        ranAway = true;
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
    
    public ArrayList<String> readMoveMessages() {
        ArrayList<String> m = moveMessages;
        moveMessages = new ArrayList<String>();
        return m;
    }
    
    public void addMoveMessage(String m) {
        boolean containsMessage = false;
        for(String s : moveMessages) {
            if (s.equals(m)) {
                containsMessage = true;
                break;
            }
        }
        if (!containsMessage) {
            moveMessages.add(m);
        }
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
    
    public void setMoveVar(int pos, double val) {
        customMoveVars[pos] = val;
    }
    
    public void resetMoveVars() {
        currentMove = null;
        moveTarget = null;
        moveStep = 0;
        damageDealt = 0;
        hitTarget = false;
        customMoveVars = new double[customMoveVars.length];
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        if (sprite != null) {
            sprite.setPosition(getX(), getY());
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
            currentMove.doMoveStep(this, moveTarget, moveStep);
        }
    }
}

