package com.github.enderrun.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.enderrun.EnderRun;
import com.github.enderrun.language.LocalizedLiteral;

public class ERCommandExecutor implements CommandExecutor {

    enum Handler {
        JOIN(new JoinCommand(), 
        		EnderRun.getInstance().languageLoader.getValue(LocalizedLiteral.JOIN_COMMAND_HELP),
        		EnderRun.getInstance().languageLoader.getValue(LocalizedLiteral.JOIN_COMMAND_HELP)),
        GENERATE(new GenerateCommand(), "generate", "Test Generate world"),
        SOUND(new SoundtrackCommand(), "sound", "Test Play Soundtrack");

        private CommandHandler handler;
        private String name, help;

        Handler(CommandHandler commandHandler, String commandName, String commandHelp) {
            handler = commandHandler;
            name = commandName;
            help = commandHelp;
        }

        public CommandHandler getHandler() {
            return handler;
        }
        
        public String getCommandName() {
        	return this.name;
        }

        public String getHelp() {
            return help;
        }
        
        public static Handler getHandlerByName(String commandName) throws UnsupportedCommandException {
        	for (Handler h : Handler.values()) {
        		if (h.name.equalsIgnoreCase(commandName)) return h;
        	}
        	throw new UnsupportedCommandException();
        }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
    	if(args.length == 0) {    		
    		displayHelp(sender, label);    		
        }
    	
    	else {
            try {
                Handler handler = Handler.getHandlerByName(args[0]);
                ArrayList<String> argList = new ArrayList<String>(Arrays.asList(args));
                if(argList.size() > 0) {
                    argList.remove(0);
                }
                handler.getHandler().handle(sender, argList);
            } catch (UnsupportedCommandException e) {
                sender.sendMessage(EnderRun.getInstance().languageLoader.getValue(LocalizedLiteral.INVALID_ARGS));
            }
        }

        return true;
    }
    
    private void displayHelp(CommandSender sender, String command) {
    	
        sender.sendMessage(ChatColor.GOLD + EnderRun.getInstance().languageLoader.getValue(LocalizedLiteral.HELP_MENU));
        for(Handler h:Handler.values()) {
            sender.sendMessage(ChatColor.GOLD + "/" + command + " " + h.name().toLowerCase() + " - " + h.getHelp());
        }
    }

}
