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
                Mon player = new Mon("Bob", Species.getSpecies("Charmander"), 10);

                // creates the second dufflemon for the battle
                Mon enemy = new Mon("Joe", Species.getSpecies("Kingdra"), 5);

                GlobalData.initialize(new Stage(), new BitmapFont());
                TextBox blah = new TextBox(275, 100, "Hello World");
                GameObject.makeIndependent(blah);
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
            heat.addRelationship(water, 2);
            water.addRelationship(heat, 0.5);
            dragon.addRelationship(heat, 0.5);
            Move tackle = Move.makeMove(new Move("Tackle", normal, true, 40, 1, 35, 0) {
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
            HashMap<Move,Integer> moveset = new HashMap<Move,Integer>();
            moveset.put(tackle, 1);
            Type[] typeset = {water, dragon};
            Species.makeSpecies("Charmander", new Stats(55, 40, 65, 50), heat, moveset);
            Species.makeSpecies("Kingdra", new Stats(80, 75, 65, 80), typeset, moveset);
        }
}
