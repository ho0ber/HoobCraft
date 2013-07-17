package com.ho0ber.hoobcraft;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HoobCraftPlayerListener extends HoobCraft implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		// If the player right clicked...
		if (event.getAction().toString().equals("RIGHT_CLICK_AIR") || event.getAction().toString().equals("RIGHT_CLICK_BLOCK"))
		{
			// Get the item they had in hand.
			ItemStack item = event.getItem();
			// If they were holding an item...
			if(item != null)
			{
				// Hand off to rightClickEvent.
				rightClickEvent(event.getPlayer(), event.getItem());
			}
		}
	}
	
	public void rightClickEvent(Player source, ItemStack item)
	{
		ItemMeta im = source.getItemInHand().getItemMeta();
		if (im.hasLore() && 
			im.getDisplayName().equals("Acolyte's Wand") &&
			(item.getTypeId() == 280))
		{
			performAction(source, "fireball", 10);
		}
	}
	
	public void performAction(Player player, String eventType, int cost)
	{
		if (player.getLevel() > cost)
		{
			player.setLevel(player.getLevel() - cost);
			new HoobCraftEvent(player, eventType);
		}
	}
}