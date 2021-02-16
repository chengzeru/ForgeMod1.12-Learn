package chengzeru.MC.Mod.villager;

import chengzeru.MC.Mod.MyFirstMod;
import chengzeru.MC.Mod.item.ItemRegistryHandler;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Random;

@Mod.EventBusSubscriber
public class VillagerRegistryHandler {
    public static final VillagerRegistry.VillagerProfession DIRT_WORKER =
            new VillagerRegistry.VillagerProfession(MyFirstMod.MODID + ":dirt_worker",
                    MyFirstMod.MODID + ":textures/entity/villager/dirt_worker.png",
                    MyFirstMod.MODID + ":textures/entity/zombie_villager/zombie_dirt_worker.png");
    public static final VillagerRegistry.VillagerCareer DIRT_WORKER_CAREER =
            new VillagerRegistry.VillagerCareer(DIRT_WORKER, MyFirstMod.MODID + ".dirtWorker");

    @SubscribeEvent
    public static void onRegistry(RegistryEvent.Register<VillagerRegistry.VillagerProfession> event) {
        IForgeRegistry<VillagerRegistry.VillagerProfession> registry = event.getRegistry();
        DIRT_WORKER_CAREER.addTrade(1,
                new EntityVillager.EmeraldForItems(ItemRegistryHandler.DIRT_BALL,
                        new EntityVillager.PriceInfo(8, 10)),
                new EntityVillager.ListItemForEmeralds(Item.getItemFromBlock(Blocks.DIRT),
                        new EntityVillager.PriceInfo(1, 2)));
        DIRT_WORKER_CAREER.addTrade(2,
                new DirtBallToDirtPickaxe(new EntityVillager.PriceInfo(3,6)));
        registry.register(DIRT_WORKER);
    }

    private static class DirtBallToDirtPickaxe implements EntityVillager.ITradeList{
        private final EntityVillager.PriceInfo priceInfo;
        private DirtBallToDirtPickaxe(EntityVillager.PriceInfo dirtPickaxePriceInfo){
            this.priceInfo=dirtPickaxePriceInfo;
        }
        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
            int emeraldAmount =this.priceInfo.getPrice(random);
            recipeList.add(new MerchantRecipe(
                    new ItemStack(ItemRegistryHandler.DIRT_BALL,3,0),
                    new ItemStack(Items.EMERALD,emeraldAmount,0),
                    new ItemStack(ItemRegistryHandler.DIRT_PICKAXE)
            ));
        }
    }
}
