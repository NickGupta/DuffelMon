/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

/**
 *
 * @author csstudent
 */
public class Mon {
    
    private Species species;
    private Stats stats;
    private double health;
    
    public Mon(Species s, Stats st) {
       species = s;
       stats = st;
       health = st.getHealth();
    }
    
    public double getHealth(){
        return health;
    }
}
