package com.skilldistillery.cards.blackjack;

import com.skilldistillery.myutils.UserInput;

public class BlackjackGameManager implements UserInput{
	
	private BlackjackMenu mainMenu; 
	private BlackjackMenu gameMenu;
	private BlackjackPlayer player = new BlackjackPlayer();
	private BlackjackDealer dealer = new BlackjackDealer();
	
	public BlackjackGameManager() {
		mainMenu = new BlackjackMenu();
		mainMenu.addHeader("Welcome to BlackJack Console Editon");
		mainMenu.addHeader("Good Luck And Have Fun!");
		
		gameMenu = new BlackjackMenu();
		gameMenu.addHeader("Choose an option");
		gameMenu.addOption("1. Hit");
		gameMenu.addOption("2. Stay");
	}
	
	public static void main(String[] args) {
		BlackjackGameManager game = new BlackjackGameManager();
		
		game.runGame();
	}
	
	public void runGame() {
		System.out.println(mainMenu.buildMenu());
		System.out.println(gameMenu.buildMenu());
	}

}
