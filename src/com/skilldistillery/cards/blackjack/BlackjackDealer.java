package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.Deck;

public class BlackjackDealer {
	
	private Deck deck;
	private BlackjackHand hand;
	
	public BlackjackDealer() {
		deck = new Deck();
		hand = new BlackjackHand();
	}
	
	public void replenishDeck() {
		Deck moreCards = new Deck();
		deck.addToDeck(moreCards.getDeck());
	}
	
	public Deck getDeck() {
		return deck;
	}
	public BlackjackHand getHand() {
		return hand;
	}

}
