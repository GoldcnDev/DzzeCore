package dazecore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SaveLocCommand implements CommandExecutor {

    public HashMap<Player, Double> y = new HashMap<>();
    public HashMap<Player, Double> x = new HashMap<>();
    public HashMap<Player, Double> z = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if(cmd.getLabel().equalsIgnoreCase("saveloc")){
            y.put(p, p.getLocation().getY());
            x.put(p, p.getLocation().getX());
            z.put(p, p.getLocation().getZ());
            p.sendMessage(checkCC("&aYou have saved your current location. "));
            p.sendMessage(checkCC("&aView it by doing &b/lastloc"));
            p.sendMessage(checkCC("&4&lWARNING: &6Saving your location again will override your last saved location."));
        }

        return true;
    }

    public String checkCC(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
