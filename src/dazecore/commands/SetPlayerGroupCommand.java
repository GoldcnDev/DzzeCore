package dazecore.commands;

import dazecore.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class SetPlayerGroupCommand implements CommandExecutor {
    Main main;
    public SetPlayerGroupCommand(Main main) {
        this.main = main;
    }
    PermissionAttachment attachment;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);
        if(cmd.getLabel().equalsIgnoreCase("setplayergroup")){
            if(!p.hasPermission("core.setplayergroup")){
                p.sendMessage(checkCC("&5[Permissions]&8: &cInvalid permissions."));
            } else {
                if(args.length == 0){
                    return false;
                }
                if(args.length == 1){
                p.sendMessage(checkCC("&5[Permissions]&8: &cInvalid arguments."));
                return false;
                }
                if(args.length == 2){
                    String group = args[1];
                    if(main.fm.getGroups().getConfigurationSection("groups").contains(group)){
                        p.sendMessage(checkCC("&5[Permissions]&8: &aSet player &b" + target.getName() + "&a's group to &b" + group));
                        main.fm.getPlayers().set(target.getUniqueId().toString(), group);
                        main.fm.savePlayers();
                    } else {
                        p.sendMessage(checkCC("&5[Permissions]&8: &cGroup is nonexistent."));
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
