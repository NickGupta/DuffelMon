/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author csstudent
 */

public class MonDisplay extends GameObject {
    
    public Mon mon = null;
    private float front; //If front > 0, you're front-facing; if front < 0, back-facing
    //This can be used as a multiplier for anything that flips when the mon is facing the other way
    public MonDisplay(Mon m, boolean f, float x, float y){
        mon = m;
        if (f) {
            front = 1;
        } else {
            front = -1;
        }
        setX(x);
        setY(y);
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        Sprite s;
        if (front > 0) {
            s = mon.getFrontSprite();
        } else {
            s = mon.getBackSprite();
        }
        batch.draw(s, getX() - s.getWidth()/2, getY());
    }
    
    @Override
    public void doFrame() {
        frameActions();
        setX(getX() + front*getXSpeed());
        setY(getY() + getYSpeed());
    }
}

