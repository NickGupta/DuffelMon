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
        INTRO, WAITING, TURN1
    }
    private Combatant player;
    private Combatant enemy;
    private States state;
    private BattleMenu menu = null;
    private Combatant toMoveFirst = null;
    private Combatant toMoveSecond = null;
    
    private Battle(Mon[] p, BattleAI pA, Mon[] e, BattleAI eA) {
        player = Combatant.makeCombatant(p, pA, false);
        enemy = Combatant.makeCombatant(e, eA, true);
        state = States.INTRO;
    }
    
    public static Battle startBattle(Mon[] p, BattleAI pA, Mon[] e, BattleAI eA) {
        battle = new Battle(p, pA, e, eA);
        GameObject.makeIndependent(battle);
        return battle;
    }
    
    public static Battle startBattle(Mon[] p, BattleAI pA, Mon e, BattleAI eA) {
        Mon[] mArray = new Mon[1];
        mArray[0] = e;
        return startBattle(p, pA, mArray, eA);
    }
    
    public static Battle startBattle(Mon p, BattleAI pA, Mon[] e, BattleAI eA) {
        Mon[] mArray = new Mon[1];
        mArray[0] = p;
        return startBattle(mArray, pA, e, eA);
    }
    
    public static Battle startBattle(Mon p, BattleAI pA, Mon e, BattleAI eA) {
        Mon[] mArray = new Mon[1];
        mArray[0] = p;
        return startBattle(mArray, pA, e, eA);
    }
    
    public static Battle getBattle() {
        return battle;
    }
    
    public Combatant getPlayerCombatant() {
        return player;
    }
    
    public Combatant getEnemyCombatant() {
        return enemy;
    }
    
    private Move actionToMove(Combatant actor, String action) {
        String aType = action.substring(0, 4);
        if (aType.equals("MOVE")) {
            int numMove = Integer.parseInt(action.substring(4)) - 1;
            if (actor.getCurrentMon().getPowerPoints(numMove) > 0) {
                return actor.getCurrentMon().getMove(numMove);
            }
            return null;
        } else if (aType.equals("CHNG")) {
            return null;
        } else if (aType.equals("ITEM")) {
            return null;
        } else if (aType.equals("ESCP")) {
            return null;
        }
        return null;
    }
    
    private double monToPriority(Mon mon) {
        return mon.getSpeed();
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        if (menu != null) {
            menu.draw(batch, alpha);
        }
        enemy.draw(batch, alpha);
        player.draw(batch, alpha);
    }
    
    @Override
    public void frameActions() {
        if (state == States.INTRO) {
            player.showInfoDisplay();
            enemy.showInfoDisplay();
            state = States.WAITING;
            if (player.isPlayer()) {
                menu = new BattleMenu(0, 100, player);
            } else {
                player.getAI().chooseAction(player, enemy);
            }
            enemy.getAI().chooseAction(enemy, player);
        }
        if (state == States.WAITING) {
            String outputP;
            if (player.isPlayer()) {
                outputP = menu.getOutput();
            } else {
                outputP = player.getAI().getOutput();
            }
            String outputE = enemy.getAI().getOutput();
            if (outputP != null && outputE != null) {
                if (player.isPlayer()) {
                    menu = null;
                } else {
                    player.getAI().setOutput(null);
                }
                enemy.getAI().setOutput(null);
                player.setMoveToUse(actionToMove(player, outputP));
                enemy.setMoveToUse(actionToMove(enemy, outputE));
                double priorityP = player.getMoveToUse().getPriority();
                double priorityE = enemy.getMoveToUse().getPriority();
                if (priorityP > priorityE) {
                    toMoveFirst = player;
                } else if (priorityE > priorityP) {
                    toMoveFirst = enemy;
                } else {
                    priorityP = monToPriority(player.getCurrentMon());
                    priorityE = monToPriority(enemy.getCurrentMon());
                    if (priorityP > priorityE) {
                        toMoveFirst = player;
                    } else if (priorityE > priorityP) {
                        toMoveFirst = enemy;
                    } else if (Math.random() < 0.5) {
                        toMoveFirst = player;
                    } else {
                        toMoveFirst = enemy;
                    }
                }
                if (toMoveFirst == player) {
                    toMoveSecond = enemy;
                } else {
                    toMoveSecond = player;
                }
                state = States.TURN1;
                MonDisplay u, t;
                toMoveFirst.getMoveToUse().useInBattle(toMoveFirst.getMonDisplay(), toMoveSecond.getMonDisplay());
            }
        }
    }
    
    @Override
    public void doFrame() {
        super.doFrame();
        if (menu != null) {
            menu.doFrame();
        }
        player.doFrame();
        enemy.doFrame();
    }
}
