/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
        /* Go right 
         * Go backwards, make a method to walk backwards
         * make a square, move two, make a square, move two
         * while facing east, do this
         * move up
         * while facing west, do this
         * while front is clear 
         * do this move east
         * go up
         * do this move west
         */
        
        while(frontIsClear()) {
            doThisWhileMovingEast(); /* karel ends one row up facing north */
            doThisWhileMovingWest();
        }
    }

    /* while facing east, karel puts a beeper and then moves two columns to the right(east),  */
    private void doThisWhileMovingEast() {
        while(facingEast()) {
            putBeeper();
            moveTwiceEast();
        }
        turnLeft(); /* karel ends up facing west */
    }

    /* if the front is blocked after the first move, it means karel just hit the corner of the map, therefore it needs to turn left, if it's facing east */
    private void moveTwiceEast() {
        move();
        if(frontIsBlocked()) {
        	turnLeft();
        }
        
        move();
    }

    /* mirrors the other function, except it moves west, and will need to turn right if it's facing west */
    private void moveTwiceWest() {
        move();
        if(frontIsBlocked()) {
            turnRight();
        }
        move();
    }

    /* same as the function facing east, except karel goes west */
    private void doThisWhileMovingWest() {
        while(facingWest()) {
            putBeeper();
            moveTwiceWest();
        }
        turnRight(); /* karel ends up going east */
    }

}
