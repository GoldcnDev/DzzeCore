package dazecore.commands;

import dazecore.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class SetPrefixCommand implements CommandExecutor {
    Main main;
    public SetPrefixCommand(Main main) {
        this.main = main;
    }
    PermissionAttachment attachment;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        String group = args[0];
        attachment = p.addAttachment(main);
        if(cmd.getLabel().equalsIgnoreCase("setprefix")){
            if(!p.hasPermission("core.setprefix")){
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
                    String prefix = "";
                    for (int i = 1; i < args.length; i++){
                        String arg = (args[i] + " ");
                        prefix = (prefix + arg);
                    }
                    main.fm.getGroups().set("groups." + group + ".prefix", prefix);
                    main.fm.saveGroups();
                    p.sendMessage(checkCC("&5[Permissions]&8: &aSet group &b" + group + "&a's prefix to &b" + checkCC(prefix)));
                }
            }
        }

        return true;
    }

    public String checkCC(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
