package chengzeru.MC.Mod.item;

import chengzeru.MC.Mod.MyFirstMod;
import chengzeru.MC.Mod.creativetab.TabFirstMod;
import chengzeru.MC.Mod.potion.PotionRegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class ItemDirtAppleFood extends ItemFood {
    private final Random r = new Random();

    public ItemDirtAppleFood() {
        super(4, 1.2f, false);
        this.setCreativeTab(TabFirstMod.TAB_FIRST_MOD);
        this.setAlwaysEdible();
        this.setRegistryName("dirt_apple");
        this.setUnlocalizedName(MyFirstMod.MODID + ".dirtApple");
        this.setHasSubtypes(true);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            if (stack.getMetadata() > 0) {
                player.addPotionEffect(new PotionEffect(PotionRegistryHandler.POTION_DIRT_PROTECTION, 1200, 1));
                int i = r.nextInt(2);
                if (i == 1) {
                    player.addPotionEffect(new PotionEffect(MobEffects.POISON, 200, 1));
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack) {
        return super.hasEffect(stack) || stack.getMetadata() > 0;
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            items.add(new ItemStack(this, 1, 0));
            items.add(new ItemStack(this, 1, 1));
        }
    }
}
