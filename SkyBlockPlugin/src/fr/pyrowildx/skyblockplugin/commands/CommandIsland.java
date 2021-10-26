package fr.pyrowildx.skyblockplugin.commands;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.pyrowildx.skyblockplugin.Constants;
import fr.pyrowildx.skyblockplugin.Main;

public class CommandIsland implements CommandExecutor {

	private Main main;

	public CommandIsland(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;
			String uuid = String.valueOf(player.getUniqueId());

			if (cmd.getName().equalsIgnoreCase("is")) {
				if (args.length == 0) {
					Location location = main.getConfig().getLocation(uuid + ".isHome");
					if (new Location(location.getWorld(), location.getX(), location.getY() - 0.01f, location.getZ())
							.getBlock().getType().equals(Material.AIR)) {
						player.teleport(new Location(Bukkit.getWorld("world"), 0.5, 100, 0.5));
					} else {
						player.teleport(location);
					}

				} else if (args[0].equalsIgnoreCase("sethome")) {
					DecimalFormat dF = new DecimalFormat("##.00");
					Location location = player.getLocation();
					main.getConfig().set(uuid + ".isHome", location);
					main.saveConfig();
					main.reloadConfig();
					player.sendMessage("§aLe home de votre île a bien été mis en :" +
							"\nX : " + String.valueOf(dF.format(location.getX())) +
							"\nY : " + String.valueOf(dF.format(location.getY())) +
							"\nZ : " + String.valueOf(dF.format(location.getZ())));

				} else if (args[0].equalsIgnoreCase("gen")) {
					DecimalFormat dF = new DecimalFormat("##.00");
					List<Double> coeffs = Arrays.asList(15.0 / Constants.JOB_MAX_LVL,
							5.0 / Constants.JOB_MAX_LVL,
							2.0 / Constants.JOB_MAX_LVL,
							1.0 / Constants.JOB_MAX_LVL,
							-8.0 / Constants.JOB_MAX_LVL,
							-6.0 / Constants.JOB_MAX_LVL,
							-6.0 / Constants.JOB_MAX_LVL,
							-3.0 / Constants.JOB_MAX_LVL);
					int mineurLvl = main.getConfig().getInt(uuid + ".mineurLevel");
					List<Double> rdmValues = Arrays.asList(
							50 - mineurLvl * coeffs.get(0),
							20 - mineurLvl * coeffs.get(1),
							4 - mineurLvl * coeffs.get(2),
							2 - mineurLvl * coeffs.get(3),
							17 - mineurLvl * coeffs.get(4),
							4 - mineurLvl * coeffs.get(5),
							2 - mineurLvl * coeffs.get(6),
							1 - mineurLvl * coeffs.get(7));
					player.sendMessage("—————————————————————————" +
							"\n§bStone : §d" + dF.format(rdmValues.get(0)) + "%" +
							"\n§bCoal : §d" + dF.format(rdmValues.get(1)) + "%" +
							"\n§bRedstone : §d" + dF.format(rdmValues.get(2)) + "%" +
							"\n§bLapis Lazuli : §d" + dF.format(rdmValues.get(3)) + "%" +
							"\n§bIron : §d" + dF.format(rdmValues.get(4)) + "%" +
							"\n§bGold : §d" + dF.format(rdmValues.get(5)) + "%" +
							"\n§bDiamond : §d" + dF.format(rdmValues.get(6)) + "%" +
							"\n§bEmerald: §d" + dF.format(rdmValues.get(7)) + "%" +
							"\n§aLes pourcentages dépendent de votre niveau Mineur !" +
							"\n§r—————————————————————————");

				} else if (args[0].equalsIgnoreCase("lvl")) {
					int exp = main.getConfig().getInt(uuid + ".islandExp");
					System.out.println(exp);
					player.sendMessage("—————————————————————————" +
							"\n§eBloc de Fer = 5 Exp" +
							"\n§eBloc d'Or = 15 Exp" +
							"\n§eBloc de Diamant = 75 Exp" +
							"\n§eBloc d'Émeraude = 160 Exp" +
							"\n§b§lNiveau de l'île : §d§l" + String.valueOf(exp / 1000) +
							"\n§b§lExpérience Total : §d§l" + String.valueOf(exp) + " (1000 Exp = 1 Lvl)" +
							"\n§r—————————————————————————");

				} else if (args[0].equalsIgnoreCase("help")) {
					player.sendMessage("—————————————————————————" +
							"\n§e/is §r: Téléporte à l'île." +
							"\n§e/is sethome §r: Change le home de l'île." +
							"\n§e/is lvl §r: Affiche le niveau de l'île." +
							"\n§e/is gen §r: Informations sur le générateur de l'île." +
							"\n§e/is help §r: Affiche les commandes disponibles." +
							"\n§e/sethome §r: Change votre home." +
							"\n§e/home §r: Téléporte à votre home." +
							"\n§e/shop §r: Ouvre la boutique." +
							"\n§e/sell all §r: Vend tous les objets vendables dans votre inventaire." +
							"\n§e/warp <destination> §r: Téléporte à la destination souhaitée." +
							"\n§e/jobs §r: Informations sur vos jobs." +
							"\n§e/fly §r: Active/Désactive le Fly." +
							"\n§e/boosts §r: Active tous les boosts que vous avez acheté sauf le Fly." +
							"\n—————————————————————————");

				} else if (args[0].equals("spawnarmorstand")) {
					summonArmorStand(new Location(Bukkit.getWorld("world"), 99995.5, 1, 99999.5), Constants.LOTO_B_J);
					summonArmorStand(new Location(Bukkit.getWorld("world"), 99997.5, 1, 99999.5), Constants.LOTO_SUPER);
					summonArmorStand(new Location(Bukkit.getWorld("world"), 100003.5, 1, 99999.5), Constants.LOTO_CASH);
					summonArmorStand(new Location(Bukkit.getWorld("world"), 100005.5, 1, 99999.5),
							Constants.LOTO_MILLIO);

				} else if (args[0].equals("getchests")) {
					player.getInventory().addItem(getChest(Constants.LOTO_B_J));
					player.getInventory().addItem(getChest(Constants.LOTO_SUPER));
					player.getInventory().addItem(getChest(Constants.LOTO_CASH));
					player.getInventory().addItem(getChest(Constants.LOTO_MILLIO));

				} else {
					player.performCommand("is help");
				}

			}
		}

		return false;
	}

	private ItemStack getChest(String name) {
		ItemStack item = new ItemStack(Material.CHEST, 1);
		ItemMeta itemM = item.getItemMeta();
		itemM.setDisplayName(name);
		item.setItemMeta(itemM);
		return item;
	}

	private void summonArmorStand(Location location, String name) {
		World w = Bukkit.getWorld("world");
		ArmorStand a = (ArmorStand) w.spawnEntity(location, EntityType.ARMOR_STAND);
		a.setCustomName(name);
		a.setVisible(false);
		a.setCustomNameVisible(true);
	}

}
