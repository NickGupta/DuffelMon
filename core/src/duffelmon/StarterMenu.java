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
public class StarterMenu extends Menu {
    private int selection = 0;
    private BitmapFont font = GlobalData.getFont();
    private Color fontColor = Color.BLACK;
    Species[] species = new Species[3];
    Sprite[] sprites = new Sprite[3];
    
    public StarterMenu(Menu m) {
        super(m);
        initializeMon(0, "Mooshroom");
        initializeMon(1, "Rabbage");
        initializeMon(2, "Lamplord");
    }
    
    private void initializeMon(int pos, String name) {
        species[pos] = Species.getSpecies(name);
        Sprite s = new Sprite(species[pos].getTextures().getFrontTexture());
        s.setScale(2);
        s.setOrigin(s.getWidth(), s.getHeight());
        sprites[pos] = s;
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        Menu.drawBox(batch, alpha, 0, 0, 512, 448, true);
        font.setColor(fontColor);
        font.draw(batch, "Which DuffelMon?", 32, 384);
        for (int i = 0; i < species.length; i++) {
            sprites[i].setPosition(96 + i * 160, 256);
            sprites[i].draw(batch);
            font.draw(batch, species[i].getName(), 40 + i * 160, 176);
        }
        font.draw(batch, "________", 40 + selection * 160, 176);
        super.draw(batch, alpha);
    }
    
    @Override
    public void frameActions() {
        if (GlobalData.keyPressed(GlobalData.Inputs.RIGHT)) {
            selection++;
            if (selection > 2) {
                selection = 0;
            }
        } else if (GlobalData.keyPressed(GlobalData.Inputs.LEFT)) {
            selection--;
            if (selection < 0) {
                selection = 2;
            }
        }
        if (GlobalData.keyPressed(GlobalData.Inputs.SELECT)) {
            setServant(new MonNameMenu(this));
        }
    }
    
    @Override
    public boolean readServantOutput(String o) {
        setOutput(o + "/" + species[selection].getName());
        return true;
    }
}
