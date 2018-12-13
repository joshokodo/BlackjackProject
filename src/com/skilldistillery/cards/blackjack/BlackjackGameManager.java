package com.skilldistillery.cards.blackjack;

import java.util.List;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Rank;
import com.skilldistillery.cards.common.Suit;
import com.skilldistillery.myutils.consoleUI.*;

public class BlackjackGameManager implements UserInputInterface {

	private BlackjackMenu menu;
	private BlackjackPlayer player;
	private BlackjackDealer dealer;
	private boolean gameover;
	private boolean playerWon;
	private boolean stillPlaying;
	private int choice;
	private int choiceMax;
	private long bet;

	public BlackjackGameManager() {
		menu = new BlackjackMenu();
		player = new BlackjackPlayer();
		dealer = new BlackjackDealer();
		gameover = false;
		playerWon = false;
		stillPlaying = false;
		choice = -1;
		choiceMax = 2;
		bet = 0;

	}

	public static void main(String[] args) {
		BlackjackGameManager game = new BlackjackGameManager();

		game.runProgram();
	}

	// runs program and gives user option to exit
	public void runProgram() {
		menu.setAsMainMenu();
		menu.printMenu();
		performMainMenuOption(getIntInput(1, 2));

	}

	// sets up a condition where if player stays on the first turn
	// dealer will continue to hit til 17 even if its score beats the
	// players before getting to at least 17
	private void setUpToTestDealerKeepsHittingTilSeventeen() {
		int index = dealer.getDeck().size();

		player.getAllCardsFromHand().set(0, new Card(Suit.SPADES, Rank.FIVE));
		player.getAllCardsFromHand().set(1, new Card(Suit.SPADES, Rank.FIVE));
		dealer.getAllCardsFromHand().set(0, new Card(Suit.SPADES, Rank.FOUR));
		dealer.getAllCardsFromHand().set(1, new Card(Suit.SPADES, Rank.FIVE));

		for (int i = 0; i < 2; i++) {
			player.getAllCardsFromHand().get(i).flipCardUp();
			dealer.getAllCardsFromHand().get(i).flipCardUp();
		}
		dealer.getAllCardsFromHand().get(1).flipCardDown();

		dealer.getDeck().set(--index, new Card(Suit.SPADES, Rank.FIVE));
		dealer.getDeck().set(--index, new Card(Suit.SPADES, Rank.TWO));

	}


	// starts game loop
	private void playGame() {

		while (stillPlaying) {

			initialStart();
			placeBet();

			printGameStatus();

			setInitialOptions();

			while (!gameover) {

				// checks if player has a blackjack or has busted
				if (playerHandIsDone(player.getHand())) {
					gameover = true;

				} else {
					menu.printMenu();

					choice = getIntInput(1, choiceMax);
					choiceMax = 2;

					performGameOption(choice);
					menu.setAsGameMenu(bet);

					gameover = dealerPastSeventeen();
				}
			}

			didPlayerWin();
			gameAftermath();
			resetRound();

		}
		exitProgram();
	}

	// determines if player won game and calculates betting accordingly
	// sets appropriate "play again" menu, prompting user to continue
	// game or to exit program
	private void gameAftermath() {
		if (playerWon) {
			player.winMoney(bet);
			menu.setAsWinPlayAgainMenu(player.getMoney(), bet);
		} else {

			// if game was pushed
			if (hasSameValue(player.getHand(), dealer.getHand())) {
				menu.setAsTiePlayAgainMenu(player.getMoney());
			} else {
				player.loseMoney(bet);
				menu.setAsLosePlayAgainMenu(player.getMoney(), bet);
			}
		}

		menu.printMenu();
		choice = getIntInput(1, 2);
		stillPlaying = choice == 1 ? true : false;
	}

	// executes code that either starts a new game
	// or exits program
	private void performMainMenuOption(int choice) {
		switch (choice) {

			case 1 :
				stillPlaying = true;
				playGame();
				break;

			case 2 :
				exitProgram();
		}
	}

	// has dealer deal a card to the passed in arg (hand)
	// intended for flexibility to facilitate split feature
	private void dealCardToHand(BlackjackHand hand) {
		hand.addCard(dealer.dealACard(true));
		adjustAces(hand);

	}

	// executes code according to user input that either doubles
	// down the bet and follows double down protocol, hit (deals card to player)
	// or stays ( goes to dealers turn protocol)
	private void performGameOption(int choice) {
		switch (choice) {

			case 3 :
				bet *= 2;

			case 1 :
				dealCardToHand(player.getHand());
				printGameStatus();
				if (choice != 3) {
					break;
				}

			case 2 :
				dealersTurn();
				break;

			case 4 :
				// TODO create method to hand entire split protocol. add code
				// below?
				// player.splitTheHand();
				// dealCardToHand(player.getHand());
				// dealCardToHand(player.getSplitHand());
				// bet *= 2;
				// printGameStatus();
				// menu.setAsGameMenuForHand(bet);
				break;

			default :
				System.out.println(
						"Im from performGameMenuOption default case. I Shouldnt be printing");

		}

	}

	// has dealer continually deal to itself until it hand is at a condition
	// that doesn't allow the dealer to make any more moves according to
	// blackjack
	// rules or wind/lose conditions

	private void dealersTurn() {

		// reveals dealers card and shows opening hand
		dealer.getCardFromHand(1).flipCardUp();
		printGameStatus();

		while (true) {

			if (dealerPastSeventeen()) {
				return;
			}

			dealer.dealCardToSelf(true);
			adjustAces(dealer.getHand());
			printGameStatus();
		}
	}

	// sets and prints an exit message and closes implemented scanner input
	private void exitProgram() {
		menu.setAsExitMenu();
		menu.printMenu();
		closeKeyboard();
		System.exit(0);

	}

	// clears player and dealers hands and checks to see if replenishing deck
	// is necessary
	private void resetRound() {
		player.getAllCardsFromHand().clear();
		dealer.getAllCardsFromHand().clear();
		checkToReplenish();
	}

	// resets game logic fields, shuffles dealers deck
	// and deals starting hand to players
	private void initialStart() {
		gameover = false;
		playerWon = false;
		choice = -1;
		choiceMax = 2;
		bet = 0;

		dealer.shuffleDeck();
		dealCardToHand(player.getHand());
		dealCardToHand(dealer.getHand());
		dealCardToHand(player.getHand());
		dealer.dealCardToSelf(false);
	}

	// checks if dealers deck is getting low, if so
	// adds a new deck to existing deck and shuffles
	private void checkToReplenish() {
		if (dealer.getDeck().size() <= 15) {
			dealer.replenishDeck();
			dealer.shuffleDeck();
		}
	}

	// goes thru entire list of cards passed and prints
	// ascii art form to screen
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

	// gets hand value for both dealer and player and displays it
	// along with ascii art form to the screen
	private void printGameStatus() {
		int dealerValue = dealer.getHandValue();
		int playerValue = player.getHandValue();

		printCards(dealer.getAllCardsFromHand());
		System.out.println("Dealers hand value: " + dealerValue);

		printCards(player.getAllCardsFromHand());
		System.out.println("Your hand value: " + playerValue);

	
		System.out.println(
				"-------------------------------------------------------");
	}

	// Determines if player has at least enough money to place the minimum bet
	// and prompts user for bet and sets bet Amount to users input
	private void placeBet() {
		if (player.getMoney() >= 5) {

			menu.setAsBetsMenu(player.getMoney());
			menu.printMenu();
			bet = getLongInput(5, player.getMoney());
		} else {
			System.out.println("Looks like you got no money to spend.");
			System.out.println("Come back when you got some doe.");
			exitProgram();
		}
	}

	// checks if hand passed is a Bust. if so, checks for soft aces
	// and replaces its value as hard aces
	private void adjustAces(BlackjackHand hand) {
		if (handBusted(hand)) {

			for (Card card : hand.getCards()) {

				if (card.getRank().equals(Rank.HIGH_ACE)) {
					card.setRank(Rank.LOW_ACE);
				}
			}
		}
	}

	// sets initial game menu depending of if split is an option
	// or not
	private void setInitialOptions() {
		// TODO uncomment below when ready to add split feature. delete code
		// below
		// commented

		// if(playerCanSplit()) {
		// menu.setAsInitialGameMenuWithSplitOption(bet);
		// choiceMax = 4;
		// }
		// else {
		// menu.setAsInitialGameMenu(bet);
		// choiceMax = 3;
		// }

		menu.setAsInitialGameMenu(bet);
		choiceMax = 3;

	}

	// condition checks methods

	// returns true if player has the option to split, false otherwise
	private boolean playerCanSplit() {
		if (player.getAllCardsFromHand().size() == 2) {
			Rank cardRank1 = player.getCardFromHand(0).getRank();
			Rank cardRank2 = player.getCardFromHand(1).getRank();

			return cardRank1.equals(cardRank2);
		}
		return false;
	}

	private boolean handBusted(BlackjackHand hand) {
		return hand.getTotalValue() > 21;
	}

	private boolean hasBetterValue(BlackjackHand hand1, BlackjackHand hand2) {
		return hand1.getTotalValue() > hand2.getTotalValue();
	}

	private boolean hasBlackjack(BlackjackHand hand) {
		return hand.getTotalValue() == 21;
	}

	private boolean hasSameValue(BlackjackHand hand1, BlackjackHand hand2) {
		return hand1.getTotalValue() == hand2.getTotalValue();
	}

	private boolean dealerPastSeventeen() {
		return dealer.getHandValue() >= 17;
	}

	// checks if hand passed in is in a condition that will not allow anymore
	// turns for it. BlackjackHand arg is to facilitate split feature
	private boolean playerHandIsDone(BlackjackHand hand) {
		return handBusted(hand) || hasBlackjack(hand);
	}

	// determines if player is winner of the game and sets the playerWon field
	// accordingly
	private void didPlayerWin() {

		// ensures dealers hidden card is accounted for
		// when determining winner and prints last game status
		boolean dealerHasHiddenCard = !dealer.getCardFromHand(1).isFaceUp();
		if (dealerHasHiddenCard) {
			dealer.getCardFromHand(1).flipCardUp();
			printGameStatus();
		}

		boolean winCondition1 = hasBlackjack(player.getHand())
				&& !hasBlackjack(dealer.getHand());
		boolean winCondition2 = !handBusted(player.getHand())
				&& handBusted(dealer.getHand());
		boolean winCondition3 = !handBusted(player.getHand())
				&& hasBetterValue(player.getHand(), dealer.getHand());

		playerWon = winCondition1 || winCondition2 || winCondition3;
	}

}