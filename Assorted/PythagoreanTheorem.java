/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
        Instructions();
        int a = readInt("a: ");
        int b = readInt("b: ");
        println("c = " + computePythagorean(a, b));
	}

    private void Instructions() {
        println("Enter numbers to compute Pythagorean Theorem.");
    }
    
    public double computePythagorean(int a, int b) {
        a = a * a;
        b = b * b;
        double totalofAandB = a + b;
        double c = Math.sqrt(totalofAandB);
        return c;


    }
}
