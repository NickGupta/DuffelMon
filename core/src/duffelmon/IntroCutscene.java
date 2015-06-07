/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author Andrew
 */
public class IntroCutscene extends Menu {
    
    private Sprite sprite;
    private enum States {
        BEFORENAME, NAME, BEFORESTARTER, STARTER, AFTERSTARTER
    }
    private States state;
    
    public IntroCutscene() {
        super();
        sprite = new Sprite(new Texture("professor.png"));
        sprite.setScale(2);
        sprite.setOrigin(sprite.getWidth(), sprite.getHeight());
        state = States.BEFORENAME;
        ArrayList<String> messages = new ArrayList<String>();
        messages.add("Hello there! Welcome to the world of DuffelMon!");
        messages.add("I'm Professor Shrub, a well-known DuffelMon researcher!");
        messages.add("I'm about to help you embark on an incredible adventure!");
        messages.add("But first, what is your name?");
        setServant(new TextBox(messages, true));
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        sprite.setPosition(256, 288);
        sprite.draw(batch);
        super.draw(batch, alpha);
    }
    
    @Override
    public boolean readServantOutput(String o) {
        if (state == States.BEFORENAME) {
            state = States.NAME;
        } else if (state == States.NAME) {
            state = States.BEFORESTARTER;
        } else if (state == States.BEFORESTARTER) {
            state = States.STARTER;
        } else if (state == States.STARTER) {
            state = States.AFTERSTARTER;
        } else if (state == States.AFTERSTARTER) {
            GameObject.makeDependent(this);
            GameMenu g = new GameMenu();
            GameObject.makeIndependent(g);
        }
        return true;
    }
}
