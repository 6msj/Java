/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
    private static int RECTANGLEWIDTH = 140; /* user defined constants to create the rectangle */
    private static int RECTANGLEHEIGHT = 60;
    private static int SPACEABOVE = 30;
    private static int SPACEBETWEEN  = 30;

	public void run() {
        int width = getWidth();
        int height = getHeight();
        int centerStart = width/2 - RECTANGLEWIDTH/2; /* is where the center box starts, assigned a variable because the left and right boxes uses it as a base */
        int rightStart = centerStart + SPACEBETWEEN + RECTANGLEWIDTH; /* defining the coordinates of the right box */
        int leftStart = centerStart - SPACEBETWEEN - RECTANGLEWIDTH; /* definting the coordinates of the left box */
        int heightStart = height/2; /* defining the height the box takes */
        int topXstart = width/2 - RECTANGLEWIDTH/2;
        int topYstart = height/2 - SPACEABOVE - RECTANGLEHEIGHT;
        /* most variables are already calculated and pushed to the 
         * method addBox, below takes care of creating the boxes */
        add(addBox(centerStart, heightStart)); /* is the center box */
        add(addBox(topXstart, topYstart)); /*is the top box */
        add(addBox(leftStart, heightStart)); /* is the left box */
        add(addBox(rightStart, heightStart)); /* is the right box */
        /* the rest of the Glines will have a different width setting, all of its endpoints are the same 
         * this section takes care of the lines */
        GLine leftLine = new GLine(leftStart+RECTANGLEWIDTH/2, heightStart, topXstart+RECTANGLEWIDTH/2, topYstart+RECTANGLEHEIGHT);
        add(leftLine);
        GLine rightLine = new GLine(rightStart+RECTANGLEWIDTH/2, heightStart, topXstart+RECTANGLEWIDTH/2, topYstart+RECTANGLEHEIGHT);
        add(rightLine);
        GLine centerLine = new GLine(centerStart+RECTANGLEWIDTH/2, heightStart, topXstart+RECTANGLEWIDTH/2, topYstart+RECTANGLEHEIGHT);
        add(centerLine);
        /* last steps are to create labels for each box 
         * this section creates the labels */
        label(centerStart, heightStart, "ConsoleProgram");
        label(topXstart, topYstart, "Program");
        label(leftStart, heightStart, "GraphicsProgram");
        label(rightStart, heightStart, "DialogProgram");
	}

    private GRect addBox(int x, int y) {
        GRect box = new GRect(RECTANGLEWIDTH, RECTANGLEHEIGHT);
        box.setLocation(x, y);
        return box;
    }

    private void label(int x, int y, String z) {
        x = x + RECTANGLEWIDTH/2;
        y = y + RECTANGLEHEIGHT/2;
        GLabel label = new GLabel(z, x, y);
        label.setLocation(x-(label.getWidth()/2), y+(label.getAscent()/2)); /*shift the label by half of its center to get a true center */
        add(label);
    }

}

/* to find the coordinates where the lines should meet, use the "starts" and add 1/2 the RECTANGLEWIDTH */ 
/* to find the coordinates where the lines end, use the start of the topBox, add 1/2 to its width, and subtract it by the RECTANGLEHEIGHT */

