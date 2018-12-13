package com.skilldistillery.cards.blackjack;

import java.util.List;

import com.skilldistillery.cards.common.Card;

public abstract class AbstractBlackjackPlayer {

	protected BlackjackHand hand;

	public AbstractBlackjackPlayer() {
		hand = new BlackjackHand();
	}

	// setters and getters
	public BlackjackHand getHand() {
		return hand;
	}

	public void setHand(BlackjackHand hand) {
		this.hand = hand;
	}

	// other methods
	public List<Card> getAllCardsFromHand() {
		return hand.getCards();
	}

	public int getHandValue() {
		return hand.getTotalValue();
	}

	public void addCardToHand(Card card) {
		hand.addCard(card);
	}

	public Card removeCardFromHand(int index) {
		return hand.takeCardOut(index);
	}

	public Card getCardFromHand(int index) {
		return hand.getCards().get(index);
	}

}