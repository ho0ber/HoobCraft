package com.ho0ber.hoobcraft;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

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
		else if (args[0].equals("give") || args[0].equals("g"))
		{
			if (args.length >= 3)
			{
				giveHoobItem(sender, args[1], args[2], args);
				return true;
			}
			else
				return false;
		}
		return false;
	}
	
	public void giveHoobItem(CommandSender sender, String playerName, String item, String[] args)
	{
		Player target = null;
		target = findPlayer(playerName);
		String dis = "";
		
		if (target != null)
		{
			switch(item)
			{
			case "1":
				giveBookOne(target);
				break;
			case "Shelf1":
				giveShelfOne(target);
				break;
			case "disguise":
				if (args.length > 3)
					dis = args[3];
				if (args.length > 4)
					for(int i = 4; i < args.length; i++)
						dis = dis + " " + args[i];
				giveDisguiseWand(target, dis);
				break;
			case "Shelf2":
				giveShelfTwo(target);
				break;
			}
		}
	}
	
	public void giveShelfOne(Player player)
	{
		ItemStack item = new ItemStack(Material.BOOKSHELF, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName("Acolyte's Shelf of Arcane Knowledge");
		List<String> l = new ArrayList<String>();
        l.add(ChatColor.RED + " The source of the knowledge of an acolyte");
        l.add(ChatColor.GREEN + " Use this wisely");
		im.setLore(l);
		item.setItemMeta(im);
		PlayerInventory playerInv = player.getInventory();
		playerInv.addItem(item);
	}
	
	public void giveDisguiseWand(Player player, String disguise)
	{
		ItemStack item = new ItemStack(Material.STICK, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName("Wand of Disguise "+disguise);
		List<String> l = new ArrayList<String>();
        l.add(ChatColor.RED + " Disguises the wielder as");
        l.add(disguise);
		im.setLore(l);
		item.setItemMeta(im);
		PlayerInventory playerInv = player.getInventory();
		playerInv.addItem(item);
	}
	
	public void giveShelfTwo(Player player)
	{
		ItemStack item = new ItemStack(Material.JUKEBOX, 1);
		item.setData(new MaterialData(Material.JUKEBOX, (byte) 7));
		ItemMeta im = item.getItemMeta();
		im.setDisplayName("Adept's Shelf of Arcane Knowledge");
		List<String> l = new ArrayList<String>();
        l.add(ChatColor.RED + " The source of the knowledge of an adept");
        l.add(ChatColor.GREEN + " Use this wisely");
		im.setLore(l);
		item.setItemMeta(im);
		PlayerInventory playerInv = player.getInventory();
		playerInv.addItem(item);
	}
	
	public void giveBookOne(Player player)
	{
		//Replacing this with SavedBooks - easier to implement/format
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta meta = (BookMeta) book.getItemMeta();
		meta.setTitle("Acolyte's Tome of Basic Spellcraft");
		meta.setAuthor("Arcanus, Lord of Magic");
		meta.addPage("The arcanist is careful; the arcanist is wise.");
		meta.addPage("1. The Acolyte's Wand\n \n" +
					 "The Acolyte's first tool is that of the basic wooden wand. " +
					 "To craft such a wand, the Acolyte must find the finest of iron" +
					 "and smelt it into an ingot. This ingot may be placed at the top" +
					 "of a rod of wood. The Acolyte will be able to fuse these materials" +
					 "into a simple magic wand.");
		book.setItemMeta(meta);
		PlayerInventory playerInv = player.getInventory();
		playerInv.addItem(book);
	}
	
}
	