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
	initialStart();
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
		menu.setAsWinPlayAgainMenu(player.getMoney());
	    } 
	    else {
		player.loseMoney(bet);
		menu.setAsLosePlayAgainMenu(player.getMoney());
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
    
    public void playGame() {
	
	boolean stillPlaying = true;
	
	while(stillPlaying) {
	    
	    initialStart();
	    boolean gameover = false;
	    long bet = 0;
	    int choice = -1;
	    
	    while(!gameover) {
		
		bet = placeBet();
		
		if(bet <= 0) {
		    gameover = true;
		}
		//is duplicated below key 1
		else if(hasBlackjack(player)) {
		    
		    gameover = true;
		    printGameStatus(false);
		    
		    if(isBlackjackWin()) {
			menu.setAsWinPlayAgainMenu(player.getMoney());
			player.winMoney(bet);
		    }
		    
		    else {
			menu.setAsTiePlayAgainMenu(player.getMoney());
		    }
		    
		}
		else {
		    
		    printGameStatus(true);
		    
		    menu.setAsGameMenu();
		    menu.printMenu();
		    choice = getIntInput(1, 2);
		    
		    printGameStatus(false);

		    if(playerBusted(player)) {
			menu.setAsLosePlayAgainMenu(player.getMoney());
			player.loseMoney(bet);
		    }
		    //is duplicated above key 1
		    else if(hasBlackjack(player)) {
			    
			gameover = true;
			printGameStatus(false);
			    
			if(isBlackjackWin()) {
			    menu.setAsWinPlayAgainMenu(player.getMoney());
			    player.winMoney(bet);
			}  
			else {
			    menu.setAsTiePlayAgainMenu(player.getMoney());
			}
			    
		    }
		}
		
		
	    }
	    resetRound();
	    
	    
	}
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
    private void performGameMenuOption(int choice) {
	switch (choice) {
	
	case 1:
	    player.takeACard(dealer.dealACard(true));
	    break;
	    
	case 2:
	    exitProgram();
	}
    }

    // returns true if the game should continue to the dealers turn
    // retunrs false if player either won or lost
    private boolean playersTurn() {
	menu.setAsGameMenu();
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
	initialStart();
    }

    private void initialStart() {
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
	if(dealer.getDeck().size() <= 15) {
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
    private long placeBet() {
	if(player.getMoney() >= 5) {
		
	    menu.setAsBetsMenu(player.getMoney());
	    menu.printMenu();
	    return getLongInput(5, player.getMoney());
	}
	else {
	    System.out.println("Looks like you got not money to spend.");
	    System.out.println("Come back when you got some doe.");
	    return 0;
	}
    }

    
    // condition checks
    private boolean playerBusted(BlackjackPlayer player) {
	return player.getHandValue() > 21;
    }

    private boolean hasBetterValue(BlackjackPlayer p1, BlackjackPlayer p2) {
	return p1.getHandValue() > p2.getHandValue();
    }

    private boolean hasBlackjack(BlackjackPlayer player) {
	return player.getHandValue() == 21;
    }
    
    private boolean hasSameValue(BlackjackPlayer p1, BlackjackPlayer p2) {
	return p1.getHandValue() == p2.getHandValue();
    }
    
    private boolean dealerPastSeventeen() {
	return dealer.getHandValue() >= 17;
    }
    
    private boolean isBlackjackWin() {
	
	if(hasBlackjack(player) && hasBlackjack(dealer)) {
	    return false;
	} 
	else {
	    return true;
		
	}
	
    }
}
