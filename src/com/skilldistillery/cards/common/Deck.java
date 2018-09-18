package com.skilldistillery.cards.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> deck;

    public Deck() {
	Suit[] suits = Suit.values();
	Rank[] ranks = Rank.values();
	deck = new ArrayList<>();

	for (Suit suit : suits) {

	    for (Rank rank : ranks) {

		if (!rank.equals(Rank.LOW_ACE)) {
		    deck.add(new Card(suit, rank));
		}
	    }
	}
    }

    // used to test split feature by making every card the same
//    public Deck(boolean whatever) {
//	deck = new ArrayList<>();
//	for (int i = 0; i < 52; i++) {
//	    deck.add(new Card(Suit.SPADES, Rank.FIVE));
//	}
//    }

    public int checkDeckSize() {
	return deck.size();
    }

    public Card dealCard(Boolean isFaceUp) {
	int indexOfTopCard = deck.size() - 1;
	Card dealtCard = deck.remove(indexOfTopCard);

	if (isFaceUp) {
	    dealtCard.flipCardUp();
	} else {
	    dealtCard.flipCardDown();
	}

	return dealtCard;
    }

    public void shuffle() {
	Collections.shuffle(deck);
    }

    public void addToDeck(Collection<Card> moreCards) {
	deck.addAll(moreCards);
    }

    public List<Card> getDeck() {
	return deck;
    }

}