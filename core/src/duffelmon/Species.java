/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.*;

public class Species {
    
    private static TreeMap<String,Species> speciesMap = new TreeMap<String,Species>();
    private static Species defaultSpecies = new Species("DefaultSpecies", new TreeMap<Integer,Move>());
    
    private String name;
    private TreeMap<Integer,Move> moveMap = null;
    
    private Species(String n, TreeMap<Integer,Move> m) {
        name = n;
        moveMap = m;
    }
    
    public static Species makeSpecies(String n) {
        Species s = new Species(n, new TreeMap<Integer,Move>());
        speciesMap.put(n, s);
        return s;
    }
    
    public static Species getSpecies(String s) {
        return speciesMap.get(s);
    }
    
    public static Species getDefaultSpecies() {
        return defaultSpecies;
    }
    
    public static Move[] generateMoves(Species s, int l) {
        Move[] m = {null, null, null, null};
        return m;
    }
    
    public String getName() {
        return name;
    }
    
}