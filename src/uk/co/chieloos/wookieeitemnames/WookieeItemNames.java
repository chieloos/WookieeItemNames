package uk.co.chieloos.wookieeitemnames;

import java.io.File;
import java.util.logging.Level;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class WookieeItemNames extends JavaPlugin {

    private PluginManager manager;
    private PluginDescriptionFile pdf;
    private ItemNames win;

    @Override
    public void onEnable() {
        loadConfig();
        win = new ItemNames(getLogger());
        pdf = this.getDescription();
        getLogger().log(Level.INFO, "Enabled {0} v{1}", new Object[]{pdf.getName(), pdf.getVersion()});
        manager = this.getServer().getPluginManager();
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Disabled {0} v{1}", new Object[]{pdf.getName(), pdf.getVersion()});
    }

    private void loadConfig() {
        File file = new File(getDataFolder() + File.separator + "config.yml");
        if (!file.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }
}
