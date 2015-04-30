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
public class Move {
    private static TreeMap<String,Move> moveMap = new TreeMap<String,Move>();
    
    private String name;
    private double damage;
    private double accuracy;
    
    private Move(String n, double d, double a) {
        name = n;
        damage = d;
        accuracy = a;
    }
    
    public static Move makeMove(String n, double d, double a) {
        Move m = new Move(n, d, a);
        moveMap.put(n, m);
        return m;
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
