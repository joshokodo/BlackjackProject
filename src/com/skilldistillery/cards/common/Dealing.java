package com.skilldistillery.cards.common;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Dealing {

	private final Scanner keyboard = new Scanner(System.in);
	private final List<Card> userCards = new ArrayList<>();
	private final Deck deck = new Deck();

	public static void main(String[] args) {
		Dealing dealingApp = new Dealing();
		dealingApp.run();
	}

	private void run() {
		int numberOfCards = getNumberOfCards();
		userCards.addAll(drawNumberOfCards(numberOfCards));
		printUserCards();

	}

	private int getNumberOfCards() {

		int number = 0;
		String numberString = "";

		do {

			System.out.print("Enter the number of cards you want: ");
			numberString = keyboard.next();
			keyboard.nextLine(); // gets rid of trailing text
			try {
				number = Integer.parseInt(numberString);

				boolean isLessThan52 = number < 52;
				boolean isPositive = number >= 0;

				if (!isPositive || !isLessThan52) {
					System.out.println("Not a positive whole number number. try again");
				}
			} catch (NumberFormatException e) {
				System.out.println("Not a numeric value. try again");
			}
		} while (number <= 0);
		return number;
	}

	private Collection<Card> drawNumberOfCards(int number) {
		Collection<Card> drawnCards = new ArrayList<>();
		deck.shuffle();
		for (int i = 0; i < number; i++) {

			if (deck.checkDeckSize() > 0) {
				Card drawnCard = deck.dealCard();
				drawnCards.add(drawnCard);
			}
		}

		return drawnCards;
	}

	private void printUserCards() {

		String[] cardLines = new String[5];
		int totalValue = 0;
		if (userCards.size() == 0) {
			System.out.println("User doesnt have cards");
		}
		for(int i = 0; i < cardLines.length; i++) {
			
		}
		for (Card card : userCards) {
			card.flipCardUp();
			totalValue += card.getValue();
			System.out.println(card.getAsciiCard());
		}
		System.out.println("Total Value: " + totalValue);
	}
	
	// ______
	//|3     |
	//|______|
	//|
	

}
