package dazecore.commands;

import dazecore.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class VanishCommand implements CommandExecutor {
    Main main;
    public VanishCommand(Main main) {
        this.main = main;
    }
    public Set<UUID> vanished = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getLabel().equalsIgnoreCase("vanish")){
            if(!p.hasPermission("core.vanish")){
                p.sendMessage(checkCC("&5[DazeCore]&8: &cInvalid permissions."));
            } else {
                if(!vanished.contains(p.getUniqueId())){
                    for(Player online: Bukkit.getOnlinePlayers()){
                        online.hidePlayer(main, p);
                        vanished.add(p.getUniqueId());
                    }
                    p.sendMessage(checkCC("&5[DazeCore]&8: &aYou are now hidden."));
                } else {
                    for(Player online: Bukkit.getOnlinePlayers()){
                        online.showPlayer(main, p);
                        vanished.remove(p.getUniqueId());
                    }
                    p.sendMessage(checkCC("&5[DazeCore]&8: &aYou are now visible."));
                }
            }
        }

        return true;
    }

    public String checkCC(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
