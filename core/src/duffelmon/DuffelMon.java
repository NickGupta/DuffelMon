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
                          
                MainMenu m = new MainMenu();
                GameObject.makeIndependent(m);
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
            GlobalData.initialize(new Stage(), new BitmapFont());
            
            //Textures
            GlobalData.makeTexture("largeImpact", "movesprites/largeimpact.png");
            
            //Types
            Type normal = Type.makeType("Normal");
            Type fire = Type.makeType("Fire");
            Type water = Type.makeType("Water");
            Type ice = Type.makeType("Ice");
            Type earth = Type.makeType("Earth");
            Type air = Type.makeType("Air");
            Type electric = Type.makeType("Electric");
            Type light = Type.makeType("Light");
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
            StatusEffectType.makeEffectType(new StatusEffectType("Increase Attack") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Attack rose!";
                }
                @Override
                public double attackEffect() {
                    return 6.0/5.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Decrease Attack") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Attack fell!";
                }
                @Override
                public double attackEffect() {
                    return 5.0/6.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Increase Defense") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Defense rose!";
                }
                @Override
                public double defenseEffect() {
                    return 6.0/5.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Decrease Defense") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Defense fell!";
                }
                @Override
                public double defenseEffect() {
                    return 5.0/6.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Increase Speed") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Speed rose!";
                }
                @Override
                public double speedEffect() {
                    return 6.0/5.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Decrease Speed") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Speed fell!";
                }
                @Override
                public double speedEffect() {
                    return 5.0/6.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Increase Attitude") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Attitude rose!";
                }
                @Override
                public double attitudeEffect() {
                    return 6.0/5.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Decrease Attitude") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Attitude fell!";
                }
                @Override
                public double attitudeEffect() {
                    return 5.0/6.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Increase Accuracy") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Accuracy rose!";
                }
                @Override
                public double accuracyEffect() {
                    return 6.0/5.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Decrease Accuracy") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Accuracy fell!";
                }
                @Override
                public double accuracyEffect() {
                    return 5.0/6.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Increase Evasion") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Evasion rose!";
                }
                @Override
                public double evasionEffect() {
                    return 6.0/5.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Decrease Evasion") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Evasion fell!";
                }
                @Override
                public double evasionEffect() {
                    return 5.0/6.0;
                }
            });
            
            //Utility moves
            Move.makeMove(new Move("ChangeMons", normal, false, 0, 1, 0, 100) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            uDisplay.addMoveMessage(uDisplay.getMon().getName() + " tried to use an item, but it couldn't find it!");
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move.makeMove(new Move("Item_Null", normal, false, 0, 1, 0, 100) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            uDisplay.addMoveMessage(uDisplay.getMon().getName() + " tried to use an item, but it couldn't find it!");
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            
            //Item moves
            Move.makeMove(new Move("Item_Potion", normal, false, 0, 1, 0, 100) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            uDisplay.getMon().increaseHealth(50);
                            uDisplay.addMoveMessage(uDisplay.getMon().getName() + "'s health was restored by half!");
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            
            //Moves (Note: Feel free to override the frameActions() and triggerTimer() methods in any move effects you create)
            Move struggle = Move.makeMove(new Move("Struggle", normal, true, 12.5, 0.5, 0, 0) {
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
            Move tackle = Move.makeMove(new Move("Tackle", normal, true, 20, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        /*
                        case 0:
                            uDisplay.setXSpeed(4);
                            nextMoveStep(uDisplay);
                            waitUntilNextMoveStep(uDisplay, 10);
                            break;
                        case 1:
                            basicDamageAttempt(uDisplay, tDisplay);
                            MoveEffect impact = Battle.getBattle().addMoveEffect(new MoveEffect(tDisplay.getX(), tDisplay.getY(), GlobalData.getTexture("largeImpact"), 2, true));
                            impact.setTimer("die", 10);
                            uDisplay.setXSpeed(-4);
                            nextMoveStep(uDisplay);
                            waitUntilNextMoveStep(uDisplay, 10);
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
            Move swipe = Move.makeMove(new Move("Swipe", steel, true, 20, 1, 10, 0) {
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
            Move guard = Move.makeMove(new Move("Guard", normal, false, 0, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            for(int i = 0; i < 3; i++) {
                                inflictStatusEffect(uDisplay, uDisplay, StatusEffectType.getEffectType("Increase Defense"), 1);
                            }
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move furySlash = Move.makeMove(new Move("Fury Slash", steel, true, 15, 0.5, 5, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            basicDamageAttempt(uDisplay, tDisplay);
                            uDisplay.setMoveVar(0, uDisplay.getMoveVar(0) + 1);
                            if (uDisplay.getMoveVar(0) >= 4) {
                                finishMove(uDisplay);
                            } else {
                                waitUntilNextMoveStep(uDisplay, 20);
                            }
                        break;
                    }
                }
            });
            Move spark = Move.makeMove(new Move("Spark", fire, true, 20, 1, 10, 0) {
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
            Move shock = Move.makeMove(new Move("Shock", electric, true, 20, 1, 10, 0) {
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
            Move coldTouch = Move.makeMove(new Move("Cold Touch", ice, true, 10, 1, 8, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            basicDamageAttempt(uDisplay, tDisplay);
                            inflictStatusEffect(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Speed"), 5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move snowBall = Move.makeMove(new Move("Snow Ball", ice, true, 20, 1, 10, 0) {
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
            // steadys the mon on the ground preparing to be hit, raises defense
            Move steady = Move.makeMove(new Move("Steady", earth, false, 0, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            inflictStatusEffect(uDisplay, uDisplay, StatusEffectType.getEffectType("Increase Defense"), 5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move gust = Move.makeMove(new Move("Gust", air, true, 20, 1, 10, 0) {
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
            Move sting = Move.makeMove(new Move("Sting", poison, true, 20, 1, 10, 0) {
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
            Move poisonDart = Move.makeMove(new Move("Poison Dart", poison, true, 12.5, 1, 8, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            basicDamageAttempt(uDisplay, tDisplay);
                            inflictStatusEffectAttempt(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Evasion"), 5, 0.8);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            // does dmg and has a small chance of slowing the targets speed
            Move iceBlast = Move.makeMove(new Move("Ice Blast", ice, true, 40, 1, 5, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            basicDamageAttempt(uDisplay, tDisplay);
                            inflictStatusEffectAttempt(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Speed"), 5, 0.2);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            // blows a very cold gust of wind at the target
            // does little damage, but has a very high chance to slow speed and damage
            Move northernWinds = Move.makeMove(new Move("Northern Winds", ice, true, 10, 1, 5, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            basicDamageAttempt(uDisplay, tDisplay);
                            inflictStatusEffect(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Speed"), 5);
                            inflictStatusEffectAttempt(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Attack"), 5, 0.5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            // covers the mon in a layer of string ice to raise defence
            Move iceShield = Move.makeMove(new Move("Ice Shield", ice, false, 0, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            inflictStatusEffect(uDisplay, uDisplay, StatusEffectType.getEffectType("Increase Defense"), 5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move inferno = Move.makeMove(new Move("Inferno", fire, true, 50, 0.75, 5, 0) {
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
            Move thickSkin = Move.makeMove(new Move("Thick Skin", fire, false, 0, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            inflictStatusEffect(uDisplay, uDisplay, StatusEffectType.getEffectType("Increase Defense"), 5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move thunderBolt = Move.makeMove(new Move("Thunder Bolt", electric, true, 50, 1, 10, 0) {
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
            Move lightningStrike = Move.makeMove(new Move("Lightning Strike", electric, true, 15, (2.0/3.0), 5, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            basicDamageAttempt(uDisplay, tDisplay);
                            uDisplay.setMoveVar(0, uDisplay.getMoveVar(0) + 1);
                            if (uDisplay.getMoveVar(0) >= 3) {
                                finishMove(uDisplay);
                            } else {
                                waitUntilNextMoveStep(uDisplay, 20);
                            }
                        break;
                    }
                }
            });
            // raises the attack of the dufflemon
            Move overCharge = Move.makeMove(new Move("Over Charge", electric, false, 0, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            inflictStatusEffect(uDisplay, uDisplay, StatusEffectType.getEffectType("Increase Attack"), 5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move waterGun = Move.makeMove(new Move("Water Gun", water, true, 20, 1, 10, 0) {
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
            Move waterBlast = Move.makeMove(new Move("Water Blast", water, true, 50, 0.75, 5, 0) {
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
            // shields the water dufflemon in a water bubble
            Move bubbleShield = Move.makeMove(new Move("Bubble Shield", water, false, 0, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            inflictStatusEffect(uDisplay, uDisplay, StatusEffectType.getEffectType("Increase Defense"), 5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            // dufflemon hydrates themself, gaining stats
            Move hydrate = Move.makeMove(new Move("Hydrate", water, false, 0, 1, 5, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            inflictStatusEffect(uDisplay, uDisplay, StatusEffectType.getEffectType("Increase Attack"), 5);
                            inflictStatusEffect(uDisplay, uDisplay, StatusEffectType.getEffectType("Increase Speed"), 5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move rockThrow = Move.makeMove(new Move("Rock Throw", earth, true, 20, 1, 10, 0) {
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
            Move bugBite = Move.makeMove(new Move("Bug Bite", bug, true, 20, 1, 10, 0) {
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
            Move harden = Move.makeMove(new Move("Harden", bug, false, 0, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            inflictStatusEffect(uDisplay, uDisplay, StatusEffectType.getEffectType("Increase Defense"), 5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move gammaBurst = Move.makeMove(new Move("Gamma Burst", light, true, 25, 0.75, 5, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            basicDamageAttempt(uDisplay, tDisplay);
                            uDisplay.setMoveVar(0, uDisplay.getMoveVar(0) + 1);
                            if (uDisplay.getMoveVar(0) >= 2) {
                                finishMove(uDisplay);
                            } else {
                                waitUntilNextMoveStep(uDisplay, 30);
                            }
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
            Type[] types = {bug, light};
            moveset = new HashMap<Move,Integer>();
            moveset.put(tackle, 1);
            moveset.put(harden, 4);
            moveset.put(bugBite, 7);
            moveset.put(gammaBurst, 10);
            Species.makeSpecies("Pulsect", new BaseStats(30, 50, 50, 20), types, moveset);
            moveset = new HashMap<Move,Integer>();
            moveset.put(tackle, 1);
            moveset.put(swipe, 4);
            moveset.put(guard, 7);
            moveset.put(furySlash, 10);
            Species.makeSpecies("Scythera", new BaseStats(40, 20, 50, 40), normal, moveset);
        }
}
