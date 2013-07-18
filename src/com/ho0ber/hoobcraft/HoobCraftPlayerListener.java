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
		String hand = null;
		if (event.getAction().toString().equals("LEFT_CLICK_AIR") || event.getAction().toString().equals("LEFT_CLICK_BLOCK"))
			hand = "LEFT";
		else if (event.getAction().toString().equals("RIGHT_CLICK_AIR") || event.getAction().toString().equals("RIGHT_CLICK_BLOCK"))
			hand = "RIGHT";
		// Get the item they had in hand.
		ItemStack item = event.getItem();
		// If they were holding an item...
		if(item != null && hand != null)
		{
			// Hand off to rightClickEvent.
			ClickEvent(event.getPlayer(), event.getItem(), hand);
		}

	}
	
	public void ClickEvent(Player source, ItemStack item, String hand)
	{
		//plugin.getLogger().info("HoobCraft: Got clickEvent");
		ItemMeta im = source.getItemInHand().getItemMeta();
		if (im.hasLore() && 
			im.getDisplayName().equals("Acolyte's Wand") &&
			(item.getTypeId() == 280))
		{
			if (hand == "LEFT")
				performAction(source, "smallfireball", 1);
		}
		else if (im.hasLore() && 
				im.getDisplayName().equals("Adept's Wand") &&
				(item.getTypeId() == 50))
		{
			if (hand == "LEFT")
				performAction(source, "fireball", 1);
		}
		else if (im.hasLore() && 
				im.getDisplayName().equals("Master's Wand") &&
				(item.getTypeId() == 76))
		{
			if (hand == "LEFT")
				performAction(source, "lightning", 1);
		}
		else if (im.hasLore() && 
				im.getDisplayName().equals("Arcane Lord's Sceptre") &&
				(item.getTypeId() == 369))
		{
			if (hand == "LEFT")
				performAction(source, "tnt", 0);
			else
				performAction(source, "teleport", 0);
		}
		else if (im.hasLore() && 
				im.getDisplayName().equals("The Arcanium") &&
				(item.getTypeId() == 340))
		{
			if (hand == "LEFT")
				performAction(source, "pray", 0);
			else
				performAction(source, "heal", 0);
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
	

}