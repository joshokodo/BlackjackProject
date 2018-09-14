package com.skilldistillery.cards.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	private List<Card> deck;
	
	public Deck() {
		Suit[] suits = Suit.values();
		Rank[] ranks = Rank.values();
		deck = new ArrayList<>();
		
		for (Suit suit : suits) {
			for (Rank rank : ranks) {
				deck.add(new Card(suit, rank));
			}
		}
	}
	
	public int checkDeckSize() {
		return deck.size();
	}
	
	public Card dealCard() {
		int indexOfTopCard = deck.size() - 1;
		return deck.remove(indexOfTopCard);
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}

}
