package com.github.enderrun.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.enderrun.util.Lang;

public class ERCommandExecutor implements CommandExecutor {

    enum Handler {
        JOIN(new JoinCommand(), "Joins the game.");

        private CommandHandler handler;
        private String help;

        Handler(CommandHandler ch, String s) {
            handler = ch;
            help = s;
        }

        public CommandHandler getHandler() {
            return handler;
        }

        public String getHelp() {
            return help;
        }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(ChatColor.GOLD + "EnderRun Help:");
            for(Handler h:Handler.values()) {
                sender.sendMessage(ChatColor.GOLD + "/" + label + " " + h.name().toLowerCase() + " - " + h.getHelp());
            }
        } else {
            Handler handler = Handler.valueOf(args[0]);
            if(handler != null) {
                List<String> argList = Arrays.asList(args);
                if(argList.size() > 0) {
                    argList.remove(0);
                }
                handler.getHandler().handle(sender, argList);
            } else {
                sender.sendMessage(Lang.INVALID_ARGS.toString());
            }
        }
        return true;
    }

}
