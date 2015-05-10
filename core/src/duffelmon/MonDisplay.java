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
 * @author csstudent
 */

public class MonDisplay extends GameObject {
    
    private Mon mon = null;
    private float front; //If front > 0, you're front-facing; if front < 0, back-facing
    //This can be used as a multiplier for anything that flips when the mon is facing the other way
    private Sprite sprite;
    
    public MonDisplay(Mon m, boolean f, float x, float y){
        mon = m;
        Texture t;
        if (f) {
            front = 1;
            t = mon.getFrontTexture();
        } else {
            front = -1;
            t = mon.getBackTexture();
        }
        sprite = new Sprite(t);
        sprite.setScale(2);
        if (front > 0) {
            sprite.setFlip(true, false);
        }
        sprite.setOrigin(t.getWidth(), 0);
        setX(x);
        setY(y);
    }
    
    public Mon getMon() {
        return mon;
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        sprite.setX(getX());
        sprite.setY(getY());
        sprite.draw(batch);
    }
    
    @Override
    public void doFrame() {
        handleTimers();
        frameActions();
        setX(getX() + front*getXSpeed());
        setY(getY() + getYSpeed());
    }
}

