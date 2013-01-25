/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
        double totalWidth = getWidth();
        double totalHeight = getHeight();
        int counter = 7; /* 14 blocks at the beginning, if building left and right at the same time, number of times to build is the counter */
        double centerBlockCoordinates = totalWidth/2 - BRICK_WIDTH/2; /* will be used to build all center blocks for 'odd' rows */
        totalHeight -= BRICK_HEIGHT; /*it starts one brick above the totalheight, otherwise the first blocks won't show */
        while(counter != 0) {
            /* the for loop below builds the 'even' portion of the pyramid, where all the blocks are an even number */
            for(int i=0; i<counter; i++) {
                /* below is towards making the box to the right */
                GRect boxRight = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
                boxRight.setLocation(totalWidth/2 + (BRICK_WIDTH*i), totalHeight);
                add(boxRight);
                /* below is towards making the box to the left */
                GRect boxLeft = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
                boxLeft.setLocation(totalWidth/2 - BRICK_WIDTH - (BRICK_WIDTH*i), totalHeight);
                add(boxLeft);
            }
            counter --; /* counter subtracts because with each ascending even or odd row, there will be two blocks left */
            totalHeight -= BRICK_HEIGHT; /*the width doesn't change except with respect to the for loops, but the height should be changed accordingly after every row is filled */
            /* adds the middle box */
            GRect boxMiddle = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
            boxMiddle.setLocation(centerBlockCoordinates, totalHeight);
            add(boxMiddle);
            /* the loop below builds the 'odd' portion of the pyramid, where all rows starts with one block in the middle */
            for(int i=0; i<counter; i++) {
                /* below is towards making the box to the right, with careful attention to build around the center block*/
                GRect boxRight = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
                boxRight.setLocation(centerBlockCoordinates+BRICK_WIDTH + (BRICK_WIDTH*i), totalHeight);
                add(boxRight);
                /* below is towards making the box to the left, with careful attention to build around the center block */
                GRect boxLeft = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
                boxLeft.setLocation(centerBlockCoordinates-BRICK_WIDTH - (BRICK_WIDTH*i), totalHeight);
                add(boxLeft);
            }
            totalHeight -= BRICK_HEIGHT;
        }
        
	}
}
/* GRect box = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
            box.setLocation(totalWidth/2 + (BRICK_WIDTH*i), totalHeight - BRICK_HEIGHT);
            add(box); */

