/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 *
 * @author csstudent
 */
public class TextBox extends Menu {
    
    private ArrayList<String> messages;
    private int messagePos = 0;
    private boolean pressToAdvance;
    
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    
    public TextBox(Menu m, ArrayList<String> me, boolean p) {
        super(m);
        messages = me;
        pressToAdvance = p;
    }
    
    public TextBox(ArrayList<String> me, boolean p) {
        this(null, me, p);
    }
    
    public TextBox(Menu m, String me, boolean p) {
        super(m);
        messages = new ArrayList<String>();
        messages.add(me);
        pressToAdvance = p;
    }
    
    public TextBox(String me, boolean p) {
        this(null, me, p);
    }
    
    public void addMessage(String s) {
        messages.add(s);
        if (getOutput().equals("Done")) {
            setOutput(null);
        }
    }
    
    public void advanceMessage() {
        if (getOutput() == null) {
            if (messagePos < messages.size() - 1) {
                messagePos++;
            } else {
                setOutput("Done");
            }
        }
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        Menu.drawBox(batch, alpha, 0, 0, 512, 128);
        font.setColor(fontColor);
        font.draw(batch, messages.get(messagePos), 16, 112);
    }
    
    @Override
    public void frameActions() {
        if (pressToAdvance && getOutput() == null
           && GlobalData.keyPressed(GlobalData.Inputs.SELECT)) {
            advanceMessage();
        }
    }
}