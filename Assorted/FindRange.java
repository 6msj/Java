/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
    public static final int SENTINEL = 0; /* constant integer that will stop the program */
	public void run() {
        printInformation();
        int lowest = readInt("Enter the first number." );
        int highest = readInt("Enter the second number. ");
        if(highest < lowest) {
            int placeholder = lowest;
            lowest = highest;
            highest = placeholder;
        }
        println("The lower number entered is " + lowest + " and the higher number entered is " + highest + " .");
        int number = 1;
        while(number != SENTINEL) {
            number = readInt("Enter a number, hit zero to stop. : ");
            if(number == SENTINEL) {
                break;
            }
            if(number < lowest) {
                lowest = number;
            }
            if(number > highest) {
                highest = number;
            }
        }
        printRange(lowest, highest);
	}

    private void printInformation() {
        println("Enter in numbers and this program will find the range.");
        println("Enter 0 to stop.");

    }

    private void printRange(int lowest, int highest) {
        println("The lowest is " + lowest + " and the highest is " + highest + ".");
    }
}

