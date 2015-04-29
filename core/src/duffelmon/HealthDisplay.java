/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author csstudent
 */
public class HealthDisplay extends Actor {
    
    BitmapFont font = new BitmapFont();
    Combatant combatant;
    float x;
    float y;
    
    public HealthDisplay(Combatant c, float xP, float yP) {
        combatant = c;
        x = xP;
        y = yP;
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        Mon currentMon = combatant.getCurrentMon();
        font.draw(batch, "Health: "+currentMon.getHealth()+" / "+currentMon.getMaxHealth(), x, y);
    }
}
