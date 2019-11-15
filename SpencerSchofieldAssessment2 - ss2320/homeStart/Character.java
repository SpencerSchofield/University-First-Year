
/**
 * Enumeration class Character
 * A character in the game.
 * 
 * @author Olaf Chitil
 * @version 19/2/2019
 */
public class Character
{
    private String desc;
    private Item it;
    /**
     * Constructor initialising description and item.
     * @param desc The name of the character.
     * @param it The item they are holding.
     */
    public Character(String desc, Item it)
    {
        this.desc = desc;
        this.it = it;
    }

    /**
     * Return the description and description of item if it exists.
     * @return the description of the character and what item they are holding,
     *      if they are holding any item.
     */
    public String toString()
    {
        if (this.it != null){
           return desc + " "+ "having the item" + " " + it;
        }else
           return desc;
    }
    
    /**
     * Take the given item from the character if it has that item.
     * Return whether item was taken.
     * @return whether item was taken.
     */
    public boolean take(Item it)
    {
        if (this.it == it){
            this.it = null;
            return true;
        } else{
            return false; 
        }
    }
}
