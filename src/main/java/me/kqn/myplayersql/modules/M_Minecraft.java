package me.kqn.myplayersql.modules;

import me.kqn.myplayersql.api.IModule;
import me.kqn.myplayersql.util.NMSUtils.INMS;
import org.bukkit.entity.Player;

public class M_Minecraft implements IModule {
    public static final String ID = "Minecraft";
    @Override
    public String getModelId() {
        return ID;
    }

    @Override
    public String getDesc() {
        return "Minecraft原版数据";
    }

    @Override
    public byte[] getData(Player pPlayer) throws Exception {
        INMS nms=INMS.getInstance();
        if(nms==null)throw new NullPointerException();
        return nms.getNBTData(pPlayer).getBytes();
    }

    @Override
    public void restore(Player pPlayer, byte[] pData) throws Exception {
        String str_nbt=new String(pData);
        INMS nms=INMS.getInstance();
        if(nms==null)throw new NullPointerException();
        nms.writeNBTData(str_nbt,pPlayer);
    }
}
