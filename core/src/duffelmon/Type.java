/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.TreeMap;

/**
 *
 * @author csstudent
 */
public class Type {
    
    private static TreeMap<String,Type> typeMap = new TreeMap<String,Type>();
    
    private String name;
    private TreeMap<Type,Double> relationships = new TreeMap<Type,Double>();
    
    private Type(String n) {
        name = n;
    }
    
    public static Type makeType(String n) {
        Type t = new Type(n);
        typeMap.put(n, t);
        return t;
    }
    
    public static Type getType(String s) {
        return typeMap.get(s);
    }
    
    public TreeMap<Type,Double> getRelationships() {
        return relationships;
    }
    
    public void addRelationship(Type t, double m) {
        relationships.put(t, m);
    }
    
}
