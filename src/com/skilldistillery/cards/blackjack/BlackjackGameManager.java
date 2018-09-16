package com.skilldistillery.cards.blackjack;

import java.util.List;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Rank;
import com.skilldistillery.myutils.UserInput;

public class BlackjackGameManager implements UserInput {

    private BlackjackMenu menu;
    private BlackjackPlayer player;
    private BlackjackDealer dealer;
    private boolean gameover;
    private boolean playerWon;
    private long bet;

    public BlackjackGameManager() {
	menu = new BlackjackMenu();
	player = new BlackjackPlayer();
	dealer = new BlackjackDealer();
	gameover = false;
	playerWon = false;
	bet = 0;

    }

    public static void main(String[] args) {
	BlackjackGameManager game = new BlackjackGameManager();

	game.runProgram();
    }

    public void runProgram() {
	menu.setAsMainMenu();
	menu.printMenu();
	performMainMenuOption(getIntInput(1, 2));

    }

    public void playGame() {

	boolean stillPlaying = true;

	while (stillPlaying) {

	    initialStart();
	    bet = placeBet(); // returns zero if player can't bet at least minimum

	    if (bet <= 0) {
		exitProgram();
	    }

	    // shows opening hand of player and sets up game menu
	    printGameStatus();
	    menu.setAsInitialGameMenu(bet);
	    int choice = -1;
	    
	    while (!gameover) {

		if (playerIsDone()) {
		    gameover = true;
		} 
		else {
		    menu.printMenu();
		    
		    choice = getIntInput(1, 3);
		    
		    performGameMenuOption(choice);
		    //menu.setAsGameMenu();
		    
		    if(choice == 3) {
			performGameMenuOption(2);
		    }
		    gameover = dealerIsDone();
		}
	    }

	    playerWon = didPlayerWin();
	    gameAftermath();

	    menu.printMenu();
	    choice = getIntInput(1, 2);
	    stillPlaying = choice == 1 ? true : false;

	    resetRound();

	}
    }

    private void gameAftermath() {
	if (playerWon) {
	    player.winMoney(bet);
	    menu.setAsWinPlayAgainMenu(player.getMoney(), bet);
	} 
	else {

	    // if game was pushed
	    if (hasSameValue(player, dealer)) {
		menu.setAsTiePlayAgainMenu(player.getMoney());
	    } 
	    else {
		player.loseMoney(bet);
		menu.setAsLosePlayAgainMenu(player.getMoney(), bet);
	    }
	}
    }

    private void performMainMenuOption(int choice) {
	switch (choice) {

	case 1:
	    playGame();
	    break;

	case 2:
	    exitProgram();
	}
    }

    private void performGameMenuOption(int choice) {
	switch (choice) {
	
	case 3:
	    bet *= 2;
	case 1:
	    player.addCardToHand(dealer.dealACard(true));
	    checkAces(player);
	    printGameStatus();
	    break;

	case 2:
	    dealersTurn();
	    break;

	default:
	    System.out.println("Im from performGameMenuOption default case. I Shouldnt be printing");

	}

    }

    private void dealersTurn() {

	// reveals dealers card and shows opening hand
	dealer.getCardFromHand(1).flipCardUp();
	printGameStatus();

	while (true) {

	    if (dealerIsDone()) {
		return;
	    }

	    dealer.dealCardToSelf(true);
	    checkAces(dealer);
	    printGameStatus();
	}
    }

    private void exitProgram() {
	menu.setAsExitMenu();
	menu.printMenu();
	closeKeyboard();
	System.exit(0);

    }

    private void resetRound() {
	player.getAllCardsFromHand().clear();
	dealer.getAllCardsFromHand().clear();
	checkToReplenish();
    }

    private void initialStart() {
	gameover = false;
	playerWon = false;
	bet = 0;

	dealer.shuffleDeck();
	player.addCardToHand(dealer.dealACard(true));
	dealer.dealCardToSelf(true);
	player.addCardToHand(dealer.dealACard(true));
	dealer.dealCardToSelf(false);
    }

    private void checkToReplenish() {
	if (dealer.getDeck().size() <= 15) {
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

    private void printGameStatus() {
	int dealerValue = dealer.getHandValue();

	int playerValue = player.getHandValue();

	printCards(dealer.getAllCardsFromHand());
	System.out.println("Dealers hand value: " + dealerValue);

	printCards(player.getAllCardsFromHand());
	System.out.println("Your hand value: " + playerValue);
	System.out.println("-------------------------------------------------------");
    }

    private long placeBet() {
	if (player.getMoney() >= 5) {

	    menu.setAsBetsMenu(player.getMoney());
	    menu.printMenu();
	    return getLongInput(5, player.getMoney());
	} 
	else {
	    System.out.println("Looks like you got no money to spend.");
	    System.out.println("Come back when you got some doe.");
	    return 0;
	}
    }

    
    private void checkAces(AbstractBlackjackPlayer player) {
	if (someoneBusted(player)) {
	    
	    for (Card card : player.getAllCardsFromHand()) {
		
		if(card.getRank().equals(Rank.HIGH_ACE)) {
		    card.setRank(Rank.LOW_ACE);
		}
	    }
	}
    }
    // condition checks
    
    // checks if player/dealer busted and if they have a 
    // soft Ace, turns it hard
    private boolean someoneBusted(AbstractBlackjackPlayer player) {
	return player.getHandValue() > 21;
    }

    private boolean hasBetterValue(AbstractBlackjackPlayer p1, AbstractBlackjackPlayer p2) {
	return p1.getHandValue() > p2.getHandValue();
    }

    private boolean hasBlackjack(AbstractBlackjackPlayer player) {
	return player.getHandValue() == 21;
    }

    private boolean hasSameValue(AbstractBlackjackPlayer p1, AbstractBlackjackPlayer p2) {
	return p1.getHandValue() == p2.getHandValue();
    }

    private boolean dealerPastSeventeen() {
	return dealer.getHandValue() >= 17;
    }

    private boolean playerIsDone() {
	return someoneBusted(player) || hasBlackjack(player);
    }

    private boolean dealerIsDone() {
	return dealerPastSeventeen() || someoneBusted(dealer) || 
		hasBetterValue(dealer, player) || hasBlackjack(dealer);
    }

    private boolean didPlayerWin() {

	// ensures dealers hidden card is accounted for
	// when determining winner and prints last game status
	boolean dealerHasHiddenCard = !dealer.getCardFromHand(1).isFaceUp();
	if(dealerHasHiddenCard) {
	    dealer.getCardFromHand(1).flipCardUp();
	    printGameStatus();
	}
	
	boolean winCondition1 = hasBlackjack(player) && !hasBlackjack(dealer);
	boolean winCondition2 = !someoneBusted(player) && hasBetterValue(player, dealer);
	boolean winCondition3 = someoneBusted(dealer);
	
	return winCondition1 || winCondition2 || winCondition3;
    }

}
