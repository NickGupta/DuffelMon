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
public class Move {
    private static TreeMap<String,Move> moveMap = new TreeMap<String,Move>();
    private String name;
    private double damage;
    
    private Move(String n, double d) {
        name = n;
        damage = d;
    }
    
    public static Move makeMove(String n, double d) {
        Move a = new Move(n, d);
        moveMap.put(n, a);
        return a;
    }
    
    public static Move getMove(String s) {
        return moveMap.get(s);
    }
    
    public String getName() {
        return name;
    }
    
    public double getDamage() {
        return damage;
    }
    
}
