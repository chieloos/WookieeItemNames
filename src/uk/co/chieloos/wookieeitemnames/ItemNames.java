package uk.co.chieloos.wookieeitemnames;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public final class ItemNames {

    private Logger logger;
    private FileConfiguration INConfig = null;
    private FileConfiguration cfg;
    private File INConfigFile = null;

    public ItemNames(Logger logger) {
        cfg = getINConfig();
        this.logger = logger;
        if(logger == null){
            logger.info("sdf");
        }
    }
    public String getItemName(String itemname, int dataval) {
        Material itemmat = Material.getMaterial(itemname);
        if (itemmat != null) {
            String name = getItemName(itemmat.getId(), dataval);
            if (name != null) {
                //logger.info("Found");
                return name;
            }
        }
        return null;
    }
    public String getItemName(int itemid, int dataval) {
        String name;
        name = cfg.getString("items." + itemid + "." + dataval + ".name");
        if (name == null) {
            Material itemmat = Material.getMaterial(itemid);
            if (itemmat == null) {
                return null;
            } else {
                //logger.info("Found");
                return itemmat.name();
            }
        } else {
            //logger.info("Found");
            return name;
        }
    }
    public String getEnchantName(String enchant) {
        String name;
        name = cfg.getString("enchants." + enchant + ".name");
        if (name == null) {
            return null;
        } else {
            //logger.info("Found");
            return name;
        }
    }
    public void reloadINConfig() {
        if (INConfigFile == null) {
            INConfigFile = new File("plugins/WookieeItemNames", "config.yml");
        }
        INConfig = YamlConfiguration.loadConfiguration(INConfigFile);
    }
    public FileConfiguration getINConfig() {
        if (INConfig == null) {
            this.reloadINConfig();
        }
        return INConfig;
    }
    public void saveINConfig() {
        if (INConfig == null || INConfigFile == null) {
            return;
        }
        try {
            getINConfig().save(INConfigFile);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Could not save config to " + INConfigFile, ex);
        }
    }
}
