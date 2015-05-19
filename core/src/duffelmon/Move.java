/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;
import java.util.HashMap;

/**
 *
 * @author csstudent
 */
public class Move {
    private static HashMap<String,Move> moveMap = new HashMap<String,Move>();
    
    private String name;
    private Type type;
    private boolean targets;
    private double damage;
    private double accuracy;
    private int powerPoints;
    private double priority;
    
    
    public Move(String n, Type t, boolean ta, double d, double a, int p, double pr) {
        name = n;
        type = t;
        targets = ta;
        damage = d;
        accuracy = a;
        powerPoints = p;
        priority = pr;
    }
    
    public static Move makeMove(Move m) {
        moveMap.put(m.getName(), m);
        return m;
    }
    
    public static Move getMove(String s) {
        return moveMap.get(s);
    }
    
    public String getName() {
        return name;
    }
    
    public Type getType() {
        return type;
    }
    
    public boolean targetsOpponent() {
        return targets;
    }
    
    public double getDamage() {
        return damage;
    }
    
    public double getAccuracy() {
        return accuracy;
    }
    
    public int getPowerPoints() {
        return powerPoints;
    }
    
    
    
    public double getPriority() {
        return priority;
    }
    
    public void useInBattle(MonDisplay uDisplay, MonDisplay tDisplay) {
        uDisplay.setCurrentMove(this);
        uDisplay.setMoveTarget(tDisplay);
        doMoveStep(uDisplay, tDisplay, 0);
    }
    
    /**
     * Performs one of a series of actions, called steps, that make up the
     * animations and functions of this move. This method is intended to be
     * overridden when creating a new Move object.
     * @param uDisplay Mon display that's performing the move
     * @param tDisplay Target of the performed move
     * @param step Number of step to perform
     */
    public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {}
    
    /**
     * Increments the current move step of a specified mon display. This method
     * is intended to be called within doMoveStep() to indicate that a step is
     * finished and the display can move on to the next one.
     * @param uDisplay Mon display to have its current move step incremented
     */
    public void nextMoveStep(MonDisplay uDisplay) {
        uDisplay.setMoveStep(uDisplay.getMoveStep() + 1);
    }
    
    /**
     * Tells a specified mon display that the move it's currently performing is
     * finished. This method in intended to be called within doMoveStep() to
     * indicate that the step sequence is finished and no more steps need to be
     * performed.
     * @param uDisplay Mon display to have its move finished
     */
    public void finishMove(MonDisplay uDisplay) {
        uDisplay.resetMoveVars();
        uDisplay.setMoveFinished(true);
    }
    
    /**
     * Instructs a specified MonDisplay to wait a specified number of frames
     * before beginning the next step of its current move. This method is
     * intended to be called within doMoveStep() to time the move's animation
     * sequence appropriately. Calling this method is necessary to trigger
     * the move's next step.
     * @param uDisplay Mon display to be told to wait
     * @param frames Number of frames to wait for
     */
    public void waitUntilNextMoveStep(MonDisplay uDisplay, int frames) {
        uDisplay.setTimer("MoveStep", frames);
    }
    
    public void absoluteDamage(MonDisplay uDisplay, MonDisplay tDisplay, double damage) {
        if (damage > 0) {
            uDisplay.registerHit(damage);
            tDisplay.getMon().decreaseHealth(damage);
        }
    }
    
    public void basicDamage(MonDisplay uDisplay, MonDisplay tDisplay, Type type, double damage) {
        double damageToDeal = damage;
        damageToDeal *= uDisplay.getMon().getAttack()/tDisplay.getMon().getDefense();
        for (Type t : tDisplay.getMon().getTypes()) {
            damageToDeal *= t.getRelationship(type);
        }
        absoluteDamage(uDisplay, tDisplay, damageToDeal*(Math.random()*0.2 + 0.9));
    }
    
    public void basicDamage(MonDisplay uDisplay, MonDisplay tDisplay) {
        basicDamage(uDisplay, tDisplay, getType(), getDamage());
    }
    
    public boolean basicDamageAttempt(MonDisplay uDisplay, MonDisplay tDisplay, Type type, double damage, double accuracy) {
        if (Math.random() < Math.min(accuracy * (uDisplay.getMon().getAccuracy()/tDisplay.getMon().getEvasion()), 1)) {
            basicDamage(uDisplay, tDisplay, type, damage);
            return true;
        }
        return false;
    }
    
    public boolean basicDamageAttempt(MonDisplay uDisplay, MonDisplay tDisplay) {
        return basicDamageAttempt(uDisplay, tDisplay, getType(), getDamage(), getAccuracy());
    }
}
