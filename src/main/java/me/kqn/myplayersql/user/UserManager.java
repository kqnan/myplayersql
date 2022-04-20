package me.kqn.myplayersql.user;

import me.kqn.myplayersql.api.IModule;
import me.kqn.myplayersql.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import me.kqn.myplayersql.api.MPSAPI;
import java.util.Map;

public class UserManager {
    public User getUserData(Player pPlayer, boolean pCloseInv) {
        if (!pPlayer.isOnline()) return null;
        if (pCloseInv) {
            InventoryView tView = pPlayer.getOpenInventory();
            if (tView != null && !tView.getCursor().getType().isAir()) {
                ItemStack tCursor = tView.getCursor().clone();
                tView.setCursor(new ItemStack(Material.AIR));
                pPlayer.getInventory().addItem(tCursor);
                pPlayer.closeInventory();
            }
            //if (tView != null && PlayerListener.ClosedInvs.add(tView.getTopInventory())) {
             //   pPlayer.closeInventory();
           // }
        }

        User tUser = new User(pPlayer);
        Map<String, byte[]> tDatas = tUser.getDataMap(true);
        for (IModule sModel : MPSAPI.getEnableModel()) {
            try {
                tDatas.put(sModel.getModelId().toLowerCase(), sModel.getData(pPlayer));
            } catch (Throwable exp) {
                Logger.Log("模块:"+sModel.getModelId()+"序列化错误");
                exp.printStackTrace();
            }
        }

        return tUser;
    }

}
