package com.bergerkiller.bukkit.sr;

import org.bukkit.command.CommandSender;

import com.bergerkiller.bukkit.common.config.FileConfiguration;
import com.bergerkiller.bukkit.common.PluginBase;

public class StreamRemover extends PluginBase {
	
	public void enable() {		
		//General registering
		SRListener listener = new SRListener();
		this.register(listener);

		//Settings
		FileConfiguration config = new FileConfiguration(this);
		config.load();
		listener.allowLava = config.get("allowLava", false);
		config.save();
	}
	public void disable() {
		
	}

	public boolean command(CommandSender sender, String command, String[] args) {
		return true;
	}
	
	@Override
	public void permissions() {
	}
	
	
}
