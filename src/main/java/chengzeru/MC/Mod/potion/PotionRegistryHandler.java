package chengzeru.MC.Mod.potion;

import chengzeru.MC.Mod.MyFirstMod;
import chengzeru.MC.Mod.item.ItemRegistryHandler;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class PotionRegistryHandler {
    public static final PotionDirtProtection POTION_DIRT_PROTECTION = new PotionDirtProtection();
    public static final PotionType POTION_TYPE_DIRT_PROTECTION = getDirtProtection(0, 3600, "dirt_protection");
    public static final PotionType POTION_TYPE_LONG_DIRT_PROTECTION = getDirtProtection(0, 9600, "dirt_long_protection");
    public static final PotionType POTION_TYPE_STRONG_DIRT_PROTECTION = getDirtProtection(1, 1800, "dirt_strong_protection");

    @SubscribeEvent
    public static void onPotionRegistry(RegistryEvent.Register<Potion> event) {
        IForgeRegistry<Potion> registry = event.getRegistry();
        registry.register(POTION_DIRT_PROTECTION);
    }

    @SubscribeEvent
    public static void onPotionTypeRegistry(RegistryEvent.Register<PotionType> event) {
        IForgeRegistry<PotionType> registry=event.getRegistry();
        registry.register(POTION_TYPE_DIRT_PROTECTION);
        registry.register(POTION_TYPE_LONG_DIRT_PROTECTION);
        registry.register(POTION_TYPE_STRONG_DIRT_PROTECTION);
    }

    private static PotionType getDirtProtection(int level, int duration, String str) {
        PotionType dirtProtection = new PotionType(MyFirstMod.MODID + ".dirtProtection", new PotionEffect(POTION_DIRT_PROTECTION, duration, level));
        dirtProtection.setRegistryName(str);
        return dirtProtection;
    }
    public static void register(){
        PotionHelper.addMix(POTION_TYPE_DIRT_PROTECTION, Items.REDSTONE,POTION_TYPE_LONG_DIRT_PROTECTION);
        PotionHelper.addMix(POTION_TYPE_DIRT_PROTECTION,Items.GLOWSTONE_DUST,POTION_TYPE_STRONG_DIRT_PROTECTION);
        PotionHelper.addMix(PotionTypes.AWKWARD, ItemRegistryHandler.ITEM_COMPRESSED_DIRT,POTION_TYPE_DIRT_PROTECTION);
    }
}
