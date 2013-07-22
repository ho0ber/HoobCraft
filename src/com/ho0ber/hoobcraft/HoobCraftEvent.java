package com.ho0ber.hoobcraft;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class HoobCraftEvent {	
	// Define all HoobCraftEvent fields.
	Player player;
	Location playerlocation;
	Location targetlocation;
	World playerworld;
	
	// HoobCraftEvent constructor.
	// This is run when new HoobCraftEvent(...) is called.
	public HoobCraftEvent(Player sourcePlayer, String eventtype)
	{
		// Gather some data...
		player = sourcePlayer;
		playerlocation = player.getLocation();
		playerworld = player.getWorld();
		targetlocation = player.getTargetBlock(null, 500).getLocation();
		
		// Check which helper method should be run by the constructor.
		switch (eventtype)
		{
		// If eventtype is fireball...
		case "fireball": createFireballEvent();
						 break;
		// If eventtype is fireball...
		case "smallfireball": createSmallFireballEvent();
						 break;
		// If eventtype is teleport...
		case "teleport": teleportPlayerEvent();
						 break;
		// If eventtype is tnt...
		case "tnt": createTNTEvent();
					break;
		// If eventtype is lightning...
		case "lightning": createLightningEvent();
		  break;
		  
		case "pray": createPrayEvent(1,5);
		  break;
		  
		case "praylots": createPrayEvent(10,0);
		  break;
		  
		case "fly": createToogleFlyEvent();
		  break;
		  
		case "heal": createHealingEvent();
		  break;
		// If none of these event types are matched, do nothing.
		default: player.sendMessage(ChatColor.RED + "Invalid event type: " + eventtype);
				 break;
		}
	}
	
	public boolean createPrayEvent(int amount, int limit)
	{
		if(player.getLevel() < limit || limit == 0)
		{
			player.sendMessage(ChatColor.RED + "Adding " + amount + " up to level" + limit);
			ExperienceManager expMan = new ExperienceManager(player);
			expMan.changeExp(amount);
			return true;
		}
		else
			player.sendMessage(ChatColor.RED + "NOT " + amount + " up to level" + limit + "; pl = " + player.getLevel());
		
		return false;
	}
	
	public boolean createToogleFlyEvent()
	{
		// Check if the player is already allowed to fly.
		if (player.getAllowFlight())
		{
			// If they are allowed to fly, disallow it.
			player.setAllowFlight(false);
			return false;
		}
		// If player does not have flying enabled...
		else
		{
			// Allow flight.
			player.setAllowFlight(true);
			return true;
		}
	}

	public boolean createLightningEvent()
	{
		// Call world method strikeLightning at targetLocation.
		playerworld.strikeLightning(targetlocation);
		return true;
	}

	public boolean createHealingEvent()
	{
		// Call world method strikeLightning at targetLocation.
		if(player.getHealth() < 20)
			player.setHealth(player.getHealth()+1);
		return true;
	}

	// Method to create fireballs.
	public boolean createFireballEvent()
	{
		// Gets a location right in front of the player to prevent the fireball from hitting them.
		Vector direction = player.getEyeLocation().getDirection().multiply(2);
		Location startlocation = player.getEyeLocation().add(direction);
		// Gets the coordinate difference between the target location and startlocation and dumps it into a vector.
		Vector fireballvector = new Vector(targetlocation.getX() - startlocation.getX(),
										   targetlocation.getY() - startlocation.getY(),
										   targetlocation.getZ() - startlocation.getZ());
		// Spawns a fireball at startlocation and makes that fireball acessible via variable "fireball".
		Fireball fireball = playerworld.spawn(startlocation, Fireball.class);
		// Set the direction of the fireball to fireballvector.
		fireball.setDirection(fireballvector);
		// Sets the source of the fireball to the player.
		fireball.setShooter(player);
		return true;
	}
	
	public boolean createSmallFireballEvent()
	{
		// Gets a location right in front of the player to prevent the fireball from hitting them.
		Vector direction = player.getEyeLocation().getDirection().multiply(2);
		Location startlocation = player.getEyeLocation().add(direction);
		// Gets the coordinate difference between the target location and startlocation and dumps it into a vector.
		Vector fireballvector = new Vector(targetlocation.getX() - startlocation.getX(),
										   targetlocation.getY() - startlocation.getY(),
										   targetlocation.getZ() - startlocation.getZ());
		// Spawns a fireball at startlocation and makes that fireball acessible via variable "fireball".
		SmallFireball fireball = playerworld.spawn(startlocation, SmallFireball.class);
		// Set the direction of the fireball to fireballvector.
		fireball.setDirection(fireballvector);
		// Sets the source of the fireball to the player.
		fireball.setShooter(player);
		return true;
	}
	
	// Method to create TNT blocks.
	public boolean createTNTEvent()
	{
		// Spawn a new entity of type "TNTPrimed" at the target location.
		playerworld.createExplosion(targetlocation, 6.0f, true);
		return true;
	}
	
	// Method to teleport the player.
	public boolean teleportPlayerEvent()
	{
		// Transfer pitch and yaw.
		targetlocation.setPitch(playerlocation.getPitch());
		targetlocation.setYaw(playerlocation.getYaw());
		
		// Add 1 to y to prevent the player from getting stuck in blocks.
		targetlocation.setY((targetlocation.getY()) + 1);
		
		// Teleport the player.
		player.teleport(targetlocation);
		return true;
	}
}
