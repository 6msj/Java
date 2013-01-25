/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

    public void run() {
        for(int i=0; i<5; i++) {
            placeBeepers();
            if(i==4) {
            	break;
            }
            checkIfBlocked();
        // run method 5 times
        }
    }

	//move 4 times, check if beeper is there, place if not
    private void placeBeepers() {
        for(int i=0; i<4; i++) {
            if(noBeepersPresent()) {
                putBeeper();
            }
            if(frontIsBlocked()) {
            	break;
            }
            moveFour();
        }
    }

    private void moveFour() {
        for(int i=0; i<4; i++) {
            move();
        }
    }

    private void checkIfBlocked() {
        if(notFacingNorth()) {
            while(notFacingNorth()) {
                turnLeft();
            }
        }
        move();
        if(leftIsClear()) {
            while(notFacingWest()) {
                turnLeft();
            }
        } else if (rightIsClear()) {
            while(notFacingEast()) {
                turnRight();
            }
        }
    }

}
