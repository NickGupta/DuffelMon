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
public class MonNameMenu extends Menu {
    private int selection = 0;
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    
    public MonNameMenu(Menu m) {
        super(m);
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        Menu.drawBox(batch, alpha, 0, 0, 512, 128, true);
        font.setColor(fontColor);
        font.draw(batch, "Would you like to name your DuffelMon?", 16, 112);
        font.draw(batch, "Yes", 320, 112);
        font.draw(batch, "No", 320, 82);
        font.draw(batch, "___", 320, 112 - selection * 30);
        super.draw(batch, alpha);
    }
    
    @Override
    public void frameActions() {
        if (GlobalData.keyPressed(GlobalData.Inputs.DOWN)) {
            selection++;
            if (selection > 1) {
                selection = 0;
            }
        } else if (GlobalData.keyPressed(GlobalData.Inputs.UP)) {
            selection--;
            if (selection < 0) {
                selection = 1;
            }
        }
        if(GlobalData.keyPressed(GlobalData.Inputs.SELECT)) {
            if (selection == 0) {
                setServant(new NameMenu(this));
            } else if (selection == 1) {
                setOutput("");
            }
        }
    }
    
    @Override
    public boolean readServantOutput(String o) {
        setOutput(o);
        return true;
    }
}
