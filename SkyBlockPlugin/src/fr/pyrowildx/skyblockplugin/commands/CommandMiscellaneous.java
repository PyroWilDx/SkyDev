package fr.pyrowildx.skyblockplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.pyrowildx.skyblockplugin.Constants;
import fr.pyrowildx.skyblockplugin.Main;

public class CommandMiscellaneous implements CommandExecutor {
	
	private Main main;

	public CommandMiscellaneous(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;
			String uuid = String.valueOf(player.getUniqueId());
			
			if (cmd.getName().equalsIgnoreCase("fly")) {
				if (main.getConfig().getBoolean(uuid + ".fly")) {
					if (player.getAllowFlight()) {
						player.setAllowFlight(false);
						player.sendMessage("�aFly d�sactiv� ! ");
					} else {
						player.setAllowFlight(true);
						player.sendMessage("�aFly activ� ! ");
					}
				} else {
					player.sendMessage("�cVous n'avez pas acc�s au /fly, achetez le fly dans le shop !");
				}
			}
			
			if (cmd.getName().equalsIgnoreCase("boosts")) {
				boolean hasBoost = false;
				if (main.getConfig().getBoolean(uuid + ".strength")) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
					hasBoost = true;
					player.sendMessage("�aForce II activ� !");
				}
				if (main.getConfig().getBoolean(uuid + ".speed")) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
					hasBoost = true;
					player.sendMessage("�aSpeed I activ� !");
				}
				if (main.getConfig().getBoolean(uuid + ".haste")) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0));
					hasBoost = true;
					player.sendMessage("�aHaste I activ� !");
				}
				if (main.getConfig().getBoolean(uuid + ".resistance")) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
					hasBoost = true;
					player.sendMessage("�aResistance I activ� !");
				}
				if (!hasBoost) {
					player.sendMessage("�cVous n'avez aucun boost, vous pouvez en acheter dans le shop !");
				}
			}
			
			if (cmd.getName().equalsIgnoreCase("bottlexp")) {
				if (args.length == 0) {
					
				} else {
					
				}
			}
			
			if (cmd.getName().equalsIgnoreCase("jobs")) {
				player.sendMessage("�������������������������");
				player.sendMessage(getJobInfo(uuid, "agriculteur", "Agriculteur"));
				player.sendMessage(getJobInfo(uuid, "chasseur", "Chasseur"));
				player.sendMessage(getJobInfo(uuid, "bucheron", "B�cheron"));
				player.sendMessage(getJobInfo(uuid, "mineur", "Mineur"));
				player.sendMessage("�������������������������");
			}
			
		}
		
		return false;
	}
	
	private String getJobInfo(String uuid, String job, String jobName) {
		long jobLvl = main.getConfig().getInt(uuid + "." + job + "Level");
		if (jobLvl < Constants.JOB_MAX_LVL) {
			return "�b�l" + jobName + " :" +
				"\n  �bNiveau : �d" + String.valueOf(jobLvl) +
				"\n  �bExp : �d" + String.valueOf(main.getConfig().getInt(uuid + "." + job + "Exp")) + " / " + String.valueOf((int) Math.ceil((Math.pow(jobLvl, 2.35f) / 10.0f) + (jobLvl * 10.75f))) + " Exp";
		} else {
			return "�b�l" + jobName + " :" +
					"\n  �bNiveau : �d" + String.valueOf(jobLvl) +
					"\n  �bExp : �d" + String.valueOf(main.getConfig().getInt(uuid + "." + job + "Exp")) + " Exp (Niveau MAX)";
		}
	}

}
