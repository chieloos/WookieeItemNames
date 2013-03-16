package uk.co.chieloos.wookieeitemnames;

import java.io.File;

public class WookieeItemNamesConfig {

    protected WookieeItemNames plugin;

    public WookieeItemNamesConfig(WookieeItemNames plugin) {
        this.plugin = plugin;
    }

    protected void loadConfig() {
        File file = new File(plugin.getDataFolder() + File.separator + "config.yml");
        if (!file.exists()) {
            plugin.getConfig().options().copyDefaults(true);
            plugin.saveConfig();
        }
    }
}
