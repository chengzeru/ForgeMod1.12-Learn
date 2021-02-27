package chengzeru.MC.Mod.crafting;

import chengzeru.MC.Mod.config.FirstmodConfig;
import chengzeru.MC.Mod.item.ItemRegistryHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class FurnaceRecipeRegistryHandler {
    public static void register() {
        GameRegistry.addSmelting(ItemRegistryHandler.DIRT_PICKAXE, new ItemStack(ItemRegistryHandler.DIRT_BALL), 0.1f);
        GameRegistry.addSmelting(ItemRegistryHandler.DIRT_HEAD, new ItemStack(ItemRegistryHandler.DIRT_BALL), 0.1f);
        GameRegistry.addSmelting(ItemRegistryHandler.DIRT_CHEST, new ItemStack(ItemRegistryHandler.DIRT_BALL), 0.1f);
        GameRegistry.addSmelting(ItemRegistryHandler.DIRT_LEGS, new ItemStack(ItemRegistryHandler.DIRT_BALL), 0.1f);
        GameRegistry.addSmelting(ItemRegistryHandler.DIRT_FEET, new ItemStack(ItemRegistryHandler.DIRT_BALL), 0.1f);
    }

    @SubscribeEvent
    public static void onFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        ResourceLocation registryName = event.getItemStack().getItem().getRegistryName();
        String registryNameResourceDomain = registryName.getResourceDomain();
        String registryNameResourcePath = registryName.getResourcePath();
        if ("minecraft".equals(registryNameResourceDomain) && "dirt".equals(registryNameResourcePath)) {
            event.setBurnTime(FirstmodConfig.dirtBurnTime);
        } else if ("forge".equals(registryNameResourceDomain) && "bucketfilled".equals(registryNameResourcePath)) {
            event.setBurnTime(2000);
        }
    }
}
