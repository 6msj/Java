/*
 * The driver to run the sample scenario for nethack
 */
package nethack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author David Yang
 */
public class NetHack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // No map yet, and just a few simple actions
        String name;
        Character hero;
        Item scroll = new ScrollOfNothing();
        Item anotherScroll = new ScrollOfNothing();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Welcome to (not-yet) Nethack!");
        System.out.print("What is the name of your hero? ");
        name = stdin.readLine();
        
        Floor floor = new Floor();
        /* Added an extra parameter to hero so the hero can have a reference of the Floor. */
        hero = new Character(name, 12, floor);	// 12 is the "hit points" or "life points" of the Character

        floor.place(hero, 2, 3);    // (0,0 is top left point)
        floor.place(scroll, 3, 3);
        floor.place(anotherScroll, 3, 2);
        // When you move onto a position with items, you automatically
        // pick them up -- the next move picks up the scroll object
        floor.move(hero, Directions.EAST);  // move to (3,3)
        floor.move(hero, Directions.NE);    // move to (4,2)
        // next move picks up anotherScroll object
        // can have more than one item in the inventory
        floor.move(hero, Directions.WEST);  // move to (3,2)
        hero.read(0);	// Scroll of Nothing identified
        floor.move(hero, Directions.NW);    // move to (2,1)
        floor.move(hero, Directions.SW);    // move to (1,2)
        floor.move(hero, Directions.SE);    // move to (2,3)
        floor.move(hero, Directions.NORTH); // move to (2,2)
        
        // if you try to move into a square where there is another character,
        // you are attempting an attack
        Character punchingBag = new Character("Punching Bag", 11, floor);
        
        floor.place(punchingBag, 2, 3);
        // You are not strong enough to lower hitpoints with just your fists
        floor.move(hero, Directions.SOUTH);
        
        // Now attack with a weapon
        // To simplify things, a LongSword does 5 points of damage
        Item longsword = new LongSword();
        floor.place(longsword, 3, 2);
        floor.move(hero, Directions.EAST); // get the sword by moving to (3,2)
        hero.listInventory();
        hero.wield(1);  // wielding long sword
        floor.move(hero, Directions.SW);	// attack the punching bag
        
        // Try to read the longsword
        try {
        	hero.listInventory();
        	hero.read(1);	// trying to read the sword
        }
        catch (UnsupportedOperationException e) {
        	System.out.println("You cannot read that item");
        }
        
        // A couple more scrolls
        Item goodScroll = new ScrollOfEnchantWeapon(hero);
        Item badScroll = new ScrollOfAmnesia();
        Item informativeScroll = new ScrollOfIdentify(hero);
        
        floor.place(goodScroll, 2, 2);
        floor.place(badScroll, 2, 2);	// can have more than one item in a position
        floor.place(informativeScroll, 2, 2);
        hero.listInventory(); // should have picked up the 3 scrolls
        floor.move(hero, Directions.WEST);	// pick up the 3 scrolls
        hero.read(2);	// increase damage done by sword by 1
        floor.move(hero, Directions.SOUTH);	// should cause game to announce death of punching bag
        hero.read(3);	// scroll of identify now at index 3 because of reading enchant weapon
        // Game should ask you which of the items do you want to identify, listing the items
        // Choose the sword, and you should get that it is a "+1 longsword"
        hero.listInventory();	// lists Scroll of Nothing, +1 longsword, scroll (for scroll of amnesia)
        hero.read(2);	// you forget everything
        hero.listInventory();	// you go back to listing only general information for all items
        
        System.out.println("That's all so far ...");
        
        appear_RingOfIncreaseDamage(floor, hero);
        appear_RingOfTeleportation(floor, hero);
        
        

    }
    
    static void appear_RingOfIncreaseDamage(Floor floor, Character hero) {
        /* James Nguyen - Testing the Ring of Increased Damaged */
        Item DamageRing = new RingOfIncreaseDamage();
        floor.place(DamageRing, 2, 2);
        floor.place(hero, 2, 2);
        hero.listInventory();
        hero.pickup(DamageRing);
        hero.listInventory();
        System.out.println(hero.inflictDamage() + " is the amount of damage " + hero.name + " can do!"); /* The damage should be at 6. (Base Damage + Prior Enchantment) */
        hero.equipRing(2);
        System.out.println(hero.inflictDamage() + " is the amount of damage " + hero.name + " can do!"); /* The damage should be at 6 + Random Number. (Base Damage + Prior Enchantment + RingOfIncreaseDamage) */
    }
    
    static void appear_RingOfTeleportation(Floor floor, Character hero) {
    	/* James Nguyen - Testing the Ring of Teleportation */
    	Item TeleportRing = new RingOfTeleportation();
    	floor.place(TeleportRing, 3, 3);
    	floor.place(hero, 3, 3);
    	hero.listInventory();
    	hero.pickup(TeleportRing);
    	hero.listInventory();
    	hero.equipRing(3);
    	/* Call emptyMove 85 times to see if the character teleports even once. */
    	for(int i=0; i<85; i++) {
    		System.out.println("Empty Move: " + i);
    		hero.emptyMove();
    	}
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}