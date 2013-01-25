/*
 * One floor of the dungeon
 */
package nethack;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author David Yang
 */
class Floor {
    // The items on this floor
    protected HashMap<Point, ArrayList<Item>> items;
    
    // The characters on this floor
    protected HashMap<Point, Character> characters;
    
    
    public Floor() {
        characters = new HashMap<>();
        items = new HashMap<>();
    }

    void place(Character character, int col, int row) {
        characters.put(new Point(col, row), character);
        character.setPosition(col, row);
    }

    void move(Character character, int direction) {
        Point pos = character.getPosition();
        Point newPos = new Point(pos.x + Directions.XMOD[direction],
                                 pos.y + Directions.YMOD[direction]);
        if (null != characters.get(newPos)) {
            int damage = character.computeDamage();
            Character target = characters.get(newPos);
            target.attacked(damage);
            System.out.println("You attacked " + target.getName() + " for " + damage + " hit points");
            if (0 >= target.getHitPoints()) {
                System.out.println(target.getName() + " has breathed its last");
                characters.remove(newPos);
            }
        } else {
            characters.remove(pos);
            characters.put(newPos, character);
            character.setPosition(newPos.x, newPos.y);
            System.out.println(character.getName() + " moved to (" + newPos.x + ", " + newPos.y + ")");
            ArrayList<Item> foundItems = items.get(newPos);
            if (null != foundItems) {
                for (Item item : foundItems) {
                    character.pickup(item);
                    System.out.println("You pick up a " + item.getDescription());
                }
                foundItems.clear();
            }
            
        }
        
        /* After moving the character, check for the number of rings equipped and cast every spell. */
        for(int i=0; i<character.equippedRings.size(); i++) {
	        character.equippedRings.get(i).ringSpell(character, this);
        }
        
    }

    void place(Item item, int col, int row) {
        Point pt = new Point(col, row);
        ArrayList<Item> existing = items.get(pt);
        if (null == existing) {
            existing = new ArrayList<Item>();
            existing.add(item);
            items.put(pt, existing);
        }
        else existing.add(item);
    }
    
}
