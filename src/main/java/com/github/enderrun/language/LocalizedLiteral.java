package com.github.enderrun.language;

/**
 * A list of all of the localized (i.e customizable for other languages)
 * strings used in the plugin
 */
public enum LocalizedLiteral {
	TITLE("title-name", "&4[&fEnderRun&4]:"),
	INVALID_ARGS("invalid-args","&cInvalid args!"), 
	MUST_BE_PLAYER("player-only","Sorry but that can only be run by a player!"), 
	NO_PERMS("no-permissions", "&cYou don''t have permission for that!"),	 
	WORLD_NAME("world-name", "EnderRunMap"),
	
	//Game lobby messages
	JOINED_GAME("joined-game", "Joined game!"),
	JOINED_NEW_GAME("joined-new-game", "You're the first person in the new game!"),
	GAME_IN_PROGRESS("game-in-progress", "A game is already in progress!"),
	//Uses format string to store player
	GAME_FINISHED("game-finished", "This game is finished. The winner is %s."),
	GAME_BEGUN("game-begun", "The game has begun!"),
	
	// Help menu messages
	HELP_MENU("help-menu", "EnderRun Help:"), 
	JOIN_COMMAND_HELP("join-command-help", "Joins a game"),
	BEGIN_COMMAND_HELP("begin-command-help", "Starts a game, closing the lobby");

	private String path;
	private String defaultValue;

	LocalizedLiteral(String path, String defaultValue) {
		this.path = path;
		this.defaultValue = defaultValue;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}

}
