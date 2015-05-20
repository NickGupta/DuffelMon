package duffelmon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
            fire.addRelationship(fire, 0);
            fire.addRelationship(water, 2);
            fire.addRelationship(ice, 2);
            fire.addRelationship(air, 2);
            fire.addRelationship(electric, 0);
            fire.addRelationship(plant, 0.5);
            water.addRelationship(fire, 0.5);
            water.addRelationship(electric, 2);
            water.addRelationship(poison, 2);
            
            //Status effect types
            StatusEffectType.makeEffect(new StatusEffectType("Increase Attack") {
                
            });
            
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
                            waitUntilNextMoveStep(uDisplay, 10);
                            break;
                        case 1:
                            basicDamageAttempt(uDisplay, tDisplay);
                            MoveEffect moveEffect = new MoveEffect(tDisplay.getX(), tDisplay.getY());
                            moveEffect.addMoveTexture(new Texture("movesprites/tackle.png"), "tackle");
                            uDisplay.setXSpeed(-4);
                            nextMoveStep(uDisplay);
                            waitUntilNextMoveStep(uDisplay, 10);
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
            Move shock = Move.makeMove(new Move("Shock", electric, true, 25, 1, 35, 0) {
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
            // Should slow the speed of the mon hit and do a small amount of damage aswell
            Move coldTouch = Move.makeMove(new Move("Cold Touch", ice, true, 25, 1, 35, 0) {
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
            Move snowBall = Move.makeMove(new Move("Snow Ball", ice, true, 25, 1, 35, 0) {
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
            // steadys the mon on the ground preparing to be hit, raises defence
            Move steady = Move.makeMove(new Move("Steady", earth, false, 25, 1, 35, 0) {
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
            Move gust = Move.makeMove(new Move("Gust", air, true, 25, 1, 35, 0) {
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
            // does poisen type damage but has no chance of applying a DOT effect
            Move sting = Move.makeMove(new Move("Sting", poison, true, 25, 1, 35, 0) {
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
            // does a little but of damage, but has a high chance of applying a DOT
            Move poisonDart = Move.makeMove(new Move("Poison Dart", poison, true, 25, 1, 35, 0) {
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
            // does dmg and has a small chance of slowing the targets speed
            Move iceBlast = Move.makeMove(new Move("Ice Blast", ice, true, 25, 1, 35, 0) {
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
            // blows a very cold gust of wind at the target
            // does little damage, but has a very high chance to slow speed and damage
            Move northernWinds = Move.makeMove(new Move("Northern Winds", ice, true, 25, 1, 35, 0) {
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
            // covers the mon in a layer of string ice to raise defence
            Move iceShield = Move.makeMove(new Move("Ice Shield", ice, false, 25, 1, 35, 0) {
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
            Move inferno = Move.makeMove(new Move("Inferno", fire, true, 25, 1, 35, 0) {
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
            // the mons thick skin to defend against the fire they use to
            // attack also helps defend against other things raises defence
            Move thickSkin = Move.makeMove(new Move("Thick Skin", fire, true, 25, 1, 35, 0) {
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
            Move thunderBolt = Move.makeMove(new Move("Thunder Bolt", electric, true, 25, 1, 35, 0) {
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
            Move lightningStrike = Move.makeMove(new Move("Lightning Strike", electric, true, 25, 1, 35, 0) {
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
            // raises the attack of the dufflemon
            Move overCharge = Move.makeMove(new Move("Over Charge", electric, false, 25, 1, 35, 0) {
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
            Move waterGun = Move.makeMove(new Move("Water Gun", water, true, 25, 1, 35, 0) {
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
            Move waterBlast = Move.makeMove(new Move("Water Blast", water, true, 25, 1, 35, 0) {
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
            // dufflemon hydrates themself, gaining stats
            Move hydrate = Move.makeMove(new Move("Hydrate", water, false, 25, 1, 35, 0) {
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
            Move rockThrow = Move.makeMove(new Move("Rock Throw", earth, true, 25, 1, 35, 0) {
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
