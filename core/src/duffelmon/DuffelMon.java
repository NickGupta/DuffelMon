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
            GlobalData.makeTexture("duffelBagFront", "duffelbag_front.png");
            GlobalData.makeTexture("duffelBagBack", "duffelbag_back.png");
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
            normal.addRelationship(fire, 2);
            normal.addRelationship(steel, 2);
            normal.addRelationship(bug, 0.5);
            
            fire.addRelationship(water, 2);
            fire.addRelationship(air, 2);
            fire.addRelationship(plant, 0.5);
            
            water.addRelationship(poison, 2);
            water.addRelationship(ice, 2);
            water.addRelationship(fire, 0.5);
            
            ice.addRelationship(fire, 2);
            ice.addRelationship(light, 2);
            ice.addRelationship(water, 0.5);
            
            earth.addRelationship(water, 2);
            earth.addRelationship(flying, 2);
            earth.addRelationship(light, 0.5);
            
            air.addRelationship(earth, 2);
            air.addRelationship(bug, 2);
            air.addRelationship(flying, 0.5);
            
            electric.addRelationship(plant, 2);
            electric.addRelationship(earth, 2);
            electric.addRelationship(bug, 0.5);
            
            light.addRelationship(bug, 2);
            light.addRelationship(flying, 2);
            light.addRelationship(ice, 0.5);
            
            steel.addRelationship(electric, 2);
            steel.addRelationship(light, 2);
            steel.addRelationship(steel, 0.5);
            
            poison.addRelationship(plant, 2);
            poison.addRelationship(steel, 2);
            poison.addRelationship(poison, 0.5);
            
            flying.addRelationship(air, 2);
            flying.addRelationship(ice, 2);
            flying.addRelationship(earth, 0.5);
            
            plant.addRelationship(fire, 2);
            plant.addRelationship(steel, 2);
            plant.addRelationship(electric, 0.5);
            
            bug.addRelationship(poison, 2);
            bug.addRelationship(electric, 2);
            bug.addRelationship(air, 0.5);
            
            //Status effect types
            StatusEffectType.makeEffectType(new StatusEffectType("Increase Attack") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Attack rose!";
                }
                @Override
                public double attackEffect() {
                    return 3.0/2.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Decrease Attack") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Attack fell!";
                }
                @Override
                public double attackEffect() {
                    return 2.0/3.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Increase Defense") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Defense rose!";
                }
                @Override
                public double defenseEffect() {
                    return 3.0/2.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Decrease Defense") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Defense fell!";
                }
                @Override
                public double defenseEffect() {
                    return 2.0/3.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Increase Speed") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Speed rose!";
                }
                @Override
                public double speedEffect() {
                    return 3.0/2.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Decrease Speed") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Speed fell!";
                }
                @Override
                public double speedEffect() {
                    return 2.0/3.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Increase Attitude") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Attitude rose!";
                }
                @Override
                public double attitudeEffect() {
                    return 3.0/2.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Decrease Attitude") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Attitude fell!";
                }
                @Override
                public double attitudeEffect() {
                    return 2.0/3.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Increase Accuracy") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Accuracy rose!";
                }
                @Override
                public double accuracyEffect() {
                    return 4.0/3.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Decrease Accuracy") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Accuracy fell!";
                }
                @Override
                public double accuracyEffect() {
                    return 3.0/4.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Increase Evasion") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Evasion rose!";
                }
                @Override
                public double evasionEffect() {
                    return 4.0/3.0;
                }
            });
            StatusEffectType.makeEffectType(new StatusEffectType("Decrease Evasion") {
                @Override
                public String getApplicationMessage(MonDisplay tDisplay) {
                    return tDisplay.getMon().getName() + "'s Evasion fell!";
                }
                @Override
                public double evasionEffect() {
                    return 3.0/4.0;
                }
            });
            
            //Utility moves
            Move.makeMove(new Move("ChangeMons", normal, false, 0, 1, 0, 100) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            nextMoveStep(uDisplay);
                            waitUntilNextMoveStep(uDisplay, 30);
                            break;
                        case 1:
                            uDisplay.becomeDuffelBag();
                            nextMoveStep(uDisplay);
                            waitUntilNextMoveStep(uDisplay, 30);
                            break;
                        case 2:
                            uDisplay.getCombatant().setMon(uDisplay.getCombatant().getMoveSlotToUse());
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move.makeMove(new Move("Escape", normal, false, 0, 1, 0, 100) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            String message;
                            if (tDisplay.getCombatant().isTrainer()) {
                                message = "But " + uDisplay.getMon().getName() + " can't run from a Trainer battle!";
                            } else {
                                message = uDisplay.getMon().getName() + " successfully escaped!";
                                uDisplay.runAway();
                            }
                            uDisplay.addMoveMessage(message);
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
            Move punch = Move.makeMove(new Move("Punch", normal, true, 20, 1, 10, 0) {
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
            Move slap = Move.makeMove(new Move("Slap", normal, true, 30, 1, 5, 0) {
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
            Move crush = Move.makeMove(new Move("Crush", normal, true, 12.5, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            basicDamageAttempt(uDisplay, tDisplay);
                            inflictStatusEffectAttempt(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Speed"), 5, 0.5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move swat = Move.makeMove(new Move("Swat", normal, true, 15, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            basicDamageAttempt(uDisplay, tDisplay);
                            inflictStatusEffectAttempt(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Speed"), 5, 0.5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move bodySlam = Move.makeMove(new Move("Body Slam", earth, true, 30, .8, 5, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            basicDamageAttempt(uDisplay, tDisplay);
                            inflictStatusEffectAttempt(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Speed"), 5, 0.5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move charge = Move.makeMove(new Move("Charge", normal, true, 40, 0.8, 5, 0) {
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
            Move bite = Move.makeMove(new Move("Bite", steel, true, 20, 1, 10, 0) {
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
                            for(int i = 0; i < 2; i++) {
                                inflictStatusEffect(uDisplay, uDisplay, StatusEffectType.getEffectType("Increase Defense"), 3);
                            }
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move massage = Move.makeMove(new Move("Massage", normal, false, 0, 1, 5, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            inflictStatusEffect(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Attack"), 5);
                            inflictStatusEffect(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Speed"), 5);
                            tDisplay.getMon().increaseHealth(12.5);
                            uDisplay.addMoveMessage(tDisplay.getMon().getName() + " was healed slightly!");
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move furySlash = Move.makeMove(new Move("Fury Slash", steel, true, 20, 0.5, 5, 0) {
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
                            if (basicDamageAttempt(uDisplay, tDisplay)) {
                                inflictStatusEffect(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Speed"), 5);
                            }
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
            Move liftoff = Move.makeMove(new Move("Liftoff", air, false, 0, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            inflictStatusEffect(uDisplay, uDisplay, StatusEffectType.getEffectType("Increase Evasion"), 5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move tailWind = Move.makeMove(new Move("Tail Wind", air, false, 0, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            inflictStatusEffect(uDisplay, uDisplay, StatusEffectType.getEffectType("Increase Speed"), 5);
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
            Move indigestion = Move.makeMove(new Move("Indigestion", poison, true, 25, 1, 5, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            if (basicDamageAttempt(uDisplay, tDisplay)) {
                                inflictStatusEffectAttempt(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Evasion"), 5, 0.8);
                            }
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move toxicGas = Move.makeMove(new Move("Toxic Gas", poison, false, 0, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            inflictStatusEffect(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Attack"), 5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move parasiteSpore = Move.makeMove(new Move("Parasite Spore", poison, false, 0, 1, 5, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            if (Math.random() < 0.75) {
                                inflictStatusEffect(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Attack"), 5);
                                inflictStatusEffect(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Evasion"), 5);
                            }
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
                            if (basicDamageAttempt(uDisplay, tDisplay)) {
                                inflictStatusEffectAttempt(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Speed"), 5, 0.2);
                            }
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            // blows a very cold gust of wind at the target
            // does little damage, but has a very high chance to slow speed and damage
            Move blizzard = Move.makeMove(new Move("Blizzard", ice, true, 10, 1, 5, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            if (basicDamageAttempt(uDisplay, tDisplay)) {
                                inflictStatusEffect(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Speed"), 5);
                                inflictStatusEffectAttempt(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Attack"), 5, 0.5);
                            }
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
            Move thunderBolt = Move.makeMove(new Move("Thunder Bolt", electric, true, 40, 1, 5, 0) {
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
            Move tase = Move.makeMove(new Move("Tase", electric, true, 45, .9, 5, 0) {
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
            Move segway = Move.makeMove(new Move("Segway", electric, true, 20, .75, 10, 0) {
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
            Move douse = Move.makeMove(new Move("Douse", water, true, 30, .9, 10, 0) {
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
            Move buzz = Move.makeMove(new Move("Buzz", bug, true, 15, 1, 15, 0) {
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
            Move flash = Move.makeMove(new Move("Flash", light, false, 0, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            inflictStatusEffect(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Accuracy"), 5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move auroraLaser = Move.makeMove(new Move("Aurora Laser", light, true, 40, 1, 5, 0) {
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
            Move melanoma = Move.makeMove(new Move("Melanoma", light, true, 45, 0.5, 5, 0) {
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
            Move taxi = Move.makeMove(new Move("Taxi", flying, false, 0, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            inflictStatusEffect(uDisplay, tDisplay, StatusEffectType.getEffectType("Decrease Speed"), 5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move seed = Move.makeMove(new Move("Seed", plant, true, 20, 1, 10, 0) {
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
            Move squeak = Move.makeMove(new Move("Squeak", normal, true, 0, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            inflictStatusEffect(uDisplay, uDisplay, StatusEffectType.getEffectType("Decrease Accuracy"), 5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move tailWhirl = Move.makeMove(new Move("Tail Whirl", normal, true, 15, 1, 10, 1) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move scratch = Move.makeMove(new Move("Scratch", normal, true, 25, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move impale = Move.makeMove(new Move("Impale", bug, true, 30, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move bobAndWeave = Move.makeMove(new Move("BobAndWeave", flying, false, 0, 1, 10, 2) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            inflictStatusEffect(uDisplay, uDisplay, StatusEffectType.getEffectType("Increase Evasion"), 5);
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move shellSmash = Move.makeMove(new Move("Shell Smash", normal, true, 5, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move airFist = Move.makeMove(new Move("Air Fist", air, true, 15, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            Move diveBomb = Move.makeMove(new Move("Dive Bomb", flying, true, 30, 1, 10, 0) {
                @Override
                public void doMoveStep(MonDisplay uDisplay, MonDisplay tDisplay, int step) {
                    switch(step) {
                        case 0:
                            finishMove(uDisplay);
                            break;
                    }
                }
            });
            
            //Mons
            HashMap<Move,Integer> moveset = new HashMap<Move,Integer>();
            moveset.put(tackle, 1);
            moveset.put(spark, 5);
            //Species.makeSpecies("Charmander", new BaseStats(55, 40, 65, 50), fire, moveset);
            
            moveset = new HashMap<Move,Integer>();
            moveset.put(tackle, 1);
            //Species.makeSpecies("Kingdra", new BaseStats(80, 75, 65, 80), water, moveset);
            
            Type[] types = new Type[2];
            types[0] = bug;
            types[1] = light;
            moveset = new HashMap<Move,Integer>();
            moveset.put(tackle, 1);
            moveset.put(harden, 4);
            moveset.put(bugBite, 7);
            moveset.put(gammaBurst, 10);
            Species.makeSpecies("Pulsect", new BaseStats(50, 70, 70, 40), types, moveset);
            
            moveset = new HashMap<Move,Integer>();
            moveset.put(tackle, 1);
            moveset.put(swipe, 4);
            moveset.put(guard, 7);
            moveset.put(furySlash, 10);
            Species.makeSpecies("Scythera", new BaseStats(60, 40, 70, 60), normal, moveset);
            
            types[0] = ice;
            types[1] = flying;
            moveset = new HashMap<Move,Integer>();
            moveset.put(gust, 1);
            moveset.put(coldTouch, 4);
            moveset.put(liftoff, 7);
            moveset.put(blizzard, 10);
            Species.makeSpecies("Flurricane", new BaseStats(70, 50, 40, 70), types, moveset);
            
            moveset = new HashMap<Move,Integer>();
            moveset.put(tackle, 1);
            moveset.put(toxicGas, 4);
            moveset.put(charge, 7);
            moveset.put(parasiteSpore, 10);
            Species.makeSpecies("Mooshroom", new BaseStats(60, 50, 50, 70), poison, moveset);
            
            moveset = new HashMap<Move,Integer>();
            moveset.put(punch, 1);
            moveset.put(harden, 4);
            moveset.put(bugBite, 7);
            moveset.put(massage, 10);
            Species.makeSpecies("Massant", new BaseStats(70, 40, 60, 60), bug, moveset);
            
            moveset = new HashMap<Move,Integer>();
            moveset.put(bite, 1);
            moveset.put(flash, 4);
            moveset.put(crush, 7);
            moveset.put(auroraLaser, 10);
            Species.makeSpecies("Auroralisk", new BaseStats(60, 50, 70, 50), light, moveset);
            
            moveset = new HashMap<Move,Integer>();
            moveset.put(waterGun, 1);
            moveset.put(douse, 4);
            moveset.put(hydrate, 7);
            moveset.put(tackle, 10);
            Species.makeSpecies("Spongerob", new BaseStats(50, 60, 60, 60), water, moveset);
            
            moveset = new HashMap<Move,Integer>();
            moveset.put(tackle, 1);
            moveset.put(steady, 4);
            moveset.put(bodySlam, 7);
            moveset.put(slap, 10);
            Species.makeSpecies("Vince Mon", new BaseStats(60, 60, 70, 40), earth, moveset);
            
            moveset = new HashMap<Move,Integer>();
            moveset.put(tackle, 1);
            moveset.put(bite, 4);
            moveset.put(swat, 7);
            moveset.put(slap, 10);
            Species.makeSpecies("Pawprince", new BaseStats(60, 60, 60, 50), normal, moveset);
            
            types[0] = bug;
            types[1] = flying;
            moveset = new HashMap<Move,Integer>();
            moveset.put(bugBite, 1);
            moveset.put(liftoff, 4);
            moveset.put(buzz, 7);
            moveset.put(harden, 10);
            Species.makeSpecies("Margarinefree", new BaseStats(70, 60, 70, 10), types, moveset);
            /*
            moveset = new HashMap<Move,Integer>();
            moveset.put(tackle, 1);
            moveset.put(tase, 4);
            moveset.put(melanoma, 7);
            moveset.put(slap, 10);
            Species.makeSpecies("Lampface", new BaseStats(60, 70, 50, 50), light, moveset);
            
            moveset = new HashMap<Move,Integer>();
            moveset.put(slap, 1);
            moveset.put(segway, 4);
            moveset.put(tase, 7);
            moveset.put(slap, 10);
            Species.makeSpecies("Paul Blart", new BaseStats(60, 70, 50, 50), electric, moveset);
            
            types[0] = poison; //Why is a taco snail poisonous
            types[1] = flying; //Why does a taco snail fly
            moveset = new HashMap<Move,Integer>();
            moveset.put(indigestion, 1);
            moveset.put(sting, 4);
            moveset.put(taxi, 7);
            moveset.put(slap, 10);
            Species.makeSpecies("Taco Snail", new BaseStats(60, 70, 50, 50), types, moveset);
            
            moveset = new HashMap<Move,Integer>();
            moveset.put(tackle, 1);
            moveset.put(seed, 4);
            moveset.put(bodySlam, 7);
            moveset.put(slap, 10);
            Species.makeSpecies("Cabbage 
            face", new BaseStats(60, 70, 50, 50), plant, moveset);
            
            moveset = new HashMap<Move, Integer>();
            moveset.put(tackle, 1);
            moveset.put(squeack, 4);
            moveset.put(tailWhirl, 7);
            moveset.put(scratch, 10);
            Species.makeSpecies("Scurrymon", new BaseStats(60, 70, 50, 50), normal, moveset);
            
            moveset = new HashMap<Move, Integer>();
            moveset.put(tackle, 1);
            moveset.put(poisonSting, 4);
            moveset.put(bugBite, 7);
            moveset.put(impale, 10);
            types[0] = bug;
            types[1] = poison;
            Species.makeSpecies("Scorpio", new BaseStats(60, 70, 50, 50), types, moveset);
            
            moveset = new HashMap<Move, Integer>();
            moveset.put(tackle, 1);
            moveset.put(spark, 4);
            moveset.put(bobAndWeave, 7);
            moveset.put(inferno, 10);
            types[0] = fire;
            types[1] = flying;
            Species.makeSpecies("Pyrofly", new BaseStats(60, 70, 50, 50), types, moveset);
            
            moveset = new HashMap<Move, Integer>();
            moveset.put(tackle, 1);
            moveset.put(guard, 4);
            moveset.put(bite, 7);
            moveset.put(charge, 10);
            Species.makeSpecies("Ravageros", new BaseStats(60, 70, 50, 50), steel, moveset);
            
            moveset = new HashMap<Move, Integer>();
            moveset.put(tackle, 1);
            moveset.put(harden, 4);
            moveset.put(guard, 7);
            moveset.put(shellSmash, 10);
            types[0] = bug;
            types[1] = steel;
            Species.makeSpecies("Rolypoly", new BaseStats(60, 70, 50, 50), types, moveset);
            
            moveset = new HashMap<Move, Integer>();
            moveset.put(tackle, 1);
            moveset.put(snowball, 4);
            moveset.put(crush, 7);
            moveset.put(charge, 10);
            Species.makeSpecies("Snowstorm Yeti", new BaseStats(60, 70, 50, 50), ice, moveset);
            
            moveset = new HashMap<Move, Integer>();
            moveset.put(tackle, 1);
            moveset.put(iceShield, 4);
            moveset.put(iceBlast, 7);
            moveset.put(blizzard, 10);
            Species.makeSpecies("Glacier Scuttler", new BaseStats(60, 70, 50, 50), ice, moveset);
            
            moveset = new HashMap<Move, Integer>();
            moveset.put(shock, 1);
            moveset.put(lightningStrike, 4);
            moveset.put(overCharge, 7);
            moveset.put(thunderbolt, 10);
            types[0] = electric;
            types[1] = light;
            Species.makeSpecies("Eclectrode", new BaseStats(60, 70, 50, 50), types, moveset);
            
            moveset = new HashMap<Move, Integer>();
            moveset.put(tackle, 1);
            moveset.put(gust, 4);
            moveset.put(airFist, 7);
            moveset.put(diveBomb, 10);
            Species.makeSpecies("Babloon", new BaseStats(60, 70, 50, 50), flying, moveset);
            */
        }
}
