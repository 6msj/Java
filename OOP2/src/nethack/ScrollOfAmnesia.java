/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nethack;

/**
 *
 * @author David Yang
 */
class ScrollOfAmnesia extends Scroll {

    public ScrollOfAmnesia() {
        itemTypeID = 1;
        fullDescription = "Scroll of Amnesia";
    }
    
    @Override
    public void read() {
        Knowledge.clear();
    }
}
