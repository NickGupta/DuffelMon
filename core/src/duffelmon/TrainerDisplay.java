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
    
    private Trainer trainer;
    private Sprite sprite;
    boolean visible = true;
    
    public TrainerDisplay(float x, float y, Trainer t) {
        setX(x);
        setY(y);
        trainer = t;
        sprite = new Sprite(new Texture("trainersprites/" + t.getType() + ".png"));
        sprite.setScale(2);
        sprite.setOrigin(sprite.getWidth(), 0);
    }
    
    public Trainer getTrainer() {
        return trainer;
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
