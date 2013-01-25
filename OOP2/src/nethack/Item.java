/*
 * The root class for the hierarchy of items.
 */
package nethack;
import java.util.Random;

/**
 *
 * @author David Yang
 */
abstract class Item {
    protected int itemTypeID;
    protected String fullDescription, partialDescription;
    public boolean cursed;
    public boolean blessed;

    // Default version of read() -- you cannot read most items, like potions, helmets, etc.
    public void read() {
        throw new UnsupportedOperationException("Cannot be read");
    }

    // Return description of item
    public String getDescription() {
        if (Knowledge.isIdentified(itemTypeID))
            return fullDescription;
        else return partialDescription;
    }

    // modify item's damage
    public void enchantWeapon() {
        // Regular items are not helped
    }
    
    public void disenchantWeapon() {
    	/* Similar to enchant weapon */
    }
    
    // default = attacking without a weapon = 0 damage
    public int computeTotalDamage() {
        return 0;
    }

   /* default = no ring = 0 effect */
    public int ringSpell(Character character, Floor floor) {
    	return 0;
    }
    
	public void determineBlessing() {
		Random randomGenerator = new Random();
		int randNumber = randomGenerator.nextInt(3); // 0 1 2 
		if(randNumber == 0) {
			blessed = false;
			cursed = false;
		} else if (randNumber == 1) {
			blessed = true;
			cursed = false;
		} else if (randNumber == 2) {
			blessed = false;
			cursed = true;
		}
	}
	
	public boolean isCursed() {
		return cursed;
	}
	
	public boolean isBlessed() {
		return blessed;
	}
	
	public void unCurse() {
		cursed = false;
	}
}
