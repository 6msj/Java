/*
 * The general scroll class
 */
package nethack;

/**
 *
 * @author David Yang
 */
abstract class Scroll extends Item {
    
    public Scroll() {
        partialDescription = "scroll";
        determineBlessing();
    }
    
    // By default, reading a scroll identifies it
    @Override
    public void read() {
        Knowledge.identify(itemTypeID);
    }
    
}
