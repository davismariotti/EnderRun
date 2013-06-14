package com.github.enderrun.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.enderrun.util.GameManager;
import com.github.enderrun.util.Lang;

public class JoinCommand implements CommandHandler {

    public boolean handle(CommandSender sender, List<String> args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            GameManager.getInstance().addPlayer(player.getName());
            player.sendMessage(Lang.JOIN_GAME.toString());
        } else {
            sender.sendMessage(Lang.TITLE.toString() + Lang.MUST_BE_PLAYER);
        }
        return true;
    }

}
