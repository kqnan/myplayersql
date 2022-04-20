package me.kqn.myplayersql;

import me.kqn.myplayersql.api.MPSAPI;
import me.kqn.myplayersql.modules.M_Minecraft;
import me.kqn.myplayersql.util.NMSUtils.INMS;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.MojangsonParser;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Base64;

public final class Myplayersql extends JavaPlugin {
    /**获取玩家nbt
     * @param player:玩家
     *
     * */
    String getNBTData(Player player){
        EntityPlayer nmsplayer=((CraftPlayer)player).getHandle();
        NBTTagCompound compound = new NBTTagCompound();
        nmsplayer.save(compound);
        System.out.println(compound.toString());//输出整个nbt（很长），发现是明文的，占空间大
        System.out.println(compound.getKeys());//输出nbt的每个键，发现有Inventory,Health,FoodLevel等键
        //教程上是直接调用a_（）。但这里失败了，原因是saveID返回了null。但是a_()只是相当于 在save()的基础上加了个id键
        return compound.toString();
    }

    /**把nbt数据写入到玩家
     * @param str_nbt:
     *               字符出nbt
     * @param player:
     *              玩家
     * */
    void writeNBTData(String str_nbt,Player player)throws  Exception{
        NBTTagCompound nbtTagCompound = MojangsonParser.parse(str_nbt);
        EntityPlayer nmsplayer=((CraftPlayer)player).getHandle();
        nmsplayer.load(nbtTagCompound);
    }
    private static Myplayersql instance;
    public static Myplayersql getInstance(){
        return instance;
    }
    private void registerDefaultModules(){
        MPSAPI.registerModel(new M_Minecraft());
    }
    @Override
    public void onEnable() {
        instance=this;
        registerDefaultModules();//注册默认模块 例如:Minecraft原版
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
