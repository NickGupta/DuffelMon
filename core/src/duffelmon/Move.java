/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;
import java.util.TreeMap;

/**
 *
 * @author csstudent
 */
public class Move {
    private static TreeMap<String,Move> moveMap = new TreeMap<String,Move>();
    
    private String name;
    private Type type;
    private double damage;
    private double accuracy;
    private int powerPoints;
    
    private Move(String n, Type t, double d, double a, int p) {
        name = n;
        type = t;
        damage = d;
        accuracy = a;
        powerPoints = p;
    }
    
    public static Move makeMove(String n, Type t, double d, double a, int p) {
        Move m = new Move(n, t, d, a, p);
        moveMap.put(n, m);
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
    
    public double getDamage() {
        return damage;
    }
    
    public double getAccuracy() {
        return accuracy;
    }
    
    public int getPowerPoints() {
        return powerPoints;
    }
    
    public void useInBattle(MonDisplay uDisplay, MonDisplay tDisplay) {
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
}
