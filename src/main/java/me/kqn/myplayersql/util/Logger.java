package me.kqn.myplayersql.util;

import org.bukkit.Bukkit;

public class Logger {
    public static  void Log(String msg){
        Bukkit.getServer().getLogger().info("[Myplayersql]"+msg);
    }
}
