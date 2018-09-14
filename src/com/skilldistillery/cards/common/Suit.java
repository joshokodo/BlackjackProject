package com.skilldistillery.cards.common;

public enum Suit {
	HEARTS("\u2764"), 
	SPADES("\u2660"), 
	CLUBS("\u2663"), 
	DIAMONDS("\u2666");

	Suit(String name) {
		this.name = name;
	}

	private String name;

	@Override
	public String toString() {
		return name;
	}

}
