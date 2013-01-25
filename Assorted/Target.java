/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
    private static final double LARGERADIUS = 72; /*default value given */
    private static final double MIDDLERADIUS = 46.8; /*attained from converting .65 of 72 pixels */
    private static final double SMALLRADIUS = 21.6; /* attained from converting .3 of 72 pixels */
	public void run() {
        double width = getWidth();
        double height = getHeight();
        /* supplies the constants to a function that builds a circle around those values, width and height are directly affected by the radius, since shapes will start to the absolute right of 1/2 of the width, it is neccessary to subtract the radius from the center which is the value width, do the same for height */
        add(returnsCircle(LARGERADIUS, LARGERADIUS, width-LARGERADIUS, height-LARGERADIUS));  
        add(returnsCircle(MIDDLERADIUS, MIDDLERADIUS, width-MIDDLERADIUS, height-MIDDLERADIUS));
        add(returnsCircle(SMALLRADIUS, SMALLRADIUS, width-SMALLRADIUS, height-SMALLRADIUS));

	}

    private GOval returnsCircle(double a, double b, double width, double height) {
        GOval circle = new GOval(a, b);
        if(a != MIDDLERADIUS) {
            circle.setLocation(width/2, height/2);
            circle.setFilled(true); 
            circle.setFillColor(Color.red);
            circle.setColor(Color.red);
        } else { /*if it doesn't match the middle radius, it's red */
            circle.setLocation(width/2, height/2);
            circle.setFilled(true); /*sets the fill to true, so the target colors correctly */
            circle.setFillColor(Color.white);
            circle.setColor(Color.white);
        }
        return circle;
    }
}
