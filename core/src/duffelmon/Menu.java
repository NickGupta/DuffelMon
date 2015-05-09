/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 *
 * @author Andrew
 */
public abstract class Menu extends GameObject {
    
    private Menu master = null;
    private Menu servant = null;
    private String output = null;
    
    public Menu(float x, float y) {
        setX(x);
        setY(y);
    }
    
    public Menu(Menu m, float x, float y) {
        this(x, y);
        master = m;
    }
    
    /**
     * Returns this menu's master, which is another menu that it is a servant
     * of. If this menu has no master, null will be returned.
     * @return This menu's master, or null if there is none
     */
    public Menu getMaster() {
        return master;
    }
    
    /**
     * Returns this menu's servant, which is another menu that this menu is
     * currently using to help it perform its task. If this menu has no current
     * servant, null will be returned.
     * @return This menu's servant, or null if there is none
     */
    public Menu getServant() {
        return servant;
    }
    
    /**
     * Sets this menu's servant to a specified menu. While its servant exists,
     * this menu will remain inactive and let the player interact with the
     * servant alone.
     * @param s Desired servant menu
     */
    public void setServant(Menu s) {
        servant = s;
    }
    
    /**
     * Returns this menu's output string, which it uses to communicate the
     * player's choices to other objects, including its master.
     * @return The output string
     */
    public String getOutput() {
        return output;
    }
    
    /**
     * Sets this menu's output string to a specified value.
     * @param o 
     */
    public void setOutput(String o) {
        output = o;
    }
    
    /**
     * Reads an output string and performs actions based on it, with the
     * assumption that the string is the output of this menu's servant. This
     * method is automatically called as soon as this menu has an existing
     * servant and its output string is not null.
     * @param o Output string to read
     * @return True if the servant's task is finished and it can be removed;
     * false if the servant should remain active
     */
    public boolean readServantOutput(String o) {
        return true;
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        if (servant != null) {
            servant.draw(batch, alpha);
        }
    }
    
    @Override
    public void doFrame() {
        if (servant != null) {
            servant.doFrame();
            String servantOutput = servant.getOutput();
            if (servantOutput != null && readServantOutput(servantOutput)) {
                servant = null;
            }
        }
        if (servant == null) {
            super.doFrame();
        }
    }
    
}
