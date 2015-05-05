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
    
    public static void makeIndependent(GameObject g) {
        independentObjects.add(g);
        GlobalData.getStage().addActor(g);
    }
    
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
