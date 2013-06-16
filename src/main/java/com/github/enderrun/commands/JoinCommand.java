package com.github.enderrun.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.enderrun.EnderRun;
import com.github.enderrun.util.GameManager;
import com.github.enderrun.util.LocalizedLiteral;

public class JoinCommand implements CommandHandler {

    public boolean handle(CommandSender sender, List<String> args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            GameManager.getInstance().addPlayer(player.getName());
            player.sendMessage(EnderRun.getInstance().languageLoader.getValue(LocalizedLiteral.JOIN_GAME));
        } else {
            sender.sendMessage(EnderRun.getInstance().languageLoader.getValue(LocalizedLiteral.TITLE) 
            		+ " " + 
            		EnderRun.getInstance().languageLoader.getValue(LocalizedLiteral.MUST_BE_PLAYER));
        }
        return true;
    }

}
