package com.ho0ber.hoobcraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HoobCraftCommands extends HoobCraft implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		// If no arguments are given...
		if (args.length == 0)
		{
			// Run the toggle function and return what it returns.
			return false;
		}
		
		// If the kill argument was given...
		else if (args[0].equals("kill") || args[0].equals("k"))
		{
			return false;
		}
		
		// If the toggle argument was given...
		else if (args[0].equals("toggle") || args[0].equals("t"))
		{
			// Run the toggle function and return what it returns.
			return false;
		}
		
		// If the type argument was given...
		else if (args[0].equals("type") || args[0].equals("ct"))
		{
			// Run the set event function then return true.
			return false;
		}
		
		// If the types argument was given...
		else if (args[0].equals("types"))
		{
			return false;
		}
		
		// If the check argument was given...
		else if (args[0].equals("check") || args[0].equals("c"))
		{
			return false;
		}
		
		// If the fly argument was given...
		else if (args[0].equals("fly") || args[0].equals("f"))
		{
			return false;
		}
		
		// If the help argument was given...
		else if (args[0].equals("help") || args[0].equals("h"))
		{
			// If a second argument was given...
			if (args.length > 1)
			{
				// Request the specified help page.
				printHelp(sender, args[1]);
			}
			// If no second argument was given...
			else
			{
				// Print help page 1.
				printHelp(sender, "1");
			}
			// Return true.
			return true;
		}
		
		// If the setdefault argument was given and the player has permission...
		else if (args[0].equals("setdefault") && sender.hasPermission("HoobCraft.setdefault"))
		{
			// Run the change default type function and return true.
			return false;
		}
		
		// This plugin has been handed an argument that it can't handle.
		else
		{
			// Send the source an error message.
			sender.sendMessage(ChatColor.RED + "Unknown command! Type /HoobCraft help for commands.");
			
			// Return true since we handled error messages.
			return true;
		}
	}
	
	public void printHelp(CommandSender sender, String page)
	{
		if (page.equals("1") || page == null)
		{
			sender.sendMessage(ChatColor.GREEN + "HoobCraft by Ho0ber");
			sender.sendMessage(ChatColor.GREEN + "------------- HELP -------------");
			sender.sendMessage(ChatColor.GREEN + "[] is optional, () is required");
			sender.sendMessage(ChatColor.GREEN + "1.) Toggling HoobCraft:");
			sender.sendMessage(ChatColor.GREEN + "/HoobCraft toggle [target]");
			sender.sendMessage(ChatColor.GREEN + "--------------------------------");
			sender.sendMessage(ChatColor.GREEN + "2.) Changing Sword Type:");
			sender.sendMessage(ChatColor.GREEN + "/HoobCraft type (type) [target]");
			sender.sendMessage(ChatColor.GREEN + "--------------------------------");
			sender.sendMessage(ChatColor.GREEN + "3.) Toggling Flight:");
			sender.sendMessage(ChatColor.GREEN + "/HoobCraft flight [target]");
			sender.sendMessage(ChatColor.GREEN + "--------------------------------");
			sender.sendMessage(ChatColor.GREEN + "4.) Killing Players:");
			sender.sendMessage(ChatColor.GREEN + "/HoobCraft kill [target]");
			sender.sendMessage(ChatColor.GREEN + "--------------------------------");
			sender.sendMessage(ChatColor.GREEN + "Sword Types: /HoobCraft types");
			sender.sendMessage(ChatColor.GREEN + "Command Aliases: /HoobCraft help alias");
			sender.sendMessage(ChatColor.RED + "Admin Commands: /HoobCraft help admin");
		}
		else if (page.equals("alias"))
		{
			sender.sendMessage(ChatColor.GREEN + "HoobCraft");
			sender.sendMessage(ChatColor.GREEN + "------------- ALIASES -------------");
			sender.sendMessage(ChatColor.GREEN + "1.) /HoobCraft: /as");
			sender.sendMessage(ChatColor.GREEN + "toggle: t");
			sender.sendMessage(ChatColor.GREEN + "type: ct");
			sender.sendMessage(ChatColor.GREEN + "fly: f");
			sender.sendMessage(ChatColor.GREEN + "check: c");
			sender.sendMessage(ChatColor.GREEN + "help: h");
		}
		else if (page.equals("admin"))
		{
			sender.sendMessage(ChatColor.GREEN + "HoobCraft");
			sender.sendMessage(ChatColor.RED + "------------- ADMIN -------------");
			sender.sendMessage(ChatColor.RED + "1.) Change default sword (in configuration):");
			sender.sendMessage(ChatColor.RED + "/HoobCraft setdefault (type)");
			sender.sendMessage(ChatColor.GREEN + "Normal Help: /HoobCraft help");
		}
		else
		{
			sender.sendMessage(ChatColor.RED + "There is no help page named: \"" + page + "\"!");
		}
	}
}
	