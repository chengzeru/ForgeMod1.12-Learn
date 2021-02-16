package chengzeru.MC.Mod.item;

import chengzeru.MC.Mod.MyFirstMod;
import chengzeru.MC.Mod.capability.CapabilityRegistryHandler;
import chengzeru.MC.Mod.capability.DirtBallPower;
import chengzeru.MC.Mod.creativetab.TabFirstMod;
import chengzeru.MC.Mod.entity.EntityDirtBall;
import chengzeru.MC.Mod.network.NetworkRegistryHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemDirtBall extends Item {

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return 100;
    }

    public ItemDirtBall() {
        this.setUnlocalizedName(MyFirstMod.MODID + ".dirtBall");
        this.setRegistryName("dirt_ball");
        this.setMaxStackSize(16);
        this.setCreativeTab(TabFirstMod.TAB_FIRST_MOD);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack item = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote) {
            DirtBallPower power = playerIn.getCapability(CapabilityRegistryHandler.DIRT_BALL_POWER, null);
            float orange = power.getOrangePower(), green = power.getGreenPower(), blue = power.getBluePower();
            if (!playerIn.capabilities.isCreativeMode) {
                if (orange >= 2 && green >= 2 && blue >= 2) {
                    item.shrink(1);
                } else {
                    playerIn.sendMessage(new TextComponentTranslation("message.firstmod.power.doNotHaveEnoughPower"));
                    return ActionResult.newResult(EnumActionResult.PASS, item);
                }

                power.setOrangePower(orange - 2);
                power.setBluePower(blue - 2);
                power.setGreenPower(green - 2);
            }
            //同步数据
            NetworkRegistryHandler.Power.sendClientCustomPacket(playerIn);

            EntityDirtBall entityDirtBall = new EntityDirtBall(worldIn, playerIn);
            EntityDirtBall.ENTITY =playerIn;
            float pitch = playerIn.rotationPitch, yaw = playerIn.rotationYaw;
            entityDirtBall.shoot(playerIn, pitch, yaw, 0.0f, 1.5f, 1.0f);
            worldIn.spawnEntity(entityDirtBall);
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, item);
    }
}
