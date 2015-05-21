/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 *
 * @author Andrew
 */
public class Battle extends GameObject {
    
    private static Battle battle;
    
    private enum States {
        INTRO, BEFORE, TURN1, BETWEEN, TURN2, AFTER
    }
    private Combatant player;
    private Combatant enemy;
    private States state;
    private BattleMenu menu = null;
    private TextBox textBox = null;
    private ArrayList<MoveEffect> moveEffects = new ArrayList<MoveEffect>();
    private Combatant toMoveFirst = null;
    private Combatant toMoveSecond = null;
    
    private Battle(Mon[] p, Item[] pI, BattleAI pA, Mon[] e, Item[] eI, BattleAI eA) {
        player = Combatant.makeCombatant(p, pI, pA, false);
        enemy = Combatant.makeCombatant(e, eI, eA, true);
        state = States.INTRO;
    }
    
    public static Battle startBattle(Mon[] p, Item[] pI, BattleAI pA, Mon[] e, Item[] eI, BattleAI eA) {
        battle = new Battle(p, pI, pA, e, eI, eA);
        GameObject.makeIndependent(battle);
        return battle;
    }
    
    public static Battle startBattle(Mon[] p, Item[] pI, BattleAI pA, Mon e, BattleAI eA) {
        Mon[] mArray = new Mon[1];
        mArray[0] = e;
        return startBattle(p, pI, pA, mArray, null, eA);
    }
    
    public static Battle startBattle(Mon[] e, Item[] eI, BattleAI eA) {
        Player player = GlobalData.getPlayer();
        return startBattle(player.getMons(), player.getItems(), null, e, eI, eA);
    }
    
    public static Battle startBattle(Mon e, BattleAI eA) {
        Mon[] mArray = new Mon[1];
        mArray[0] = e;
        return startBattle(mArray, null, eA);
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
    
    public MoveEffect addMoveEffect(MoveEffect m) {
        moveEffects.add(m);
        return m;
    }
    
    private Move actionToMove(Combatant actor, String action) {
        String aType = action.substring(0, 4);
        if (aType.equals("MOVE")) {
            int numMove = Integer.parseInt(action.substring(4));
            if (actor.getCurrentMon().getPowerPoints(numMove) > 0) {
                return actor.getCurrentMon().getMove(numMove);
            }
            return Move.getMove("Struggle");
        } else if (aType.equals("CHNG")) {
            return null;
        } else if (aType.equals("ITEM")) {
            int numItem = Integer.parseInt(action.substring(4));
            return actor.getItem(numItem).getMove();
        } else if (aType.equals("ESCP")) {
            return null;
        }
        return null;
    }
    
    private int actionToMoveSlot(Combatant actor, String action) {
        String aType = action.substring(0, 4);
        if (aType.equals("MOVE")) {
            int numMove = Integer.parseInt(action.substring(4));
            if (actor.getCurrentMon().getPowerPoints(numMove) > 0) {
                return numMove;
            }
        }
        return -1;
    }
    
    private double monToPriority(Mon mon) {
        return mon.getSpeed();
    }
    
    private void startNewTurn() {
        state = States.BEFORE;
        if (player.isPlayer()) {
            menu = new BattleMenu(player);
        } else {
            player.getAI().chooseAction(player, enemy);
        }
        enemy.getAI().chooseAction(enemy, player);
    }
    
    private void useMove(Combatant user, Combatant target) {
        textBox = new TextBox(user.getCurrentMon().getName() + " used " + user.getMoveToUse().getName() + "!", true);
        if (user.getMoveSlotToUse() != -1) {
            user.getCurrentMon().decrementPowerPoints(user.getMoveSlotToUse());
        }
        user.getMoveToUse().useInBattle(user.getMonDisplay(), target.getMonDisplay());
    }
    
    private void finishMove(Combatant c) {
       c.getMonDisplay().setMoveFinished(false);
       c.getMonDisplay().getMon().decrementStatusEffects();
       String m = c.getMonDisplay().readMoveMessage();
       if (m != null) {
           textBox.addMessage(m);
       }
    }
    
    private void waitAfterTurnForTextBox() {
        if (getTimer("waitAfterTurn") == -1 && (textBox == null || textBox.getOutput() != null)) {
            textBox = null;
            setTimer("waitAfterTurn", 30);
        }
    }
    
    private void faintCurrentMon(Combatant c) {
        c.getMonDisplay().faint();
    }
    
    private boolean faintDeadMons() {
        Mon playerMon = player.getCurrentMon();
        Mon enemyMon = enemy.getCurrentMon();
        boolean playerFainted = false;
        boolean enemyFainted = false;
        if (playerMon.getHealth() == 0) {
            faintCurrentMon(player);
            playerFainted = true;
        }
        if (enemyMon.getHealth() == 0) {
            faintCurrentMon(enemy);
            enemyFainted = true;
        }
        String message = null;
        if (playerFainted && enemyFainted) {
            message = "Both mons fainted!";
        } else if (playerFainted) {
            message = playerMon.getName() + " fainted!";
        } else if (enemyFainted) {
            message = enemyMon.getName() + " fainted!";
        }
        if (playerFainted || enemyFainted) {
            textBox = new TextBox(message, false);
            return true;
        }
        return false;
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        enemy.draw(batch, alpha);
        for(MoveEffect m : moveEffects) {
            m.draw(batch, alpha);
        }
        player.draw(batch, alpha);
        if (menu != null) {
            menu.draw(batch, alpha);
        } else if (textBox != null) {
            textBox.draw(batch, alpha);
        } else {
            Menu.drawBox(batch, alpha, 0, 0, 512, 128);
        }
    }
    
    @Override
    public void frameActions() {
        if (state == States.INTRO) {
            player.showMonDisplay();
            player.showInfoDisplay();
            enemy.showMonDisplay();
            enemy.showInfoDisplay();
            startNewTurn();
        }
        if (state == States.BEFORE) {
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
                player.setMoveSlotToUse(actionToMoveSlot(player, outputP));
                enemy.setMoveToUse(actionToMove(enemy, outputE));
                enemy.setMoveSlotToUse(actionToMoveSlot(enemy, outputP));
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
                useMove(toMoveFirst, toMoveSecond);
            }
        }
        if (state == States.TURN1) {
            if (toMoveFirst.getMonDisplay().getMoveFinished()) {
                toMoveFirst.getMonDisplay().setMoveFinished(false);
                state = States.BETWEEN;
            }
        }
        if (state == States.BETWEEN) {
            waitAfterTurnForTextBox();
        }
        if (state == States.TURN2) {
            if (toMoveSecond.getMonDisplay().getMoveFinished()) {
                toMoveSecond.getMonDisplay().setMoveFinished(false);
                state = States.AFTER;
            }
        }
        if (state == States.AFTER) {
            waitAfterTurnForTextBox();
        }
    }
    
    @Override
    public void doFrame() {
        super.doFrame();
        if (menu != null) {
            menu.doFrame();
        }
        if (textBox != null) {
            textBox.doFrame();
        }
        player.doFrame();
        enemy.doFrame();
        for(int i = 0; i < moveEffects.size(); i++) {
            moveEffects.get(i).doFrame();
            if (!moveEffects.get(i).isAlive()) {
                moveEffects.remove(i);
                i--;
            }
        }
    }
    
    @Override
    public void triggerTimer(String s) {
        if (s.equals("waitAfterTurn")) {
            if (faintDeadMons()) {
                
            } else {
                if (state == States.BETWEEN) {
                    state = States.TURN2;
                    useMove(toMoveSecond, toMoveFirst);
                } else if (state == States.AFTER) {
                    startNewTurn();
                }
            }
        }
    }
}
