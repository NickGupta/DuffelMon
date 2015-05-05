/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;
import java.util.ArrayList;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * This is the main 'game object' class. Every object type that does things
 * every frame other than drawing must extend this class.
 * @author csstudent
 */
public abstract class GameObject extends Actor {

    public static ArrayList<GameObject> independentObjects = new ArrayList<GameObject>();
    
    /**
     * Makes a GameObject independent. Independent GameObjects will run their
     * frame actions automatically, will be drawn every render-frame, and will
     * not disappear if other objects lose their references to them. GameObjects
     * are dependent by default, so this should be called upon the creation of
     * a GameObject that you want to be independent.
     * @param g GameObject to be made independent
     */
    public static void makeIndependent(GameObject g) {
        independentObjects.add(g);
        GlobalData.getStage().addActor(g);
    }
    
    /**
     * Makes a GameObject dependent. Dependent GameObjects will not run their
     * frame actions or draw themselves unless another object calls
     * frameActions() or draw() for them, and they will disappear if no other
     * objects have references to them, just like any other Java object.
     * @param g GameObject to be made dependent
     */
    public static void makeDependent(GameObject g) {
        independentObjects.remove(g);
        g.remove();
    }
    
    public static void runFrameActions() {
        for(GameObject g : independentObjects) {
            g.frameActions();
        }
    }
    
    public abstract void frameActions();
    
}
