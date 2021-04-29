package dazecore.main;

import dazecore.commands.*;
import dazecore.events.JoinQuitListener;
import dazecore.managers.FileManagement;
import dazecore.managers.PermissionManagement;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import dazecore.events.ChatListener;
import net.milkbowl.vault.chat.Chat;

import java.io.File;

public class Main extends JavaPlugin {

	public Main plugin;
	public FileManagement fm = new FileManagement(this);
	PermissionManagement pm = new PermissionManagement();
	MuteCommand mc = new MuteCommand(this);
	ChatListener cl = new ChatListener(this, mc);
	VanishCommand vc = new VanishCommand(this);
	JoinQuitListener jql = new JoinQuitListener(this, pm);
	SkyDiveCommand sky = new SkyDiveCommand(this);
	SetPlayerGroupCommand setPlayerGroupCommand = new SetPlayerGroupCommand(this);
	SetPrefixCommand setPrefixCommand = new SetPrefixCommand(this);
	CreateGroupCommand createGroupCommand = new CreateGroupCommand(this);
	DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(this);
	AddPermissionCommand addPermissionCommand = new AddPermissionCommand(this, pm);
	SaveLocCommand saveloc = new SaveLocCommand();
	WhereAmICommand whereAmICommand = new WhereAmICommand();
	LastLocCommand lastLocCommand = new LastLocCommand();
	
	@Override
	public void onEnable() {
		plugin = this;
		createConfig();
		fm.setupGroups();
		fm.setupPlayers();
		fm.groupscfg.options().copyDefaults(true);
		fm.playerscfg.options().copyDefaults(true);
		Bukkit.getServer().getConsoleSender().sendMessage("DazeCore is enabled.");
		Bukkit.getServer().getPluginManager().registerEvents(cl, this);
		Bukkit.getServer().getPluginManager().registerEvents(jql, this);
		Bukkit.getPluginCommand("mute").setExecutor(mc);
		Bukkit.getPluginCommand("vanish").setExecutor(vc);
		Bukkit.getPluginCommand("skydive").setExecutor(sky);
		Bukkit.getPluginCommand("creategroup").setExecutor(createGroupCommand);
		Bukkit.getPluginCommand("deletegroup").setExecutor(deleteGroupCommand);
		Bukkit.getPluginCommand("setprefix").setExecutor(setPrefixCommand);
		Bukkit.getPluginCommand("setplayergroup").setExecutor(setPlayerGroupCommand);
		Bukkit.getPluginCommand("addpermission").setExecutor(addPermissionCommand);
		Bukkit.getPluginCommand("saveloc").setExecutor(saveloc);
		Bukkit.getPluginCommand("whereami").setExecutor(whereAmICommand);
		Bukkit.getPluginCommand("lastloc").setExecutor(lastLocCommand);
	}
	
	@Override
	public void onDisable() {
		Bukkit.getServer().getConsoleSender().sendMessage("DazeCore is disabled.");
	}

	private void createConfig() {
		try {
			if (!getDataFolder().exists()) {
				getDataFolder().mkdirs();
			}
			File file = new File(getDataFolder(), "config.yml");
			if (!file.exists()) {
				getLogger().info("Config.yml not found, creating!");
				saveDefaultConfig();
			} else {
				getLogger().info("Config.yml found, loading!");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
