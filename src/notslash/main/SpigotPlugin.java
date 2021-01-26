package notslash.main;

import notslash.commands.Cmdlogs;
import notslash.events.Events;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class SpigotPlugin extends JavaPlugin {
    public File logs;
    public FileConfiguration config;

    public void onEnable() {
        getCommand("logs").setExecutor(new Cmdlogs(this));
        Bukkit.getPluginManager().registerEvents(new Events(this), this);
        logs = new File(getDataFolder() + File.separator + "logs.yml");
        if (!logs.exists()) {
            try {
                logs.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(logs);
    }

    public void saveLogs() {
        try {
            config.save(logs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}