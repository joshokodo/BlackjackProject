package com.skilldistillery.cards.blackjack;

import java.util.List;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.myutils.UserInput;

public class BlackjackGameManager implements UserInput {

    private BlackjackMenu menu;
    private BlackjackPlayer player;
    private BlackjackDealer dealer;
    private boolean playerWon;

    public BlackjackGameManager() {
	menu = new BlackjackMenu();
	menu.setAsMainMenu();
	player = new BlackjackPlayer();
	dealer = new BlackjackDealer();
	playerWon = false;
    }

    public static void main(String[] args) {
	BlackjackGameManager game = new BlackjackGameManager();

	game.runProgram();
    }

    public void runProgram() {

	menu.printMenu();
	performMainMenuOption(getIntInput(1, 2));

    }

    private void runGame() {
	startingDeal();
	long bet = 0;
	while (true) {
	    
	    if(player.getMoney() >= 5) {
		
		menu.setAsBetsMenu(player.getMoney());
		menu.printMenu();
		bet = getLongInput(5, player.getMoney());
	    }
	    else {
		System.out.println("Looks like you got not money to spend.");
		System.out.println("Come back when you got some doe.");
		break;
	    }

	    if (hasBlackjack(player)) {
		playerWon = true;
	    } 
	    else {
		
		if (playersTurn()) {
		    playerWon = dealersTurn();
		}
	    }
	    
	    if (playerWon) {
		player.winMoney(bet);
		menu.setAsPlayAgainMenu(true, player.getMoney());
	    } 
	    else {
		player.loseMoney(bet);
		menu.setAsPlayAgainMenu(false, player.getMoney());
	    }
	    
	    menu.printMenu();
	    int choice = getIntInput(1, 2);
	    if(choice == 1) {
		resetRound();
		continue;
	    }
	    else {
		break;
	    }

	}
	exitProgram();

    }

    private void performMainMenuOption(int choice) {
	switch (choice) {

	case 1:
	    runGame();
	    break;

	case 2:
	    exitProgram();
	}
    }

    // returns true if the game should continue to the dealers turn
    // retunrs false if player either won or lost
    private boolean playersTurn() {
	menu.setAsUserMenu();
	boolean playerStayed = false;

	do {

	    printGameStatus(true);

	    menu.printMenu();
	    int choice = getIntInput(1, 2);

	    if (choice == 1) {
		player.takeACard(dealer.dealACard(true));
	    } 
	    else if (choice == 2) {
		playerStayed = true;
		continue;
	    }

	    if (playerBusted(player)) {
		playerWon = false;
		return false;
	    }
	    if (hasBlackjack(player)) {
		playerWon = true;
		return false;
	    }

	} while (!playerStayed);

	return true;

    }

    // returns true if player won, false if player loses
    private boolean dealersTurn() {
	dealer.getHand().get(1).flipCardUp();

	while (true) {

	    printGameStatus(false);

	    if (hasBlackjack(dealer)) {
		return false;
	    } 
	    else if (!playerBusted(dealer)) {

		if (hasBetterValue(dealer, player)) {
		    return false;
		}
		dealer.dealCardToSelf(true);
		continue;

	    } 
	    else {
		return true;
	    }

	}
    }

    private void exitProgram() {
	menu.setAsExitMenu();
	menu.printMenu();
	closeKeyboard();
	System.exit(0);

    }
    
    private void resetRound() {
	resetHands();
	checkToReplenish();
	startingDeal();
    }

    private void startingDeal() {
	dealer.shuffleDeck();
	player.takeACard(dealer.dealACard(true));
	dealer.dealCardToSelf(true);
	player.takeACard(dealer.dealACard(true));
	dealer.dealCardToSelf(false);
    }
    
    private void resetHands() {
	player.getHand().clear();
	dealer.getHand().clear();
    }
    private void checkToReplenish() {
	if(dealer.getDealerDeck().size() <= 10) {
	    dealer.replenishDeck();
	    dealer.shuffleDeck();
	}
    }

    private void printCards(List<Card> hand) {
	StringBuilder cards = new StringBuilder();
	for (int i = 0; i < 5; i++) {

	    for (Card card : hand) {
		cards.append(card.getAsciiCard(i));
	    }
	    cards.append("\n");
	}

	System.out.println(cards);

    }

    private void printGameStatus(boolean playerTurn) {
	int dealerValue = playerTurn ? dealer.getHand().get(0).getValue() : dealer.getHandValue();

	int playerValue = player.getHandValue();

	printCards(dealer.getHand());
	System.out.println("Dealers hand value: " + dealerValue);

	printCards(player.getHand());
	System.out.println("Your hand value: " + playerValue);
	System.out.println("-------------------------------------------------------");
    }

    private boolean playerBusted(BlackjackPlayer player) {
	return player.getHandValue() > 21;
    }

    private boolean hasBetterValue(BlackjackPlayer p1, BlackjackPlayer p2) {
	return p1.getHandValue() > p2.getHandValue();
    }

    private boolean hasBlackjack(BlackjackPlayer player) {
	return player.getHandValue() == 21;
    }

}
