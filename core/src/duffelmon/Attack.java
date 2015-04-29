/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;
import java.util.*;
/**
 *
 * @author csstudent
 */
public class Attack {
    
    private static TreeMap<String,Attack> map = new TreeMap<String,Attack>();
    
    private String name;
    private double damage;
    
    
    private Attack(String n, double d){
        name = n;
        damage = d;
    }
    
    public static Attack makeAttack(String n, double d) {
        Attack a = new Attack(n, d);
        map.put(n, a);
        return a;
    }
    
    public static Attack getAttack(String s) {
        return map.get(s);
    }
    
    public String getName() {
        return name;
    }
    
    public double getDamage() {
        return damage;
    }
}
