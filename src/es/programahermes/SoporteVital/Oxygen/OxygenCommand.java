package es.programahermes.SoporteVital.Oxygen;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import es.programahermes.MySQL;

public class OxygenCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("presurizar")) {
			Player player = (Player) sender;

			if (player instanceof Player) {
				// longitud argumentos
				if (args.length == 1 || args.length == 2) {
					if (args[0].equalsIgnoreCase("traje")) {
						if (hasOxygen(player.getItemInHand())) {
							if (MySQL.getOxygen(player.getName())
									+ getOxygen(player.getItemInHand()) > 700) {
								int rellenable = (int) (700 - MySQL
										.getOxygen(player.getName()));
								if (rellenable <= 0) {
									player.sendMessage(ChatColor.RED
											+ "¡Tu traje está al límite de su capacidad!");
									return true;
								} else {
									if(getOxygen(player.getItemInHand())<= rellenable){
										MySQL.addOxygen(player.getName(), getOxygen(player.getItemInHand()));
										removeOxygen(player.getItemInHand(), getOxygen(player.getItemInHand()));
										player.sendMessage(ChatColor.GREEN
												+ "¡Se ha presurizado tu traje con "+getOxygen(player.getItemInHand())+"L de ox�geno!");
										return true;
									}else{
										MySQL.addOxygen(player.getName(), rellenable);
										removeOxygen(player.getItemInHand(), rellenable);
										player.sendMessage(ChatColor.GREEN
												+ "�Se ha presurizado tu traje con "+rellenable+"L de ox�geno!");
										return true;
									}
								}

							} else {
								MySQL.addOxygen(player.getName(),
										getOxygen(player.getItemInHand()));
								player.sendMessage(ChatColor.GREEN
										+ "Se ha presruizado tu traje con "
										+ getOxygen(player.getItemInHand())
										+ "L");
								setOxygen(player.getItemInHand(), 0);
								player.playSound(player.getLocation(),
										Sound.BAT_LOOP, 2.0F, 2.0F);
								return true;
							}
						} else {
							player.sendMessage(ChatColor.RED
									+ "Este objeto no es un tanque de ox�geno");
							return true;
						}
					} else {
						if (args[0].equalsIgnoreCase("tanque")) {
							int oxygen = (int) MySQL.getOxygen(player.getName());
							setOxygen(player.getItemInHand(), oxygen);
							MySQL.removeOxygen(player.getName(), oxygen);
							player.playSound(player.getLocation(),
									Sound.BAT_LOOP, 1.0F, 1.0F);
							player.sendMessage(ChatColor.RED
									+ "Se han a�adido "
									+ Integer.parseInt(args[1])
									+ " L al tanque ");
							return true;
						} else {
							return false;
						}
					}
				} else {
					// longitud argumentos
					return false;
				}

			}
		}
		return false;

	}

	public static boolean hasOxygen(ItemStack item) {
		if (item.getItemMeta() != null) {
			if (item.getItemMeta().getLore() != null) {
				if (item.getItemMeta().getLore().get(0).contains("O2: ")) {
					return true;
				}
			}
		}

		return false;

	}

	public static int getOxygen(ItemStack item) {
		if (item.getItemMeta() != null) {
			if (item.getItemMeta().getLore() != null) {
				String o2 = item.getItemMeta().getLore().get(0);
				if (o2.contains("O2: ")) {
					String o3 = o2.replace("O2: ", "");
					String o4 = o3.replace("L", "");
					int oxygen = Integer.parseInt(o4);
					return oxygen;

				}

			}
		}

		return 0;

	}

	public static void setOxygen(ItemStack item, int oxygen) {
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("O2: " + oxygen + "L");
		meta.setLore(lore);
		item.setItemMeta(meta);
	}

	public static void addOxygen(ItemStack item, int oxygen) {
		int previous = getOxygen(item);
		setOxygen(item, previous + oxygen);
	}

	public static void removeOxygen(ItemStack item, int oxygen) {
		int previous = getOxygen(item);
		if (previous - oxygen > 0) {
			setOxygen(item, previous - oxygen);
		} else {
			setOxygen(item, 0);
		}

	}

}
