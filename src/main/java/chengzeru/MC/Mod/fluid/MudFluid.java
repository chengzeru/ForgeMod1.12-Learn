package chengzeru.MC.Mod.fluid;

import chengzeru.MC.Mod.MyFirstMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class MudFluid extends Fluid {
    public static final ResourceLocation still = new ResourceLocation(MyFirstMod.MODID + ":fluid/mud_still");
    public static final ResourceLocation flowing = new ResourceLocation(MyFirstMod.MODID + ":fluid/mud_flow");

    public MudFluid() {
        super("mud", MudFluid.still, MudFluid.flowing);
        this.setUnlocalizedName(MyFirstMod.MODID+"fluidMud");
        this.setDensity(3000);
        this.setViscosity(10000);
        this.setLuminosity(0);
        this.setTemperature(300);
    }
}
