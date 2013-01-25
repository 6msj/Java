/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nethack;
import java.util.Random;

/**
 *
 * @author David Yang
 */
class ScrollOfEnchantWeapon extends Scroll {

    protected Character hero;
    
    public ScrollOfEnchantWeapon(Character _hero) {
        itemTypeID = 2;
        fullDescription = "Scroll of Enchant Weapon";
        hero = _hero;
    }
    
    @Override
    public void read() {
        Item weapon = hero.getWeapon();
        if(this.blessed) {
        	specialBlessingToWeapon(weapon);
        } else if (this.cursed) {
        	specialCursingToWeapon(weapon);
        } else {
	        weapon.enchantWeapon();
        }
        super.read();
    }
    
    public void specialBlessingToWeapon(Item weapon) {
    	Random randomGenerator = new Random();
    	/* Uncurse the weapon if it is cursed. */
    	if(weapon.cursed) {
    		weapon.unCurse();
    	}
    	/* Blessed scrolls of enchant weapon can add +1 to +3 to the existing enchantment of a weapon.
    	 * Generate a number between 0 and 3 and then add 1. The for loop has will loop between 1 and 3 times
    	 * to enchant the weapon 1 to 3 times.
    	 */
    	for(int i=0; i<randomGenerator.nextInt(3)+1; i++) {
    		weapon.enchantWeapon();
    	}
    	
    }
    
    public void specialCursingToWeapon(Item weapon) {
    	/* Cursed scrolls of enchant weapon subtract 1 from the existing enchantment of the weapon. */
    	weapon.disenchantWeapon();
    }
    
    
    
}
