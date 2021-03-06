/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;
import java.util.ArrayList;
import java.util.HashMap;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * This is the main 'game object' class. Every object type that does things
 * every frame other than drawing must extend this class.
 * @author csstudent
 */
public abstract class GameObject extends Actor {

    private static ArrayList<GameObject> independentObjects = new ArrayList<GameObject>();
    private static ArrayList<GameObject> toMakeIndependent = new ArrayList<GameObject>();
    
    /**
     * Makes a GameObject independent. Independent GameObjects will run their
     * frame actions automatically, will be drawn every render-frame, and will
     * not disappear if other objects lose their references to them. GameObjects
     * are dependent by default, so this should be called upon the creation of
     * a GameObject that you want to be independent.
     * @param g GameObject to be made independent
     */
    public static void makeIndependent(GameObject g) {
        toMakeIndependent.add(g);
    }
    
    /**
     * Makes a GameObject dependent. Dependent GameObjects will not run their
     * frame actions or draw themselves unless another object calls
     * frameActions() or draw() for them, and they will disappear if no other
     * objects have references to them, just like any other Java object.
     * @param g GameObject to be made dependent
     */
    public static void makeDependent(GameObject g) {
        g.shouldBeDependent = true;
    }
    
    /**
     * Returns whether a GameObject is currently independent.
     * @param g GameObject to be checked
     * @return Whether the object is currently independent
     */
    public static boolean isIndependent(GameObject g) {
        return independentObjects.contains(g) || toMakeIndependent.contains(g);
    }
    
    public static void runFrameActions() {
        for (GameObject g : independentObjects) {
            g.doFrame();
        }
        for (int i = 0; i < independentObjects.size(); i++) {
            GameObject g = independentObjects.get(i);
            if (g.shouldBeDependent) {
                independentObjects.remove(i);
                g.remove();
                i--;
            }
        }
        for (GameObject g : toMakeIndependent) {
            independentObjects.add(g);
            GlobalData.getStage().addActor(g);
        }
        toMakeIndependent = new ArrayList<GameObject>();
    }
    
    private HashMap<String,Integer> timers = new HashMap<String,Integer>();
    private boolean alive = true;
    private boolean shouldBeDependent = false;
    private float xSpeed = 0;
    private float ySpeed = 0;
    
    public boolean isAlive() {
        return alive;
    }
    
    public void die() {
        alive = false;
    }
    
    public float getXSpeed() {
        return xSpeed;
    }
    
    public float getYSpeed() {
        return ySpeed;
    }
    
    public void setXSpeed(float x) {
        xSpeed = x;
    }
    
    public void setYSpeed(float y) {
        ySpeed = y;
    }
    
    /**
     * Returns a HashMap representing all of this object's active timers.
     * @return A HashMap representing all of this object's active timers
     */
    public HashMap<String,Integer> getTimers() {
        return timers;
    }
    
    /**
     * Returns the value of a specific timer, or -1 if the timer is inactive or
     * doesn't exist.
     * @param s Name of the timer
     * @return Value of the timer
     */
    public int getTimer(String s) {
        Integer timer = timers.get(s);
        if (timer == null) {
            return -1;
        }
        return timer;
    }
    
    /**
     * Sets the value of a specific timer to a specified value.
     * @param s Name of the timer
     * @param t Desired value of the timer
     */
    public void setTimer(String s, int t) {
        timers.put(s, t);
    }
    
    /**
     * This method is called whenever a timer reaches 0. You can override it in
     * order to perform certain actions when this happens.
     * @param s Name of the timer that has just reached 0
     */
    public void triggerTimer(String s) {}
    
    public void handleTimers() {
        ArrayList<String> timersToTrigger = new ArrayList<String>();
        for (String name : timers.keySet()) {
            int val = timers.get(name);
            if (val > 0) {
                timers.put(name, val - 1);
                if (val == 1) {
                    timersToTrigger.add(name);
                }
            } else {
                timers.remove(name);
            }
        }
        for (String name : timersToTrigger) {
            triggerTimer(name);
        }
    }
    
    public void frameActions() {}
    
    public void doFrame() {
        handleTimers();
        frameActions();
        setX(getX() + xSpeed);
        setY(getY() + ySpeed);
    }
    
}
