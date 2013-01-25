/* Name: James Nguyen
 * Driver demonstrating CRectangle's constructors and methods
 * Modified code from cplusplus.com to use an array, a loop and objects.
 */
package hw1;
import shape.*;


public class CRectDriver {
	public static void main(String[] args) {
		CRectangle rect = new CRectangle (3,4);
		CRectangle rectb = new CRectangle ();
		CRectangle[] rectArray = {rect, rectb};
		
		for (int i=0; i<2; i++) {
			System.out.println("rect " + i + " area: " + rectArray[i].area());
		}
	}
}
