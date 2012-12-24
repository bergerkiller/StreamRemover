package com.bergerkiller.bukkit.sr;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class StreamRemover extends JavaPlugin {
	public static boolean allowLava = false;

	@Override
	public void onEnable() {
		//General registering
		Bukkit.getPluginManager().registerEvents(new SRListener(), this);

		//Settings
		FileConfiguration config = this.getConfig();
		if (config.contains("allowLava")) {
			allowLava = config.getBoolean("allowLava");
		} else {
			config.set("allowLava", allowLava);
		}
		this.saveConfig();
	}

	@Override
	public void onDisable() {
		
	}
}
