/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 *
 * @author Andrew
 */
public class GlobalData {
    
    private static Stage stage;
    private static BitmapFont font;
    
    public static void initialize(Stage s, BitmapFont b) {
        stage = s;
        font = b;
    }
    
    public static Stage getStage() {
        return stage;
    }
    
    public static BitmapFont getFont() {
        return font;
    }
    
}
