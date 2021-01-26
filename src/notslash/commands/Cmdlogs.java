package notslash.commands;

import notslash.main.SpigotPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Cmdlogs implements CommandExecutor {
    private SpigotPlugin plugin;

    public Cmdlogs(SpigotPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("notslashcommands.cmdlogs")) {
            for (String s : plugin.config.getStringList("logs")) {
                sender.sendMessage(ChatColor.GREEN + s);
            }
            List<String> logs = plugin.config.getStringList("logs");
            logs.clear();
            plugin.config.set("logs", logs);
            return true;
        } else return false;
    }
}