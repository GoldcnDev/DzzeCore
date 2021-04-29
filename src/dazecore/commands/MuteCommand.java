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

public class MuteCommand implements CommandExecutor {
    Main main;
    public MuteCommand(Main main) {
        this.main = main;
    }
    public Set<UUID> muted = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getLabel().equalsIgnoreCase("mute")){
            if(!p.hasPermission("core.mute")){
                p.sendMessage(checkCC("&5[DazeCore]&8: &cInvalid permissions."));
            } else {
                if(args.length == 0){
                    return false;
                }
                if(args.length == 1){
                    Player target = Bukkit.getPlayer(args[0]);
                    if(!target.isOnline()){
                        p.sendMessage(checkCC("&5[DazeCore]&8: &cPlayer isn't online."));
                    } else {
                        if(!muted.contains(target.getUniqueId())){
                            p.sendMessage(checkCC("&5[DazeCore]&8: &aMuted " + target.getName() + "."));
                            target.sendMessage(checkCC("&5[DazeCore]&8: &cYou've been muted by a staff member. &4Reason: &8null."));
                            muted.add(target.getUniqueId());
                        } else {
                            p.sendMessage(checkCC("&5[DazeCore]&8: &aUnmuted " + target.getName() + "."));
                            target.sendMessage(checkCC("&5[DazeCore]&8: &aYou've been unmuted by a staff member."));
                            muted.remove(target.getUniqueId());
                        }
                    }
                }
            }
        }

        return true;
    }

    public String checkCC(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
