package com.skilldistillery.cards.blackjack;

import java.util.List;

import com.skilldistillery.cards.common.Card;

public class BlackjackPlayer extends AbstractBlackjackPlayer {

    private BlackjackHand splitHand;
    private long money;

    public BlackjackPlayer() {
	super();
	splitHand = new BlackjackHand();
	money = 500;
    }

    // setters and getters

    public long getMoney() {
	return money;
    }

    public void setMoney(long cash) {
	this.money = cash;
    }

    public BlackjackHand getSplitHand() {
	return splitHand;
    }

    public void setSplitHand(BlackjackHand splitHand) {
	this.splitHand = splitHand;
    }

    // money manipulator methods
    public void winMoney(long someCash) {
	if (someCash < 0) {
	    return;
	} else {
	    money += someCash;
	}
    }

    public void loseMoney(long someCash) {
	if (someCash < 0) {
	    return;
	} else {
	    money -= someCash;
	}
    }

    // splitHand methods

    public List<Card> getAllCardsFromSplitHand() {
	return splitHand.getCards();
    }

    public int getSplitHandValue() {
	return splitHand.getTotalValue();
    }

    public void addCardToSplitHand(Card card) {
	splitHand.addCard(card);
    }

    public Card removeCardFromSplitHand(int index) {
	return splitHand.takeCardOut(index);
    }

    public Card getCardFromSplitHand(int index) {
	return splitHand.getCards().get(index);
    }

    public void splitTheHand() {
	if (getAllCardsFromHand().size() == 2) {
	    Card removedCard = removeCardFromHand(1);
	    addCardToSplitHand(removedCard);
	} else {
	    System.out.println("Can only split a hand with exactly 2 cards");
	}
    }
}