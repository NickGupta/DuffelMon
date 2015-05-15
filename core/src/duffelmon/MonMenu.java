/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author csstudent
 */
public class MonMenu extends Menu {
    
    private Mon[] mons;
    private Sprite[] monSprites;
    private int selection = 0;
    
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    
    public MonMenu(Menu m, float x, float y, Mon[] ms) {
        super(m, x, y);
        mons = ms;
        monSprites = new Sprite[mons.length];
        for(int i = 0; i < monSprites.length; i++) {
            Sprite s = new Sprite(mons[i].getFrontTexture());
            monSprites[i] = s;
        }
    }
    @Override
    public void draw(Batch batch, float alpha) {
        Menu.drawBox(batch, alpha, getX(), getY() - 96*3, getX() + 256, getY());
        float yPos = getY();
        for(int i = 0; i < mons.length; i++) {
            Sprite sprite = monSprites[i];
            sprite.setCenter(getX() + 48, yPos - 48);
            sprite.draw(batch);
            mons[i].drawInfo(batch, alpha, font, fontColor, getX() + 96, yPos - 28);
            if (selection == i) {
                font.setColor(fontColor);
                font.draw(batch, "_____", getX() + 96, yPos - 28);
            }
            yPos -= 96;
        }
        super.draw(batch, alpha);
    }
    
    @Override
    public void frameActions() {
        if (GlobalData.keyPressed(GlobalData.Inputs.BACK)) {
            setOutput("ForgetIt");
        }
        if (GlobalData.keyPressed(GlobalData.Inputs.UP)) {
            selection -= 1;
            if (selection < 0) {
                selection = mons.length - 1;
            }
        } else if (GlobalData.keyPressed(GlobalData.Inputs.DOWN)) {
            selection += 1;
            if (selection >= mons.length) {
                selection = 0;
            }
        }
        if(GlobalData.keyPressed(GlobalData.Inputs.SELECT)){
            setOutput("CHNG" + selection);
        }
    }
}
