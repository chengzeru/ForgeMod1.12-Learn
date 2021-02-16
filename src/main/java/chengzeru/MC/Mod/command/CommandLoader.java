package chengzeru.MC.Mod.command;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommandLoader {
    public CommandLoader(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new clearDirtBallPowerCommand());
    }
}
