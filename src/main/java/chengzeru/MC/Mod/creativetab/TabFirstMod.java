package chengzeru.MC.Mod.creativetab;

import chengzeru.MC.Mod.item.ItemRegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabFirstMod extends CreativeTabs {
    public static final TabFirstMod TAB_FIRST_MOD = new TabFirstMod();

    public TabFirstMod() {
        super("firstmod");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemRegistryHandler.ITEM_COMPRESSED_DIRT);
    }

    @Override
    public boolean hasSearchBar(){
        return true;
    }

    @Override
    public int getSearchbarWidth() {
        return 45;
    }

    @Override
    public String getBackgroundImageName() {
        return "firstmod.png";
    }
}
