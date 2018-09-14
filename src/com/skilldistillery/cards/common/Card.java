package com.skilldistillery.cards.common;

public class Card {

	private final String TOP_OF_CARD = " _____ ";
	private final String TOP_VALUE_OF_CARD = "|%s%4s|";
	private final String TOP_VALUE_OF_CARD_IS_TEN = "|%s%3s|";
	private final String BOTTOM_VALUE_OF_CARD = "|%s___%s|";
	private final String BOTTOM_VALUE_OF_CARD_IS_TEN = "|%s__%s|";
	private final String MIDDLE_OF_CARD = "|     |";


	// _____
	// |3 $|
	// | |
	// | |
	// |$___3|

	private Suit suit;
	private Rank rank;
	private String[] asciiCard = new String[5];

	public Card() {

	}

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
		setAsciiCard();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}

	public String toString() {
		StringBuilder card = new StringBuilder(rank.toString());
		card.append(" of " + suit.toString());
		return card.toString();
	}

	public int getValue() {
		return rank.getValue();
	}

	public Suit getSuit() {
		return suit;
	}

	public void setAsciiCard() {

		String topValue = rank.equals(Rank.TEN) ? TOP_VALUE_OF_CARD_IS_TEN 
												: TOP_VALUE_OF_CARD;

		String bottomValue = rank.equals(Rank.TEN) ? BOTTOM_VALUE_OF_CARD_IS_TEN 
												   : BOTTOM_VALUE_OF_CARD;

		asciiCard[0] = TOP_OF_CARD;
		asciiCard[1] = String.format(topValue, rank.toString(), suit.toString());
		asciiCard[2] = MIDDLE_OF_CARD;
		asciiCard[3] = MIDDLE_OF_CARD;
		asciiCard[4] = String.format(bottomValue, suit.toString(), rank.toString());

	}

	public String getAsciiCard() {
		StringBuilder card = new StringBuilder();
		
		for (String cardLine : asciiCard) {
			card.append(cardLine + "\n");
		}
		return card.toString();
	}
	
	public String getAsciiCard(int index) {
		return asciiCard[index];
	}

}
