/* Name: James Nguyen
 *
 * Extending the Ring class, this class features:
 * While worn, each turn (moving, attacking, wielding, reading, putting on rings, removing rings) has a 1/85 chance
 * of teleporting you to some random location on the floor. */
package nethack;
import java.util.Random;

class RingOfTeleportation extends Ring {
	Random randomGenerator;
	
    public RingOfTeleportation() {
    	fullDescription = "Ring of Teleportation!";
    	partialDescription = "Ring of Teleportation!";
    	randomGenerator = new Random();
    }
    
    @Override
    /* Main method for this ring. This method returns an arbitrary and useless zero that doesn't affect anything.
     * */
    public int ringSpell(Character character, Floor floor) {
    	Teleport(character, floor);
    	//fakeTeleport(character, floor);
    	return 0;
    }
    
    /* In practice, it is very hard to test for a 1/85 chance, so test with a smaller number with another method with different numbers. */
    public void Teleport(Character character, Floor floor) {
    	int randomNum = randomGenerator.nextInt(85);
    	// 5 is an arbitrary random number, if the random number happens to be 5 (1/85) chance, it'll teleport the character.
    	if(randomNum == 5) {
    		// Teleport the character to a new place by calling floor. The 5 is an arbitrary constraint.
    		int newCol = randomGenerator.nextInt(5);
    		int newRow = randomGenerator.nextInt(5); 
    		floor.place(character, newCol, newRow);
    		System.out.println(character.name + " has been teleported!");
    	}
    }
    
    /* This method's only use is to test if the teleport mechanic actually works. Make the probability very low (1/10)
     * and then test to see if the character will actually teleport if the number 'hits'.
     */
    public void fakeTeleport(Character character, Floor floor) {
    	int randomNum = randomGenerator.nextInt(10);
    	// 5 is an arbitrary random number, if the random number happens to be 5 (1/10) chance, it'll teleport the character.
    	if(randomNum == 5) {
    		// Teleport the character to a new place by calling floor. The 5 is an arbitrary constraint.
    		int newCol = randomGenerator.nextInt(5);
    		int newRow = randomGenerator.nextInt(5); 
    		floor.place(character, newCol, newRow);
    		System.out.println(character.name + " has been teleported!");
    	}
    }
}
