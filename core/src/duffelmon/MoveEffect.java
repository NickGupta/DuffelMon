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
public class MoveEffect extends GameObject {
    
    private Sprite sprite;
    
    public MoveEffect(float x, float y, Texture t, float s, boolean b) {
        setX(x);
        setY(y);
        sprite = new Sprite(t);
        sprite.setScale(s);
        float yOrigin;
        if (b) {
            yOrigin = 0;
        } else {
            yOrigin = s*(t.getHeight()/2);
        }
        sprite.setOrigin(s*(t.getWidth()/2), yOrigin);
    }
    
    public MoveEffect(float x, float y, Texture t, float s) {
        this(x, y, t, s, false);
    }
    
    public MoveEffect(float x, float y, Texture t) {
        this(x, y, t, 1, false);
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        sprite.setPosition(getX(), getY());
        sprite.draw(batch);
    }
    
    @Override
    public void triggerTimer(String s) {
        if (s.equals("die")) {
            die();
        }
    }
    
}
