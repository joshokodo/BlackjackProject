
package com.skilldistillery.cards.blackjack;

import java.util.List;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Deck;
import com.skilldistillery.cards.common.Rank;
import com.skilldistillery.cards.common.Suit;

public class BlackjackDealer extends AbstractBlackjackPlayer {

    private Deck deck;

    public BlackjackDealer() {
	super();
	deck = new Deck();
    }

    public BlackjackDealer(int whatever) {
	super();
	deck = new Deck();

	int index = deck.checkDeckSize();

	deck.getDeck().set(--index, new Card(Suit.SPADES, Rank.FOUR));
	deck.getDeck().set(--index, new Card(Suit.SPADES, Rank.FOUR));

	deck.getDeck().set(--index, new Card(Suit.SPADES, Rank.FIVE));
	deck.getDeck().set(--index, new Card(Suit.SPADES, Rank.FIVE));

	deck.getDeck().set(--index, new Card(Suit.SPADES, Rank.FIVE));
	deck.getDeck().set(--index, new Card(Suit.SPADES, Rank.FIVE));

	deck.getDeck().set(--index, new Card(Suit.SPADES, Rank.TWO));
	deck.getDeck().set(--index, new Card(Suit.SPADES, Rank.TWO));

    }

    // used to test split feature
//    public BlackjackDealer(boolean whatever) {
//	super();
//	deck = new Deck(whatever);
//    }

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