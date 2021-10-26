package fr.pyrowildx.skyblockplugin;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Constants {
	
	public static String AGRICULTEUR_SHOP_NAME = "§a§lAgriculteur";
	public static String CHASSEUR_SHOP_NAME = "§c§lChasseur";
	public static String BUCHERON_SHOP_NAME = "§6§lBûcheron";
	public static String MINEUR_SHOP_NAME = "§b§lMineur";
	public static String OEUFS_SHOP_NAME = "§e§lOeufs";
	public static String FLEURS_SHOP_NAME = "§d§lFleurs";
	public static String BLOCS_SHOP_NAME = "§9§lBlocs";
	public static String OUTILS_MAGIQUE_SHOP_NAME = "§c§lOutils Magiques";
	public static String DIVERS_SHOP_NAME = "§f§lDivers";
	public static String SPAWNERS_SHOP_NAME = "§7§lSpawners";
	
	public static int JOB_MAX_LVL = 100;

	public static List<Item> AGRICULTEUR_ITEMS = Arrays.asList(
			createItem(Material.WHEAT, 0, 7, 10, "Wheat"),
			createItem(Material.BEETROOT, 0, 7, 11, "Beetroot"),
			createItem(Material.MELON_SLICE, 0, 1, 12, "Melon Slice"),
			createItem(Material.PUMPKIN, 0, 5, 13, "Pumpkin"),
			createItem(Material.POTATO, 50, 4, 14, "Potato"),
			createItem(Material.CARROT, 50, 4, 15, "Carrot"),
			createItem(Material.SWEET_BERRIES, 50, 3, 16, "Sweet Berries"),
			createItem(Material.WHEAT_SEEDS, 50, 1, 19, "Wheat Seeds"),
			createItem(Material.BEETROOT_SEEDS, 50, 1, 20, "Beetroot Seeds"),
			createItem(Material.MELON_SEEDS, 50, 1, 21, "Melon Seeds"),
			createItem(Material.PUMPKIN_SEEDS, 50, 1, 22, "Pumpkin Seeds"),
			createItem(Material.COCOA_BEANS, 50, 2, 23, "Cocoa Beans"),
			createItem(Material.NETHER_WART, 50, 1, 24, "Nether Wart"),
			createItem(Material.CHORUS_FRUIT, 0, 10, 25, "Chorus Fruit"),
			createItem(Material.CACTUS, 10, 2, 28, "Cactus"),
			createItem(Material.SUGAR_CANE, 50, 3, 29, "Sugar Cane"),
			createItem(Material.BAMBOO, 50, 2, 30, "Bamboo"),
			createItem(Material.KELP, 50, 5, 31, "Kelp"),
			createItem(Material.RED_MUSHROOM, 50, 5, 32, "Red Mushroom"),
			createItem(Material.BROWN_MUSHROOM, 50, 5, 33, "Brown Mushroom"),
			createItem(Material.HONEYCOMB, 1000, 100, 34, "Honeycomb"));	
	
	public static List<Item> CHASSEUR_ITEMS = Arrays.asList(
			createItem(Material.INK_SAC, 0, 16, 2, "Ink Sac"),
			createItem(Material.PRISMARINE_SHARD, 0, 16, 3, "Prismarine Shard"),
			createItem(Material.COOKED_COD, 0, 20, 4, "Cooked Cod"),
			createItem(Material.PRISMARINE_CRYSTALS, 0, 36, 5, "Prismarine Crystals"),
			createItem(Material.EGG, 0, 3, 6, "Egg"),
			createItem(Material.ROTTEN_FLESH, 0, 12, 9, "Rotten Flesh"),
			createItem(Material.BONE, 0, 8, 10, "Bone"),
			createItem(Material.ARROW, 0, 4, 11, "Arrow"),
			createItem(Material.STRING, 50, 8, 12, "String"),
			createItem(Material.SPIDER_EYE, 0, 16, 13, "Spider Eye"),
			createItem(Material.GUNPOWDER, 0, 24, 14, "Gunpowder"),
			createItem(Material.ENDER_PEARL, 0, 30, 15, "Ender Pearl"),
			createItem(Material.PHANTOM_MEMBRANE, 0, 30, 16, "Phantom Membrane"),
			createItem(Material.SCUTE, 0, 200, 17, "Scute"),
			createItem(Material.COD, 0, 10, 18, "Raw Cod"),
			createItem(Material.SALMON, 0, 200, 19, "Raw Salmon"),
			createItem(Material.FEATHER, 0, 14, 20, "Feather"),
			createItem(Material.RABBIT_HIDE, 0, 14, 21, "Rabbit Hide"),
			createItem(Material.RABBIT_FOOT, 0, 60, 22, "Rabbit's Foot"),
			createItem(Material.WHITE_WOOL, 200, 10, 23, "White Wool"),
			createItem(Material.LEATHER, 0, 14, 24, "Leather"),
			createItem(Material.SLIME_BALL, 2000, 0, 25, "Slimeball"),
			createItem(Material.MAGMA_CREAM, 0, 30, 26, "Magma Cream"),
			createItem(Material.PUFFERFISH, 0, 600, 27, "Pufferfish"),
			createItem(Material.TROPICAL_FISH, 0, 600, 28, "Tropical Fish"),
			createItem(Material.CHICKEN, 0, 5, 29, "Raw Chicken"),
			createItem(Material.RABBIT, 0, 5, 30, "Raw Rabbit"),
			createItem(Material.PORKCHOP, 0, 5, 31, "Raw Porkchop"),
			createItem(Material.MUTTON, 0, 5, 32, "Raw Mutton"),
			createItem(Material.BEEF, 0, 5, 33, "Raw Beef"),
			createItem(Material.BLAZE_ROD, 0, 30, 34, "Blaze Rod"),
			createItem(Material.GHAST_TEAR, 0, 100, 35, "Ghast Tear"),
			createItem(Material.COOKED_CHICKEN, 0, 7, 38, "Cooked Chicken"),
			createItem(Material.COOKED_RABBIT, 0, 7, 39, "Cooked Rabbit"),
			createItem(Material.COOKED_PORKCHOP, 0, 7, 40, "Cooked Porkchop"),
			createItem(Material.COOKED_MUTTON, 0, 7, 41, "Cooked Mutton"),
			createItem(Material.COOKED_BEEF, 0, 7, 42, "Steak"));
	
	public static List<Item> BUCHERON_ITEMS = Arrays.asList(
			createItem(Material.OAK_LOG, 100, 20, 19, "Oak Log"),
			createItem(Material.BIRCH_LOG, 100, 20, 20, "Birch Log"),
			createItem(Material.SPRUCE_LOG, 100, 20, 21, "Spruce Log"),
			createItem(Material.ACACIA_LOG, 100, 20, 23, "Acacia Log"),
			createItem(Material.JUNGLE_LOG, 100, 20, 24, "Jungle Log"),
			createItem(Material.DARK_OAK_LOG, 100, 20, 25, "Dark Oak Log"),
			createItem(Material.OAK_SAPLING, 10, 3, 28, "Oak Sapling"),
			createItem(Material.BIRCH_SAPLING, 10, 3, 29, "Birch Sapling"),
			createItem(Material.SPRUCE_SAPLING, 10, 3, 30, "Spruce Sapling"),
			createItem(Material.ACACIA_SAPLING, 10, 3, 32, "Acacia Sapling"),
			createItem(Material.JUNGLE_SAPLING, 10, 3, 33, "Jungle Sapling"),
			createItem(Material.DARK_OAK_SAPLING, 10, 3, 34, "Dark Oak Sapling"),
			createItem(Material.APPLE, 0, 75, 31, "Apple"));
	
	public static List<Item> MINEUR_ITEMS = Arrays.asList(
			createItem(Material.QUARTZ, 16, 5, 12, "Quartz"),
			createItem(Material.COBBLESTONE, 10, 3, 13, "Cobblestone"),
			createItem(Material.NETHERITE_INGOT, 70000, 20000, 14, "Netherite Ingot"),
			createItem(Material.COAL_BLOCK, 198, 63, 19, "Coal Block"),
			createItem(Material.REDSTONE_BLOCK, 117, 36, 20, "Redstone Block"),
			createItem(Material.LAPIS_BLOCK, 261, 81, 21, "Lapis Block"),
			createItem(Material.IRON_BLOCK, 540, 171, 22, "Iron Block"),
			createItem(Material.GOLD_BLOCK, 1413, 450, 23, "Gold Block"),
			createItem(Material.DIAMOND_BLOCK, 5895, 1890, 24, "Diamond Block"),
			createItem(Material.EMERALD_BLOCK, 11790, 3780, 25, "Emerald Block"),
			createItem(Material.COAL, 22, 7, 28, "Coal"),
			createItem(Material.REDSTONE, 13, 4, 29, "Redstone"),
			createItem(Material.LAPIS_LAZULI, 29, 9, 30, "Lapis Lazuli"),
			createItem(Material.IRON_INGOT, 60, 19, 31, "Iron Ingot"),
			createItem(Material.GOLD_INGOT, 157, 50, 32, "Gold Ingot"),
			createItem(Material.DIAMOND, 655, 210, 33, "Diamond"),
			createItem(Material.EMERALD, 1310, 420, 34, "Emerald"),
			createItem(Material.IRON_ORE, 0, 6, 40, "Iron Ore"),
			createItem(Material.GOLD_ORE, 0, 17, 41, "Gold Ore"));
	
	public static List<Item> OEUFS_ITEMS = Arrays.asList(
			createItem(Material.VILLAGER_SPAWN_EGG, 75000, 0, 10, "Villager Spawn Egg"),
			createItem(Material.BEE_SPAWN_EGG, 1000, 0, 11, "Bee Spawn Egg"),
			createItem(Material.OCELOT_SPAWN_EGG, 1000, 0, 12, "Ocelot Spawn Egg"),
			createItem(Material.FOX_SPAWN_EGG, 1000, 0, 13, "Fox Spawn Egg"),
			createItem(Material.WOLF_SPAWN_EGG, 1000, 0, 14, "Wolf Spawn Egg"),
			createItem(Material.HORSE_SPAWN_EGG, 1000, 0, 15, "Horse Spawn Egg"),
			createItem(Material.DONKEY_SPAWN_EGG, 1000, 0, 16, "Donkey Spawn Egg"),
			createItem(Material.LLAMA_SPAWN_EGG, 1000, 0, 19, "Llama Spawn Egg"),
			createItem(Material.PANDA_SPAWN_EGG, 1000, 0, 20, "Panda Spawn Egg"),
			createItem(Material.COW_SPAWN_EGG, 1000, 0, 21, "Cow Spawn Egg"),
			createItem(Material.MOOSHROOM_SPAWN_EGG, 1000, 0, 22, "Mooshroom Spawn Egg"),
			createItem(Material.PIG_SPAWN_EGG, 1000, 0, 23, "Pig Spawn Egg"),
			createItem(Material.SHEEP_SPAWN_EGG, 1000, 0, 24, "Sheep Spawn Egg"),
			createItem(Material.CHICKEN_SPAWN_EGG, 1000, 0, 25, "Chicken Spawn Egg"),
			createItem(Material.RABBIT_SPAWN_EGG, 1000, 0, 28, "Rabbit Spawn Egg"),
			createItem(Material.POLAR_BEAR_SPAWN_EGG, 1000, 0, 29, "Polar Bear Spawn Egg"),
			createItem(Material.PARROT_SPAWN_EGG, 1000, 0, 30, "Parrot Spawn Egg"),
			createItem(Material.DOLPHIN_SPAWN_EGG, 1000, 0, 31, "Dolphin Spawn Egg"),
			createItem(Material.SQUID_SPAWN_EGG, 1000, 0, 32, "Squid Spawn Egg"),
			createItem(Material.TURTLE_SPAWN_EGG, 1000, 0, 33, "Turtle Spawn Egg"),
			createItem(Material.COD_SPAWN_EGG, 1000, 0, 34, "Cod Spawn Egg"),
			createItem(Material.TROPICAL_FISH_SPAWN_EGG, 1000, 0, 39, "Tropical Fish Spawn Egg"),
			createItem(Material.SALMON_SPAWN_EGG, 1000, 0, 40, "Salmon Spawn Egg"),
			createItem(Material.PUFFERFISH_SPAWN_EGG, 1000, 0, 41, "Pufferfish Spawn Egg"));
	
	public static List<Item> FLEURS_ITEMS = Arrays.asList(
			createItem(Material.SEAGRASS, 1, 0, 10, "Seagrass"),
			createItem(Material.SEA_PICKLE, 1, 0, 11, "Sea Pickle"),
			createItem(Material.LILY_PAD, 1, 0, 12, "Lily Pad"),
			createItem(Material.POPPY, 1, 1, 13, "Poppy"),
			createItem(Material.DANDELION, 1, 0, 14, "Dandelion"),
			createItem(Material.OXEYE_DAISY, 1, 0, 15, "Oxeye Daisy"),
			createItem(Material.WITHER_ROSE, 1, 0, 16, "Wither Rose"),
			createItem(Material.BLUE_ORCHID, 1, 0, 19, "Blue Orchid"),
			createItem(Material.CORNFLOWER, 1, 0, 20, "Cornflower"),
			createItem(Material.ALLIUM, 1, 0, 21, "Allium"),
			createItem(Material.WHITE_TULIP, 1, 0, 22, "White Tulip"),
			createItem(Material.PINK_TULIP, 1, 0, 23, "Pink Tulip"),
			createItem(Material.ORANGE_TULIP, 1, 0, 24, "Orange Tulip"),
			createItem(Material.RED_TULIP, 1, 0, 25, "Red Tulip"),
			createItem(Material.AZURE_BLUET, 1, 0, 28, "Azure Bluet"),
			createItem(Material.LILY_OF_THE_VALLEY, 1, 0, 29, "Lily of the Valley"),
			createItem(Material.LARGE_FERN, 1, 0, 30, "Large Fern"),
			createItem(Material.SUNFLOWER, 1, 0, 31, "Sunflower"),
			createItem(Material.ROSE_BUSH, 1, 0, 32, "Rose Bush"),
			createItem(Material.PEONY, 1, 0, 33, "Peony"),
			createItem(Material.LILAC, 1, 0, 34, "Lilac"));
	
	public static List<Item> BLOCS_ITEMS = Arrays.asList(
			createItem(Material.GRASS_BLOCK, 1000, 0, 9, "Grass Block"),
			createItem(Material.DIRT, 50, 0, 10, "Dirt"),
			createItem(Material.PODZOL, 100, 0, 11, "Podzol"),
			createItem(Material.MYCELIUM, 1000, 0, 12, "Mycelium"),
			createItem(Material.GRAVEL, 50, 0, 13, "Gravel"),
			createItem(Material.SAND, 50, 0, 14, "Sand"),
			createItem(Material.RED_SAND, 50, 0, 15, "Red Sand"),
			createItem(Material.STONE, 50, 0, 16, "Stone"),
			createItem(Material.SPONGE, 1000, 0, 17, "Sponge"),
			createItem(Material.ICE, 50, 0, 18, "Ice"),
			createItem(Material.SNOW_BLOCK, 150, 0, 19, "Snow Block"),
			createItem(Material.PRISMARINE, 250, 0, 20, "Prismarine"),
			createItem(Material.PRISMARINE_BRICKS, 250, 0, 21, "Prismarine Bricks"),
			createItem(Material.DARK_PRISMARINE, 250, 0, 22, "Dark Prismarine"),
			createItem(Material.SEA_LANTERN, 250, 0, 23, "Sea Lantern"),
			createItem(Material.PACKED_ICE, 150, 0, 24, "Compressed Ice"),
			createItem(Material.CLAY, 50, 0, 25, "Clay"),
			createItem(Material.MAGMA_BLOCK, 200, 0, 26, "Magma Block"),
			createItem(Material.NETHERRACK, 10, 0, 27, "Netherrack"),
			createItem(Material.NETHER_BRICKS, 20, 0, 28, "Nether Bricks"),
			createItem(Material.RED_NETHER_BRICKS, 20, 0, 29, "Red Nether Bricks"),
			createItem(Material.QUARTZ_BLOCK, 64, 0, 30, "Block of Quartz"),
			createItem(Material.GLOWSTONE, 50, 0, 31, "Glowstone"),
			createItem(Material.SOUL_SAND, 50, 0, 32, "Soul Sand"),
			createItem(Material.PURPUR_BLOCK, 20, 0, 33, "Purpur Block"),
			createItem(Material.END_STONE, 50, 0, 34, "End Stone"),
			createItem(Material.BLUE_ICE, 100, 0, 35, "Blue Ice"),
			createItem(Material.TERRACOTTA, 50, 0, 36, "Terracotta"),
			createItem(Material.BRICKS, 50, 0, 37, "Bricks"),
			createItem(Material.SHROOMLIGHT, 100, 0, 43, "Shroomlight"),
			createItem(Material.SOUL_SOIL, 100, 0, 44, "Soul Soil"));
	
	public static List<Item> ALL_ITEMS = Stream.of(AGRICULTEUR_ITEMS, CHASSEUR_ITEMS,
			BUCHERON_ITEMS, MINEUR_ITEMS, OEUFS_ITEMS, FLEURS_ITEMS, BLOCS_ITEMS)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

	public static List<CustomItem> MAGIQUE_ITEMS = Arrays.asList(
			createCustomItem(Material.DIAMOND_SWORD, 600000, 21, "§cÉppée Magique", "§cÉppée Magique", 
					Arrays.asList("", "§cSharpness 6 & Looting 4"), false, 
					Arrays.asList(Enchantment.DAMAGE_ALL, Enchantment.LOOT_BONUS_MOBS,
							Enchantment.FIRE_ASPECT, Enchantment.SWEEPING_EDGE,
							Enchantment.DURABILITY), Arrays.asList(6, 4, 2, 3, 3)),
			createCustomItem(Material.FISHING_ROD, 100000, 22, "§cRod Magique", "§cRod Magique",
					Arrays.asList("", "§cLuck of the Sea 4 & Lure 4"), false,
					Arrays.asList(Enchantment.LUCK, Enchantment.LURE), Arrays.asList(4, 4)),
			createCustomItem(Material.BOW, 200000, 23, "§cArc Magique", "§cArc Magique",
					Arrays.asList("", "§cPower 6 & Punch 4 & Flame 2"), false,
					Arrays.asList(Enchantment.ARROW_DAMAGE, Enchantment.ARROW_KNOCKBACK,
							Enchantment.ARROW_FIRE, Enchantment.ARROW_INFINITE,
							Enchantment.DURABILITY), Arrays.asList(6, 4, 2, 1, 3)),
			createCustomItem(Material.DIAMOND_PICKAXE, 300000, 30, "§cPioche Magique", "§cPioche Magique", 
					Arrays.asList("", "§cEfficiency 6 & Fortune 4"), false,
					Arrays.asList(Enchantment.DIG_SPEED, Enchantment.LOOT_BONUS_BLOCKS,
							Enchantment.DURABILITY), Arrays.asList(6, 4, 3)),
			createCustomItem(Material.DIAMOND_AXE, 600000, 31, "§cHache Magique", "§cHache Magique",
					Arrays.asList("", "§7Cette §chache §7vous permet de couper", "§7les arbres §cinstantanément§7."), false, 
					Arrays.asList(Enchantment.DIG_SPEED, Enchantment.DURABILITY), Arrays.asList(6, 3)),
			createCustomItem(Material.DIAMOND_HOE, 700000, 32, "§cHoue Magique", "§cHoue Magique",
					Arrays.asList("", "§7Cette §choue §7vous permet de §creplanter", "§cautomatiquement §7les plants récoltés."), false, 
					Arrays.asList(Enchantment.LOOT_BONUS_BLOCKS, Enchantment.DURABILITY), Arrays.asList(4, 3)));
	
	public static List<CustomItem> DIVERS_ITEMS = Arrays.asList(
			createCustomItem(Material.NAME_TAG, 10000, 4, "Name Tag", null, null, true, null, null),
			createCustomItem(Material.BLAZE_POWDER, 600000, 11, "§c§lBoost Force II", "§c§lBoost Force II", 
					Arrays.asList("", "§7Vous donne l'effet §cStrength II", "§7constament avec le §c/boosts !"), true, null, null),
			createCustomItem(Material.SUGAR, 600000, 12, "§b§lBoost Speed I", "§b§lBoost Speed I", 
					Arrays.asList("", "§7Vous donne l'effet §cSpeed I", "§7constament avec le §c/boosts !"), true, null, null),
			createCustomItem(Material.FEATHER, 1000000, 13, "§f§lBoost Fly", "§f§lBoost Fly", 
					Arrays.asList("", "§7Vous permet de §cvoler", "§7avec le §c/fly §7!"), true, null, null),
			createCustomItem(Material.GOLDEN_PICKAXE, 600000, 14, "§e§lBoost Célérité I", "§e§lBoost Célérité I", 
					Arrays.asList("", "§7Vous donne l'effet §cCélérité I", "§7constament avec le §c/boosts !"), true, null, null),
			createCustomItem(Material.SHIELD, 200000, 15, "§8§lBoost Resistance II", "§8§lBoost Resistance II", 
					Arrays.asList("", "§7Vous donne l'effet §cResistance II", "§7constament avec le §c/boosts !"), true, null, null),
			createCustomItem(Material.ENCHANTED_BOOK, 25000, 21, "§eLivre Silk Touch", "§eLivre Silk Touch", null, true,
					Arrays.asList(Enchantment.SILK_TOUCH), Arrays.asList(1)),
			createCustomItem(Material.ENCHANTED_BOOK, 50000, 22, "§eLivre Frost Walker II", "§eLivre Frost Walker II", null, true,
					Arrays.asList(Enchantment.FROST_WALKER), Arrays.asList(2)),
			createCustomItem(Material.ENCHANTED_BOOK, 75000, 23, "§eLivre Mending", "§eLivre Mending", null, true,
					Arrays.asList(Enchantment.MENDING), Arrays.asList(1)),
			createCustomItem(Material.WATER_BUCKET, 100, 30, "Water Bucket", null, null, true, null, null),
			createCustomItem(Material.LAVA_BUCKET, 100, 32, "Lava Bucket", null, null, true, null, null),
			createCustomItem(Material.CHEST, 600000, 31, "§c§lSellChest", "§c§lSellChest",
					Arrays.asList("", "§7Vend §cautomatiquement §7les items dans", "§7le coffre toutes les §c30 secondes§7."), true, null, null),
			createCustomItem(Material.HOPPER, 100000, 40, "§a§lHopper de Farm", "§a§lHopper de Farm", null, true, null, null));
	
	public static List<CustomItem> SPAWNERS_ITEMS = Arrays.asList(
			createCustomItem(Material.SPAWNER, 100000, 12, "§ePig Spawner", "§ePig Spawner", null, true, null, null),
			createCustomItem(Material.SPAWNER, 200000, 13, "§eCow Spawner", "§eCow Spawner", null, true, null, null),
			createCustomItem(Material.SPAWNER, 200000, 14, "§eChicken Spawner", "§eChicken Spawner", null, true, null, null),
			createCustomItem(Material.SPAWNER, 200000, 19, "§eSheep Spawner", "§eSheep Spawner", null, true, null, null),
			createCustomItem(Material.SPAWNER, 250000, 20, "§eRabbit Spawner", "§eRabbit Spawner", null, true, null, null),
			createCustomItem(Material.SPAWNER, 500000, 21, "§eZombie Spawner", "§eZombie Spawner", null, true, null, null),
			createCustomItem(Material.SPAWNER, 700000, 22, "§eSkeleton Spawner", "§eSkeleton Spawner", null, true, null, null),
			createCustomItem(Material.SPAWNER, 900000, 23, "§eCreeper Spawner", "§eCreeper Spawner", null, true, null, null),
			createCustomItem(Material.SPAWNER, 1000000, 24, "§eSpider Spawner", "§eSpider Spawner", null, true, null, null),
			createCustomItem(Material.SPAWNER, 1200000, 25, "§eBlaze Spawner", "§eBlaze Spawner", null, true, null, null),
			createCustomItem(Material.SPAWNER, 800000, 30, "§eMagma Cube Spawner", "§eMagma Cube Spawner", null, true, null, null),
			createCustomItem(Material.SPAWNER, 1500000, 31, "§eIron Golem Spawner", "§eIron Golem Spawner", null, true, null, null),
			createCustomItem(Material.SPAWNER, 2000000, 32, "§eGuardian Spawner", "§eGuardian Spawner", null, true, null, null));
	
	public static List<CustomItem> ALL_CUSTOM_ITEMS = Stream.of(MAGIQUE_ITEMS, DIVERS_ITEMS, SPAWNERS_ITEMS)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
	
	private static Item createItem(Material material, int bP, int sP, int slot, String name) {
		Item item = new Item();
		item.material = material;
		item.buyPrice = bP;
		item.sellPrice = sP;
		item.slot = slot;
		item.name = name;
		return item;
	}
	
	private static CustomItem createCustomItem(Material material, int bP, int slot, String name, String displayName, List<String> desc, boolean breakable, List<Enchantment> enchantments, List<Integer> enchLvls) {
		CustomItem item = new CustomItem();
		item.item = new ItemStack(material, 1);
		ItemMeta itemM = item.item.getItemMeta();
		if (!breakable) itemM.setUnbreakable(true);
		if (displayName != null) itemM.setDisplayName(displayName);
		itemM.setLore(desc);
		if (enchantments != null) {
			for (int i = 0; i < enchantments.size(); i++) {
				itemM.addEnchant(enchantments.get(i), enchLvls.get(i), true);
			}
		}
		item.item.setItemMeta(itemM);
		item.buyPrice = bP;
		item.slot = slot;
		item.name = name;
		item.displayName = displayName;
		item.desc = desc;
		return item;
	}
	
	public static class Item {
		public Material material;
		public int buyPrice;
		public int sellPrice;
		public int slot;
		public String name;
	}
	
	public static class CustomItem {
		public ItemStack item;
		public int buyPrice;
		public int slot;
		public String name;
		public String displayName;
		public List<String> desc;
	}

	public static String LOTO_B_J = "§0§lBlack Jack";
	public static String LOTO_SUPER = "§e§lSuper";
	public static String LOTO_CASH = "§1§lCash";
	public static String LOTO_MILLIO = "§3§lMillionnaire";
	
}
