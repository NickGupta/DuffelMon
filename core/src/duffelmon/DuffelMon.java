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
                Mon pMon = new Mon(null, Species.getSpecies("Charmander"), 10);
                
                Player player = new Player();
                player.addMon(pMon);
                GlobalData.setPlayer(player);
                
                // creates the second dufflemon for the battle
                Mon enemy = new Mon(null, Species.getSpecies("Kingdra"), 8);

                GlobalData.initialize(new Stage(), new BitmapFont());
                Battle.startBattle(enemy, new RandomMoveAI());
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
            //Types
            Type normal = Type.makeType("Normal");
            Type fire = Type.makeType("Fire");
            Type water = Type.makeType("Water");
            Type ice = Type.makeType("Ice");
            Type earth = Type.makeType("Earth");
            Type air = Type.makeType("Air");
            Type electric = Type.makeType("Electric");
            Type steel = Type.makeType("Steel");
            Type poison = Type.makeType("Poison");
            Type flying = Type.makeType("Flying");
            Type plant = Type.makeType("Plant");
            Type bug = Type.makeType("Bug");
            
            //Type effectiveness
            fire.addRelationship(fire, 0.5);
            fire.addRelationship(water, 2);
            fire.addRelationship(ice, 2);
            fire.addRelationship(air, 2);
            fire.addRelationship(electric, 0.5);
            fire.addRelationship(plant, 0.5);
            water.addRelationship(fire, 0.5);
            water.addRelationship(electric, 2);
            water.addRelationship(poison, 2);
            
            //Item moves
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
            //Moves
            Move struggle = Move.makeMove(new Move("Struggle", normal, true, 12.5, 0.5, 35, 0) {
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
            Move tackle = Move.makeMove(new Move("Tackle", normal, true, 25, 1, 35, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            uDisplay.setXSpeed(4);
                            nextMoveStep(uDisplay);
                            waitUntilNextMoveStep(uDisplay, 30);
                            break;
                        case 1:
                            basicDamageAttempt(uDisplay, tDisplay);
                            uDisplay.setXSpeed(-4);
                            nextMoveStep(uDisplay);
                            waitUntilNextMoveStep(uDisplay, 30);
                            break;
                        case 2:
                            uDisplay.setXSpeed(0);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move spark = Move.makeMove(new Move("Spark", fire, true, 25, 1, 35, 0) {
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
            //Mons
            HashMap<Move,Integer> moveset = new HashMap<Move,Integer>();
            moveset.put(tackle, 1);
            moveset.put(spark, 5);
            Species.makeSpecies("Charmander", new BaseStats(55, 40, 65, 50), fire, moveset);
            moveset = new HashMap<Move,Integer>();
            moveset.put(tackle, 1);
            Species.makeSpecies("Kingdra", new BaseStats(80, 75, 65, 80), water, moveset);
        }
}
