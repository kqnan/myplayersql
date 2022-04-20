package me.kqn.myplayersql.user;

import com.destroystokyo.paper.io.IOUtil;
import me.kqn.myplayersql.util.Logger;
import org.bukkit.entity.Player;


import java.io.*;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class User {

    /** 标识,玩家名字 */
    private String  name;
    private UUID uuid;
    /** 数据是否被锁定 */
    public boolean mLocked;
    /** 数据,key请保证小写 */
    private ConcurrentHashMap<String, byte[]> mData = new ConcurrentHashMap<>();
    /** {@link #mData}的数据序列化缓存 */
    private transient byte[] mDataCache = null;
    public User(Player player){
        name=player.getName();
        uuid=player.getUniqueId();
    }

    /**获取数据,并且压缩 (GZIPOutput)
     * */
    public byte[] getData() {
        if (this.mDataCache == null) {
            ByteArrayOutputStream tBAOStream = new ByteArrayOutputStream();
            DataOutputStream tDOStream = null;
            try {
                tDOStream = new DataOutputStream(new GZIPOutputStream(tBAOStream));
                tDOStream.writeInt(this.mData.size());
                for (Map.Entry<String, byte[]> sEntry : this.mData.entrySet()) {
                    tDOStream.writeUTF(sEntry.getKey().toLowerCase());
                    byte[] tValue = sEntry.getValue();
                    tDOStream.writeInt(tValue.length);
                    if (tValue.length > 0) {
                        tDOStream.write(tValue);
                    }
                }
            } catch (IOException exp) {
                Logger.Log("序列化时发生错误");
                exp.printStackTrace();
            } finally {
                try {
                    tDOStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.mDataCache = tBAOStream.toByteArray();
        }
        return this.mDataCache;
    }


    /**
     * 获取模块序列化的数据,可以一编辑
     *
     * @param pResetCache
     *            是否重置序列化到SQL数据的缓存
     * @return 模块序列化数据
     */
    public Map<String, byte[]> getDataMap(boolean pResetCache) {
        if (pResetCache) {
            this.mDataCache = null;
        }
        return this.mData;
    }

    public boolean isLocked() {
        return this.mLocked;
    }


}
