package dazecore.commands;

import dazecore.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class DeleteGroupCommand implements CommandExecutor {
    Main main;
    public DeleteGroupCommand(Main main) {
        this.main = main;
    }
    PermissionAttachment attachment;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);
        if(cmd.getLabel().equalsIgnoreCase("deletegroup")){
            if(!p.hasPermission("core.deletegroup")){
                p.sendMessage(checkCC("&5[Permissions]&8: &cInvalid permissions."));
            } else {
                if(args.length == 0){
                    return false;
                }
                if(args.length == 1){
                    String group = args[0];
                    if(!main.fm.getGroups().contains("groups." + group)){
                        p.sendMessage(checkCC("&5[Permissions]&8: &cThat group does not exist."));
                    } else {
                        main.getConfig().set("groups." + group, null);
                        main.fm.saveGroups();
                        p.sendMessage(checkCC("&5[Permissions]&8: &aSuccessfully deleted group &b" + group));
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
