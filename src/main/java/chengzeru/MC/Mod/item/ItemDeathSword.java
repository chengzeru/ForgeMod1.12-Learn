package chengzeru.MC.Mod.item;

import chengzeru.MC.Mod.MyFirstMod;
import chengzeru.MC.Mod.creativetab.TabFirstMod;
import net.minecraft.item.ItemSword;

public class ItemDeathSword extends ItemSword {
    public ItemDeathSword() {
        super(ItemRegistryHandler.DEATH_MATERIAL);
        this.setUnlocalizedName(MyFirstMod.MODID + ".deathSword");
        this.setCreativeTab(TabFirstMod.TAB_FIRST_MOD);
        this.setRegistryName("death_sword");
    }
}
