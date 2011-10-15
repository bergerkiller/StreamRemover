package com.bergerkiller.bukkit.sr;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class StreamRemover extends JavaPlugin {
	private final SRBlockListener blockListener = new SRBlockListener();
			
	public void onEnable() {		
		//General registering
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_FROMTO, blockListener, Priority.Highest, this);

		//Settings
		Configuration config = getConfiguration();
		blockListener.allowLava = config.getBoolean("allowLava", false);
		config.setProperty("allowLava", blockListener.allowLava);
		config.save();
		
        //final msg
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
	}
	public void onDisable() {
		System.out.println("Stream Remover disabled!");
	}
	
	
}
