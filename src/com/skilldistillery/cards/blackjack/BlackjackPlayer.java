package com.skilldistillery.cards.blackjack;

import java.util.List;

import com.skilldistillery.cards.common.Card;

public class BlackjackPlayer {
	
	private BlackjackHand hand;
	private long money;
	
	public BlackjackPlayer() {
		hand = new BlackjackHand();
		money = 500;
	}

	public BlackjackPlayer(long cash) {
		hand = new BlackjackHand();
		this.money = cash;
	}

	// setters and getters
	public List<Card> getHand() {
		return hand.getCards();
	}
	
	public long getMoney() {
		return money;
	}
	
	public void setMoney(long cash) {
		this.money = cash;
	}
	
	public int getHandValue() {
		return hand.getTotalValue();
	}
	
	// other methods
	public void takeACard(Card card) {
		hand.addCard(card);
	}

	// money manipulator methods
	public void winMoney(long someCash) {
		if(someCash < 0) {
			return;
		}
		else {
			money += someCash;
		}
	}
	
	public void loseMoney(long someCash) {
		if(someCash < 0) {
			return;
		}
		else {
			money -= someCash;
		}
		
	}
	
	
	
	
}
