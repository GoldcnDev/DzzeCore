package dazecore.events;

import dazecore.commands.MuteCommand;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import dazecore.main.Main;

public class ChatListener implements Listener {
	
	Main main;
	MuteCommand mutecmd;
	public ChatListener(Main main, MuteCommand mc) {
		this.main = main;
		this.mutecmd = mc;
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String playerGroup = main.fm.getPlayers().getString(String.valueOf(p.getUniqueId()));
		String groupPrefix = main.fm.getGroups().getString("groups." + playerGroup + ".prefix");
		if(!(groupPrefix == null)) {
			e.setFormat(checkColourCodes(groupPrefix) + p.getDisplayName() + ChatColor.DARK_GRAY + ": " + ChatColor.RESET + checkColourCodes(e.getMessage()));
		} else {
			e.setFormat(checkColourCodes(main.fm.getGroups().getString("groups.default.prefix")) + p.getDisplayName() + ChatColor.DARK_GRAY + ": " + ChatColor.RESET + checkColourCodes(e.getMessage()));
		}
		if(mutecmd.muted.contains(p.getUniqueId())){
			e.setCancelled(true);
			p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 5, 3);
			p.sendMessage(checkColourCodes("&4[Punish]&8: &cYou are muted - &8Message not sent."));
		}
		if(swear(e.getMessage(), "nigger") || swear(e.getMessage(), "fag") || swear(e.getMessage(), "cunt")
		 || swear(e.getMessage(), "Nigger") || swear(e.getMessage(), "Fag")){
			e.setCancelled(true);
			p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 5, 3);
			p.sendMessage(checkColourCodes("&4[Filter]&8: &8Message not sent &c- nice try."));
		}
	}

	public boolean swear(String message, String string){
		if(message.contains(string)){
			return true;
		}
		 return false;
	}

	public String checkColourCodes(String string){
		return ChatColor.translateAlternateColorCodes('&', string);
	}

	
}
