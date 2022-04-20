package me.kqn.myplayersql.api;

import me.kqn.myplayersql.Myplayersql;

import java.util.LinkedHashMap;

public class MPSAPI {
    /** 插件实例 */
    private static Myplayersql mPlugin = null;
    /** 注册的模块 */
    private static LinkedHashMap<String, IModule> mRegistedModels = new LinkedHashMap<>();


    /**
     * 获取插件实例
     *
     * @return 插件实例
     * @throws IllegalStateException
     *             插件未实例化
     */
    public static Myplayersql getPlugin() {
        synchronized(MPSAPI.class) {
            if (MPSAPI.mPlugin == null) {
                MPSAPI.mPlugin = Myplayersql.getInstance();
            }
        }
        return MPSAPI.mPlugin;
    }
    /**
     * 注册一个数据模块
     *
     * @param pModel
     *            模块
     */
    public static void registerModel(IModule pModel) {
        if(pModel.getModelId()==null){
        System.out.println("模块名称不能为空");
        }
        String tLowerCase = pModel.getModelId().toLowerCase();
        if(mRegistedModels.containsKey(tLowerCase)){
            System.out.println( "模块名称 " + pModel.getModelId() + " 已经被注册");
        }
        MPSAPI.mRegistedModels.put(tLowerCase, pModel);
    }
    public static void checkModels(boolean pNotify){
        //待实现
    }
    public static IModule[] getEnableModel() {
        IModule[] enablemodule=new IModule[mRegistedModels.size()];
        int i=0;
        for (String str:mRegistedModels.keySet()){
            enablemodule[i++]=mRegistedModels.get(str);
        }
        return enablemodule;
    }
}
