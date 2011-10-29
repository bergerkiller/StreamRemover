package com.bergerkiller.bukkit.sr;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("rawtypes")
public class Configuration {
	
	public Configuration(JavaPlugin plugin) {
		this(plugin.getDataFolder() + File.separator + "config.yml");
	}
	public Configuration(String sourcepath) {
		this(new File(sourcepath));
	}
	public Configuration(File source) {
		this.source = source;
		this.config = new YamlConfiguration();
		this.properties = new ArrayList<Property>();
	}
	
	private ArrayList<Property> properties;
	private YamlConfiguration config;
	private File source;
	
	public <T> Property<T> add(String path, T def) {
		Property<T> prop = new Property<T>();
		prop.path = path;
		prop.value = def;
		this.properties.add(prop);
		return prop;
	}
	
	public void init() {
		this.load();
		this.save();
	}
	@SuppressWarnings("unchecked")
	public void load() {
		try {
			this.config.load(this.source);
			for (Property p : this.properties) {
				p.value = this.config.get(p.path, p.value);
			}
		} catch (Exception ex) {
			System.out.println("[Configuration] Error while loading file '" + this.source + "':");
			ex.printStackTrace();
		}
	}
	public void save() {
		try {
			for (Property p : this.properties) {
				this.config.set(p.path, p.value);
			}
			this.config.save(this.source);
		} catch (Exception ex) {
			System.out.println("[Configuration] Error while saving to file '" + this.source + "':");
			ex.printStackTrace();
		}
	}
	
	public static class Property<T> {
		private String path;
		private T value;
		private Property() {};
		
		public T get() {
			return this.value;
		}
		public void set(T value) {
			this.value = value;
		}

	}

}
