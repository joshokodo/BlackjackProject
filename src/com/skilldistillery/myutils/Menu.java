package com.skilldistillery.myutils;

import java.util.Collections;
import java.util.List;

public interface Menu {

	final int SPACING_CUSHION = 4;

	// try having a List that you can add to and then this is built
	default String buildCustomMenu(char lining, List<String> headers, List<String> options) {

		// determines space between lining by finding longest length string line
		// and adding 4 to it
		int space = getLongestLength(headers, options) + SPACING_CUSHION;

		StringBuilder menu = new StringBuilder(getCustomBorder(lining, space));
		menu.append(getCustomSpace(lining, space));

		for (String header : headers) {
			menu.append(getMenuHeader(lining, space, header));
		}

		menu.append(getCustomSpace(lining, space));

		for (String option : options) {
			menu.append(getMenuOption(lining, space, option));
			menu.append(getCustomSpace(lining, space));
		}
		menu.append(getCustomBorder(lining, space));

		return menu.toString();
	}

	default String getCustomBorder(char lining, int space) {
		// string builder okay
		StringBuilder border = new StringBuilder("" + lining + lining);
		for (int i = 0; i < space; i++) {
			border.append(lining);
		}
		border.append("\n");
		return border.toString();
	}

	default String getCustomSpace(char lining, int space) {
		StringBuilder border = new StringBuilder();
		border.append(lining);
		for (int i = 0; i < space; i++) {
			border.append(" ");
		}
		border.append(lining + "\n");
		return border.toString();
	}

	// returns a formatted menu option line
	default String getMenuOption(char lining, int space, String option) {

		// --space makes up for space between left lining and option
		String format = "%c %-" + --space + "s%c%n";
		return String.format(format, lining, option, lining);
	}

	default String getMenuHeader(char lining, int space, String header) {

		// calculates the remaining space when putting header in the line
		// should always be at least 4;
		int remainingSpace = (space - header.length());

		int leftSpace = 1 + remainingSpace / 2;
		int rightSpace = 1 + remainingSpace / 2;

		if (remainingSpace % 2 != 0) {

			rightSpace++;
		}

		String format = "%-" + leftSpace + "c%s%" + rightSpace + "c%n";
		return String.format(format, lining, header, lining);
	}

	default int getLongestLength(List<String> headers, List<String> options) {
		int longest = 0;

		for (String header : headers) {
			if (header.length() > longest) {
				longest = header.length();
			}
		}
		for (String option : options) {
			if (option.length() > longest) {
				longest = option.length();
			}
		}
		return longest;
	}

}