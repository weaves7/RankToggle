package org.makershaven.ranktoggle;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class RankToggle extends JavaPlugin {

    private PluginDescriptionFile pdFile = getDescription();
    private Logger log = this.getLogger();

    String PERMS_PLUGIN;
    String PARENT_GROUP;


    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("LuckPerms") != null) {
            log.info("LuckPerms found, using lp command");
            PERMS_PLUGIN = "lp";
            PARENT_GROUP = "parent";
            finishEnable();
        }
        else if (getServer().getPluginManager().getPlugin("PermissionsEx") != null) {
            log.info("PermissionsEx found, using pex command");
            PERMS_PLUGIN = "pex";
            PARENT_GROUP = "group";
            finishEnable();
        }
        else {
            log.warning("No compatible permissions plugin found!");
            log.warning("Please install LuckPerms or PermissionsEx!");
            this.getServer().getPluginManager().disablePlugin(this);
        }

    }

    private void finishEnable() {
        getCommand("rankon").setExecutor(new Commands(this));
        getCommand("rankoff").setExecutor(new Commands(this));

    }


}
