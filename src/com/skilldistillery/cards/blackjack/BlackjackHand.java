package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Hand;

public class BlackjackHand extends Hand {

	public BlackjackHand() {
		
	}

	@Override
	public int getHandValue() {
		int handValue = 0;
		
		for (Card card : getHand()) {
			handValue += card.getValue();
		}
		
		return handValue;
	}
	
	

}
