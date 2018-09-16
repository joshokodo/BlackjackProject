package com.skilldistillery.cards.blackjack;

import java.util.List;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Deck;

public class BlackjackDealer extends BlackjackPlayer {

    private Deck deck;

    public BlackjackDealer() {
	super(Long.MAX_VALUE);
	deck = new Deck();
    }

    public void replenishDeck() {
	Deck moreCards = new Deck();
	deck.addToDeck(moreCards.getDeck());
    }

    public void shuffleDeck() {
	deck.shuffle();
    }

    public Card dealACard(boolean isFaceUp) {
	return deck.dealCard(isFaceUp);
    }

    public void dealCardToSelf(boolean isFaceUp) {
	takeACard((dealACard(isFaceUp)));
    }

    public List<Card> getDeck() {
	return deck.getDeck();
    }

}
