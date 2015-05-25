/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import java.util.HashMap;

/**
 *
 * @author csstudent
 */
public class Type {
    
    private static HashMap<String,Type> typeMap = new HashMap<String,Type>();
    
    private String name;
    private HashMap<Type,Double> relationships = new HashMap<Type,Double>();
    
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
    
    public String getName() {
        return name;
    }
    
    public double getRelationship(Type t) {
        Double r = relationships.get(t);
        if (r == null) {
            return 1;
        }
        return r;
    }
    
    public void addRelationship(Type t, double m) {
        relationships.put(t, m);
    }
    
}
