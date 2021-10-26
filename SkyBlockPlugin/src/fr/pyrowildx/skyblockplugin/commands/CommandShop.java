package fr.pyrowildx.skyblockplugin.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.pyrowildx.skyblockplugin.Constants;

public class CommandShop implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (cmd.getName().equalsIgnoreCase("shop")) {
				Inventory inv = Bukkit.createInventory(null, 36, "�8�lBoutique");
				inv.setItem(11, updateItem(Material.WHEAT, Constants.AGRICULTEUR_SHOP_NAME, Arrays.asList("", "�aboost �7de vente avec", "�7le niveau �aagriculteur �7!")));
				inv.setItem(12, updateItem(Material.ROTTEN_FLESH, Constants.CHASSEUR_SHOP_NAME, Arrays.asList("", "�aboost �7de vente avec", "�7le niveau �achasseur �7!")));
				inv.setItem(13, updateItem(Material.DIAMOND_PICKAXE, Constants.OUTILS_MAGIQUE_SHOP_NAME, Arrays.asList("", "�7les outils magiques sont �aincassables �7!")));
				inv.setItem(14, updateItem(Material.GRASS_BLOCK, Constants.BLOCS_SHOP_NAME, null));
				inv.setItem(15, updateItem(Material.POPPY, Constants.FLEURS_SHOP_NAME, null));
				inv.setItem(20, updateItem(Material.OAK_LOG, Constants.BUCHERON_SHOP_NAME, Arrays.asList("", "�aboost �7de vente avec", "�7le niveau �ab�cheron �7!")));
				inv.setItem(21, updateItem(Material.DIAMOND, Constants.MINEUR_SHOP_NAME, Arrays.asList("", "�aboost �7de vente avec", "�7le niveau �amineur �7!")));
				inv.setItem(22, updateItem(Material.SPAWNER, Constants.SPAWNERS_SHOP_NAME, null));
				inv.setItem(23, updateItem(Material.CHICKEN_SPAWN_EGG, Constants.OEUFS_SHOP_NAME, null));
				inv.setItem(24, updateItem(Material.BUCKET, Constants.DIVERS_SHOP_NAME, null));
				fillInventory(inv, Material.LIGHT_BLUE_STAINED_GLASS_PANE);
				player.openInventory(inv);
			}
			
			if (cmd.getName().equalsIgnoreCase("sell")) {
				if (args.length == 0 || !args[0].equalsIgnoreCase("all")) {
					player.sendMessage("Utilisez </sell all> pour tout vendre dans votre inventaire.");
				}
			}
			
		}
		
		return false;
	}
	
	public ItemStack updateItem(Material material, String name, List<String> desc) {
		ItemStack item = new ItemStack(material, 1);
		ItemMeta itemM = item.getItemMeta();
		itemM.setDisplayName(name);
		itemM.setLore(desc);
		item.setItemMeta(itemM);
		return item;
	}
	
	private void fillInventory(Inventory inv, Material material) {
		int i = 0;
		ItemStack fillItem = new ItemStack(material, 1);
		ItemMeta fillItemM = fillItem.getItemMeta();
		fillItemM.setDisplayName(" ");
		fillItem.setItemMeta(fillItemM);
		for (ItemStack item : inv.getContents()) {
			if (item == null) {
				inv.setItem(i, fillItem);
			}
			i++;
		}
	}

}
