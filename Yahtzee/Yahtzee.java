/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		setZero();
		for(int round=0; round<N_SCORING_CATEGORIES; round++) {
			for(int turn=0; turn<nPlayers; turn++) {
				DICE = diceToUse();
				//1. Click on the Roll Dice button to set up the initial roll of all five dice. 
				display.waitForPlayerToClickRoll(turn+1); // starts at 1 and goes up
				display.displayDice(DICE); // takes in an array of ints and displays it as dice
				
				//2. Select a set of dice and then click the Roll again button to reroll the selected dice. 
				for(int step=0; step<numSteps; step++) {
					//3. Repeat step 2 to generate the final dice configuration after the third roll. 
					display.waitForPlayerToSelectDice(); // pauses the game and waits for dice selection
					checkForReRolls();
					display.displayDice(DICE);
				}

				//4. Click on a category to store the score in the appropriate box.	
				int categoryToCheck = display.waitForPlayerToSelectCategory();
				checkCategories(categoryToCheck, turn+1);
				//display.updateScorecard(categoryToCheck, i+1, categoryToCheck);
			}
		}
		tallyPoints();
	}
	
	private void tallyPoints() {
		boolean bonus = false; 
		for(int i=0; i<nPlayers; i++) {
			display.updateScorecard(UPPER_SCORE, i+1, UPPERSCORE[i]);
			display.updateScorecard(LOWER_SCORE, i+1, LOWERSCORE[i]);

			if(UPPERSCORE[i] > 63) {
				display.updateScorecard(UPPER_BONUS, i+1, BONUS); 
				bonus = true;
			}

			if(bonus) {
				UPPERSCORE[i] += BONUS;
			}

			FINALSCORE[i] = UPPERSCORE[i] + LOWERSCORE[i];
			display.updateScorecard(TOTAL, i+1, FINALSCORE[i]);
			
		}
	}
	
	private void setZero() {
		for(int i=0; i<nPlayers; i++) {
			/* set everything to 0 */
			UPPERSCORE[i] = 0;
			LOWERSCORE[i] = 0;
			FINALSCORE[i] = 0;
		}
	}
	
	private void checkCategories(int cat_num, int player) {
		if(cat_num <= SIXES || cat_num == CHANCE) {
            /* add the scores to upper, or lower score for later evaluation */
			LOWERSCORE[player-1] += checkOneSixChance(cat_num, player);

		} else if (cat_num == THREE_OF_A_KIND) {
			UPPERSCORE[player-1] += checkKind(cat_num, player, 3); // last digit is the number of repeated cards to check for

		} else if (cat_num == FOUR_OF_A_KIND) {
			UPPERSCORE[player-1] += checkKind(cat_num, player, 4);

		} else if (cat_num == FULL_HOUSE) {
			LOWERSCORE[player-1] += checkHouse(cat_num, player);

		} else if (cat_num == YAHTZEE) {
			LOWERSCORE[player-1] += checkKind(cat_num, player, 5);

		} else if (cat_num == LARGE_STRAIGHT) {
			LOWERSCORE[player-1] += largeStraight(cat_num, player);

		} else if (cat_num == SMALL_STRAIGHT) {
			LOWERSCORE[player-1] += smallStraight(cat_num, player);

		}
	}
	
	private int smallStraight(int num, int player) {
		int[] array = new int [numFaceDice];
		for(int i=0; i<N_DICE; i++) {
			array[DICE[i]-1]++;
		}
		
		int index = 0;
		if(checkFourInARow(index, array)) {
			int score = 30;
			display.updateScorecard(num, player, score);
			return score;

		} else {
			int score = 0;
			display.updateScorecard(num, player, score);
			return score;
	
		}
	}
	
	private int largeStraight(int num, int player) {
		int[] array = new int [numFaceDice];
		for(int i=0; i<N_DICE; i++) {
			array[DICE[i]-1]++;
		}
		
		int index = 0;
		if(checkForOnes(index, array)) {
			int score = 40;
			display.updateScorecard(num, player, score);
			return score;

		} else {
			int score = 0;
			display.updateScorecard(num, player, score);
			return score;
		}
		
	}
	
	private boolean checkFourInARow(int index, int[] array) {
		if(array[index] != 1 && array[index+1] != 1) {
			// no way there can be a four in a row 
			return false;
		}
		int counter = 0; //keep track of the number of times it iterates successfully

		for(int i=0; i<N_DICE; i++) {
			if(array[i] == 1) {
				counter++;
			}
		}

		if(counter >= 4) {
			return true; //means 4 numbers showed up and consecutively
		} else {
			return false;
		}
	}
	
	private boolean checkForOnes(int index, int[] array) {
		if(index == N_DICE-1 && array[N_DICE-1] == 1) {
			/* if index has reached the end and the end is one, it means all of them are 1s */
			return true;
		}

		if(array[index] != 1) {
			return false;
		} else {
			return checkForOnes(index+1, array);
		}
	}
	
	private int checkHouse(int num, int player) {
		int[] array = new int [numFaceDice];
		
		for(int i=0; i<N_DICE; i++) {
			array[DICE[i]-1]++;
		}
		
		for(int i=0; i<N_DICE; i++) {
			if(array[i] == 3) {
				for(int j=0; j<N_DICE; j++) {
					if(array[j] == 2) {
						int score = 25; // score for a full house is 25
						display.updateScorecard(num, player, score);
						return score;
					}
				}
			}
		}
		int score = 0; //else return the score as 0
        display.updateScorecard(num, player, score);
		return score;
	}
	
	private int checkKind(int num, int player, int numCheck) {
		int[] array = new int [numFaceDice];
		int score = 0;

		for(int i=0; i<N_DICE; i++) {
			array[DICE[i]-1]++;
		}
		
		for(int i=0; i<numFaceDice; i++) {
			/* if there's a match, it means the triple/four of a kind/yahtzee is there
			 * reward accordingly 
			 */
			if(array[i] >= numCheck) {
				score = ((i+1) * numCheck);
				if(numCheck == 5) {
					score = 50; // if yahtzee set to 50
					display.updateScorecard(num, player, score);
					return score;
				}
				/* if not yahtzee, return and draw the score as normal */
				display.updateScorecard(num, player, score);
				return score;
				
			} 
		} 
		
		score = 0; // there was no match, so they get 0 points
		display.updateScorecard(num, player, score);
		return score;
	}
	
	private int checkOneSixChance(int num, int player) {
		/* these are always valid, so there is no need to check if a 0 should be returned */
		int score = 0;
		for(int i=0; i<N_DICE; i++) {
			if(DICE[i] == num) {
				score += num; // if the number on the dice matches, add it to the total score */
			}
		}
		/* if num is equal to 15, it means it's a chance
		 * so set score to 0 and do it again,
		 * this time adding every number
		 */
		if(num == 15) {
			score = 0;
			for(int i=0; i<N_DICE; i++) {
				score += DICE[i]; //add the value of that particular die 
			}
		}
		display.updateScorecard(num, player, score);
		return score;
	}
	
	private void checkForReRolls() {
		for(int i=0; i<N_DICE; i++) {
			/* if it returns true, it means the index(which is that specific die)
			 * was selected, so change it to another random number
			 */
			if(display.isDieSelected(i) == true) {
				DICE[i] = ran_gen();
			}
		}
	}
	
	private int[] diceToUse() {
		/* generates an array of dice to use
		 * will be used every time a player's turn starts
		 * to generate a new set
		 */
		int[] array = new int [N_DICE];

		for(int i=0; i<N_DICE; i++) {
			array[i] = ran_gen();
		}

		return array;
	}
	
	private int[] sample() {
		// for testing 
		int[] array = new int [N_DICE];

		for(int i=0; i<N_DICE; i++) {
			array[i] = 2;
		}

		return array;
	}
	
	private int ran_gen() {
		int return_This = rgen.nextInt(6) + 1;
		return return_This;
	}

		
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int numSteps = 1; //change back to 2 later
	private int numFaceDice = 6;
	private int BONUS = 35;
	private int[] DICE = new int [N_DICE];
	private int[] UPPERSCORE = new int [MAX_PLAYERS];
	private int[] LOWERSCORE = new int [MAX_PLAYERS];
	private int[] FINALSCORE = new int [MAX_PLAYERS];

}
