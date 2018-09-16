package com.skilldistillery.cards.blackjack;

import com.skilldistillery.myutils.AbstractMenu;

public class BlackjackMenu extends AbstractMenu {

    private final static char MENU_BORDER_LINING = '\u2664';

    private final static String MAIN_MENU_HEADER_1 = "Welcome to BlackJack Console Editon";
    private final static String MAIN_MENU_HEADER_2 = "Good Luck And Have Fun!";
    private final static String MAIN_MENU_HEADER_3 = "Choose an option";
    private final static String MAIN_MENU_OPTION_1 = "1. Play BlackJack";
    private final static String MAIN_MENU_OPTION_2 = "2. Exit Program";

    private final static String GAME_MENU_HEADER_1 = "So...what's your next move?";
    private final static String GAME_MENU_OPTION_1 = "1. Hit";
    private final static String GAME_MENU_OPTION_2 = "2. Stay";
    private final static String GAME_MENU_OPTION_3 = "3. Split";
    
    private final static String BETS_MENU_HEADER_1 = "You currently have $";
    private final static String BETS_MENU_HEADER_2 = "Okay, hotshot. Place your bet! ($5 Minimum)";
    
    private final static String PLAY_AGAIN_MENU_HEADER_1 = "Play again?";
    private final static String PLAY_AGAIN_MENU_OPTION_1 = "1. Yes";
    private final static String PLAY_AGAIN_MENU_OPTION_2 = "2. No";
    
    private final static String WIN_MENU_HEADER_1 = "Congrats! Looks like you won!";
    private final static String LOSE_MENU_HEADER_2 = "Rats! Looks like you lost!";
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

    public void setAsGameMenu() {
	clearAll();
	addHeader(GAME_MENU_HEADER_1);
	addOption(GAME_MENU_OPTION_1);
	addOption(GAME_MENU_OPTION_2);
    }

    public void setAsGameMenuWithSplitOption() {
	clearAll();
	addHeader(GAME_MENU_HEADER_1);
	addOption(GAME_MENU_OPTION_1);
	addOption(GAME_MENU_OPTION_2);
	addOption(GAME_MENU_OPTION_3);
    }
    
    public void setAsWinPlayAgainMenu(long playerMoney) {
	clearAll();
	
	addHeader(WIN_MENU_HEADER_1);
	
	addHeader(BETS_MENU_HEADER_1 + playerMoney);
	addHeader(PLAY_AGAIN_MENU_HEADER_1);
	addOption(PLAY_AGAIN_MENU_OPTION_1);
	addOption(PLAY_AGAIN_MENU_OPTION_2);
    }
    public void setAsLosePlayAgainMenu(long playerMoney) {
	clearAll();
	
	addHeader(LOSE_MENU_HEADER_2);
	
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
