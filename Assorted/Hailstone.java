/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
        int number = readInt("Enter a number: ");
        int numberOfTries = 0;
        while(number != 1) {
            if(number%2 == 0) {
                print(number + " is even, so I take half: ");
                number = number / 2;
                println(number);
            } else {
                print(number + " is odd, so I make 3n+1: ");
                number = (number*3) + 1;
                println(number);
            }
            numberOfTries ++;
        }
        println("The process took " + numberOfTries + " to reach 1.");
		/* You fill this in */
	}
}

