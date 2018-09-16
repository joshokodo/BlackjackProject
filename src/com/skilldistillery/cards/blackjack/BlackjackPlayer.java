package com.skilldistillery.cards.blackjack;

public class BlackjackPlayer extends AbstractBlackjackPlayer{

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


    // money manipulator methods
    public void winMoney(long someCash) {
	if (someCash < 0) {
	    return;
	} 
	else {
	    money += someCash;
	}
    }

    public void loseMoney(long someCash) {
	if (someCash < 0) {
	    return;
	} 
	else {
	    money -= someCash;
	}

    }

}