/*
 * Long sword -- has some unique properties in nethack
 */
package nethack;

/**
 *
 * @author David Yang
 */
class LongSword extends Weapon {
    
    public LongSword() {
        itemTypeID = 4;
        partialDescription = "long sword";
        determineBlessing();
    }

    // Compute base damage of this weapon
    @Override
    public int computeBaseDamage() {
        // @TODO replace with a randomized calculation
        return 5;   // "stub" implementation
    }
    
}
