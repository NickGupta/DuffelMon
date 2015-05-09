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
    private int powerPoints;
    
    private Move(String n, double d, double a, int p) {
        name = n;
        damage = d;
        accuracy = a;
        powerPoints = p;
    }
    
    public static Move makeMove(String n, double d, double a, int p) {
        Move m = new Move(n, d, a, p);
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
    
    public double getAccuracy() {
        return accuracy;
    }
    
    public int getPowerPoints() {
        return powerPoints;
    }
    
    public void useInBattle(Mon user, Mon uDisplay, Mon target, MonDisplay tDisplay) {}
    
}
