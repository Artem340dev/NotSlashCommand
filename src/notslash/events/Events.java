package notslash.events;

import notslash.main.SpigotPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Events implements Listener {
    private SpigotPlugin plugin;

    public Events(SpigotPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public synchronized void onCmd(AsyncPlayerChatEvent e) {
        Map<String, String[]> cmds = Bukkit.getCommandAliases();
        for (Map.Entry<String, String[]> entry : cmds.entrySet()) {
            if (entry.getKey().equals(e.getMessage().split(" ")[0])) {
                e.setCancelled(true);
                Player p = e.getPlayer();
                CraftPlayer player = (CraftPlayer) p;
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6&lCMDS&7] &aУспешно!"));
                List<String> list = plugin.config.getStringList("logs");
                list.add("[LOGS] [" + LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getMonthValue() + "/" + LocalDate.now().getYear() + "] [" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + "]" + p.getName() + " used command: " + e.getMessage());
                plugin.config.set("logs", list);
                plugin.saveLogs();
                player.getHandle().playerConnection.chat("/"+e.getMessage(), true);
            }
        }
    }
}