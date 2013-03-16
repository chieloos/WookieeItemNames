package uk.co.chieloos.wookieeitemnames;

import java.io.File;
import java.util.logging.Level;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class WookieeItemNames extends JavaPlugin {

    protected final WookieeItemNamesConfig wcfg = new WookieeItemNamesConfig(this);
    
    protected PluginManager manager;
    protected PluginDescriptionFile pdf;
    protected ItemNames win;
    

    @Override
    public void onEnable() {
        win = new ItemNames(getLogger());
        File file = new File(getDataFolder() + File.separator + "config.yml");
        if (!file.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        } 
        pdf = this.getDescription();
        getLogger().log(Level.INFO, "Enabled {0} v{1}", new Object[]{pdf.getName(), pdf.getVersion()});
        manager = this.getServer().getPluginManager();
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Disabled {0} v{1}", new Object[]{pdf.getName(), pdf.getVersion()});
    }
}
