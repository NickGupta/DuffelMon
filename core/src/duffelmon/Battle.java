/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.ArrayList;
import java.util.HashMap;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 *
 * @author Andrew
 */
public class Battle extends GameObject {
    
    private static Battle battle;
    
    private enum States {
        INTRO, TRAINERMOVE, ENEMYSEND, PLAYERSEND, BEFORE, TURN1, BETWEEN,
        TURN2, AFTER, FAINTED, SELECTLIVEMON, SENDLIVEMON, AFTERSENDLIVE,
        TRAINERMOVEBACK, OUTRO
    }
    private Combatant player;
    private Combatant enemy;
    private States state;
    private Menu menu = null;
    private TextBox textBox = null;
    private TrainerDisplay trainer = null;
    private ArrayList<MoveEffect> moveEffects = new ArrayList<MoveEffect>();
    private Combatant toMoveFirst = null;
    private Combatant toMoveSecond = null;
    
    private Battle(Mon[] pM, Item[] pI, Trainer pT, BattleAI pA, Mon[] eM, Item[] eI, Trainer eT, BattleAI eA) {
        player = Combatant.makeCombatant(pM, pI, pT, pA, false);
        enemy = Combatant.makeCombatant(eM, eI, eT, eA, true);
        if (eT == null) {
            enemy.showMonDisplay();
            enemy.showInfoDisplay();
            textBox = new TextBox("A wild " + enemy.getCurrentMon().getName() + " appeared!", true);
            state = States.ENEMYSEND;
        } else {
            trainer = new TrainerDisplay(416, 304, eT);
            textBox = new TextBox(enemy.getTrainer().getFullName() + " is ready to battle!", true);
            state = States.INTRO;
        }
    }
    
    public static Battle startBattle(Trainer p, Trainer e) {
        battle = new Battle(p.getMons(), p.getItems(), p, p.getAI(), e.getMons(), e.getItems(), e, e.getAI());
        GameObject.makeIndependent(battle);
        return battle;
    }
    
    public static Battle startBattle(Trainer p, Mon e, BattleAI a) {
        Mon[] eArray = new Mon[1];
        eArray[0] = e;
        battle = new Battle(p.getMons(), p.getItems(), p, p.getAI(), eArray, null, null, a);
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
        } else if (aType.equals("MONS")) {
            return Move.getMove("ChangeMons");
        } else if (aType.equals("ITEM")) {
            int numItem = Integer.parseInt(action.substring(4));
            if (actor.getItem(numItem) != null) {
                return actor.getItem(numItem).getMove();
            }
            return Move.getMove("Item_Null");
        } else if (aType.equals("ESCP")) {
            return Move.getMove("Escape");
        }
        return null;
    }
    
    private int actionToMoveSlot(Combatant actor, String action) {
        if (action.substring(0, 4).equals("ESCP")) {
            return -1;
        }
        return Integer.parseInt(action.substring(4));
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
        if (moveName.length() >= 5 && moveName.substring(0, 5).equals("Item_")) {
            message = user.getCurrentMon().getName() + " used a " + moveName.substring(5) + "!";
            user.useItem(user.getMoveSlotToUse());
        } else if (moveName.equals("ChangeMons")) {
            message = user.getTrainer().getName() + " sent out " + user.getMon(user.getMoveSlotToUse()).getName() + "!";
        } else if (user.getMoveSlotToUse() >= 0) {
            message = user.getCurrentMon().getName() + " used " + moveName + "!";
            user.getCurrentMon().decrementPowerPoints(user.getMoveSlotToUse());
        } else {
            message = user.getCurrentMon().getName() + " tried to run away!";
        }
        textBox = new TextBox(message, false);
        user.getMoveToUse().useInBattle(user.getMonDisplay(), target.getMonDisplay());
    }
    
    private void finishMove(Combatant c) {
        c.getMonDisplay().setMoveFinished(false);
        c.getCurrentMon().decrementStatusEffects();
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
    
    private boolean switchToLiveMon(Combatant c, Combatant o) {
        Mon mon = c.getCurrentMon();
        if (mon.getHealth() > 0) {
            return true;
        }
        boolean defeated = true;
        for(Mon m : c.getMons()) {
            if (m != null && m.getHealth() > 0) {
                defeated = false;
                break;
            }
        }
        if (defeated) {
            player.hideMonDisplay();
            player.hideInfoDisplay();
            enemy.hideMonDisplay();
            enemy.hideInfoDisplay();
            if (enemy.isTrainer()) {
                textBox = new TextBox(c.getTrainer().getName() + " was defeated!", false);
                state = States.TRAINERMOVEBACK;
                trainer.setVisible(true);
                trainer.setXSpeed(-4);
                setTimer("trainerStop", 45);
            } else {
                beginOutro(o, c);
            }
            return false;
        }
        toMoveFirst = c;
        toMoveSecond = o;
        if (c.isPlayer()) {
            menu = new MonMenu(256, 288, c, c.getMons(), true);
            state = States.SELECTLIVEMON;
        } else {
            c.getAI().chooseMon(c, o);
            String output = c.getAI().getOutput();
            c.setMoveToUse(actionToMove(c, output));
            c.setMoveSlotToUse(actionToMoveSlot(c, output));
            useMove(c, o);
            state = States.SENDLIVEMON;
        }
        return false;
    }
    
    private void waitAfterTurnForTextBox() {
        if (getTimer("waitAfterTurn") == -1 && (textBox == null || textBox.getOutput() != null)) {
            textBox = null;
            if (player.getMonDisplay().getRanAway() || enemy.getMonDisplay().getRanAway()) {
                endBattle();
            } else {
                setTimer("waitAfterTurn", 30);
            }
        }
    }
    
    private void faintCurrentMon(Combatant c) {
        c.hideMonDisplay();
    }
    
    private void beginOutro(Combatant w, Combatant l) {
        state = States.OUTRO;
        if (enemy.isTrainer()) {
            textBox.setPressToAdvance(true);
        } else {
            String message;
            if (w.isTrainer()) {
                message = w.getTrainer().getName() + " defeated the wild DuffelMon!";
            } else if (l.isTrainer()) {
                message = l.getTrainer().getName() + " was defeated by the wild DuffelMon!";
            } else {
                message = w.getMon(0).getName() + " defeated the enemy DuffelMon!";
            }
            textBox = new TextBox(message, true);
        }
        if (l.isTrainer()) {
            if (w.isPlayer()) {
                int moneyGiven = GlobalData.getPlayer().giveMoney(l.getTrainer().getMoneyToGive());
                if (moneyGiven > 0) {
                    textBox.addMessage(w.getTrainer().getName() + " earned $" + moneyGiven + " for winning!");
                }
            } else if (w.isTrainer()) {
                int moneyGiven = GlobalData.getPlayer().takeMoney(l.getTrainer().getMoneyToGive());
                if (moneyGiven > 0) {
                    textBox.addMessage(l.getTrainer().getName() + " had to pay " + w.getTrainer().getName() + " $" + moneyGiven + " for losing!");
                }
            }
        }
    }
    
    private void beginOutro() {
        boolean enemyDefeated = true;
        for (int i = 0; i < enemy.getMons().length; i++) {
            if (enemy.getMon(i).getHealth() > 0) {
                enemyDefeated = false;
                break;
            }
        }
        if (enemyDefeated) {
            beginOutro(player, enemy);
        } else {
            beginOutro(enemy, player);
        }
    }
    
    private void endBattle() {
        GameObject.makeDependent(this);
        player.restoreMons();
        enemy.restoreMons();
        GameMenu g = new GameMenu();
        GameObject.makeIndependent(g);
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        if (trainer != null) {
            trainer.draw(batch, alpha);
        }
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
            if (textBox.getOutput() != null) {
                textBox = null;
                state = States.TRAINERMOVE;
                trainer.setXSpeed(4);
                setTimer("trainerStop", 45);
            }
        }
        if (state == States.ENEMYSEND) {
            if (textBox.getOutput() != null) {
                player.showMonDisplay();
                player.showInfoDisplay();
                textBox = new TextBox(player.getTrainer().getName() + " sent out " + player.getCurrentMon().getName() + "!", true);
                state = States.PLAYERSEND;
            }
        }
        if (state == States.PLAYERSEND) {
            if (textBox.getOutput() != null) {
                textBox = null;
                startNewTurn();
            }
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
                enemy.setMoveSlotToUse(actionToMoveSlot(enemy, outputE));
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
        if (state == States.FAINTED) {
            if (textBox == null || textBox.getOutput() != null) {
                textBox = null;
                if (switchToLiveMon(enemy, player) && switchToLiveMon(player, enemy)) {
                    startNewTurn();
                }
            }
        }
        if (state == States.SELECTLIVEMON) {
            String output = menu.getOutput();
            if (output != null) {
                menu = null;
                toMoveFirst.setMoveToUse(actionToMove(toMoveFirst, output));
                toMoveFirst.setMoveSlotToUse(actionToMoveSlot(toMoveFirst, output));
                useMove(toMoveFirst, toMoveSecond);
                state = States.SENDLIVEMON;
            }
        }
        if (state == States.SENDLIVEMON) {
            if (toMoveFirst.getMonDisplay().getMoveFinished()) {
                finishMove(toMoveFirst);
                state = States.AFTERSENDLIVE;
            }
        }
        if (state == States.AFTERSENDLIVE) {
            waitAfterTurnForTextBox();
        }
        if (state == States.OUTRO) {
            if (textBox == null || textBox.getOutput() != null) {
                textBox = null;
                endBattle();
            }
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
        if (trainer != null) {
            trainer.doFrame();
        }
        enemy.doFrame();
        for(int i = 0; i < moveEffects.size(); i++) {
            moveEffects.get(i).doFrame();
            if (!moveEffects.get(i).isAlive()) {
                moveEffects.remove(i);
                i--;
            }
        }
        player.doFrame();
    }
    
    @Override
    public void triggerTimer(String s) {
        if (s.equals("trainerStop")) {
            trainer.setXSpeed(0);
            if (state == States.TRAINERMOVE) {
                trainer.setVisible(false);
                enemy.showMonDisplay();
                enemy.showInfoDisplay();
                textBox = new TextBox(enemy.getTrainer().getName() + " sent out " + enemy.getCurrentMon().getName() + "!", true);
                state = States.ENEMYSEND;
            } else if (state == States.TRAINERMOVEBACK) {
                beginOutro();
            }
        } else if (s.equals("waitAfterTurn")) {
            if (state == States.AFTERSENDLIVE) {
                state = States.FAINTED;
            } else {
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
                if (playerFainted || enemyFainted) {
                    state = States.FAINTED;
                    String message = null;
                    if (playerFainted && enemyFainted) {
                        message = "Both DuffelMon fainted!";
                    } else if (playerFainted) {
                        message = playerMon.getName() + " fainted!";
                    } else if (enemyFainted) {
                        message = enemyMon.getName() + " fainted!";
                    }
                    textBox = new TextBox(message, true);
                    if (enemyFainted && !playerFainted) {
                        int levelBefore = player.getCurrentMon().getLevel();
                        player.getCurrentMon().getStats().gainExperience(enemy.getCurrentMon().getStats());
                        int levelAfter = player.getCurrentMon().getLevel();
                        if (levelAfter > levelBefore) {
                            textBox.addMessage(player.getCurrentMon().getName() + " grew to level " + levelAfter + "!");
                            HashMap<Move,Integer> moveMap = player.getCurrentMon().getSpecies().getMoveMap();
                            for(Move m : moveMap.keySet()) {
                                int moveLevel = moveMap.get(m);
                                if (moveLevel > levelBefore && moveLevel < levelAfter) {
                                    player.getCurrentMon().learnMove(m);
                                    textBox.addMessage(player.getCurrentMon().getName() + " learned " + m.getName() + "!");
                                }
                            }
                        }
                    }
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
}
