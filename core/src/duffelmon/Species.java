/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import com.badlogic.gdx.graphics.Texture;

public class Species {
    
    private static HashMap<String,Species> speciesMap = new HashMap<String,Species>();
    
    private String name;
    private MonTextures textures;
    private Type[] types = null;
    private HashMap<Move,Integer> moveMap = null;
    
    private Species(String n, Type[] t, HashMap<Move,Integer> m) {
        name = n;
        Texture texf = new Texture("monsprites/" + name + "_front.png");
        Texture texb = new Texture("monsprites/" + name + "_back.png");
        textures = new MonTextures(texf, texb);
        types = t;
        moveMap = m;
    }
    
    public static Species makeSpecies(String n, Type t, HashMap<Move,Integer> m) {
        Type[] tArray = {t};
        return makeSpecies(n, tArray, m);
    }
    
    public static Species makeSpecies(String n, Type[] t, HashMap<Move,Integer> m) {
        Species s = new Species(n, t, m);
        speciesMap.put(n, s);
        return s;
    }
    
    public static Species getSpecies(String s) {
        return speciesMap.get(s);
    }
    
    public String getName() {
        return name;
    }
    
    public Type[] getTypes() {
        return types;
    }
    
    public HashMap<Move,Integer> getMoveMap() {
        return moveMap;
    }
    
    public MonTextures getTextures() {
        return textures;
    }
    
    public Move[] generateMoves(int l) {
        ArrayList<Move> moveList = new ArrayList<Move>();
        Set<Move> moveSet = moveMap.keySet();
        int level = l;
        while (level > 0 && moveList.size() < 4) {
            for(Move m : moveSet) {
                if (moveMap.get(m) == l) {
                    moveList.add(m);
                }
                if (moveList.size() == 4) {
                    break;
                }
            }
            level--;
        }
        Move[] moveArray = new Move[4];
        for(int i = 0; i < moveList.size(); i++) {
            moveArray[i] = moveList.get(i);
        }
        return moveArray;
    }
    
}