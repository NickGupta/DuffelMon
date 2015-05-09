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
public class Battle extends GameObject {
    
    private static Battle battle;
    
    public enum States {
        INTRO
    }
    private Combatant player;
    private Combatant enemy;
    private States state;
    private MonDisplay mP = null;
    private MonDisplay mE = null;
    private MonInfoDisplay iP = null;
    private MonInfoDisplay iE = null;
    private BattleMenu menu = null;
    
    private Battle(Combatant p, Combatant e) {
        player = p;
        enemy = e;
        state = States.INTRO;
        //mP = new MonDisplay(player.getCurrentMon(), false, 200, 100);
        //mE = new MonDisplay(enemy.getCurrentMon(), true, 300, 400);
        //iP = new MonInfoDisplay(player.getCurrentMon(), 400, 100);
        //iE = new MonInfoDisplay(enemy.getCurrentMon(), 100, 400);
    }
    
    public static Battle startBattle(Combatant p, Combatant e) {
        battle = new Battle(p, e);
        GameObject.makeIndependent(battle);
        return battle;
    }
    
    public static Battle getBattle() {
        return battle;
    }
    
    public Combatant getPlayerCombatant() {
        return player;
    }
    
    public Combatant getEenemyCombatant() {
        return enemy;
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        if (mP != null) {
            mP.draw(batch, alpha);
        }
        if (mE != null) {
            mE.draw(batch, alpha);
        }
        if (iP != null) {
            iP.draw(batch, alpha);
        }
        if (iE != null) {
            iE.draw(batch, alpha);
        }
        if (menu != null) {
            menu.draw(batch, alpha);
        }
    }
    
    @Override
    public void doFrame() {
        if (mP != null) {
            mP.doFrame();
        }
        if (mE != null) {
            mE.doFrame();
        }
        if (menu != null) {
            menu.doFrame();
        }
    }
}
