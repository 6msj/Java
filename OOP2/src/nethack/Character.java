/*
 * The general class for a Character. One of the features of the game is that
 * monsters in the game can actually pick up the weapons, wands, and other items
 * and use them against you.
*/
package nethack;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author David Yang
 */
class Character {
    protected String name;
    protected ArrayList<Item> inventory;
    protected int hitPoints;    // when it goes to zero or below, the character dies
    protected Item wielded;
    protected Point position;
    
    /* A reference to the game's floor is made. */
    protected Floor floor;
    /* Since user can only hold two rings at a time, ArrayList is initialized to two. */
    protected ArrayList<Item> equippedRings;

    /* Initialize with name of character, a Floor parameter is added. */
    public Character(String _name, int _hitPoints, Floor CurrentFloor) {
        name = _name;
        inventory = new ArrayList<>();
        hitPoints = _hitPoints;
        position = new Point();
        
        /* Initialized to two here. */
        equippedRings = new ArrayList<>();
        floor = CurrentFloor; /* The character has a reference of what floor he's on. */
    }
    
    /* Equip a ring by adding the item at the index of the inventory to the equippedRings array. */
    public void equipRing(int itemIndex) {
    	if(equippedRings.size() == 2) {
    		System.out.println("You've equipped the maximum amount of rings!");
    	} else {
    		equippedRings.add(inventory.get(itemIndex));
    	}
    	useRings();
    }
    
    /* The total damage is the damage and any damage boosts a ring might give. If there are no rings
     * that give damage boosts, the useRings() function returns 0 and the damage is calculated as normal.
     */
    public int inflictDamage() {
    	return wielded.computeTotalDamage() + useRings();
    }
    
    /* This function is the main function to use methods from rings. It checks the size of the ring
     * and uses as many methods as there are rings on the hand.
     */
    public int useRings() {
    	int tempValue = 0;
    	for(int i=0; i<this.equippedRings.size(); i++) {
    		tempValue += this.equippedRings.get(i).ringSpell(this, floor);
    	}
    	return tempValue;
    }
    
    
    /* itemIndex should be between 0 and 1, the indices of the equippedRings. */
    public void removeRing(int itemIndex) {
    	/* Check the index. */
    	if(itemIndex > 1 || itemIndex < 0) {
    		System.out.println("You chose the wrong hand!??");
    		return;
    	}
    	
    	/* No rings equipped. */
    	if(equippedRings.size() == 0) {
    		System.out.println("There's nothing to remove!");
    		return;
    	}
    	
    	/* If item cursed, don't remove it, else remove it. */
    	if(equippedRings.get(itemIndex).cursed) {
    		System.out.println("The ring you chose to remove is cursed!");
    	} else {
    		equippedRings.remove(itemIndex);
    	}
    	useRings();
    }
    
    /* This method's only purpose is to do nothing and call the useRings method to test the RingOfTeleportation class. 
     * It simulates a move being made (none) and then calls the function. */
    void emptyMove() {
    	useRings();
    }
    
    /* Support for unwielding a weapon. */
    public void un_wield() {
    	/* If the weapon is cursed, return from the function without doing anything. */
    	if(wielded.cursed) {
    		System.out.println("Your weapon is cursed!");
    		return;
    	} else {
	    	BareHands barehand = new BareHands();
	    	wielded = barehand;
    	}
    }
    
    // Display list of items in inventory
    public void listInventory() {
        System.out.println(name + "'s possessions: ");
        for (int index = 0; index < inventory.size(); index++)
            System.out.println(index + ". " + inventory.get(index).getDescription());
    }

    // Add an item to your inventory
    public void pickup(Item item) {
        inventory.add(item);
        useRings();
    }

    // Try to read an item. If you are successful, then the item should be removed from your inventory
    public void read(int index) {
        Item item = inventory.get(index);
        item.read();
        inventory.remove(item);
        useRings();
    }

    public void wield(int itemIndex) {
        wielded = inventory.get(itemIndex);
        useRings();
    }
    
    public int getHitPoints() { return hitPoints; }
    
    public String getName() { return name; }

    public Item getWeapon() {
        return wielded;
    }

    public void setPosition(int col, int row) {
        position.x = col;
        position.y = row;
    }
    
    public Point getPosition() {
        return position;
    }

    public Item getItem(int index) {
        return inventory.get(index);
    }

    void attacked(int damage) {
        hitPoints -= damage;
        useRings();
    }

    int computeDamage() {
        if (null == wielded)
            return 0;
        else return wielded.computeTotalDamage();
    }
    

}
