/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author Andrew
 */
public class TrainerDisplay extends GameObject {
    
    private Combatant combatant;
    private Sprite sprite;
    boolean visible = true;
    
    public TrainerDisplay(Combatant c, String t) {
        combatant = c;
        sprite = new Sprite(new Texture("trainersprites/" + t + ".png"));
        sprite.setScale(2);
        sprite.setOrigin(sprite.getWidth(), 0);
    }
    
    public Combatant getCombatant() {
        return combatant;
    }
    
    @Override
    public boolean isVisible() {
        return visible;
    }
    
    @Override
    public void setVisible(boolean v) {
        visible = v;
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        if (visible && sprite != null) {
            sprite.setPosition(getX(), getY());
            sprite.draw(batch);
        }
    }
    
}
