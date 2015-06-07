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
    public void frameActions() {
        if (state == States.NAME) {
            if (getServant() == null) {
                setServant(new NameMenu(this));
            }
        } else if (state == States.BEFORESTARTER) {
            ArrayList<String> messages = new ArrayList<String>();
            messages.add("Pleasure to meet you, " + GlobalData.getPlayer().getName() + "!");
            messages.add("This world is inhabited by creatures called DuffelMon.");
            messages.add("Some people use them as pets...");
            messages.add("...and some use them for battles!");
            messages.add("Why are they called DuffelMon? Because we keep them in duffel bags.");
            messages.add("...no, I don't think there's a better way to store them.");
            messages.add("If we could teleport them around or keep them in our pockets,");
            messages.add("that'd be great, but I'm afraid we can't.");
            messages.add("Anyway, to start you on your journey, I'll let you pick one DuffelMon to keep.");
            messages.add("Which one will it be?");
            setServant(new TextBox(messages, true));
        } else if (state == States.STARTER) {
            if (getServant() == null) {
                setServant(new StarterMenu(this));
            }
        } else if (state == States.AFTERSTARTER) {
            ArrayList<String> messages = new ArrayList<String>();
            messages.add("Excellent! So you've chosen a " + GlobalData.getPlayer().getMon(0).getSpecies().getName() + "!");
            messages.add("I wish you the best of luck on your journey!");
            setServant(new TextBox(messages, true));
        }
    }
    
    @Override
    public boolean readServantOutput(String o) {
        if (state == States.BEFORENAME) {
            state = States.NAME;
        } else if (state == States.NAME) {
            GlobalData.getPlayer().setName(o);
            state = States.BEFORESTARTER;
        } else if (state == States.BEFORESTARTER) {
            state = States.STARTER;
        } else if (state == States.STARTER) {
            int slashIndex = o.indexOf("/");
            String name = o.substring(0, slashIndex);
            if (name.equals("")) {
                name = null;
            }
            GlobalData.getPlayer().addMon(new Mon(name, Species.getSpecies(o.substring(slashIndex + 1)), 5));
            state = States.AFTERSTARTER;
        } else if (state == States.AFTERSTARTER) {
            GameObject.makeDependent(this);
            GameMenu g = new GameMenu();
            GameObject.makeIndependent(g);
        }
        return true;
    }
}
