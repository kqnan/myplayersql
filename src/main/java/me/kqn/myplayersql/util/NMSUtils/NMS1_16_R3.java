package me.kqn.myplayersql.util.NMSUtils;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.MojangsonParser;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMS1_16_R3 extends INMS {

    @Override
    public String getNBTData(Player player) {
        EntityPlayer nmsplayer=((CraftPlayer)player).getHandle();
        NBTTagCompound compound = new NBTTagCompound();
        nmsplayer.save(compound);

        return compound.toString();
    }

    @Override
    public boolean writeNBTData(String str_nbt, Player player) {
        NBTTagCompound nbtTagCompound = null;
        try {
            nbtTagCompound = MojangsonParser.parse(str_nbt);
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
            return false;
        }
        EntityPlayer nmsplayer=((CraftPlayer)player).getHandle();
        nmsplayer.load(nbtTagCompound);
        return true;
    }
}
