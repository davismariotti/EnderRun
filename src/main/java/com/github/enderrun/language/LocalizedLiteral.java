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
	JOIN_GAME("joined-game", "Joined game!"), 
	WORLD_NAME("world-name", "EnderRunMap"),

	// Help menu items
	HELP_MENU("help-menu", "EnderRun Help:"), 
	JOIN_COMMAND_HELP("join-command", "Joins a game");

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
