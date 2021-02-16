package chengzeru.MC.Mod.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class FirstModGuiHandler implements IGuiHandler {
    public static final int DIRT_COMPRESSOR = 1;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == DIRT_COMPRESSOR) {
            return new ContainerDirtCompressor(player, world, x, y, z);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == DIRT_COMPRESSOR) {
            return new GuiDirtCompressor(player, world, x, y, z);
        }
        return null;
    }
}
