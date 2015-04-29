/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.*;

public class Species {
    
    private static TreeMap<String,Species> speciesMap = new TreeMap<String,Species>();
    private static Species defaultSpecies = new Species("DefaultSpecies");
    
    private String name;
    
    private Species(String n) {
        name = n;
    }
    
    public static Species makeSpecies(String n) {
        Species s = new Species(n);
        speciesMap.put(n, s);
        return s;
    }
    
    public static Species getSpecies(String s) {
        return speciesMap.get(s);
    }
    
    public static Species getDefaultSpecies() {
        return defaultSpecies;
    }
    
    public String getName() {
        return name;
    }
    
}