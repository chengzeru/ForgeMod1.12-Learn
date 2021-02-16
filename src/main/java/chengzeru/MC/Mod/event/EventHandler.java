package chengzeru.MC.Mod.event;

import chengzeru.MC.Mod.MyFirstMod;
import chengzeru.MC.Mod.capability.CapabilityRegistryHandler;
import chengzeru.MC.Mod.capability.DirtBallPower;
import chengzeru.MC.Mod.command.CommandLoader;
import chengzeru.MC.Mod.enchantment.EnchantmentRegistryHandler;
import chengzeru.MC.Mod.entity.EntityDirtBallKing;
import chengzeru.MC.Mod.network.NetworkRegistryHandler;
import chengzeru.MC.Mod.potion.PotionRegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.DifficultyInstance;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber
public class EventHandler {
    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof EntityPlayer && !entity.world.isRemote) {
            entity.sendMessage(new TextComponentTranslation("message.firstmod.welcome", MyFirstMod.NAME, entity.getName()));
            //同步数据
            NetworkRegistryHandler.Power.sendClientCustomPacket((EntityPlayer) entity);
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        Entity source = event.getSource().getImmediateSource();
        if (source instanceof EntityPlayer && !source.world.isRemote) {
            EntityPlayer player = (EntityPlayer) source;
            ItemStack heldItemMainHand = player.getHeldItemMainhand();
            int level = EnchantmentHelper.getEnchantmentLevel(
                    EnchantmentRegistryHandler.EXPLOSION, heldItemMainHand
            );
            if (level > 0) {
                Entity target = event.getEntity();
                target.world.createExplosion(target,
                        target.posX, target.posY, target.posZ, 2 * level, false);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        DamageSource source = event.getSource();
        if ("fall".equals(source.getDamageType())) {
            EntityLivingBase target = event.getEntityLiving();
            Potion potion = PotionRegistryHandler.POTION_DIRT_PROTECTION;
            if (target.isPotionActive(potion)) {
                PotionEffect effect = target.getActivePotionEffect(potion);
                BlockPos pos = new BlockPos(target.posX, target.posY - 0.2, target.posZ);
                Block block = target.world.getBlockState(pos).getBlock();
                if (block == Blocks.DIRT || block == Blocks.GRASS) {
                    event.setAmount(effect.getAmplifier() > 0 ? 0 : event.getAmount() / 2);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityStruckByLightning(EntityStruckByLightningEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof EntitySlime && !entity.world.isRemote && !entity.isDead) {
            EntityDirtBallKing newEntity = new EntityDirtBallKing(entity.world);
            newEntity.setPosition(entity.posX, entity.posY, entity.posZ);
            DifficultyInstance difficulty = entity.world.getDifficultyForLocation(new BlockPos(entity));
            newEntity.onInitialSpawn(difficulty, null);
            if (entity.hasCustomName()) {
                newEntity.setAlwaysRenderNameTag(entity.getAlwaysRenderNameTag());
                newEntity.setCustomNameTag(entity.getCustomNameTag());
            }

            entity.world.spawnEntity(newEntity);
            entity.setDead();

            event.setCanceled(true);
        }
    }

    private static TextComponentTranslation addPower(EntityDirtBallKing entity, DirtBallPower power, float amount) {
        byte color = entity.getColor();
        if (color == 2) {
            power.setGreenPower(power.getGreenPower() + amount);
            return new TextComponentTranslation("message.firstmod.power.add.green", amount);
        }
        if (color == 1) {
            power.setBluePower(power.getBluePower() + amount);
            return new TextComponentTranslation("message.firstmod.power.add.blue", amount);
        }
        power.setOrangePower(power.getOrangePower() + amount);
        return new TextComponentTranslation("message.firstmod.power.add.orange", amount);
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (entity instanceof EntityDirtBallKing) {
            float amount = Math.min(entity.getHealth(), event.getAmount());
            Entity source = event.getSource().getTrueSource();
            if (source instanceof EntityPlayer) {
                DirtBallPower power = source.getCapability(CapabilityRegistryHandler.DIRT_BALL_POWER, null);
                TextComponentTranslation text = addPower((EntityDirtBallKing) entity, power, amount);
                //同步数据
                NetworkRegistryHandler.Power.sendClientCustomPacket((EntityPlayer) source);
                source.sendMessage(text);

            }
        }
    }

    public static void serverStarting(FMLServerStartingEvent event)
    {
        new CommandLoader(event);
    }
}
