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
public class ItemType {
    private String name;
    private Move move;
    
    private static HashMap<String,Move> ItemMap = new HashMap<String,Move>();
    
    public ItemType(String n, Move m){
        name = n;
        move = m;
        ItemMap.put(name, move);
    }
    
    public static Move getSpecies(String s) {
        return ItemMap.get(s);
    }
}
