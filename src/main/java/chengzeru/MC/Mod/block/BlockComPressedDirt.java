package chengzeru.MC.Mod.block;

import chengzeru.MC.Mod.MyFirstMod;
import chengzeru.MC.Mod.creativetab.TabFirstMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockComPressedDirt extends Block {
    public BlockComPressedDirt() {
        super(Material.GROUND);
        this.setUnlocalizedName(MyFirstMod.MODID + ".compressedDirt");
        this.setRegistryName("compressed_dirt");
        this.setHarvestLevel("shovel",0);
        this.setHardness(0.5F);
        this.setCreativeTab(TabFirstMod.TAB_FIRST_MOD);
    }
}
