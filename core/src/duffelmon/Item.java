/*Represents a single item
*/
package duffelmon;


/**
 *
 * @author Jonathan
 */
public class Item {
    
    ItemType type;
  
    
    
    
   
    
    public Item(ItemType type){
        this.type = type;
    }
   
    public ItemType getType() {
        return type;
    }
    
    public Move getMove(){
        return move;
    }
}
