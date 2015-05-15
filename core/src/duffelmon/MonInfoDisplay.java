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
    public Combatant combatant;
    private float x;
    private float y;
    
    public MonInfoDisplay(Combatant c, float xP, float yP) {
        combatant = c;
        x = xP;
        y = yP;
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        Menu.drawBox(batch, alpha, x, y - 48, x + 128, y);
        Mon mon = combatant.getCurrentMon();
        if (mon != null) {
            mon.drawInfo(batch, alpha, font, fontColor, x + 4, y - 4);
        }
    }
}
