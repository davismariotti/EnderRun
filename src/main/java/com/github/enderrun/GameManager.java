package com.github.enderrun;

public class GameManager {
	
	private static GameManager instance;
	public Game currentGame = new Game();
		
	public static GameManager getInstance() {
		if (GameManager.instance == null) GameManager.instance = new GameManager();
		return GameManager.instance;
	}
	
	public void startNewGame() {
		currentGame.finish();
		currentGame = new Game();
	}
}
