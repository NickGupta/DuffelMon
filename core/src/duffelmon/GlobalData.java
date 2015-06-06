/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author Andrew
 */
public class GlobalData {
    
    private static HashMap<String,Texture> textureMap = new HashMap<String,Texture>();
    private static Player player = null;
    private static Stage stage;
    private static BitmapFont font;
    private static Texture whiteTexture, blackTexture;

    public enum Inputs {
        UP, DOWN, LEFT, RIGHT, SELECT, BACK
    }
    private enum InputStatus {
        NOTHELD, PRESSED, HELD, RELEASED
    }
    private static HashMap<Inputs,InputStatus> inputs = new HashMap<Inputs,InputStatus>();
    private static HashMap<Inputs,Integer> inputKeys = null;
    
    public static void initialize(Stage s, BitmapFont b) {
        stage = s;
        font = b;
        whiteTexture = new Texture("white.png");
        blackTexture = new Texture("black.png");
        inputKeys = new HashMap<Inputs,Integer>();
        inputKeys.put(Inputs.UP, Input.Keys.UP);
        inputKeys.put(Inputs.DOWN, Input.Keys.DOWN);
        inputKeys.put(Inputs.LEFT, Input.Keys.LEFT);
        inputKeys.put(Inputs.RIGHT, Input.Keys.RIGHT);
        inputKeys.put(Inputs.SELECT, Input.Keys.Z);
        inputKeys.put(Inputs.BACK, Input.Keys.X);
    }
    
    public static Player getPlayer() {
        return player;
    }
    
    public static void setPlayer(Player p) {
        player = p;
    }
    
    public static Stage getStage() {
        return stage;
    }
    
    public static BitmapFont getFont() {
        return font;
    }
    
    public static Texture getWhiteTexture() {
        return whiteTexture;
    }
    
    public static Texture getBlackTexture() {
        return blackTexture;
    }
    
    public static void makeTexture(String n, String f) {
        textureMap.put(n, new Texture(f));
    }
    
    public static void addTexture(String n, Texture t) {
        textureMap.put(n, t);
    }
    
    public static Texture getTexture(String s) {
        return textureMap.get(s);
    }
    
    public static void updateInputs() {
        for(Inputs i : Inputs.values()) {
            InputStatus formerStatus = inputs.get(i);
            if (Gdx.input.isKeyPressed(inputKeys.get(i))) {
                if (formerStatus == InputStatus.NOTHELD) {
                    inputs.put(i, InputStatus.PRESSED);
                } else {
                    inputs.put(i, InputStatus.HELD);
                }
            } else {
                if (formerStatus == InputStatus.HELD) {
                    inputs.put(i, InputStatus.RELEASED);
                } else {
                    inputs.put(i, InputStatus.NOTHELD);
                }
            }
        }
    }
    
    public static boolean keyPressed(Inputs i) {
        return inputs.get(i) == InputStatus.PRESSED;
    }
    
    public static boolean keyHeld(Inputs i) {
        InputStatus status = inputs.get(i);
        return status == InputStatus.PRESSED || status == InputStatus.HELD;
    }
    
    public static boolean keyReleased(Inputs i) {
        return inputs.get(i) == InputStatus.RELEASED;
    }
    
    public static double logBase(double base, double num) {
        return Math.log(num)/Math.log(base);
    }
    
}
