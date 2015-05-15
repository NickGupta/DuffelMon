/*Represents a single item
*/
package duffelmon;


/**
 *
 * @author Jonathan
 */
public class Item {
    
    private String name;
    private int amount;
    
    public Item(String n, int a) {
        name = n;
        amount = a;
    }
    
    public Item(String n) {
        this(n, 1);
    }
    
    public String getName() {
        return name;
    }
    
    public Move getMove() {
        return Move.getMove("Item_" + name);
    }
    
    public int getAmount() {
        return amount;
    }
    
    public void incrementAmount() {
        amount++;
    }
    
    public void decrementAmount() {
        amount--;
    }
    
}
