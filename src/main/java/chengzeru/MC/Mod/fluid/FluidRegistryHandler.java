package chengzeru.MC.Mod.fluid;

import chengzeru.MC.Mod.MyFirstMod;
import chengzeru.MC.Mod.block.BlockRegistryHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FluidRegistryHandler {
    public static Fluid fluidMud = new MudFluid();

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        registerFluidRender(BlockRegistryHandler.BLOCK_FLUID_MUD, "fluid_mud");
    }

    @SideOnly(Side.CLIENT)
    public static void registerFluidRender(BlockFluidBase blockFluid, String blockStateName) {
        final String location = MyFirstMod.MODID + ":" + blockStateName;
        final Item itemFluid = Item.getItemFromBlock(blockFluid);
        ModelLoader.setCustomMeshDefinition(itemFluid, stack -> new ModelResourceLocation(location, "fluid"));
        ModelLoader.setCustomStateMapper(blockFluid, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(location, "fluid");
            }
        });
    }
}
