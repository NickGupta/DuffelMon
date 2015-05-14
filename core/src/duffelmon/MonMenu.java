/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author csstudent
 */
public class MonMenu extends Menu{
    
    private Player p;
    private String m;
    private Mon[] mons;
    private Float xPos;
    private Float yPos;
    
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    
    
    public MonMenu(float x, float y, Player pla) {
        super(x, y);
        p = pla;
        mons = Player.getMons();
        xPos = x;
        yPos = y;
    }
    @Override
    public void draw(Batch batch, float alpha) {
        font.setColor(fontColor);
        
        // Draws the name of each Mon and a Sprite next to them
        // Mon 0
        Texture t;
        Sprite sprite;
        t = mons[0].getFrontTexture();
        sprite = new Sprite(t);
        sprite.setScale(1);
        sprite.setOrigin(t.getWidth(), 0);
        font.draw(batch, mons[0].getName(), xPos, yPos);
        xPos -= 100;
        sprite.setX(xPos);
        sprite.setY(yPos);
        sprite.draw(batch);
        xPos += 100;
        yPos -= 80;
        
        // Mon 1
        t = mons[1].getFrontTexture();
        sprite = new Sprite(t);
        sprite.setScale(1);
        sprite.setOrigin(t.getWidth(), 0);
        font.draw(batch, mons[1].getName(), xPos, yPos);
        xPos -= 100;
        sprite.setX(xPos);
        sprite.setY(yPos);
        sprite.draw(batch);
        xPos += 100;
        yPos -= 80;
        
        // Mon 2
        t = mons[2].getFrontTexture();
        sprite = new Sprite(t);
        sprite.setScale(1);
        sprite.setOrigin(t.getWidth(), 0);
        font.draw(batch, mons[2].getName(), xPos, yPos);
        xPos -= 100;
        sprite.setX(xPos);
        sprite.setY(yPos);
        sprite.draw(batch);
        xPos += 100;
        
        // Draws an underscore to show what is currently selected
        font.draw(batch, "_____", x, y);
        super.draw(batch, alpha);
    }
     
    @Override
    public void frameActions() {
         
    }
     
     
}
