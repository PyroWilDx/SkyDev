package fr.pyrowildx.skyblockplugin;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.pyrowildx.skyblockplugin.Constants.Item;
import fr.pyrowildx.skyblockplugin.commands.CommandIsland;
import fr.pyrowildx.skyblockplugin.commands.CommandMiscellaneous;
import fr.pyrowildx.skyblockplugin.commands.CommandShop;
import fr.pyrowildx.skyblockplugin.commands.CommandTeleport;

public class Main extends JavaPlugin {

	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		saveDefaultConfig();
		System.out.println("Server Start !");

		if (!getConfig().contains("sellChests")) {
			getConfig().set("sellChests", null);
			getConfig().set("sellChests.uuids", new ArrayList<String>());
			saveConfig();
			reloadConfig();
		}
		new BukkitRunnable() {
			@Override
			public void run() {
				List<String> uuids = getConfig().getStringList("sellChests.uuids");
				for (String uuid : uuids) {
					Player player = Bukkit.getPlayer(UUID.fromString(uuid));
					if (player != null && player.isOnline()) {
						int total = 0;
						List<Location> locations = (List<Location>) getConfig().get("sellChests." + uuid + ".locations");
						for (Location location : locations ) {
							Chest chest = (Chest) location.getBlock().getState();
							for (ItemStack itemS : chest.getInventory().getContents()) {
								if (itemS != null) {
									for (Item item : Constants.ALL_ITEMS) {
										if (itemS.getType() == item.material) {
											int amount = itemS.getAmount();
											long price = item.sellPrice;
											if (price > 0) {
												price = price * amount;
												String shop = getShop(item.material);
												if (shop != null) {
													price = Math.round((price * (1 + (getConfig().getInt(uuid + "." + shop) * 2) / 100.0)));
												}
												getConfig().set(uuid + ".money", getConfig().getLong(uuid + ".money") + price);
												saveConfig();
												reloadConfig();
												total += price;
												chest.getInventory().remove(itemS);
											}
											continue;
										}
									}
								}
							}
						}
						if (total > 0) {
					        DecimalFormat dF = (DecimalFormat) NumberFormat.getInstance(Locale.US);
					        DecimalFormatSymbols symbols = dF.getDecimalFormatSymbols();
					        symbols.setGroupingSeparator(',');
					        dF.setDecimalFormatSymbols(symbols);
							player.sendMessage("Vos �cSellChests �rvous ont rapport� �a$" + dF.format(total) + "�r.");
						}
					}
				}
			}
		}.runTaskTimer(this, 0, 600);
		
		getCommand("shop").setExecutor(new CommandShop());
		getCommand("sell").setExecutor(new CommandShop());
		getCommand("warp").setExecutor(new CommandTeleport(this));
		getCommand("sethome").setExecutor(new CommandTeleport(this));
		getCommand("home").setExecutor(new CommandTeleport(this));
		getCommand("is").setExecutor(new CommandIsland(this));
		getCommand("fly").setExecutor(new CommandMiscellaneous(this));
		getCommand("boosts").setExecutor(new CommandMiscellaneous(this));
		getCommand("jobs").setExecutor(new CommandMiscellaneous(this));
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
	}
	
	@Override
	public void onDisable() {
		System.out.println("Server Close !");
	}
	
	private String getShop(Material material) {
		for (Item item : Constants.AGRICULTEUR_ITEMS) {
			if (material == item.material) {
				return "agriculteurLevel";
			}
		}
		for (Item item : Constants.CHASSEUR_ITEMS) {
			if (material == item.material) {
				return "chasseurLevel";
			}
		}
		for (Item item : Constants.BUCHERON_ITEMS) {
			if (material == item.material) {
				return "bucheronLevel";
			}
		}
		for (Item item : Constants.MINEUR_ITEMS) {
			if (material == item.material) {
				return "mineurLevel";
			}
		}
		return null;
	}
	
}
