package chengzeru.MC.Mod.item;

import chengzeru.MC.Mod.MyFirstMod;
import chengzeru.MC.Mod.block.BlockRegistryHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSword;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class ItemRegistryHandler {
    public static final ItemDirtBall DIRT_BALL = new ItemDirtBall();
    public static final ItemBlock ITEM_COMPRESSED_DIRT = withRegistryName(new ItemBlock(BlockRegistryHandler.BLOCK_COMPRESSED_DIRT));
    public static final ItemBlock ITEM_DIRT_COMPRESSOR=withRegistryName(new ItemBlock(BlockRegistryHandler.BLOCK_DIRT_COMPRESSOR));
    public static final Item.ToolMaterial DIRT_TOOL_MATERIAL = EnumHelper.addToolMaterial("DIRT", 1, 44, 3.0f, 1.0f, 5);
    public static final Item.ToolMaterial DEATH_MATERIAL=EnumHelper.addToolMaterial("DEATH",3, 1561, 8.0F, 996.0F, 10);
    public static final ItemDirtPickaxe DIRT_PICKAXE = new ItemDirtPickaxe();
    public static final ItemDeathSword DEATH_SWORD= new ItemDeathSword();
    public static final ItemArmor.ArmorMaterial DIRT_ARMOR_MATERIAL=EnumHelper.addArmorMaterial("DIRT", MyFirstMod.MODID+":dirt",5,new int[]{1,2,2,1},9, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,0);
    public static final ItemDirtArmor DIRT_FEET=new ItemDirtArmor(EntityEquipmentSlot.FEET);
    public static final ItemDirtArmor DIRT_LEGS=new ItemDirtArmor(EntityEquipmentSlot.LEGS);
    public static final ItemDirtArmor DIRT_CHEST=new ItemDirtArmor(EntityEquipmentSlot.CHEST);
    public static final ItemDirtArmor DIRT_HEAD=new ItemDirtArmor(EntityEquipmentSlot.HEAD);

    @SubscribeEvent
    public static void onRegistry(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(DIRT_BALL);
        registry.register(ITEM_COMPRESSED_DIRT);
        registry.register(ITEM_DIRT_COMPRESSOR);
        registry.register(DIRT_PICKAXE);
        registry.register(DIRT_FEET);
        registry.register(DIRT_LEGS);
        registry.register(DIRT_CHEST);
        registry.register(DIRT_HEAD);
        registry.register(DEATH_SWORD);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegistry(ModelRegistryEvent event) {
        registerModel(DIRT_BALL);
        registerModel(ITEM_COMPRESSED_DIRT);
        registerModel(ITEM_DIRT_COMPRESSOR);
        registerModel(DIRT_PICKAXE);
        registerModel(DIRT_FEET);
        registerModel(DIRT_LEGS);
        registerModel(DIRT_CHEST);
        registerModel(DIRT_HEAD);
        registerModel(DEATH_SWORD);

    }

    @SideOnly(Side.CLIENT)
    private static void registerModel(Item item) {
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, model);
    }

    private static ItemBlock withRegistryName(ItemBlock item) {
        item.setRegistryName(item.getBlock().getRegistryName());
        return item;
    }



}
