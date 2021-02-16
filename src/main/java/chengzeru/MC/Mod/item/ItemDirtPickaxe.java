package chengzeru.MC.Mod.item;

import chengzeru.MC.Mod.MyFirstMod;
import chengzeru.MC.Mod.creativetab.TabFirstMod;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

import javax.swing.text.TabableView;

public class ItemDirtPickaxe extends ItemPickaxe {
    protected ItemDirtPickaxe() {
        super(ItemRegistryHandler.DIRT_TOOL_MATERIAL);
        this.setUnlocalizedName(MyFirstMod.MODID + ".dirtPickaxe");
        this.setCreativeTab(TabFirstMod.TAB_FIRST_MOD);
        this.setRegistryName("dirt_pickaxe");
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        Block block = state.getBlock();
        float speed = super.getDestroySpeed(stack, state);
        return (block == Blocks.DIRT || block == Blocks.GLASS) ? speed * 10 : speed;
    }
}
