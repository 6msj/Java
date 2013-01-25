/* Name: James Nguyen
 * The BareHands.java file's only purpose is to replace weapons that are unwielded. */


package nethack;

class BareHands extends Weapon {
    public BareHands() {
        //itemTypeId = 5; /* Not sure if it should have an itemTypeId. */
        partialDescription = "bare hands";
    }

    /* Compute the base damage of your hands. */
    @Override
    public int computeBaseDamage() {
        /* Bare hands do zero damage. */
        return 0;
    }
}
