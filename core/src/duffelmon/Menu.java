/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 *
 * @author Andrew
 */
public abstract class Menu extends GameObject {
    
    private Menu master;
    private Menu servant = null;
    private String output = null;
    
    public Menu(Menu m, float x, float y) {
        setX(x);
        setY(y);
        master = m;
    }
    
    public Menu(Menu m) {
        this(m, 0, 0);
    }
    
    public Menu(float x, float y) {
        this(null, x, y);
    }
    
    public Menu() {
        this(null);
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
    
    public static void drawBox(Batch batch, float alpha, float x1, float y1, float x2, float y2, boolean outline) {
        Texture white = GlobalData.getWhiteTexture();
        Texture black = GlobalData.getBlackTexture();
        float xl = Math.min(x1, x2);
        float yl = Math.min(y1, y2);
        float xh = Math.max(x1, x2);
        float yh = Math.max(y1, y2);
        batch.draw(white, xl, yl, xh - xl, y2 - y1);
        if (outline) {
            batch.draw(black, xl, yl, xh - xl, 2);
            batch.draw(black, xl, yh - 2, xh - xl, 2);
            batch.draw(black, xl, yl, 2, yh - yl);
            batch.draw(black, xh - 2, yl, 2, yh - yl);
        }
    }
    
    public static void drawBox(Batch batch, float alpha, float x1, float y1, float x2, float y2) {
        drawBox(batch, alpha, x1, y1, x2, y2, true);
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
        } else {
            super.doFrame();
        }
    }
    
}
