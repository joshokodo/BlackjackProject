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
		
	}

	public static void main(String[] args) {
		BlackjackGameManager game = new BlackjackGameManager();

		game.runProgram();
	}

	public void runProgram() {
		
		System.out.println(menu.buildMenu());
		performMainMenuOption(getIntInput(1, 2));

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
			playerWon = dealersTurn();
			break;
			
		}
	}
	
	private void runGame() {
		menu.setAsGameMenu();
		startingDeal();
		int choice = 0;
		while(true) {
			
			printGameStatus(true);
			
			if(hasBlackjack(player)) {
				playerWon = true;
				break;
			}
			
			System.out.println(menu.buildMenu());
			
			choice = getIntInput(1, 2);
			performGameMenuOption(choice);
		
			if(choice == 2) {
				break;
			}
			
		}
		if(playerWon) {
			System.out.println("Congrats! Looks like you won!");
		}
		else {
			System.out.println("Rats!. Looks like you lost!");
		}
		
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
	
	private void playersTurn() {
		
	}
	
	// returns true if player won, false if player loses
	private boolean dealersTurn() {
		dealer.getHand().get(1).flipCardUp();
		
		
		while(true) {
			
			printGameStatus(false);
			if(!playerBusted(dealer)) {
				
				if(hasBetterValue(dealer, player)) {
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
		System.out.println(menu.buildMenu());
		closeKeyboard();
		System.exit(0);
		
	}
	
	private void startingDeal() {
		dealer.shuffleDeck();
		player.takeACard(dealer.dealACard(true));
		dealer.dealCardToSelf(true);
		player.takeACard(dealer.dealACard(true));
		dealer.dealCardToSelf(false);
	}
	
	private void printCards(List<Card> hand ) {
		StringBuilder cards = new StringBuilder();
		for(int i = 0; i < 5; i++) {
			
			for (Card card : hand) {
				cards.append(card.getAsciiCard(i));
			}
			cards.append("\n");
		}
		
		System.out.println(cards);
		
	}
	
	private void printGameStatus(boolean playerTurn) {
		int dealerValue = playerTurn ? dealer.getHand().get(0).getValue()
									 : dealer.getHandValue();
		
		int playerValue = player.getHandValue();
		
		printCards(dealer.getHand());
		System.out.println("Dealers hand value: " + dealerValue);
		
		printCards(player.getHand());
		System.out.println("Your hand value: " + playerValue);
		System.out.println("\n\n\n\n");
	}

}
