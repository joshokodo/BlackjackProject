package com.skilldistillery.cards.blackjack;

import com.skilldistillery.myutils.consoleUI.*;

public class BlackjackMenu extends AbstractUI {

	private final static char MENU_BORDER_LINING = '\u2664';

	private final static String MAIN_MENU_HEADER_1 = "Welcome to BlackJack Console Editon";
	private final static String MAIN_MENU_HEADER_2 = "Good Luck And Have Fun!";
	private final static String MAIN_MENU_HEADER_3 = "Choose an option";
	private final static String MAIN_MENU_OPTION_1 = "1. Play BlackJack";
	private final static String MAIN_MENU_OPTION_2 = "2. Exit Program";

	private final static String GAME_MENU_HEADER_FOR_HAND = "So...what's your next move with the first hand?";
	private final static String GAME_MENU_HEADER_FOR_SPLIT_HAND = "So...what's your next move with the split hand?";

	private final static String GAME_MENU_HEADER_1 = "So...what's your next move?";
	private final static String GAME_MENU_OPTION_1 = "1. Hit";
	private final static String GAME_MENU_OPTION_2 = "2. Stay";
	private final static String GAME_MENU_OPTION_3 = "3. Double Down";
	private final static String GAME_MENU_OPTION_4 = "4. Split";

	private final static String BETS_MENU_HEADER_1 = "You currently have $";
	private final static String BETS_MENU_HEADER_2 = "Okay, hotshot. Place your bet! ($5 Minimum)";
	private final static String GENERAL_BETS_MENU_HEADER = "You're betting $";

	private final static String PLAY_AGAIN_MENU_HEADER_1 = "Play again?";
	private final static String PLAY_AGAIN_MENU_OPTION_1 = "1. Yes";
	private final static String PLAY_AGAIN_MENU_OPTION_2 = "2. No";

	private final static String WIN_MENU_HEADER_1 = "Congrats! Looks like you won $";
	private final static String LOSE_MENU_HEADER_2 = "Rats! Looks like you lost $";
	private final static String TIE_MENU_HEADER_3 = "Hmm! Looks like a push (tie)! oh well.";

	private final static String EXIT_MESSAGE = "Thanks for playing. hope you won a bunch.";

	public BlackjackMenu() {
		super(MENU_BORDER_LINING);
	}

	public void setAsMainMenu() {
		clearAll();
		addHeader(MAIN_MENU_HEADER_1);
		addHeader(MAIN_MENU_HEADER_2);
		addHeader(MAIN_MENU_HEADER_3);
		addOption(MAIN_MENU_OPTION_1);
		addOption(MAIN_MENU_OPTION_2);
	}

	public void setAsGameMenu(long betAmount) {
		clearAll();
		addHeader(GENERAL_BETS_MENU_HEADER + betAmount);
		addHeader(GAME_MENU_HEADER_1);
		addOption(GAME_MENU_OPTION_1);
		addOption(GAME_MENU_OPTION_2);
	}

	public void setAsGameMenuForHand(long betAmount) {
		clearAll();
		addHeader(GENERAL_BETS_MENU_HEADER + betAmount);
		addHeader(GAME_MENU_HEADER_FOR_HAND);
		addOption(GAME_MENU_OPTION_1);
		addOption(GAME_MENU_OPTION_2);
	}

	public void setAsGameMenuForSplitHand(long betAmount) {
		clearAll();
		addHeader(GENERAL_BETS_MENU_HEADER + betAmount);
		addHeader(GAME_MENU_HEADER_FOR_SPLIT_HAND);
		addOption(GAME_MENU_OPTION_1);
		addOption(GAME_MENU_OPTION_2);
	}

	public void setAsInitialGameMenu(long betAmount) {
		clearAll();
		addHeader(GENERAL_BETS_MENU_HEADER + betAmount);
		addHeader(GAME_MENU_HEADER_1);
		addOption(GAME_MENU_OPTION_1);
		addOption(GAME_MENU_OPTION_2);
		addOption(GAME_MENU_OPTION_3);
	}

	public void setAsInitialGameMenuWithSplitOption(long betAmount) {
		clearAll();
		addHeader(GENERAL_BETS_MENU_HEADER + betAmount);
		addHeader(GAME_MENU_HEADER_1);
		addOption(GAME_MENU_OPTION_1);
		addOption(GAME_MENU_OPTION_2);
		addOption(GAME_MENU_OPTION_3);
		addOption(GAME_MENU_OPTION_4);
	}

	public void setAsWinPlayAgainMenu(long playerMoney, long betAmount) {
		clearAll();

		addHeader(WIN_MENU_HEADER_1 + betAmount);

		addHeader(BETS_MENU_HEADER_1 + playerMoney);
		addHeader(PLAY_AGAIN_MENU_HEADER_1);
		addOption(PLAY_AGAIN_MENU_OPTION_1);
		addOption(PLAY_AGAIN_MENU_OPTION_2);
	}

	public void setAsLosePlayAgainMenu(long playerMoney, long betAmount) {
		clearAll();

		addHeader(LOSE_MENU_HEADER_2 + betAmount);

		addHeader(BETS_MENU_HEADER_1 + playerMoney);
		addHeader(PLAY_AGAIN_MENU_HEADER_1);
		addOption(PLAY_AGAIN_MENU_OPTION_1);
		addOption(PLAY_AGAIN_MENU_OPTION_2);
	}

	public void setAsTiePlayAgainMenu(long playerMoney) {
		clearAll();

		addHeader(TIE_MENU_HEADER_3);

		addHeader(BETS_MENU_HEADER_1 + playerMoney);
		addHeader(PLAY_AGAIN_MENU_HEADER_1);
		addOption(PLAY_AGAIN_MENU_OPTION_1);
		addOption(PLAY_AGAIN_MENU_OPTION_2);
	}

	public void setAsBetsMenu(long playerMoney) {
		clearAll();
		addHeader(BETS_MENU_HEADER_1 + playerMoney);
		addHeader(BETS_MENU_HEADER_2);
	}

	public void setAsExitMenu() {
		clearAll();
		addHeader(EXIT_MESSAGE);
	}

}