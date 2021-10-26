package fr.pyrowildx.skyblockplugin.commands;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.pyrowildx.skyblockplugin.Main;

public class CommandTeleport implements CommandExecutor {
	
	private Main main;

	public CommandTeleport(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (cmd.getName().equalsIgnoreCase("warp")) {
				if (args.length == 0 || (!args[0].equalsIgnoreCase("nether") && 
						!args[0].equalsIgnoreCase("end") && 
						!args[0].equalsIgnoreCase("loto"))) {
					player.sendMessage("Warps disponibles : nether, end, loto");
				} else {
					Location location;
					if (args[0].equalsIgnoreCase("nether")) {
						location = new Location(Bukkit.getWorld("world_nether"), 0.5, 100, 0.5);
						player.teleport(location);
					} else if (args[0].equalsIgnoreCase("end")) {
						location = new Location(Bukkit.getWorld("world_the_end"), 125.5, 50, 0.5);
						player.teleport(location);
					} else if (args[0].equalsIgnoreCase("loto")) {
						Bukkit.getWorld("world").setTime(1000);
						location = new Location(Bukkit.getWorld("world"), 100000.5, 1, 100002.5, 180, 0);
						player.teleport(location);
					}
				}
			}
			
			String uuid = String.valueOf(player.getUniqueId());
			
			if (cmd.getName().equalsIgnoreCase("sethome")) {
			    DecimalFormat dF = new DecimalFormat("##.00");
				Location location = player.getLocation();
				main.getConfig().set(uuid + ".home", location);
				main.saveConfig();
				main.reloadConfig();
				player.sendMessage("�aVotre home a bien �t� mis en :" +
					"\nX : " + String.valueOf(dF.format(location.getX())) +
					"\nY : " + String.valueOf(dF.format(location.getY())) +
					"\nZ : " + String.valueOf(dF.format(location.getZ())));
			}
			
			if (cmd.getName().equalsIgnoreCase("home")) {
				Location location = main.getConfig().getLocation(uuid + ".home");
				if (location.getY() != -10) {
					if (new Location(location.getWorld(), location.getX(), location.getY() - 0.01f, location.getZ())
							.getBlock().getType().equals(Material.AIR)) {
						player.teleport(new Location(Bukkit.getWorld("world"), 0.5, 100, 0.5));
					} else {
						player.teleport(main.getConfig().getLocation(uuid + ".home"));
					}
					
				} else {
					player.sendMessage("�cUtilisez la commande </sethome> pour mettre un home.");
				}
			}
			
		}
		
		return false;
	}

}
