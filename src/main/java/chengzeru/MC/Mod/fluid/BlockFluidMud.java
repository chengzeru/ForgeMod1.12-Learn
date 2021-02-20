package chengzeru.MC.Mod.fluid;

import chengzeru.MC.Mod.materials.ModMaterials;
import chengzeru.MC.Mod.MyFirstMod;
import chengzeru.MC.Mod.creativetab.TabFirstMod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockFluidMud extends BlockFluidClassic {
    public BlockFluidMud() {
        super(FluidRegistryHandler.fluidMud, ModMaterials.MUD);
        this.setUnlocalizedName(MyFirstMod.MODID+".fluidMudBlock");
        this.setRegistryName("fluid_mud_block");
        this.setCreativeTab(TabFirstMod.TAB_FIRST_MOD);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
