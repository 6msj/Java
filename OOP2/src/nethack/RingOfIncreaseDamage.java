/* Name: James Nguyen
 *
 * Extending the Ring class, this class has a number of features:
 * Has an effect (determined when constructed) between -6 and +10.
 * The effect is applied on any attack while the ring is worn.
 * Make it more like that the effect is between -3 and =3. */
package nethack;
import java.util.Random;

class RingOfIncreaseDamage extends Ring {
	Random randomGenerator;
	int damageChanged;
	
	public RingOfIncreaseDamage() {
		fullDescription = "Ring of Increased Damage"; /* Useless for now, figure out how to change Item class to return full description for items that don't need to be identified. */
		partialDescription = "Ring of Increased Damage";
		randomGenerator = new Random();
		damageChanged = CalculateChangeDamage(); /* This line can go in the function ringSpell(Character, Floor). */
	}
	
	@Override
	/* Main method of this ring. The method just returns a number that can then 
	 * be added to the total damage value when the character attacks. 
	 * Fulfills the 'effect is applied on any attack' constraint. */
	public int ringSpell(Character character, Floor floor) {
		 /* This can go in this function or constructor, if this line goes in the constructor, the damage change for this ring remains the same throughout.
		  * If it is uncommented here, the damage change happens everytime this ring's function is called, which is almost every move. 
		  */
		//damageChanged = CalculateChangeDamage();
		return damageChanged;
	}
	
	/* Calculate the total damage the Ring changes with priority to -3 and +3.*/
	int CalculateChangeDamage() {
		int initialRandomNumber = randomGenerator.nextInt(4); // 0 1 2 3
		int boolRandomNumber = randomGenerator.nextInt(3); // 0 1 2
		if(boolRandomNumber == 0) {
			System.out.println("Your power will grow stronger with this ring."); // Test statement
			return initialRandomNumber; // Possibility of 0 - 3
		} else if (boolRandomNumber == 1) {
			System.out.println("Your power will grow weaker with this ring."); // Test statement
			return initialRandomNumber - 3; // Possibility of (0-3) - 3, so a possibility between -3 to 3
		} else {
			boolRandomNumber = randomGenerator.nextInt(2); // 0 1
			if(boolRandomNumber == 0) {
				System.out.println("Your power will grow far weaker with this ring."); // Test statement
				return initialRandomNumber - 6; // For a range between -6 and 3
			} else {
				System.out.println("Your power will grow limitlessly with this ring!"); // Test statement
				return initialRandomNumber + 7; // To finish the range, -6 and 10
			}
		}
	}
}


