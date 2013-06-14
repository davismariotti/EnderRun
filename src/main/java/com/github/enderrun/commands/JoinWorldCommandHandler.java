package com.github.enderrun.commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.enderrun.EnderRun;
import com.github.enderrun.util.Lang;

public class JoinWorldCommandHandler implements CommandHandler {

    public boolean onCommand(CommandSender sender, String[] args) {
        if(sender instanceof Player) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(Lang.TITLE.toString() + Lang.MUST_BE_PLAYER);
                return true;
            }

            ((Player) sender).teleport(new Location(EnderRun.getInstance().getGameWorld(), 0, 255, 0));
            return true;
        } else {

        }
        return true;
    }

}
