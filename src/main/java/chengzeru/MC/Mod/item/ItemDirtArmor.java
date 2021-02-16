package chengzeru.MC.Mod.item;

import chengzeru.MC.Mod.MyFirstMod;
import chengzeru.MC.Mod.creativetab.TabFirstMod;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemDirtArmor extends ItemArmor {
    public ItemDirtArmor(EntityEquipmentSlot equipmentSlot) {
        super(ItemRegistryHandler.DIRT_ARMOR_MATERIAL, 0, equipmentSlot);
        this.setCreativeTab(TabFirstMod.TAB_FIRST_MOD);
        this.setRegistryName("dirt_armor_" + equipmentSlot.getName());
        this.setUnlocalizedName(MyFirstMod.MODID + ".dirtArmor." + equipmentSlot.getName());
    }
}
