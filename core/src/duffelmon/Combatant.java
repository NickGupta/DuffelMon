/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 *
 * @author csstudent
 */
public class Combatant {
    
    private Mon[] mons;
    private int monsPos = 0;
    private Item[] items;
    private Trainer trainer = null;
    private BattleAI ai = null;
    private MonDisplay monDisplay = null;
    private MonInfoDisplay infoDisplay = null;
    private boolean showMonDisplay = false;
    private boolean showInfoDisplay = false;
    private Move moveToUse = null;
    private int moveSlotToUse = -1;
    
    private Combatant(Mon[] m, Item[] i, Trainer t, BattleAI a) {
        mons = m;
        items = i;
        trainer = t;
        ai = a;
    }
    
    public static Combatant makeCombatant(Mon[] m, Item[] i, Trainer t, BattleAI a, boolean front) {
        float monX, monY, infoX, infoY;
        if (front) {
            monX = 416;
            monY = 304;
            infoX = 32;
            infoY = 368;
        } else {
            monX = 96;
            monY = 128;
            infoX = 384;
            infoY = 192;
        }
        Combatant c = new Combatant(m, i, t, a);
        c.setMonDisplay(new MonDisplay(c, front, monX, monY));
        c.setInfoDisplay(new MonInfoDisplay(c, infoX, infoY));
        return c;
    }
    
    public Mon[] getMons() {
        return mons;
    }
    public Mon getMon(int i) {
        return mons[i];
    }
    
    public Mon getCurrentMon() {
        return mons[monsPos];
    }
    
    public int getMonsPos() {
        return monsPos;
    }
    
    public void setMon(int pos) {
        monsPos = pos;
        monDisplay.setMon(mons[pos]);
        showInfoDisplay();
    }
    
    public Item[] getItems() {
        return items;
    }
    
    public Item getItem(int pos) {
        return items[pos];
    }
    
    public void useItem(int pos) {
        items[pos] = null;
    }
    
    public boolean isTrainer() {
        return trainer != null;
    }
    
    public Trainer getTrainer() {
        return trainer;
    }
    
    public boolean isPlayer() {
        return ai == null;
    }
    
    public BattleAI getAI() {
        return ai;
    }
    
    public MonDisplay getMonDisplay() {
        return monDisplay;
    }
    
    public void setMonDisplay(MonDisplay m) {
        monDisplay = m;
    }
    
    public MonInfoDisplay getInfoDisplay() {
        return infoDisplay;
    }
    
    public void setInfoDisplay(MonInfoDisplay i) {
        infoDisplay = i;
    }
    
    public void showMonDisplay() {
        showMonDisplay = true;
    }
    
    public void hideMonDisplay() {
        showMonDisplay = false;
    }
    
    public void showInfoDisplay() {
        showInfoDisplay = true;
    }
    
    public void hideInfoDisplay() {
        showInfoDisplay = false;
    }
    
    public Move getMoveToUse() {
        return moveToUse;
    }
    
    public void setMoveToUse(Move m) {
        moveToUse = m;
    }
    
    public int getMoveSlotToUse() {
        return moveSlotToUse;
    }
    
    public void setMoveSlotToUse(int m) {
        moveSlotToUse = m;
    }
    
    public void draw(Batch batch, float alpha) {
        if (showMonDisplay && monDisplay != null) {
            monDisplay.draw(batch, alpha);
        }
        if (showInfoDisplay && infoDisplay != null) {
            infoDisplay.draw(batch, alpha);
        }
    }
    
    public void doFrame() {
        if (monDisplay != null) {
            monDisplay.doFrame();
        }
    }
    
}
