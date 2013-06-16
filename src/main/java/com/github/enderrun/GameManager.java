package com.github.enderrun;

import java.util.HashSet;

import org.bukkit.entity.Player;


public class GameManager {
    
    EnderRun plugin;
    private static GameManager instance;
    
    public GameManager(EnderRun er) {
        plugin = er;
        instance = this;
    }

    public HashSet<String> ingame = new HashSet<String>();
    public HashSet<String> lobby = new HashSet<String>();
    
    public void addPlayer(String name) {
        lobby.add(name);
        // TODO Check if there are enough players
    }
    
    public void transferPlayerToGame(Player player) {
        /* TODO Store all neccesary informations such as 
         * their inventory, gamemode, armor, etc.
         * 
         * Then actually tp them to the game and do normal game stuff.
         */
    }
    
    public static GameManager getInstance() {
        return instance;
    }

}
