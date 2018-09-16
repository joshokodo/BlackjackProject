
package com.skilldistillery.cards.blackjack;

import java.util.List;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Deck;

public class BlackjackDealer extends AbstractBlackjackPlayer {

    private Deck deck;

    public BlackjackDealer() {
	super();
	deck = new Deck();
    }
    public BlackjackDealer(boolean whatever) {
	super();
	deck = new Deck(whatever);
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
	addCardToHand((dealACard(isFaceUp)));
    }

    public List<Card> getDeck() {
	return deck.getDeck();
    }

}