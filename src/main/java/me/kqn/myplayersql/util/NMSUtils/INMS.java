package me.kqn.myplayersql.util.NMSUtils;

import com.sun.prism.impl.paint.PaintUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public abstract class INMS {
    /**@return 返回玩家的nbt数据，生命值，药水，背包等..
     *
     * */
    abstract public String getNBTData(Player player);
    /**写入nbt数据给玩家
     * @return 是否写入成功
     * */
    abstract public boolean writeNBTData(String str_nbt, Player player);

    /**获取本服务端版本的NMS工具
     * */
    @Nullable
    public static INMS getInstance(){
        String version=VersionUtil.getNetMinecraftServerVersion();
        System.out.println(version);
        if(version.equals("1_16_R3")){
            return new NMS1_16_R3();
        }
        return null;
    }
}
