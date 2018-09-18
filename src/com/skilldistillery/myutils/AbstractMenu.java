package com.skilldistillery.myutils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMenu {

	private final int SPACING_CUSHION = 4;

	// fields
	private List<String> headers;
	private List<String> options;
	private char lining;

	public AbstractMenu() {
		headers = new ArrayList<>();
		options = new ArrayList<>();
		lining = '*';
	}

	public AbstractMenu(char lining) {
		headers = new ArrayList<>();
		options = new ArrayList<>();
		this.lining = lining;
	}

	// setters and getters
	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public char getLining() {
		return lining;
	}

	public void setLining(char lining) {
		this.lining = lining;
	}

	// manipulate headers and options
	public void addOption(String option) {
		options.add(option);
	}

	public void removeOption(int index) {
		options.remove(index);

	}

	public void removeOption(String option) {
		options.remove(option);
	}

	public void replaceOption(String removedOption, String newOption) {

		int replaceIndex = options.indexOf(removedOption);
		options.set(replaceIndex, newOption);

	}

	public void addHeader(String header) {
		headers.add(header);
	}

	public void removeHeader(int index) {
		headers.remove(index);

	}

	public void removeHeader(String header) {
		headers.remove(header);
	}

	public void replaceHeader(String removedHeader, String newHeader) {

		int replaceIndex = headers.indexOf(removedHeader);
		headers.set(replaceIndex, newHeader);

	}

	public void clearHeaders() {
		headers.clear();
	}

	public void clearOptions() {
		options.clear();
	}

	public void clearAll() {
		clearHeaders();
		clearOptions();
	}

	// other methods

	public void printMenu() {
		System.out.println(this.buildMenu());
	}

	public String buildMenu() {
		return buildCustomMenu(lining, headers, options);
	}

	// helper methods
	private String buildCustomMenu(char lining, List<String> headers,
			List<String> options) {

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

	private String getCustomBorder(char lining, int space) {
		// string builder okay
		StringBuilder border = new StringBuilder("" + lining + lining);
		for (int i = 0; i < space; i++) {
			border.append(lining);
		}
		border.append("\n");
		return border.toString();
	}

	private String getCustomSpace(char lining, int space) {
		StringBuilder border = new StringBuilder();
		border.append(lining);
		for (int i = 0; i < space; i++) {
			border.append(" ");
		}
		border.append(lining + "\n");
		return border.toString();
	}

	// returns a formatted menu option line
	private String getMenuOption(char lining, int space, String option) {

		// --space makes up for space between left lining and option
		String format = "%c %-" + --space + "s%c%n";
		return String.format(format, lining, option, lining);
	}

	private String getMenuHeader(char lining, int space, String header) {

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

	private int getLongestLength(List<String> headers, List<String> options) {
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
