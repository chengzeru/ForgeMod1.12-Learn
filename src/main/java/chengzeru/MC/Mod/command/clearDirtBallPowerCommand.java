package chengzeru.MC.Mod.command;


import chengzeru.MC.Mod.capability.CapabilityRegistryHandler;
import chengzeru.MC.Mod.capability.DirtBallPower;
import chengzeru.MC.Mod.network.NetworkRegistryHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class clearDirtBallPowerCommand extends CommandBase {
    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new clearDirtBallPowerCommand());
    }

    @Override
    public String getName() {
        return "clearPower";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.clearPower.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        DirtBallPower power = getCommandSenderAsPlayer(sender).getCapability(CapabilityRegistryHandler.DIRT_BALL_POWER, null);
        power.setOrangePower(0.0f);
        power.setGreenPower(0.0f);
        power.setBluePower(0.0f);
        //同步数据
        NetworkRegistryHandler.Power.sendClientCustomPacket((EntityPlayer) sender);
        TextComponentTranslation text = new TextComponentTranslation("tooltip.firstmod.clearPower");
        sender.sendMessage(text);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
        // 0 代表任何人都能用 4(默认)管理员
    }
}
