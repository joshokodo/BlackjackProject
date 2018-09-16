package com.skilldistillery.myutils;

import java.util.Scanner;

public interface UserInput {

    final Scanner KEYBOARD = new Scanner(System.in);
    final String INVALID_INPUT_PROMPT = "Not a valid input. Please try again.";
    final String OUT_OF_RANGE_PROMPT = "Input not within specific range. Please try again";

    // scanner input methods
    default int getIntInput() {
	int num = -1;

	while (true) {
	    String stringChoice = KEYBOARD.next();
	    KEYBOARD.nextLine(); // to get rid of any trailing texts

	    try {
		num = Integer.parseInt(stringChoice);
	    } 
	    catch (NumberFormatException e) {
		System.out.println(INVALID_INPUT_PROMPT);
		continue;
	    }
	    break;
	}
	return num;
    }

    default int getIntInput(int min, int max) {
	int num = -1;

	while (true) {
	    String stringChoice = KEYBOARD.next();
	    KEYBOARD.nextLine(); // to get rid of any trailing texts

	    try {
		num = Integer.parseInt(stringChoice);

		boolean withinRange = num >= min && num <= max;
		if (!withinRange) {
		    System.out.println(OUT_OF_RANGE_PROMPT);
		    continue;
		}

	    } 
	    catch (NumberFormatException e) {
		System.out.println(INVALID_INPUT_PROMPT);
		continue;
	    }
	    break;
	}
	return num;
    }

    default String getStringInput() {
	return KEYBOARD.nextLine();
    }

    // prompts user for string input between length min and max
    default String getStringInput(int min, int max) {
	String returnValue = null;
	int length = 0;
	boolean withinRange = false;

	do {
	    returnValue = KEYBOARD.nextLine();
	    length = returnValue.length();
	    withinRange = length >= min && length <= max;

	    if (!withinRange) {
		System.out.println(OUT_OF_RANGE_PROMPT);
	    }

	} while (!withinRange);

	return returnValue;
    }

    default double getDoubleInput() {
	double num = -1;

	while (true) {
	    String stringChoice = KEYBOARD.next();
	    KEYBOARD.nextLine(); // to get rid of any trailing texts

	    try {
		num = Double.parseDouble(stringChoice);
	    } 
	    catch (NumberFormatException e) {
		System.out.println(INVALID_INPUT_PROMPT);
		continue;
	    }
	    break;
	}
	return num;
    }

    default long getLongInput() {
	long num = -1;

	while (true) {
	    String stringChoice = KEYBOARD.next();
	    KEYBOARD.nextLine(); // to get rid of any trailing texts

	    try {
		num = Long.parseLong(stringChoice);
	    } 
	    catch (NumberFormatException e) {
		System.out.println(INVALID_INPUT_PROMPT);
		continue;
	    }
	    break;
	}
	return num;
    }
    default long getLongInput(long min, long max) {
	long num = -1;
	
	while (true) {
	    String stringChoice = KEYBOARD.next();
	    KEYBOARD.nextLine(); // to get rid of any trailing texts
	    
	    try {
		num = Long.parseLong(stringChoice);
		
		boolean withinRange = num >= min && num <= max;
		if (!withinRange) {
		    System.out.println(OUT_OF_RANGE_PROMPT);
		    continue;
		}
	    } 
	    catch (NumberFormatException e) {
		System.out.println(INVALID_INPUT_PROMPT);
		continue;
	    }
	    break;
	}
	return num;
    }

    default void closeKeyboard() {
	KEYBOARD.close();
    }

    // prompts users with custom menu (arg 1) of choices
    // between a custom range between 0 and (arg 2) returns choice in the form of an
    // int
    default int getUserChoice(String prompt, int min, int max) {
	int choice = -1;
	boolean validChoice = false;

	while (true) {
	    System.out.println();

	    System.out.print(prompt);
	    choice = getIntInput();

	    validChoice = choice >= min && choice <= max; // resets loop condition

	    if (!validChoice) {
		System.out.println(" Not an available option. try again.");
		continue;
	    } 
	    else {
		break;
	    }
	}

	return choice;

    }

}
