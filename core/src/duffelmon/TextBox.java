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
    
    private String s;
    private float xValue;
    private float yValue;
    
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    
    public TextBox(float x, float y, String string){
        super(x, y);
        s = string;
    }
    
    
    @Override
    public void draw(Batch batch, float alpha) {
        font.setColor(fontColor);
        font.draw(batch, s, xValue, yValue);
    }
}