package duffelmon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import java.util.HashMap;

public class DuffelMon extends ApplicationAdapter {
        
        float framesPerSecond = 60;
        float elapsedTime = 0;
        
        @Override
	public void create () {
                initializeData();
                
                // creates the first dufflemon for the battle
                Mon player = new Mon(null, Species.getSpecies("Charmander"), 10);

                // creates the second dufflemon for the battle
                Mon enemy = new Mon(null, Species.getSpecies("Kingdra"), 8);

                GlobalData.initialize(new Stage(), new BitmapFont());
                Battle.startBattle(player, null, enemy, new RandomMoveAI());
	}
        
	@Override
	public void render () {
            elapsedTime += Gdx.graphics.getDeltaTime();
            while (elapsedTime > 0) {
                frameActions();
                elapsedTime -= 1.0/framesPerSecond;
            }
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            GlobalData.getStage().draw();
	}
        
        private void frameActions() {
            GlobalData.updateInputs();
            GameObject.runFrameActions();
        }
        
        private void initializeData() {
            Type normal = Type.makeType("Normal");
            Type heat = Type.makeType("Heat");
            Type water = Type.makeType("Water");
            Type dragon = Type.makeType("Dragon");
            Type electricity = Type.makeType("Electricity");
            Type rock = Type.makeType("Rock");
            Type flying = Type.makeType("Flying");
            Type bug = Type.makeType("Bug");
            Type fight = Type.makeType("Fight");
            Type poison = Type.makeType("Poison");
            
            
            
            fight.addRelationship(rock, 0.8);
            fight.addRelationship(bug, 1.2);
            flying.addRelationship(fight, 0.7);
            flying.addRelationship(rock, 1.7);
            flying.addRelationship(bug, 0.6);
            flying.addRelationship(electricity, 1.8);
            poison.addRelationship(poison, 0.5);
            rock.addRelationship(normal, 0.5);
            rock.addRelationship(flying, 0.5);
            rock.addRelationship(poison, 0.5);
            rock.addRelationship(heat, 0.5);
            rock.addRelationship(water, 1.2);
            bug.addRelationship(fight, 0.9);
            bug.addRelationship(flying, 1.6);
            bug.addRelationship(rock, 2);
            bug.addRelationship(heat, 2);
            heat.addRelationship(water, 2);
            heat.addRelationship(normal, 1.7);
            heat.addRelationship(rock, 1.6);
            heat.addRelationship(bug, 0.7);
            heat.addRelationship(heat, 0.7);
            water.addRelationship(water, 0.5);
            water.addRelationship(electricity, 1.5);
            water.addRelationship(heat, 0.5);
            electricity.addRelationship(flying, 0.5);
            electricity.addRelationship(rock, 0.8);
            electricity.addRelationship(electricity, 0.7);
            dragon.addRelationship(heat, 0.5);
            dragon.addRelationship(water, 0.5);
            dragon.addRelationship(electricity, 0.5);
            dragon.addRelationship(dragon, 0.7);
            
            Move.makeMove(new Move("Item_Potion", normal, false, 0, 1, 0, 100) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            uDisplay.getMon().increaseHealth(20);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move tackle = Move.makeMove(new Move("Tackle", normal, true, 25, 1, 35, 0) {
             
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        /*
                        case 0:
                            uDisplay.setXSpeed(4);
                            nextMoveStep(uDisplay);
                            waitUntilNextMoveStep(uDisplay, 30);
                            break;
                        case 1:
                            absoluteDamage(tDisplay, getDamage());
                            uDisplay.setXSpeed(-4);
                            nextMoveStep(uDisplay);
                            waitUntilNextMoveStep(uDisplay, 30);
                            break;
                        case 2:
                            uDisplay.setXSpeed(0);
                            finishMove(uDisplay);
                            break;
                        */
                        case 0:
                            basicDamageAttempt(uDisplay, tDisplay);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move thunderbolt = Move.makeMove(new Move("Winter Static Shock", electricity, true, 25, 1, 35, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            basicDamageAttempt(uDisplay, tDisplay);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            HashMap<Move,Integer> moveset = new HashMap<Move,Integer>();
            HashMap<Move,Integer> pikamoveset = new HashMap<Move,Integer>();
            moveset.put(tackle, 1);
            Type[] typeset = {water, dragon};
            Species.makeSpecies("Charmander", new BaseStats(55, 40, 65, 50), heat, moveset);
            Species.makeSpecies("Kingdra", new BaseStats(80, 75, 65, 80), typeset, moveset);
            pikamoveset.put(thunderbolt, 2);
            
        }
}
