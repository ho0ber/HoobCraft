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

		plugin.getLogger().info("HoobCraft: Got swing");
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
		plugin.getLogger().info("HoobCraft: Got clickEvent");
		ItemMeta im = source.getItemInHand().getItemMeta();
		if (im.hasLore() && 
			im.getDisplayName().equals("Acolyte's Wand") &&
			(item.getTypeId() == 280))
		{
			performAction(source, "fireball", 1);
		}
	}
	
	public void performAction(Player player, String eventType, int cost)
	{
		ExperienceManager expMan = new ExperienceManager(player);
		plugin.getLogger().info("HoobCraft: Got performAction");
		if (expMan.getCurrentExp() > cost)
		{	
			expMan.changeExp(0-cost);
			new HoobCraftEvent(player, eventType);
		}
	}
}