package chengzeru.MC.Mod.fluid;

import chengzeru.MC.Mod.materials.ModMaterials;
import chengzeru.MC.Mod.MyFirstMod;
import chengzeru.MC.Mod.creativetab.TabFirstMod;
import chengzeru.MC.Mod.potion.PotionRegistryHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockFluidMud extends BlockFluidClassic {
    public BlockFluidMud() {
        super(FluidRegistryHandler.fluidMud, ModMaterials.MUD);
        this.setUnlocalizedName(MyFirstMod.MODID + ".fluidMudBlock");
        this.setRegistryName("fluid_mud_block");
        this.setCreativeTab(TabFirstMod.TAB_FIRST_MOD);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (entityIn instanceof EntityLivingBase && ((EntityLivingBase) entityIn).isPotionActive(PotionRegistryHandler.POTION_DIRT_PROTECTION)) {
            return;
        }
        entityIn.setInWeb();
    }
}
