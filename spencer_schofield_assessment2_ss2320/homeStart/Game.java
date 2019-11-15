import java.util.*;
/**
 *  This class is the central class of the "World of Home" application. 
 *  "World of Home" is a very simple, text based travel game.  Users 
 *  can walk around some house. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 * @author  Michael Kölling, David J. Barnes and Olaf Chitil
 * @version 19/2/2019
 */

public class Game 
{
    private Room currentRoom;
    private boolean finished;
    private Room endRoom;
    private int timer;
    private ArrayList<Item> items;
    private Room cookRoom;

    /**
     * Create the game and initialise its internal map and set the timer to 0.
     */
    public Game() 
    {
        finished = false;
        createRooms();
        timer = 0;
        items = new ArrayList<>();
    }

    /**
     * Create all the rooms and link their exits together.
     * Create all the Characters and add them to their rooms.
     */
    private void createRooms()
    {
        Room front, hall, kitchen, livingroom, garden, cloakroom, stairs, bathroom, 
        master, ensuite, guest;

        // create the rooms
        front = new Room("in front of the house");
        hall = new Room("in the hallway");
        kitchen = new Room("in the kitchen");
        livingroom = new Room("in the livingroom");
        garden = new Room("in the garden");
        cloakroom = new Room("in the cloakroom");
        stairs = new Room("at the top of the stairs");
        bathroom = new Room("in the bathroom");
        master = new Room("in the master bedroom");
        ensuite = new Room("in the ensuite");
        guest = new Room("in the guest bedroom");

        // initialise room exits
        front.setExit(Direction.NORTH, hall);
        hall.setExit(Direction.SOUTH, front);
        hall.setExit(Direction.UP, stairs);
        hall.setExit(Direction.WEST, cloakroom);
        hall.setExit(Direction.EAST, kitchen);
        hall.setExit(Direction.NORTH, livingroom);
        kitchen.setExit(Direction.WEST, hall);
        kitchen.setExit(Direction.NORTH, livingroom);
        cloakroom.setExit(Direction.EAST, hall);
        livingroom.setExit(Direction.SOUTH, hall);
        livingroom.setExit(Direction.EAST, kitchen);
        livingroom.setExit(Direction.NORTH, garden);
        garden.setExit(Direction.SOUTH, livingroom);
        stairs.setExit(Direction.DOWN, hall);
        stairs.setExit(Direction.EAST, bathroom);
        stairs.setExit(Direction.SOUTH, guest);
        stairs.setExit(Direction.NORTH, master);
        bathroom.setExit(Direction.WEST, stairs);
        guest.setExit(Direction.NORTH, stairs);
        master.setExit(Direction.SOUTH, stairs);
        master.setExit(Direction.EAST, ensuite);
        ensuite.setExit(Direction.WEST, master);
        
        
        Character mother = new Character ("mother", Item.FLOUR);
        Character father = new Character ("father", Item.EGG);
        Character daughter = new Character ("daughter", null);
        Character son = new Character ("son", Item.SUGAR);
        
        garden.addCharacter(mother);
        master.addCharacter(father);
        livingroom.addCharacter(daughter);
        guest.addCharacter(son);

        currentRoom = front;  // start game at the front of the house
        endRoom = ensuite;  //end game at the ensuite of the master bedroom
        cookRoom = kitchen;  //add a room which the user must be in to cook ingredients
    }

    /**
     * Return the current room.
     * Post-condition: not null.
     * @return The current roomm the player is in.
     */
    public Room getCurrent()
    {
        assert currentRoom != null : "Current room is null.";
        return currentRoom;
    }

    /**
     * Return whether the game has finished or not.
     * @return Returns finished as either true or false.
     */
    public boolean finished()
    {
        return finished;
    }

    /**
     * Opening message for the player.
     * @return A welcome message.
     */
    public String welcome()
    {
        return "\nWelcome to the World of Home!\n" +
        "World of Home is a new game.\n" +
        currentRoom.getLongDescription() + "\n";
    }

    // implementations of user commands:
    /**
     * Give some help information.
     */
    public String help() 
    {
        return "You are lost. You are alone. You wander around the home.\n";
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room and return its long description; otherwise return an error message.
     * @param direction The direction in which to go.
     * Pre-condition: direction is not null.
     * If the player makes it to the end room then the game will quit.
     * @return The current room's long description.
     * @return A win message if the player wins.
     * @return A lose message if the player loses.
     */
    
    public String goRoom(Direction direction) 
    {
        assert direction != null : "Game.goRoom gets null direction";

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        
        if (nextRoom == null) {
            return "There is no exit in that direction!";
        }
        else if (nextRoom.equals(endRoom)) {
            finished = true;
            return currentRoom.getLongDescription() + "\nCongratulations! You reached the goal.\nThank you for playing.  Good bye.";
        }else if (timer==11) {
            finished = true;    
            return currentRoom.getLongDescription() + "\nLost! You ran out of time.\nThank you for playing.  Good bye.";
        } else {
            currentRoom = nextRoom;
            timer++;
            return currentRoom.getLongDescription();
        }
    }
    
    /**
     * Execute quit command.
     * @return A exit message.
     */
    public String quit()
    {
        finished = true;
        return "Thank you for playing.  Good bye.";
    }

    /**
     * Execute look command.
     * @return the current room's long description.
     */
    public String look()
    {
        return currentRoom.getLongDescription();
    }

    /**
     * Execute take command.
     * @param item The item to take.
     * Pre-condition: item is not null.
     * @return If an item has been taken.
     */
    public String take(Item item)
    {
        assert item != null : "Game.take item is null";
        if (currentRoom.take(item)){
            items.add(item);
            return "Item taken.";
        }
        return "Item not in this room.";
    }

    /**
     * Execute cook command.
     * @return A message if the player cannot cook yet.
     * @return A win message if the player wins
     */
    public String cook()
    {
        if(currentRoom == cookRoom && items.size() ==3) {
            return "Congratulations! You have won.\n" + quit();
        }
        return "You cannot cook yet.";
    }
}
