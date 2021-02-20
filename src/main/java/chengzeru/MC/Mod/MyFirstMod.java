package chengzeru.MC.Mod;

import chengzeru.MC.Mod.capability.CapabilityRegistryHandler;
import chengzeru.MC.Mod.client.renderer.RenderRegistryHandler;
import chengzeru.MC.Mod.crafting.FurnaceRecipeRegistryHandler;
import chengzeru.MC.Mod.fluid.FluidRegistryHandler;
import chengzeru.MC.Mod.network.NetworkRegistryHandler;
import chengzeru.MC.Mod.potion.PotionRegistryHandler;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

@Mod(modid = MyFirstMod.MODID, name = MyFirstMod.NAME,
        version = MyFirstMod.VERSION, acceptedMinecraftVersions = "[1.12,)")
public class MyFirstMod {
    public static final String MODID = "firstmod";
    public static final String NAME = "My First Mod";
    public static final String VERSION = "1.0.0";

    private static Logger logger;

    static {
        FluidRegistry.enableUniversalBucket();
    }
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        NetworkRegistryHandler.registry();
        CapabilityRegistryHandler.register();
        if (FluidRegistry.isFluidRegistered(FluidRegistryHandler.fluidMud))
        {
            event.getModLog().info("Found fluid {}, the registration is canceled. ", FluidRegistryHandler.fluidMud.getName());
            FluidRegistryHandler.fluidMud = FluidRegistry.getFluid(FluidRegistryHandler.fluidMud.getName());
        }
        else
        {
            FluidRegistry.registerFluid(FluidRegistryHandler.fluidMud);
            FluidRegistry.addBucketForFluid(FluidRegistryHandler.fluidMud);
        }
    }
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        chengzeru.MC.Mod.event.EventHandler.serverStarting(event);
    }

    @EventHandler
    @SideOnly(Side.CLIENT)
    public void preInitClient(FMLPreInitializationEvent event) {
        RenderRegistryHandler.register();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        FurnaceRecipeRegistryHandler.register();
        PotionRegistryHandler.register();
    }

}
