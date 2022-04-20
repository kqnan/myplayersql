package me.kqn.myplayersql.util.NMSUtils;

import org.bukkit.Bukkit;
import org.bukkit.Server;

import java.util.logging.Logger;

public class VersionUtil {
    static {
        String bukkitVersion = Bukkit.getBukkitVersion();
        if(bukkitVersion.contains("-pre") || bukkitVersion.contains("-rc")) {
            Logger logger = Bukkit.getLogger();
            logger.warning("[myplayersql] 你正在使用'-pre' 或 '-rc' 版本的spigot");
            logger.warning("[myplayersql] 预览版本可能会发生bug");
        }
    }

    /**
     * @return 返回服务端版本(Example: 1.16.5)
     */
    public static String getMinecraftVersion() {
        String bukkitVersion = Bukkit.getBukkitVersion();
        int firstDash = bukkitVersion.indexOf('-');
        return bukkitVersion.substring(0, firstDash);
    }

    /**
     * @return 返回服务端NMS版本 (Example: 1_16_R3)
     */
    public static String getNetMinecraftServerVersion() {
        Server server = Bukkit.getServer();
        Class<? extends Server> serverClass = server.getClass();
        Package serverPackage = serverClass.getPackage();
        String serverPackageName = serverPackage.getName();
        int lastPeriodIndex = serverPackageName.lastIndexOf('.');
        int nextIndex = (lastPeriodIndex + 2);
        return serverPackageName.substring(nextIndex);
    }

    /**
     * @return 返回主版本号 (Example: 1.16)
     */
    public static String getMajorMinorVersion() {
        String minecraftVersion = getMinecraftVersion();
        int lastPeriodIndex = minecraftVersion.lastIndexOf('.');
        return (lastPeriodIndex < 2 ? minecraftVersion : minecraftVersion.substring(0, lastPeriodIndex));
    }


}
