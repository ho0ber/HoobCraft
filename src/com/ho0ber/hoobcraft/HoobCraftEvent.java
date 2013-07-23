package com.ho0ber.hoobcraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.WitherSkull;
import org.bukkit.entity.Arrow;
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
	public HoobCraftEvent(Player sourcePlayer, String eventtype, String args)
	{
		// Gather some data...
		player = sourcePlayer;
		playerlocation = player.getLocation();
		playerworld = player.getWorld();
		targetlocation = player.getTargetBlock(null, 500).getLocation();
		
		switch (eventtype)
		{
		case "fireball": createFireballEvent();
						 break;
		case "smallfireball": createSmallFireballEvent();
		 break;
		case "smallfireballlaunch": createSmallFireballLaunchEvent();
		 break;
		case "largefireball": createLargeFireballEvent();
		 break;
		case "wither": createWitherEvent();
		 break;
		case "arrow": createArrowEvent();
		 break;
		case "teleport": teleportPlayerEvent();
						 break;
		case "tnt": createTNTEvent();
					break;
		case "lightning": createLightningEvent();
		  break;
		  
		case "pray": createPrayEvent(2,5);
		  break;
		  
		case "praylittle": createPrayEvent(1,2);
		  break;
		  
		case "praylots": createPrayEvent(10,0);
		  break;
		  
		case "fly": createToogleFlyEvent();
		  break;
		  
		case "commandtest": createCommandTestEvent();
		  break;
		  
		case "pcommandtest": createPCommandTestEvent();
		  break;
		  
		case "disguise": createDisguiseEvent(args);
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
	
	public boolean createCommandTestEvent()
	{
		// Call world method strikeLightning at targetLocation.
		String cmd = "give " + player.getName() + " 4 64"; 
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
		return true;
	}

	public boolean createDisguiseEvent(String disguise)
	{
		// Call world method strikeLightning at targetLocation.
		String cmd;
		if (disguise != "")
			cmd = "dis " + player.getName() + " " + disguise;
		else
			cmd = "undis " + player.getName();
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
		return true;
	}

	public boolean createPCommandTestEvent()
	{
		// Call world method strikeLightning at targetLocation.
		String cmd = "/help"; 
		player.chat(cmd);
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
		player.getWorld().playSound(player.getLocation(),Sound.BLAZE_DEATH,1, 0);
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
	
	public boolean createLargeFireballEvent()
	{
		// Gets a location right in front of the player to prevent the fireball from hitting them.
		Vector direction = player.getEyeLocation().getDirection().multiply(2);
		Location startlocation = player.getEyeLocation().add(direction);
		// Gets the coordinate difference between the target location and startlocation and dumps it into a vector.
		Vector fireballvector = new Vector(targetlocation.getX() - startlocation.getX(),
										   targetlocation.getY() - startlocation.getY(),
										   targetlocation.getZ() - startlocation.getZ());
		// Spawns a fireball at startlocation and makes that fireball acessible via variable "fireball".
		LargeFireball fireball = playerworld.spawn(startlocation, LargeFireball.class);
		// Set the direction of the fireball to fireballvector.
		fireball.setDirection(fireballvector);
		// Sets the source of the fireball to the player.
		fireball.setShooter(player);
		return true;
	}
	
	public boolean createWitherEvent()
	{
		// Gets a location right in front of the player to prevent the fireball from hitting them.
		Vector direction = player.getEyeLocation().getDirection().multiply(2);
		Location startlocation = player.getEyeLocation().add(direction);
		// Gets the coordinate difference between the target location and startlocation and dumps it into a vector.
		Vector fireballvector = new Vector(targetlocation.getX() - startlocation.getX(),
										   targetlocation.getY() - startlocation.getY(),
										   targetlocation.getZ() - startlocation.getZ());
		// Spawns a fireball at startlocation and makes that fireball acessible via variable "fireball".
		WitherSkull fireball = playerworld.spawn(startlocation, WitherSkull.class);
		// Set the direction of the fireball to fireballvector.
		fireball.setDirection(fireballvector);
		// Sets the source of the fireball to the player.
		fireball.setShooter(player);
		return true;
	}
	
	public boolean createArrowEvent()
	{
		Arrow ar = player.launchProjectile(Arrow.class);
		ar.setFireTicks(5);
		ar.setBounce(true);
		return true;
	}
	
	public boolean createSmallFireballLaunchEvent()
	{
		player.launchProjectile(SmallFireball.class);
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
