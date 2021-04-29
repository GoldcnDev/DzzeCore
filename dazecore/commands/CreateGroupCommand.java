package dazecore.commands;

import dazecore.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.ArrayList;

public class CreateGroupCommand implements CommandExecutor {
    Main main;
    public CreateGroupCommand(Main main) {
        this.main = main;
    }
    PermissionAttachment attachment;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);
        if(cmd.getLabel().equalsIgnoreCase("creategroup")){
            if(!p.hasPermission("core.creategroup")){
                p.sendMessage(checkCC("&5[Permissions]&8: &cInvalid permissions."));
            } else {
                if(args.length == 0){
                    return false;
                }
                if(args.length == 1){
                    String group = args[0];
                    if(main.fm.getGroups().getConfigurationSection("groups").contains(group)){
                        p.sendMessage(checkCC("&5[Permissions]&8: &cThat group already exists."));
                    } else {
                        main.fm.getGroups().getConfigurationSection("groups").createSection(group);
                        main.fm.getGroups().set("groups." + group + ".prefix", "");
                        main.fm.getGroups().set("groups." + group + ".permissions", new ArrayList<String>());
                        main.fm.saveGroups();

                        p.sendMessage(checkCC("&5[Permissions]&8: &aSuccessfully created group &b" + group));
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
