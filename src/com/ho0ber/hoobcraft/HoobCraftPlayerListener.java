package com.ho0ber.hoobcraft;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class HoobCraftPlayerListener extends HoobCraft implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{

		//plugin.getLogger().info("HoobCraft: Got swing");
		// If the player left clicked...
		if (event.getAction().toString().equals("LEFT_CLICK_AIR") || event.getAction().toString().equals("LEFT_CLICK_BLOCK"))
		{
			// Get the item they had in hand.
			ItemStack item = event.getItem();
			// If they were holding an item...
			if(item != null)
			{
				// Hand off to rightClickEvent.
				leftClickEvent(event.getPlayer(), event.getItem());
			}
		}
	}
	
	public void leftClickEvent(Player source, ItemStack item)
	{
		//plugin.getLogger().info("HoobCraft: Got clickEvent");
		ItemMeta im = source.getItemInHand().getItemMeta();
		if (im.hasLore() && 
			im.getDisplayName().equals("Acolyte's Wand") &&
			(item.getTypeId() == 280))
		{
			performAction(source, "smallfireball", 1);
		}
	}
	
	public void performAction(Player player, String eventType, int cost)
	{
		ExperienceManager expMan = new ExperienceManager(player);
		//plugin.getLogger().info("HoobCraft: Got performAction");
		if (expMan.getCurrentExp() > cost)
		{	
			expMan.changeExp(0-cost);
			new HoobCraftEvent(player, eventType);
		}
	}
	
	public void giveBook(Player player, String bookName)
	{
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta meta = (BookMeta) book.getItemMeta();
		meta.setTitle("Acolyte's Tome of Basic Spellcraft");
		meta.setAuthor("Arcanus, Lord of Magic");
		meta.setPage(1, "The arcanist is careful; the arcanist is wise.");
		meta.setPage(2, "Chapter One: Tools of the Acolyte\n \n" +
						"The Acolyte's first tool is that of the basic wooden wand. " +
						"To craft such a wand, the Acolyte must find the finest of iron" +
						"and smelt it into an ingot. This ingot may be placed at the top" +
						"of a rod of wood. The Acolyte will be able to fuse these materials" +
						"into a simple magic wand.");
		book.setItemMeta(meta);
	}
}