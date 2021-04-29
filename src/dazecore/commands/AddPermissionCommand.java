package dazecore.commands;

import dazecore.main.Main;
import dazecore.managers.PermissionManagement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class AddPermissionCommand implements CommandExecutor {
    Main main;
    PermissionManagement pm;
    public AddPermissionCommand(Main main, PermissionManagement perms) {
        this.main = main;
        this.pm = perms;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);
        if(cmd.getLabel().equalsIgnoreCase("addpermission")){
            if(!p.hasPermission("core.addpermission")){
                p.sendMessage(checkCC("&5[Permissions]&8: &cInvalid permissions."));
            } else {
                if(args.length == 0){
                    p.sendMessage(checkCC("&5[Permissions]&8: &cInvalid arguments."));
                }
                if(args.length == 1){
                    p.sendMessage(checkCC("&5[Permissions]&8: &cInvalid arguments."));
                }
                if(args.length == 2){
                    String group = args[0];
                    String permission = args[1];
                    if(main.fm.getGroups().getConfigurationSection("groups").contains(group)){
                        pm.setupGroupPermissions(group, permission);
                        pm.setupPermissions(p);
                        p.sendMessage(checkCC("&5[Permissions]&8: &aSet permission &b" + permission + "&ato group &b" + group));
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
