/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author csstudent
 */
public class MonInfoDisplay extends Actor {
    
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    public Mon mon = null;
    private float x;
    private float y;
    
    public MonInfoDisplay(Mon m, float xP, float yP) {
        mon = m;
        x = xP;
        y = yP;
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        if (mon != null) {
            long health = Math.round(mon.getHealth());
            long maxHealth = Math.round(mon.getMaxHealth());
            font.setColor(fontColor);
            font.draw(batch, mon.getName(), x, y);
            font.draw(batch, "Health: " + health + " / " + maxHealth, x, y - 20);
        }
    }
}
