package dazecore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import java.util.HashMap;

public class WhereAmICommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if(cmd.getLabel().equalsIgnoreCase("whereami")){
            p.sendMessage(checkCC("&6Your location is: "));
            p.sendMessage(checkCC("&eX: &f" + p.getLocation().getX()));
            p.sendMessage(checkCC("&eY: &f" + p.getLocation().getY()));
            p.sendMessage(checkCC("&eZ: &f" + p.getLocation().getZ()));
            p.sendMessage(checkCC("&aSave your location by doing &b/saveloc"));
        }

        return true;
    }

    public String checkCC(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
