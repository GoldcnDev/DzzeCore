package dazecore.commands;

import dazecore.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SkyDiveCommand implements CommandExecutor {
    Main main;
    public SkyDiveCommand(Main main) {
        this.main = main;
    }
    public Set<UUID> vanished = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        Location origin = p.getLocation();
        if(cmd.getLabel().equalsIgnoreCase("skydive")){
            if(!p.hasPermission("core.skydive")){
                p.sendMessage(checkCC("&5[DazeCore]&8: &cInvalid permissions."));
            } else {
                    for(Player online: Bukkit.getOnlinePlayers()){
                        online.teleport(new Location(online.getWorld(), origin.getX(), origin.getY()+100, origin.getZ()));
                        p.sendMessage("Time to skydive!");
                        p.getInventory().setChestplate(new ItemStack(Material.ELYTRA, 1));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 10, 10));
                    }
                    p.sendMessage(checkCC("&5[DazeCore]&8: &aTime to skydive!"));
            }
        }

        return true;
    }

    public String checkCC(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
