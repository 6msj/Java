/*
 * constants for moving around
 */
package nethack;

/**
 *
 * @author David Yang
 */
public class Directions {
    public final static int NORTH = 0;
    public final static int NE = 1;
    public final static int EAST = 2;
    public final static int SE = 3;
    public final static int SOUTH = 4;
    public final static int SW = 5;
    public final static int WEST = 6;
    public final static int NW = 7;
    
    // adjusting current position based on direction values above
    // assumes position is represented by (x, y), and origin is
    // at top left
    public final static int[] XMOD = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
    public final static int[] YMOD = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
}
