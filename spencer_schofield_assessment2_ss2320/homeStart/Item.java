
/**
 * Enumeration class Item
 * The items in the game.
 * 
 * @author Olaf Chitil
 * @version 19/2/2019
 */
public enum Item
{
    FLOUR("flour"),

    SUGAR("sugar"),

    EGG("egg");
    
    private String item;
    
    
    /**
     * Contructor with parameter.
     */
    Item(String item){
        this.item = item;
    }
    
    /**
     * Return the description of the item.
     * @return the item.
     */
    public String toString()
    {
        return item;
        
    }
    
}
