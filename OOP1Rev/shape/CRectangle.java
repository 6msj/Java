/* Name: James Nguyen
 * Modified from an example on overloading class constructors
 * from cplusplus.com
 */
package shape;

public class CRectangle {
	int width, height;
	public CRectangle() {
		width = 5;
		height = 5;
	}
	
	public CRectangle(int a, int b) {
		width = a;
		height = b;
	}
	
	public int area() {
		return (width * height);
	}
	
}
