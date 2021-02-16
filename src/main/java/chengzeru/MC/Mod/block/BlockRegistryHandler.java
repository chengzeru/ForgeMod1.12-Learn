package chengzeru.MC.Mod.block;

import akka.io.Tcp;
import chengzeru.MC.Mod.tileentity.TileEntityDirtCompressor;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import scala.reflect.internal.Trees;

@Mod.EventBusSubscriber
public class BlockRegistryHandler {
    public static final BlockComPressedDirt BLOCK_COMPRESSED_DIRT = new BlockComPressedDirt();
    public static final BlockDirtCompressor BLOCK_DIRT_COMPRESSOR = new BlockDirtCompressor();

    @SubscribeEvent
    public static void onRegistry(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(BLOCK_COMPRESSED_DIRT);
        registry.register(BLOCK_DIRT_COMPRESSOR);
        TileEntity.register(TileEntityDirtCompressor.ID, TileEntityDirtCompressor.class);
    }
}
