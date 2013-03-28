package uk.co.chieloos.wookieeitemnames;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;

public final class ItemNames {

    private Logger logger;
    private FileConfiguration INConfig = null;
    private FileConfiguration cfg;
    private File INConfigFile = null;

    public ItemNames(Logger logger) {
        cfg = getINConfig();
        this.logger = logger;
        if (logger == null) {
            logger.info("sdf");
        }
    }

    /**
     * Returns an item String based on WookieeItemNames config.yml
     * <p> 
     * If an itemname:datavalue is not found it will return null, otherwise
     * the String paired with the WookieeItemNames config.yml
     * @param itemname a bukkit itemname usually given as ITEM_NAME
     * @param dataval the data value for an item, 0 if an itemname doesn't 
     * have a data value
     * @return a string associated with the itemname data value pair or null 
     * if not found
     */
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
    /**
     * Returns an item String based on WookieeItemNames config.yml
     * <p> 
     * If an itemid:datavalue is not found it will return null, otherwise
     * the String paired with the WookieeItemNames config.yml
     * @param itemid an item id
     * @param dataval the data value for an item, 0 if an itemname doesn't 
     * have a data value
     * @return a string associated with the itemid data value pair or if 
     * not found, attempt to return Material.name(), else null
     */
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
    /**
     * Returns an Enchant String based on WookieeItemNames config.yml
     * <p> 
     * If an enchant is not found it will return null, otherwise
     * the String paired with the WookieeItemNames config.yml
     * @param enchantment the name given from Enchantment.getName()
     * @return a String associated with the enchant or null 
     * if not found
     */
    public String getEnchantName(String enchantment) {
        String name;
        name = cfg.getString("enchants." + enchantment + ".name");
        if (name == null) {
            return null;
        } else {
            //logger.info("Found");
            return name;
        }
    }
    
    /**
     * Returns an Enchant String based on WookieeItemNames config.yml
     * <p> 
     * If an enchant is not found it will return null, otherwise
     * the String paired with the WookieeItemNames config.yml
     * @param enchantment an Enchantment object
     * @return a String associated with the enchant or null if not found
     */
    public String getEnchantName(Enchantment enchantment) {
        String name;
        String enchant = enchantment.getName();
        name = cfg.getString("enchants." + enchant + ".name");
        if (name == null) {
            return null;
        } else {
            //logger.info("Found");
            return name;
        }
    }
    /**
     * Reloads the item / enchant config file
     * <p> 
     * Should be used after an edit to the config while server is running
     */
    public void reloadINConfig() {
        if (INConfigFile == null) {
            INConfigFile = new File("plugins/WookieeItemNames", "config.yml");
        }
        INConfig = YamlConfiguration.loadConfiguration(INConfigFile);
    }
    /**
     * Returns the FileConfiguration for config.yml
     * <p> 
     * Used to edit the config.yml
     * @return FileConfiguration for this plugin
     */
    public FileConfiguration getINConfig() {
        if (INConfig == null) {
            this.reloadINConfig();
        }
        return INConfig;
    }
}
