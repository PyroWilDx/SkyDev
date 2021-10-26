package fr.pyrowildx.skyblockplugin;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Nameable;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.Sign;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.pyrowildx.skyblockplugin.Constants.CustomItem;
import fr.pyrowildx.skyblockplugin.Constants.Item;

public class Listeners implements Listener {
	
	private Main main;
	
	public Listeners(Main main) {
		this.main = main;
	}
	
	private List<Item> agriculteurItems = Constants.AGRICULTEUR_ITEMS;	
	private List<Item> chasseurItems = Constants.CHASSEUR_ITEMS;
	private List<Item> bucheronItems = Constants.BUCHERON_ITEMS;
	private List<Item> mineurItems = Constants.MINEUR_ITEMS;
	private List<Item> oeufsItems = Constants.OEUFS_ITEMS;
	private List<Item> fleursItems = Constants.FLEURS_ITEMS;
	private List<Item> blocsItems = Constants.BLOCS_ITEMS;
	private List<Item> allItems = Constants.ALL_ITEMS;
	
	private List<CustomItem> magiqueItems = Constants.MAGIQUE_ITEMS;
	private List<CustomItem> diversItems = Constants.DIVERS_ITEMS;
	private List<CustomItem> spawnersItems = Constants.SPAWNERS_ITEMS;
	private List<CustomItem> allCustomItems = Constants.ALL_CUSTOM_ITEMS;
	
	private Inventory oldInv;
	private Item achPlusItem;

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		Inventory currentInv = event.getInventory();
		ItemStack currentItem = event.getCurrentItem();
		
		if (currentItem == null) return;
		
		String currentInvName = event.getView().getTitle();
		
		if (currentInvName.equals("§8§lBoutique")) {
			event.setCancelled(true);
			switch (currentItem.getType()) {
				case WHEAT:
					openCategoryShop(player, Constants.AGRICULTEUR_SHOP_NAME, agriculteurItems);
					return;
				case ROTTEN_FLESH:
					openCategoryShop(player, Constants.CHASSEUR_SHOP_NAME, chasseurItems);
					return;
				case OAK_LOG:
					openCategoryShop(player, Constants.BUCHERON_SHOP_NAME, bucheronItems);
					return;
				case DIAMOND:
					openCategoryShop(player, Constants.MINEUR_SHOP_NAME, mineurItems);
					return;
				case CHICKEN_SPAWN_EGG:
					openCategoryShop(player, Constants.OEUFS_SHOP_NAME, oeufsItems);
					return;
				case POPPY:
					openCategoryShop(player, Constants.FLEURS_SHOP_NAME, fleursItems);
					return;
				case GRASS_BLOCK:
					openCategoryShop(player, Constants.BLOCS_SHOP_NAME, blocsItems);
					return;
				case DIAMOND_PICKAXE:
					openCustomCategoryShop(player, Constants.OUTILS_MAGIQUE_SHOP_NAME, magiqueItems);
					return;
				case BUCKET:
					openCustomCategoryShop(player, Constants.DIVERS_SHOP_NAME, diversItems);
					return;
				case SPAWNER:
					openCustomCategoryShop(player, Constants.SPAWNERS_SHOP_NAME, spawnersItems);
				default:
					return;
			}
		}
		
		if (currentInvName.equals(Constants.AGRICULTEUR_SHOP_NAME) ||
				currentInvName.equals(Constants.CHASSEUR_SHOP_NAME) ||
				currentInvName.equals(Constants.BUCHERON_SHOP_NAME) ||
				currentInvName.equals(Constants.MINEUR_SHOP_NAME) ||
				currentInvName.equals(Constants.OEUFS_SHOP_NAME) ||
				currentInvName.equals(Constants.FLEURS_SHOP_NAME) ||
				currentInvName.equals(Constants.BLOCS_SHOP_NAME)) {
			event.setCancelled(true);
			if (currentItem.getType() == Material.BARRIER) {
				player.performCommand("shop");
				return;
			}

			for (Item item : allItems) {
				if (currentItem.getType() == item.material) {
					
					if (event.getClick() == ClickType.LEFT) {
						buyItems(player, item.material, item.buyPrice, item.name, 1);
						
					} else if (event.getClick() == ClickType.SHIFT_LEFT) {
						if (item.buyPrice > 0) {
							Inventory inv = Bukkit.createInventory(null, 18, "§8§lAcheter plus de §c§l" + item.name);
							for (int i = 1; i < 10; i++) {
								inv.setItem(i-1, createAcheterPlusItem(item.material, item.buyPrice, i));
							}
							inv.setItem(13, createRetourBarrier());
							player.openInventory(inv);
							oldInv = currentInv;
							achPlusItem = item;
						
						} else {
							player.sendMessage("§cVous ne pouvez pas acheter de " + item.name + " !");
						}
						
					} else if (event.getClick() == ClickType.RIGHT) {
						sellItems(player, item.material, item.sellPrice, item.name, 1);

					} else if (event.getClick() == ClickType.SHIFT_RIGHT) {
						sellItems(player, item.material, item.sellPrice, item.name, getItemAmount(player, item.material));						
					}
					return;
				}
			}
		}
		
		if (currentInvName.startsWith("§8§lAcheter plus de §c§l")) {
			event.setCancelled(true);
			if (currentItem.getType() == Material.BARRIER) {
				player.openInventory(oldInv);
				return;
			}
			if (currentItem.getType() == achPlusItem.material) {
				if (event.getClick() == ClickType.LEFT) {
					buyItems(player, achPlusItem.material, achPlusItem.buyPrice, achPlusItem.name, currentItem.getAmount() * 64);
				}
			}
			return;
		}
		
		if (currentInvName.equals(Constants.OUTILS_MAGIQUE_SHOP_NAME) ||
				currentInvName.equals(Constants.DIVERS_SHOP_NAME) ||
				currentInvName.equals(Constants.SPAWNERS_SHOP_NAME) ) {
			event.setCancelled(true);
			if (currentItem.getType() == Material.BARRIER) {
				player.performCommand("shop");
				return;
			}
			
			if (event.getClick() == ClickType.LEFT) {
				for (CustomItem item : allCustomItems) {
					String name = currentItem.getItemMeta().getDisplayName();
					if (name.equals("")) {
						if (currentItem.getType() == item.item.getType()) {
							buyCustomItem(player, item);
							return;
						}
						continue;
					}
					if (name.equals(item.item.getItemMeta().getDisplayName())) {
						int price = 100000;
						if (name.equals("§a§lHopper de Farm")) {
							String uuid = String.valueOf(player.getUniqueId());
							long remainingMoney = main.getConfig().getLong(uuid + ".money") - price;
							if (remainingMoney < 0) {
								player.sendMessage("§cVous n'avez pas assez de money !");
								return;
							}
							Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), 
									"chunkhopper give " + player.getName() + " 1");
							updatePlayerMoney(player, remainingMoney);
					        DecimalFormat dF = (DecimalFormat) NumberFormat.getInstance(Locale.US);
					        DecimalFormatSymbols symbols = dF.getDecimalFormatSymbols();
					        symbols.setGroupingSeparator(',');
					        dF.setDecimalFormatSymbols(symbols);
							player.sendMessage("Vous avez acheté §c1 Hopper de Farm §rpour §c$" + dF.format(price) + " §r!");
							return;
						} else if (name.equals("§eLivre Silk Touch")) {
							buyEnchantedBooks(player, 25000, "silk_touch", "1", "Silk Touch");
							return;
						} else if (name.equals("§eLivre Frost Walker II")) {
							buyEnchantedBooks(player, 50000, "frost_walker", "2", "Frost Walker II");
							return;
						} else if (name.equals("§eLivre Mending")) {
							buyEnchantedBooks(player, 75000, "mending", "1", "Mending");
							return;
						} else if (name.equals("§c§lBoost Force II")) {
							buyBoost(player, 600000, "strength", "Force II");
							return;
						} else if (name.equals("§b§lBoost Speed I")) {
							buyBoost(player, 600000, "speed", "Speed I");
							return;
						} else if (name.equals("§f§lBoost Fly")) {
							buyBoost(player, 1000000, "fly", "Fly");
							return;
						} else if (name.equals("§e§lBoost Célérité I")) {
							buyBoost(player, 600000, "haste", "Haste I");
							return;
						} else if (name.equals("§8§lBoost Resistance II")) {
							buyBoost(player, 200000, "resistance", "Resistance II");
							return;
						}
						buyCustomItem(player, item);
						return;
					}
				}
			}
		}
		
		if (currentInvName.equals(Constants.LOTO_B_J) ||
				currentInvName.equals(Constants.LOTO_SUPER) ||
				currentInvName.equals(Constants.LOTO_CASH) ||
				currentInvName.equals(Constants.LOTO_MILLIO)) {
			event.setCancelled(true);
			return;
		}
	}
	
	private ItemStack updateCategoryItem(Material material, int bP, int sP) {
		ItemStack item = new ItemStack(material, 1);
		ItemMeta itemM = item.getItemMeta();
		List<String> desc = new ArrayList<>();
		
        DecimalFormat dF = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = dF.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        dF.setDecimalFormatSymbols(symbols);
		
		desc.add("§7————————————");
		desc.add("§7Achat : §c$" + dF.format(bP) + " §7(clic gauche)");
		desc.add("§7(shift + clic gauche pour acheter plus)");
		desc.add("");
		desc.add("§7Vente : §a$" + dF.format(sP) + " §7(clic droit)");
		desc.add("§7(shift + clic droit pour tout vendre)");
		desc.add("§7————————————");
		
		if (bP == 0.0f) {
			desc.remove(1);
			desc.remove(1);
			desc.remove(1);
		}
		if (sP == 0.0f) {
			desc.remove(3);
			desc.remove(3);
			desc.remove(3);
		}

		itemM.setLore(desc);
		item.setItemMeta(itemM);
		return item;
	}
	
	private ItemStack createRetourBarrier() {
		ItemStack barrier = new ItemStack(Material.BARRIER, 1);
		ItemMeta barrierM = barrier.getItemMeta();
		barrierM.setDisplayName("§c§lRetour");
		barrier.setItemMeta(barrierM);
		return barrier;
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
	
	private void openCategoryShop(Player player, String name, List<Item> items) {
		Inventory inv = Bukkit.createInventory(null, 54, name);
		for (Item item : items) {
			inv.setItem(item.slot, updateCategoryItem(item.material, item.buyPrice, item.sellPrice));
		}
		inv.setItem(49, createRetourBarrier());
		fillInventory(inv, Material.MAGENTA_STAINED_GLASS_PANE);
		player.openInventory(inv);
	}
	
	private String getShop(Material material) {
		for (Item item : agriculteurItems) {
			if (material == item.material) {
				return "agriculteurLevel";
			}
		}
		for (Item item : chasseurItems) {
			if (material == item.material) {
				return "chasseurLevel";
			}
		}
		for (Item item : bucheronItems) {
			if (material == item.material) {
				return "bucheronLevel";
			}
		}
		for (Item item : mineurItems) {
			if (material == item.material) {
				return "mineurLevel";
			}
		}
		return null;
	}
	
	private int getItemAmount(Player player, Material material) {
		int amount = 0;
		for (ItemStack item : player.getInventory().getContents()) {
			if (item == null) continue;
            if (item.getType() == material) {
            	if (item.getItemMeta().getDisplayName().equals("")) {
            		amount += item.getAmount();
            	}
            }
		}
		return amount;
	}
	
	private void buyItems(Player player, Material material, int bP, String name, int amount) {
		if (bP > 0) {
			String uuid = String.valueOf(player.getUniqueId());
			long price = bP * amount;
			long remainingMoney = main.getConfig().getLong(uuid + ".money") - price;
			if (remainingMoney < 0) {
				player.sendMessage("§cVous n'avez pas assez de money !");
				return;
			}
			Map<Integer, ItemStack> remainingItems = player.getInventory().addItem(new ItemStack(material, amount));
			if (!remainingItems.isEmpty()) {
				player.getWorld().dropItem(player.getLocation(), remainingItems.get(0));
				if (remainingItems.get(0).getAmount() > 1) {
					player.sendMessage("§cVotre inventaire est rempli, " + remainingItems.get(0).getAmount() + " " + name + " sont tombés par terre !");
				} else {
					player.sendMessage("§cVotre inventaire est rempli, " + remainingItems.get(0).getAmount() + " " + name + " est tombé par terre !");
				}
			}
			updatePlayerMoney(player, remainingMoney);
	        DecimalFormat dF = (DecimalFormat) NumberFormat.getInstance(Locale.US);
	        DecimalFormatSymbols symbols = dF.getDecimalFormatSymbols();
	        symbols.setGroupingSeparator(',');
	        dF.setDecimalFormatSymbols(symbols);
			player.sendMessage("Vous avez acheté §c" + amount + " " + name + " §rpour §c$" + dF.format(price) + " §r!");
		} else {
			player.sendMessage("§cVous ne pouvez pas acheter de " + name + " !");
		}
	}
	
	private void sellItems(Player player, Material material, int sP, String name, int amount) {
		if (sP > 0) {
			if (getItemAmount(player, material) >= 1) {
				long price = sP * amount;
				String uuid = String.valueOf(player.getUniqueId());
				String shop = getShop(material);
				if (shop != null) {
					price = Math.round((price * (1 + (main.getConfig().getInt(uuid + "." + shop) * 2) / 100.0)));
				}
				updatePlayerMoney(player, main.getConfig().getLong(uuid + ".money") + price);
				player.getInventory().removeItem(new ItemStack(material, amount));
		        DecimalFormat dF = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		        DecimalFormatSymbols symbols = dF.getDecimalFormatSymbols();
		        symbols.setGroupingSeparator(',');
		        dF.setDecimalFormatSymbols(symbols);
				player.sendMessage("Vous avez vendu §a" + String.valueOf(amount) +	
						" " + name + " §rpour §a$" + dF.format(price) + " §r!");
			} else {
				player.sendMessage("§cVous n'avez pas de " + name + ".");
			}
		} else {
			player.sendMessage("§cVous ne pouvez pas vendre de " + name + " !");
		}
	}
	
	private ItemStack createAcheterPlusItem(Material material, int bP, int n) {
		ItemStack item = new ItemStack(material, n);
		ItemMeta itemM = item.getItemMeta();
		if (n > 1) {
			itemM.setDisplayName("§rAcheter §c" + String.valueOf(n) + " Stacks §rà §c$" + 
					String.valueOf(bP * n * 64));
		} else {
			itemM.setDisplayName("§rAcheter §c" + String.valueOf(n) + " Stack §rà §c$" +
					String.valueOf(bP * 64));
		}
		item.setItemMeta(itemM);
		return item;
	}
	
	private ItemStack updateCategoryCustomItem(Material material, int bP, String name, String displayName, List<String> desc) {
		ItemStack item = new ItemStack(material, 1);
		ItemMeta itemM = item.getItemMeta();
		if (displayName != null) itemM.setDisplayName(displayName);
		List <String> _desc = new ArrayList<>();
		if (desc != null) _desc.addAll(desc);
		
        DecimalFormat dF = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = dF.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        dF.setDecimalFormatSymbols(symbols);
		
		_desc.add("§7————————————");
		_desc.add("§7Achat : §c$" + dF.format(bP) + " §7(clic gauche)");
		_desc.add("§7————————————");
		itemM.setLore(_desc);
		item.setItemMeta(itemM);
		return item;
	}
	
	private void openCustomCategoryShop(Player player, String name, List<CustomItem> items) {
		Inventory inv = Bukkit.createInventory(null, 54, name);
		for (CustomItem item : items) {
			inv.setItem(item.slot, updateCategoryCustomItem(item.item.getType(), item.buyPrice, item.name, item.displayName, item.desc));
		}
		inv.setItem(49, createRetourBarrier());
		fillInventory(inv, Material.MAGENTA_STAINED_GLASS_PANE);
		player.openInventory(inv);
	}
	
	private void buyCustomItem(Player player, CustomItem item) {
		String uuid = String.valueOf(player.getUniqueId());
		long remainingMoney = main.getConfig().getLong(uuid + ".money") - item.buyPrice;
		if (remainingMoney < 0) {
			player.sendMessage("§cVous n'avez pas assez de money !");
			return;
		}
		Map<Integer, ItemStack> remainingItems = player.getInventory().addItem(item.item);
		if (!remainingItems.isEmpty()) {
			player.getWorld().dropItem(player.getLocation(), remainingItems.get(0));
			player.sendMessage("§cVotre inventaire est rempli, 1 " + item.name + " est tombé(e) par terre !");
		}
		updatePlayerMoney(player, remainingMoney);
        DecimalFormat dF = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = dF.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        dF.setDecimalFormatSymbols(symbols);
		player.sendMessage("Vous avez acheté §c1 " + item.name + " §rpour §c$" + dF.format(item.buyPrice) + " §r!");
	}
	
	private void buyEnchantedBooks(Player player, int price, String id, String lvl, String name) {
		String uuid = String.valueOf(player.getUniqueId());
		long remainingMoney = main.getConfig().getLong(uuid + ".money") - price;
		if (remainingMoney < 0) {
			player.sendMessage("§cVous n'avez pas assez de money !");
			return;
		}
		Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), 
				"give " + player.getName() + " enchanted_book{StoredEnchantments:[{id:" + id + ",lvl:" + lvl + "}]} 1");
		updatePlayerMoney(player, remainingMoney);
        DecimalFormat dF = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = dF.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        dF.setDecimalFormatSymbols(symbols);
		player.sendMessage("Vous avez acheté §c1 Enchanted Book " + name + " §rpour §c$" + dF.format(price) + " §r!");
		return;
	}
	
	private void buyBoost(Player player, int price, String boost, String name) {
		String uuid = String.valueOf(player.getUniqueId());
		long remainingMoney = main.getConfig().getLong(uuid + ".money") - price;
		if (main.getConfig().getBoolean(uuid + "." + boost)) {
			player.sendMessage("§cVous avez déjà le boost " + name + " !");
			return;
		}
		if (remainingMoney < 0) {
			player.sendMessage("§cVous n'avez pas assez de money !");
			return;
		}
		main.getConfig().set(uuid + "." + boost, true);
		main.saveConfig();
		main.reloadConfig();
		updatePlayerMoney(player, remainingMoney);
        DecimalFormat dF = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = dF.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        dF.setDecimalFormatSymbols(symbols);
		player.sendMessage("Vous avez acheté §c1 Boost " + name +" §rpour §c$" + dF.format(price) + " §r!");
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String uuid = String.valueOf(player.getUniqueId());
		
		if (!main.getConfig().contains(uuid)) {
			saveNewPlayerConfig(uuid);
		}
		
	    new BukkitRunnable() {
	        @Override
	        public void run() {
	        	updateScoreboard(player);
	        }
	    }.runTaskTimer(main, 0, 20);
	}
	
	private void saveNewPlayerConfig(String uuid) {
		main.getConfig().set(uuid, null);
		main.getConfig().set(uuid + ".islandExp", 0);
		main.getConfig().set(uuid + ".money", 2000L);
		main.getConfig().set(uuid + ".agriculteurLevel", 1);
		main.getConfig().set(uuid + ".chasseurLevel", 1);
		main.getConfig().set(uuid + ".bucheronLevel", 1);
		main.getConfig().set(uuid + ".mineurLevel", 1);
		main.getConfig().set(uuid + ".agriculteurExp", 0);
		main.getConfig().set(uuid + ".chasseurExp", 0);
		main.getConfig().set(uuid + ".bucheronExp", 0);
		main.getConfig().set(uuid + ".mineurExp", 0);
		main.getConfig().set(uuid + ".home", new Location(Bukkit.getWorld("world"), 0, -10, 0));
		main.getConfig().set(uuid + ".isHome", new Location(Bukkit.getWorld("world"), 0.5, 100, 0.5));
		main.getConfig().set(uuid + ".strength", false);
		main.getConfig().set(uuid + ".speed", false);
		main.getConfig().set(uuid + ".fly", false);
		main.getConfig().set(uuid + ".haste", false);
		main.getConfig().set(uuid + ".resistance", false);
		main.getConfig().set(uuid + ".islandExp", 0);
		main.getConfig().set(uuid + ".spawnersName", new ArrayList<String>());
		main.getConfig().set(uuid + ".spawnersLocation", new ArrayList<Location>());
		main.getConfig().set(uuid + ".ratioLoto", 0L);
		main.getConfig().set(uuid + ".deaths", 0);
		main.getConfig().set("sellChests." + uuid + ".locations", new ArrayList<Location>());
		main.saveConfig();
		main.reloadConfig();
	}
	
	private void updatePlayerMoney(Player player, long money) {
		String uuid = String.valueOf(player.getUniqueId());
		if (money >= 100000000000000000L) {
			player.sendMessage("§cVotre money est au maximum !!");
			main.getConfig().set(uuid + ".money", 100000000000000000L);
		} else {
			main.getConfig().set(uuid + ".money", money);
		}
		main.saveConfig();
		main.reloadConfig();
	}
	
	boolean isPink = true;
	
	private void updateScoreboard(Player player) {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("main", "dummy", "main");
        
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        if (isPink) {
        	objective.setDisplayName("§b§lSky§d§lDev");
        	isPink = false;
        } else {
        	objective.setDisplayName("§d§lSky§b§lDev");
        	isPink = true;
        }
        
        String uuid = String.valueOf(player.getUniqueId());
        
        long money = main.getConfig().getLong(uuid + ".money");
        
        DecimalFormat dF = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = dF.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        dF.setDecimalFormatSymbols(symbols);
        
		int agriLvl = main.getConfig().getInt(uuid + ".agriculteurLevel");
		int chassLvl = main.getConfig().getInt(uuid + ".chasseurLevel");
		int buchLvl = main.getConfig().getInt(uuid + ".bucheronLevel");
		int mineurLvl = main.getConfig().getInt(uuid + ".mineurLevel");
                
        Score _1 = objective.getScore("    ");
        Score statsS = objective.getScore("§b§lStats    ");
        Score ileS = objective.getScore("§7→ §bÎle : §d" + String.valueOf(main.getConfig().getInt(uuid + ".islandExp") / 1000) + "    ");
        Score moneyS = objective.getScore("§7→ §bMoney : §d$" + dF.format(money));
        Score ratioLotoS = objective.getScore("§7→ §bRatio Loto : §d$" + dF.format(main.getConfig().getLong(uuid + ".ratioLoto")));
        Score deathsS = objective.getScore("§7→ §bNombre de Morts : §d" + dF.format(main.getConfig().getInt(uuid + ".deaths")));
        Score _2 = objective.getScore("");
        Score metierS = objective.getScore("§b§lMétiers    ");
        Score agriS = objective.getScore("§7→ §bAgriculteur : §d" + String.valueOf(agriLvl) + " §7- §dx" + 
        		String.valueOf((float) (Math.round((1.0 + (float) (agriLvl * 2) / 100.0) * 100.0) / 100.0)) + "    ");
        Score chassS = objective.getScore("§7→ §bChasseur : §d" + String.valueOf(chassLvl) + " §7- §dx" + 
        		String.valueOf((float) (Math.round((1.0 + (float) (chassLvl * 2) / 100.0) * 100.0) / 100.0)) + "    ");
        Score buchS = objective.getScore("§7→ §bBûcheron : §d" + String.valueOf(buchLvl) + " §7- §dx" + 
        		String.valueOf((float) (Math.round((1.0 + (float) (buchLvl * 2) / 100.0) * 100.0) / 100.0)) + "    ");
        Score mineurS = objective.getScore("§7→ §bMineur : §d" + String.valueOf(mineurLvl) + " §7- §dx" + 
        		String.valueOf((float) (Math.round((1.0 + (float) (mineurLvl * 2) / 100.0) * 100.0) / 100.0)) + "    ");
        
        _1.setScore(11);
        statsS.setScore(10);
        ileS.setScore(9);
        moneyS.setScore(8);
        ratioLotoS.setScore(7);
        deathsS.setScore(6);
        _2.setScore(5);
        metierS.setScore(4);
        agriS.setScore(3);
        chassS.setScore(2);
        buchS.setScore(1);
        mineurS.setScore(0);
        
        player.setScoreboard(board);
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		if (String.valueOf(event.getMessage()).toLowerCase().startsWith("/sell all")) {
			Player player = event.getPlayer();
			
			for (ItemStack invItem : player.getInventory().getContents()) {
				if (invItem == null) continue;
				if (invItem.getItemMeta().hasDisplayName()) continue;
				for (Item shopItem : allItems) {
					if (invItem.getType() == shopItem.material) {
						sellItems(player, shopItem.material, shopItem.sellPrice, shopItem.name, invItem.getAmount());
						break;
					}
				}
			}
		}
	}
	
	List<Material> seedItems = Arrays.asList(Material.NETHER_WART, Material.WHEAT,
			Material.BEETROOTS, Material.POTATOES, Material.CARROTS);
	
	List<Material> farmItems = Arrays.asList(Material.CACTUS, Material.SUGAR_CANE, Material.BAMBOO,
			Material.KELP, Material.RED_MUSHROOM, Material.BROWN_MUSHROOM, Material.CHORUS_FRUIT,
			Material.PUMPKIN, Material.MELON, Material.SWEET_BERRY_BUSH, Material.COCOA);
	
	List<Material> woodItems = Arrays.asList(Material.OAK_LOG, Material.BIRCH_LOG, Material.SPRUCE_LOG,
			Material.ACACIA_LOG, Material.JUNGLE_LOG, Material.DARK_OAK_LOG);
	
	List<Material> oreItems = Arrays.asList(Material.STONE, Material.COBBLESTONE, Material.COAL_ORE, Material.REDSTONE_ORE,
			Material.LAPIS_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE,
			Material.NETHER_GOLD_ORE, Material.NETHER_QUARTZ_ORE, Material.ANCIENT_DEBRIS);
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (player.getLocation().getX() > 90000) {
			event.setCancelled(true);
			return;
		}
		String uuid = String.valueOf(player.getUniqueId());
		Block block = event.getBlock();
		Location location = block.getLocation();
		Material blockMaterial = block.getType();
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if (farmItems.contains(blockMaterial) || seedItems.contains(blockMaterial)) {
			addJobExp(player, "agriculteur", 4);
			if (seedItems.contains(blockMaterial)) {
				if (item.getItemMeta() != null && item.getItemMeta().getDisplayName().equals("§cHoue Magique")) {
					event.setDropItems(false);
				    event.setCancelled(true);
				    Ageable ageable = (Ageable) block.getBlockData();
				    if (ageable.getAge() == ageable.getMaximumAge()) {
					    block.breakNaturally(item);
					    block.setType(blockMaterial);
				    }
				}
			}
			
		} else if (woodItems.contains(blockMaterial)) {
			addJobExp(player, "bucheron", 8);
			if (item.getItemMeta() != null && item.getItemMeta().getDisplayName().equals("§cHache Magique")) {
				breakTree(block);
			}
			
		} else if (oreItems.contains(blockMaterial)) {
			addJobExp(player, "mineur", 6);
			
		} else if (blockMaterial == Material.SPAWNER) {
			event.setCancelled(true);
			block.breakNaturally();
			int i = ((List<Location>) main.getConfig().getList(uuid + ".spawnersLocation")).indexOf(location);
			String name = (String) main.getConfig().getList(uuid + ".spawnersName").get(i);
			if (item.getItemMeta() != null && item.getItemMeta().getEnchants().containsKey(Enchantment.SILK_TOUCH)) {
				ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
				ItemMeta spawnerM = spawner.getItemMeta();
				spawnerM.setDisplayName(name);
				spawner.setItemMeta(spawnerM);
				player.getWorld().dropItemNaturally(location, spawner);
			}
			main.getConfig().getList(uuid + ".spawnersLocation").remove(i);
			main.getConfig().getList(uuid + ".spawnersName").remove(i);
			main.saveConfig();
			main.reloadConfig();
			player.sendMessage("§cVous avez cassé un " + name + " §c!");
			
		} else if (blockMaterial == Material.CHEST) {
			Nameable stateName = (Nameable) block.getState();
			String name = stateName.getCustomName();
			if (name != null && name.equals("§c§lSellChest")) {
				int i = ((List<Location>) main.getConfig().getList("sellChests." + uuid + ".locations")).indexOf(location);
				if (i == -1) {
					event.setCancelled(true);
					player.sendMessage("§cAttention ! Vous ne pouvez pas casser le SellChest de quelqu'un d'autre !");
					return;
				}
				((List<Location>) main.getConfig().getList("sellChests." + uuid + ".locations")).remove(i);
				main.saveConfig();
				main.reloadConfig();
				player.sendMessage("§cVous avez cassé un SellChest !");
			}
			
		} else if (blockMaterial == Material.IRON_BLOCK || blockMaterial == Material.GOLD_BLOCK ||
				blockMaterial == Material.DIAMOND_BLOCK || blockMaterial == Material.EMERALD_BLOCK) {
			int exp = 0;
			switch (blockMaterial) {
				case IRON_BLOCK:
					exp = 5;
					break;
				case GOLD_BLOCK:
					exp = 15;
					break;
				case DIAMOND_BLOCK:
					exp = 75;
					break;
				case EMERALD_BLOCK:
					exp = 160;
					break;
				default:
					break;	
			}
			main.getConfig().set(uuid + ".islandExp" , main.getConfig().getInt(uuid + ".islandExp") - exp);
			main.saveConfig();
			main.reloadConfig();
		} 
	}
	
	private void addJobExp(Player player, String job, int exp) {
		String uuid = String.valueOf(player.getUniqueId());
		String lvlPath = uuid + "." + job + "Level";
		String expPath = uuid + "." + job + "Exp";
		long jobLvl = main.getConfig().getInt(lvlPath);
		int finalExp = main.getConfig().getInt(expPath) + exp;
		if (finalExp >= (int) Math.ceil((Math.pow(jobLvl, 2.35f) / 10.0f) + (jobLvl * 10.75f))) {
			if (jobLvl < Constants.JOB_MAX_LVL) {
				main.getConfig().set(lvlPath, jobLvl + 1);
				main.getConfig().set(expPath, 0);
				player.sendMessage("§aVous êtes désormais au niveau " + String.valueOf(jobLvl + 1) + " dans le métier " + job + ".");
				return;
			}
		}
		main.getConfig().set(expPath, finalExp);
		main.saveConfig();
		main.reloadConfig();
	}
	
	List<Material> treeRelatedItems = Arrays.asList(Material.OAK_LOG, Material.OAK_LEAVES,
			Material.BIRCH_LOG, Material.BIRCH_LEAVES, Material.SPRUCE_LOG, Material.SPRUCE_LEAVES,
			Material.ACACIA_LOG, Material.ACACIA_LEAVES, Material.JUNGLE_LOG, Material.JUNGLE_LEAVES,
			Material.DARK_OAK_LOG, Material.DARK_OAK_LEAVES);
	
	public void breakTree(Block tree){
		   if (!treeRelatedItems.contains(tree.getType())) return;
		   tree.breakNaturally();
		   for(BlockFace face: BlockFace.values())
		      breakTree(tree.getRelative(face));
		}
	
	private int playLoto(List<Double> probabilities, List<Integer> profits) {
		double cummulative = 0;
		double random = new Random().nextFloat();
		for (int i = 0; i < probabilities.size(); i++) {
			cummulative += probabilities.get(i);
			if (random <= cummulative) {
				return profits.get(i);
			}
		}
		return 0;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
	    if ((event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.FARMLAND) ||
	    		(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.SPAWNER)) {
	    	event.setCancelled(true);
	    }
	    Player player = event.getPlayer();
	    String uuid = String.valueOf(player.getUniqueId());
	    Block block = event.getClickedBlock();
	    if (block == null || block.getType() == null) {
	    	return;
	    }
	    if (block.getType() == Material.OAK_WALL_SIGN) {
	    	if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
	    		Sign sign = (Sign) block.getState();
	    		String text = sign.getLine(0);
	    		long money = main.getConfig().getLong(uuid + ".money");
	    		if (Arrays.asList("Black Jack", "Super", "Cash", "Millionnaire").contains(text)) {
		    		int profit = 0;
		    		if (text.equals("Black Jack")) {
		    			if (money < 2000) {
		    				player.sendMessage("§cVous n'avez pas assez de money pour jouer !");
		    				return;
		    			}
		    			profit = playLoto(Arrays.asList(0.067, 0.1075, 0.0235, 0.0338, 0.016, 0.00067, 0.000051),
		    					Arrays.asList(2000, 4000, 5000, 10000, 20000, 40000, 100000)) - 2000;		    			
		    		} else if (text.equals("Super")) {
		    			if (money < 5000) {
		    				player.sendMessage("§cVous n'avez pas assez de money pour jouer !");
		    				return;
		    			}
		    			profit = playLoto(Arrays.asList(0.1695, 0.0093),
		    					Arrays.asList(10000, 200000)) - 5000;
		    		} else if (text.equals("Cash")) {
		    			if (money < 5000) {
		    				player.sendMessage("§cVous n'avez pas assez de money pour jouer !");
		    				return;
		    			}
		    			profit = playLoto(Arrays.asList(0.0998, 0.1183, 0.0276, 0.0075, 0.0077, 0.00034),
		    					Arrays.asList(5000, 10000, 20000, 50000, 100000, 500000)) - 5000;
		    		} else if (text.equals("Millionnaire")) {
		    			if (money < 10000) {
		    				player.sendMessage("§cVous n'avez pas assez de money pour jouer !");
		    				return;
		    			}
		    			profit = playLoto(Arrays.asList(0.1267, 0.14, 0.0181, 0.01, 0.004, 0.00078),
		    					Arrays.asList(10000, 20000, 50000, 100000, 150000, 1000000)) - 10000;
		    		}
		    		main.getConfig().set(uuid + ".money", main.getConfig().getLong(uuid + ".money") + profit);
		    		main.getConfig().set(uuid + ".ratioLoto", main.getConfig().getLong(uuid + ".ratioLoto") + profit);
		    		main.saveConfig();
		    		main.reloadConfig();
		            DecimalFormat dF = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		            DecimalFormatSymbols symbols = dF.getDecimalFormatSymbols();
		            symbols.setGroupingSeparator(',');
		            dF.setDecimalFormatSymbols(symbols);
		    		if (profit < 0) {
		    			player.sendMessage("§c(╯•︹•)╯┻━┻ Aïe ! Ouille ! Vous avez perdu $" + dF.format(-profit) + "...");
		    		} else if (profit == 0) {
		    			player.sendMessage("¯\\_(❛ʖ❛)_/¯ Vous avez ni gagner, ni perdu.");
		    		} else {
		    			player.sendMessage("§a≧◉◡◉≦ EZ ! ≧◠‿◠≦ Vous avez gagné $" + dF.format(profit) + " !!!");
		    		}
	    		}
	    	}
	    }
	}
	
	@EventHandler 
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		String uuid = String.valueOf(player.getUniqueId());
		main.getConfig().set(uuid + ".deaths", main.getConfig().getInt(uuid + ".deaths") + 1);
		main.saveConfig();
		main.reloadConfig();
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
	    String uuid = String.valueOf(player.getUniqueId());
	    
		Location location = main.getConfig().getLocation(uuid + ".isHome");
		if (new Location(location.getWorld(), location.getX(), location.getY() - 0.01f, location.getZ())
				.getBlock().getType().equals(Material.AIR)) {
			event.setRespawnLocation(new Location(Bukkit.getWorld("world"), 0.5, 100, 0.5));
		} else {
			event.setRespawnLocation(location);
		}
	}
	
	List<Double> coeffs = Arrays.asList(15.0/Constants.JOB_MAX_LVL,
			5.0/Constants.JOB_MAX_LVL,
			2.0/Constants.JOB_MAX_LVL,
			1.0/Constants.JOB_MAX_LVL,
			-8.0/Constants.JOB_MAX_LVL,
			-6.0/Constants.JOB_MAX_LVL,
			-6.0/Constants.JOB_MAX_LVL,
			-3.0/Constants.JOB_MAX_LVL);
	
	@EventHandler
	public void onBlockFromTo(BlockFromToEvent event) {
		Block evBlock = event.getBlock();
	    Material type = evBlock.getType();
	    if (type == Material.WATER || type == Material.LAVA){
	        Block block = event.getToBlock();
	        if (block.getType() == Material.AIR) {
	            if (generatesCobble(type, block)) {      	
	            	block = new Location(block.getWorld(), block.getX(), block.getY()-0.01, block.getZ()).getBlock();
	        
	            	Player player = getNearestPlayer(block.getLocation());
	            	if (player == null) {
	            		return;
	            	}
	            	int mineurLvl = main.getConfig().getInt(String.valueOf(player.getUniqueId() + ".mineurLevel"));
	            	List<Double> rdmValues = new ArrayList<>();
	            	rdmValues.add(50 - mineurLvl * coeffs.get(0));
	            	rdmValues.add(rdmValues.get(0) + 20 - mineurLvl * coeffs.get(1));
	            	rdmValues.add(rdmValues.get(1) + 4 - mineurLvl * coeffs.get(2));
	            	rdmValues.add(rdmValues.get(2) + 2 - mineurLvl * coeffs.get(3));
	            	rdmValues.add(rdmValues.get(3) + 17 - mineurLvl * coeffs.get(4));
	            	rdmValues.add(rdmValues.get(4) + 4 - mineurLvl * coeffs.get(5));
	            	rdmValues.add(rdmValues.get(5) + 2 - mineurLvl * coeffs.get(6));
	            	rdmValues.add(rdmValues.get(6) + 1 - mineurLvl * coeffs.get(7));
	            	
	                float random = new Random().nextFloat(); 
	                if (random <= rdmValues.get(0) / 100) {
	                	block.setType(Material.STONE);
	                } else if (random <= rdmValues.get(1) / 100) {
	                	block.setType(Material.COAL_ORE);
	                } else if (random <= rdmValues.get(2) / 100) {
	                	block.setType(Material.REDSTONE_ORE);
	                } else if (random <= rdmValues.get(3) / 100) {
	                	block.setType(Material.LAPIS_ORE);
	                } else if (random <= rdmValues.get(4) / 100) {
	                	block.setType(Material.IRON_ORE);
	                } else if (random <= rdmValues.get(5) / 100) {
	                	block.setType(Material.GOLD_ORE);
	                } else if (random <= rdmValues.get(6) / 100) {
	                	block.setType(Material.DIAMOND_ORE);
	                } else if (random <= rdmValues.get(7) / 100) {
	                	block.setType(Material.EMERALD_ORE);
	                } else {
	                	block.setType(Material.STONE);
	                }
	            }
	        }
	    }
	}

	private final BlockFace[] faces = new BlockFace[]{
	        BlockFace.SELF,
	        BlockFace.UP,
	        BlockFace.DOWN,
	        BlockFace.NORTH,
	        BlockFace.EAST,
	        BlockFace.SOUTH,
	        BlockFace.WEST
	    };

	public boolean generatesCobble(Material type, Block b){
	    Material mirrorID1 = (type == Material.WATER ? Material.LAVA : Material.WATER);
	    Material mirrorID2 = (type == Material.WATER ? Material.LAVA : Material.WATER);
	    for (BlockFace face : faces){
	        Block r = b.getRelative(face, 1);
	        if (r.getType() == mirrorID1 || r.getType() == mirrorID2){
	            return true;
	        }
	    }
	    return false;
	}
	
	private Player getNearestPlayer(Location location) {
		Player res = null;
		Double distance = null;
		for (Player player : Bukkit.getOnlinePlayers()) {
			Double temp = null;
			try {
				temp = player.getLocation().distance(location);
			} catch (Exception e) {
				continue;
			}
			if (temp != null) {
				if (distance == null || temp < distance) {
					res = player;
					distance = temp;
				}
			}
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (player.getLocation().getX() > 90000) {
			event.setCancelled(true);
			return;
		}
		String uuid = String.valueOf(player.getUniqueId());
		Block block = event.getBlock();
		Location location = block.getLocation();
		Material blockMaterial = block.getType();
		
		if (blockMaterial == Material.SPAWNER) {
			ItemStack item = event.getItemInHand();
			String name = item.getItemMeta().getDisplayName();
			((List<String>) main.getConfig().getList(uuid + ".spawnersName")).add(name);
			((List<Location>) main.getConfig().getList(uuid + ".spawnersLocation")).add(location);
			main.saveConfig();
			main.reloadConfig();
			BlockState blockState = block.getState();
			CreatureSpawner spawner = (CreatureSpawner) blockState;
			spawner.setSpawnedType(getSpawnerEntity(name));
			blockState.update();
			player.sendMessage("§aVous avez placé un " + name + " §a!");
			
		} else if (blockMaterial == Material.CHEST) {
			Nameable stateName = (Nameable) block.getState();
			String name = stateName.getCustomName();
			if (name != null && name.equals("§c§lSellChest")) {
				if (!main.getConfig().getStringList("sellChests.uuids").contains(uuid)) {
					((List<String>) main.getConfig().getList("sellChests.uuids")).add(uuid);
					main.saveConfig();
					main.reloadConfig();
				}
				((List<Location>) main.getConfig().getList("sellChests." + uuid + ".locations")).add(location);
				main.saveConfig();
				main.reloadConfig();
				player.sendMessage("§aVous avez placé un SellChest !");
			}
			
		} else if (blockMaterial == Material.IRON_BLOCK || blockMaterial == Material.GOLD_BLOCK ||
				blockMaterial == Material.DIAMOND_BLOCK || blockMaterial == Material.EMERALD_BLOCK) {
			int exp = 0;
			switch (blockMaterial) {
				case IRON_BLOCK:
					exp = 5;
					break;
				case GOLD_BLOCK:
					exp = 15;
					break;
				case DIAMOND_BLOCK:
					exp = 75;
					break;
				case EMERALD_BLOCK:
					exp = 160;
					break;
				default:
					break;	
			}
			main.getConfig().set(uuid + ".islandExp" , main.getConfig().getInt(uuid + ".islandExp") + exp);
			main.saveConfig();
			main.reloadConfig();
		}
	}
	
	private EntityType getSpawnerEntity(String name) {
		if (name.equals("§ePig Spawner")) {
			return EntityType.PIG;
		} else if (name.equals("§eCow Spawner")) {
			return EntityType.COW;
		} else if (name.equals("§eChicken Spawner")) {
			return EntityType.CHICKEN;
		} else if (name.equals("§eSheep Spawner")) {
			return EntityType.SHEEP;
		} else if (name.equals("§eRabbit Spawner")) {
			return EntityType.RABBIT;
		} else if (name.equals("§eZombie Spawner")) {
			return EntityType.ZOMBIE;
		} else if (name.equals("§eSkeleton Spawner")) {
			return EntityType.SKELETON;
		} else if (name.equals("§eCreeper Spawner")) {
			return EntityType.CREEPER;
		} else if (name.equals("§eSpider Spawner")) {
			return EntityType.SPIDER;
		} else if (name.equals("§eBlaze Spawner")) {
			return EntityType.BLAZE;
		} else if (name.equals("§eMagma Cube Spawner")) {
			return EntityType.MAGMA_CUBE;
		} else if (name.equals("§eIron Golem Spawner")) {
			return EntityType.IRON_GOLEM;
		} else if (name.equals("§eGuardian Spawner")) {
			return EntityType.GUARDIAN;
		}
		return EntityType.PIG;
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if (damager instanceof Player) {
			Player player = (Player) damager;
			addJobExp(player, "chasseur", 2);
		}
	}
	
}
