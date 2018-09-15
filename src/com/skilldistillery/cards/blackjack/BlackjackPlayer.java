package com.skilldistillery.cards.blackjack;


public class BlackjackPlayer {
	
	private BlackjackHand hand;
	private long cash;
	
	public BlackjackPlayer() {
		hand = new BlackjackHand();
		cash = 500;
	}

	public BlackjackPlayer(long cash) {
		hand = new BlackjackHand();
		this.cash = cash;
	}

	public BlackjackHand getHand() {
		return hand;
	}

	public long getCash() {
		return cash;
	}

	public void setCash(long cash) {
		this.cash = cash;
	}
	
	public void winCash(long someCash) {
		if(someCash < 0) {
			return;
		}
		else {
			cash += someCash;
		}
	}
	
	public void loseCash(long someCash) {
		if(someCash < 0) {
			return;
		}
		else {
			cash -= someCash;
		}
		
	}
	
	
	
	
}
