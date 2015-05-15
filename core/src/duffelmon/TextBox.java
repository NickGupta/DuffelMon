/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 *
 * @author csstudent
 */
public class TextBox extends Menu{
    
    private String message;
    
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    
    public TextBox(Menu m, String me) {
        super(m);
        message = me;
    }
    
    public TextBox(String me) {
        this(null, me);
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        Menu.drawBox(batch, alpha, 0, 0, 512, 128);
        font.setColor(fontColor);
        font.draw(batch, message, 16, 224);
    }
    
    @Override
    public void frameActions() {
        if (GlobalData.keyPressed(GlobalData.Inputs.SELECT)) {
            setOutput("Done");
        }
    }
}