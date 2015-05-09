/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.TreeMap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Species {
    
    private static TreeMap<String,Species> speciesMap = new TreeMap<String,Species>();
    
    private String name;
    private MonSprite sprite;
    private Type[] types = null;
    private TreeMap<Move,Integer> moveMap = null;
    
    private Species(String n, Type[] t, TreeMap<Move,Integer> m) {
        name = n;
        Texture texf = null; //new Texture("monsprites/" + name + "_front.png");
        Texture texb = null; // Texture("monsprites/" + name + "_back.png");
        sprite = null; //new MonSprite(new Sprite(texf), new Sprite(texb));
        types = t;
        moveMap = m;
    }
    
    public static Species makeSpecies(String n, Type t, TreeMap<Move,Integer> m) {
        Type[] tArray = {t};
        return makeSpecies(n, tArray, m);
    }
    
    public static Species makeSpecies(String n, Type[] t, TreeMap<Move,Integer> m) {
        Species s = new Species(n, t, m);
        speciesMap.put(n, s);
        return s;
    }
    
    public static Species getSpecies(String s) {
        return speciesMap.get(s);
    }
    
    public static Move[] generateMoves(Species s, int l) {
        Move[] m = new Move[4];
        return m;
    }
    
    public String getName() {
        return name;
    }
    
    public Type[] getTypes() {
        return types;
    }
    
    public TreeMap<Move,Integer> getMoveMap() {
        return moveMap;
    }
    
    public MonSprite getSprite() {
        return sprite;
    }
    
}