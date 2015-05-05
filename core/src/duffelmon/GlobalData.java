/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Input;
import java.util.TreeMap;

/**
 *
 * @author Andrew
 */
public class GlobalData {
    
    private static Stage stage;
    private static BitmapFont font;

    public enum Inputs {
        UP, DOWN, LEFT, RIGHT, SELECT, BACK, MENU
    }
    private enum InputStatus {
        NOTHELD, PRESSED, HELD, RELEASED
    }
    private static TreeMap<Inputs,InputStatus> inputs = new TreeMap<Inputs,InputStatus>();
    private static TreeMap<Inputs,Integer> inputKeys = null;
    
    public static void initialize(Stage s, BitmapFont b) {
        stage = s;
        font = b;
        inputKeys = new TreeMap<Inputs,Integer>();
        inputKeys.put(Inputs.UP, Input.Keys.UP);
        inputKeys.put(Inputs.DOWN, Input.Keys.DOWN);
        inputKeys.put(Inputs.LEFT, Input.Keys.LEFT);
        inputKeys.put(Inputs.RIGHT, Input.Keys.RIGHT);
        inputKeys.put(Inputs.SELECT, Input.Keys.Z);
        inputKeys.put(Inputs.BACK, Input.Keys.X);
        inputKeys.put(Inputs.MENU, Input.Keys.ENTER);
    }
    
    public static Stage getStage() {
        return stage;
    }
    
    public static BitmapFont getFont() {
        return font;
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
    
    public static boolean keyPressed(Input i) {
        return inputs.get(i) == InputStatus.PRESSED;
    }
    
    public static boolean keyHeld(Input i) {
        InputStatus status = inputs.get(i);
        return status == InputStatus.PRESSED || status == InputStatus.HELD;
    }
    
    public static boolean keyReleased(Input i) {
        return inputs.get(i) == InputStatus.RELEASED;
    }
}
