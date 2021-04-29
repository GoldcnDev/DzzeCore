package dazecore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class LastLocCommand implements CommandExecutor {

    SaveLocCommand saveloc;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        double x = saveloc.x.get(p);
        double y = saveloc.y.get(p);
        double z = saveloc.z.get(p);
        if(cmd.getLabel().equalsIgnoreCase("lastloc")){
              p.sendMessage(checkCC("&6Your saved location was: "));
              p.sendMessage(checkCC("&eX: &f" + x+""));
              p.sendMessage(checkCC("&eY: &f" + y+""));
              p.sendMessage(checkCC("&eZ: &f" + z+""));

        }
        return true;
    }

    public String checkCC(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
