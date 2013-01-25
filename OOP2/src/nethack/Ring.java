/*Name: James Nguyen
 * This class is just a blueprint for specific rings. */

package nethack;

abstract class Ring extends Item {
	public Ring() {
		determineBlessing();
	}
	
	/* This function serves as an interface for classes that extends this one.
	 */
	public int ringSpell(Character character, Floor floor) {
		return 0;
	}

}
