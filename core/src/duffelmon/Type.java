/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.TreeMap;
import java.util.ArrayList;

/**
 *
 * @author csstudent
 */
public class Type {
    
    private static TreeMap<String,Type> typeMap = new TreeMap<String,Type>();
    
    private String name;
    private ArrayList<Type> weaknesses = new ArrayList<Type>();
    private ArrayList<Type> resistances = new ArrayList<Type>();
    private ArrayList<Type> immunities = new ArrayList<Type>();
    
    private Type(String n) {
        name = n;
    }
    
    public static Type makeType(String n) {
        Type t = new Type(n);
        typeMap.put(n, t);
        return t;
    }
    
    public ArrayList<Type> getWeaknesses() {
        return weaknesses;
    }
    
    public ArrayList<Type> getResistances() {
        return resistances;
    }
    
    public ArrayList<Type> getImmunities() {
        return immunities;
    }
    
    public void addWeakness(Type t) {
        weaknesses.add(t);
    }
    
    public void addResistance(Type t) {
        resistances.add(t);
    }
    
    public void addImmunity(Type t) {
        immunities.add(t);
    }
}
