package chengzeru.MC.Mod.entity;

import chengzeru.MC.Mod.MyFirstMod;
import chengzeru.MC.Mod.potion.PotionRegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;
import java.util.UUID;

public class EntityDirtBallKing extends EntityMob {
    public static final String ID = "dirt_ball_king";
    public static final String NAME = MyFirstMod.MODID + ".DirtBallKing";
    private static final UUID SPEED_BOOST = UUID.fromString("c227dab0-5677-48e6-a33f-10fe5919594b");
    private static final DataParameter<Byte> COLOR = EntityDataManager.createKey(EntityDirtBallKing.class, DataSerializers.BYTE);
    public static final Biome[] BIOMES = new Biome[]{Biomes.PLAINS, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.MUTATED_PLAINS};
    private static final ResourceLocation LOOT_TABLE = LootTableList.register(new ResourceLocation(MyFirstMod.MODID + ":entities/dirt_ball_king"));

    public EntityDirtBallKing(World worldIn) {
        super(worldIn);
        this.setSize(1.2f, 1.95f);
    }

    public byte getColor() {
        return this.getDataManager().get(COLOR);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new AINotAttackDirtProtection(this, 1.0, true));
        this.tasks.addTask(2, new AIMoveToDirt(this, 0.8D, 8));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 0.8));
        this.tasks.addTask(4, new AIChangeGrassToDirt(this));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
    }

    @Override
    public void setAttackTarget(@Nullable EntityLivingBase entity) {
        super.setAttackTarget(entity);
        IAttributeInstance attribute = this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
        if (entity == null) {
            attribute.removeModifier(SPEED_BOOST);
        } else if (attribute.getModifier(SPEED_BOOST) != null) {
            attribute.applyModifier(new AttributeModifier(SPEED_BOOST, "Speed boost", 0.5, 2).setSaved(false));
        }
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData data) {
        IAttributeInstance attribute = this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
        attribute.applyModifier(new AttributeModifier("Health boost", this.rand.nextInt(6), 0));
        this.getDataManager().set(COLOR, (byte) this.rand.nextInt(3));
        return super.onInitialSpawn(difficulty, data);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.getDataManager().register(COLOR, (byte) 0);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setByte("Color", this.getDataManager().get(COLOR));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.getDataManager().set(COLOR, compound.getByte("Color"));
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LOOT_TABLE;
    }

    private static class AIChangeGrassToDirt extends EntityAIBase {
        private final EntityDirtBallKing entity;

        private AIChangeGrassToDirt(EntityDirtBallKing entity) {
            this.entity = entity;
        }

        @Override
        public boolean shouldExecute() {
            BlockPos blockPos = new BlockPos(this.entity.posX, this.entity.posY - 0.2, this.entity.posZ);
            return this.entity.world.getBlockState(blockPos).getBlock() == Blocks.GRASS;
        }

        @Override
        public void updateTask() {
            BlockPos blockPos = new BlockPos(this.entity.posX, this.entity.posY - 0.2, this.entity.posZ);
            this.entity.world.setBlockState(blockPos, Blocks.DIRT.getDefaultState());
        }
    }

    private static class AINotAttackDirtProtection extends EntityAIAttackMelee {

        public AINotAttackDirtProtection(EntityCreature creature, double speedIn, boolean useLongMemory) {
            super(creature, speedIn, useLongMemory);
        }

        @Override
        public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
            if (entitylivingbase != null && entitylivingbase.isPotionActive(PotionRegistryHandler.POTION_DIRT_PROTECTION)) {
                this.attacker.setAttackTarget(null);
                return false;
            }
            return super.shouldExecute();
        }

        @Override
        public boolean shouldContinueExecuting() {
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
            if (entitylivingbase != null && entitylivingbase.isPotionActive(PotionRegistryHandler.POTION_DIRT_PROTECTION)) {
                this.attacker.setAttackTarget(null);
                return false;
            }
            return super.shouldContinueExecuting();
        }

        @Override
        public void resetTask() {
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
            if (entitylivingbase instanceof EntityPlayer && entitylivingbase.isPotionActive(PotionRegistryHandler.POTION_DIRT_PROTECTION)) {
                this.attacker.setAttackTarget(null);
            }
            super.resetTask();
        }
    }

    private static class AIMoveToDirt extends EntityAIMoveToBlock {
        private final EntityCreature creature;

        public AIMoveToDirt(EntityCreature creature, double speedIn, int length) {
            super(creature, speedIn, length);
            this.creature = creature;
        }

        @Override
        protected boolean shouldMoveTo(World worldIn, BlockPos pos) {
            if (!worldIn.isAirBlock(pos.up())) {
                return false;
            } else {
                IBlockState iblockstate = worldIn.getBlockState(pos);
                Block block = iblockstate.getBlock();
                BlockPos blockPos = new BlockPos(creature.posX, creature.posY - 2, creature.posZ);
                Block block1 = worldIn.getBlockState(blockPos).getBlock();
                if (block1 == Blocks.DIRT) {
                    return false;
                }
                return block == Blocks.DIRT;
            }
        }
    }
}
