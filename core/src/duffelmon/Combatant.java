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
    private BattleAI ai = null;
    private MonDisplay monDisplay = null;
    private MonInfoDisplay infoDisplay = null;
    private boolean showMonDisplay = false;
    private boolean showInfoDisplay = false;
    private Move moveToUse = null;
    
    private Combatant(Mon[] m, Item[] i, BattleAI a) {
        mons = m;
        items = i;
        ai = a;
    }
    
    public static Combatant makeCombatant(Mon[] m, Item[] i, BattleAI a, boolean front) {
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
        Combatant c = new Combatant(m, i, a);
        c.setMonDisplay(new MonDisplay(c, front, monX, monY));
        c.setInfoDisplay(new MonInfoDisplay(c, infoX, infoY));
        return c;
    }
    
    public static Combatant makeCombatant(Mon m, BattleAI a, boolean front) {
        Mon[] mArray = new Mon[1];
        mArray[0] = m;
        return makeCombatant(mArray, null, a, front);
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
    
    public void setMonsPos(int pos) {
        monsPos = pos;
        monDisplay.setMon(mons[pos]);
    }
    
    public Item[] getItems() {
        return items;
    }
    
    public Item getItem(int pos) {
        return items[pos];
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
    
    public void draw(Batch batch, float alpha) {
        if (monDisplay != null) {
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
