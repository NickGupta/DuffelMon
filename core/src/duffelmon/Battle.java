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
    
    private Battle(Mon[] pM, Item[] pI, BattleAI pA, Mon[] eM, Item[] eI, BattleAI eA) {
        player = Combatant.makeCombatant(pM, pI, pA, false);
        enemy = Combatant.makeCombatant(eM, eI, eA, true);
        state = States.INTRO;
    }
    
    public static Battle startBattle(Trainer p, Trainer e) {
        battle = new Battle(p.getMons(), p.getItems(), p.getAI(), e.getMons(), e.getItems(), e.getAI());
        GameObject.makeIndependent(battle);
        return battle;
    }
    
    public static Battle startBattle(Trainer p, Mon e, BattleAI a) {
        Mon[] eArray = new Mon[1];
        eArray[0] = e;
        battle = new Battle(p.getMons(), p.getItems(), p.getAI(), eArray, null, a);
        GameObject.makeIndependent(battle);
        return battle;
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
            if (actor.getCurrentMon().getMove(numMove) != null
            && actor.getCurrentMon().getPowerPoints(numMove) > 0) {
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
        String moveName = user.getMoveToUse().getName();
        String message;
        if (moveName.substring(0, 5).equals("Item_")) {
            message = user.getCurrentMon().getName() + " used a " + moveName.substring(5) + "!";
        } else {
            message = user.getCurrentMon().getName() + " used " + moveName + "!";
        }
        textBox = new TextBox(message, false);
        if (user.getMoveSlotToUse() != -1) {
            user.getCurrentMon().decrementPowerPoints(user.getMoveSlotToUse());
        }
        user.getMoveToUse().useInBattle(user.getMonDisplay(), target.getMonDisplay());
    }
    
    private void finishMove(Combatant c) {
        c.getMonDisplay().setMoveFinished(false);
        c.getMonDisplay().getMon().decrementStatusEffects();
        textBox.setPressToAdvance(true);
        ArrayList<String> m = new ArrayList<String>();
        MonDisplay display = c.getMonDisplay();
        if (display.getCurrentMove().targetsOpponent()) {
            if (!display.getHitTarget()) {
                m.add("But it missed!");
            } else if (display.getDamageDealt() == 0) {
                m.add("But it had no effect!");
            }
        }
        if (m.isEmpty()) {
            m = c.getMonDisplay().readMoveMessages();
        }
        if (!m.isEmpty()) {
            for(int i = 0; i < m.size(); i++) {
                textBox.addMessage(m.get(i));
            }
        }
        display.resetMoveVars();
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
                finishMove(toMoveFirst);
                state = States.BETWEEN;
            }
        }
        if (state == States.BETWEEN) {
            waitAfterTurnForTextBox();
        }
        if (state == States.TURN2) {
            if (toMoveSecond.getMonDisplay().getMoveFinished()) {
                finishMove(toMoveSecond);
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
            if (!faintDeadMons()) {
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
