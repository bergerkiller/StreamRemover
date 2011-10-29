package com.bergerkiller.bukkit.sr;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class StreamRemover extends JavaPlugin {
	private final SRBlockListener blockListener = new SRBlockListener();
			
	private Configuration config;
	
	public void onEnable() {		
		//General registering
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_FROMTO, blockListener, Priority.Highest, this);

		//Settings
		this.config = new Configuration(this);
		blockListener.allowLava = this.config.add("allowLava", false);
		this.config.init();
		
        //final msg
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
	}
	public void onDisable() {
		System.out.println("Stream Remover disabled!");
	}
	
	
}
