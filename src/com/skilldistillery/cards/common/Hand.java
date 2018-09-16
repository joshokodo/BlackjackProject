package com.skilldistillery.cards.common;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {

    private List<Card> hand;

    public Hand() {
	hand = new ArrayList<>();
    }

    public int getTotalValue() {
	int totalValue = 0;
	for (Card card : hand) {
	    totalValue += card.getValue();
	}
	return totalValue;
    }

    public void addCard(Card newCard) {
	hand.add(newCard);
    }

    public void clearHand() {
	hand.clear();
    }

    public List<Card> getCards() {
	return hand;
    }

    public Card takeCardOut(int cardIndex) {
	return hand.remove(cardIndex);
    }

    public String toString() {
	StringBuilder info = new StringBuilder();
	for (Card card : hand) {
	    info.append(card.toString() + "\n");
	}
	return info.toString();
    }

}
