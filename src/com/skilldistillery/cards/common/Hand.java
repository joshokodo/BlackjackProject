package com.skilldistillery.cards.common;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
	
	protected List<Card> hand;
	
	public Hand() {
		hand = new ArrayList<>();
	}
	
	abstract public int getHandValue();
	
	public void addCard(Card newCard) {
		hand.add(newCard);
	}
	
	public void clearHand() {
		hand.clear();
	}
	
	public List<Card> getHand(){
		return hand;
	}
	
	public String toString() {
		StringBuilder info = new StringBuilder();
		for (Card card : hand) {
			info.append(card.toString() + "\n");
		}
		return info.toString();
	}

}
