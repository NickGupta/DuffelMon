/*Represents a single item
*/
package duffelmon;


/**
 *
 * @author Jonathan
 */
public class Item {
    
    private String name;
    
    public Item(String n) {
        name = n;
    }
    
    public String getName() {
        return name;
    }
    
    public Move getMove() {
        return Move.getMove("Item_" + name);
    }
    
}
