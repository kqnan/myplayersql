package me.kqn.myplayersql.api;

import org.bukkit.entity.Player;

import java.util.Map;

public interface IModule {
    /**
     * 获取模块ID,用于注册,以及序列化时使用
     *
     * @return 模块标识,不能重复
     */
    public String getModelId();

    /**
     * 获取模块描述
     *
     * @return 描述
     */
    public String getDesc();
    /**
     * 获取该模块用户的序列化数据,用于存储
     *
     * @param pPlayer
     *            来源于谁的数据
     * @return 序列化的数据
     * @throws Exception
     *             序列化数据时发生异常
     */
    public byte[] getData(Player pPlayer) throws Exception;

    /**
     * 使用序列化的数据还原玩家数据
     *
     * @param pPlayer
     *            要还原的玩家
     * @param pData
     *            数据
     * @throws Exception
     *             反序列化与设置数据过程中发生异常
     */
    public void restore(Player pPlayer, byte[] pData) throws Exception;
}
