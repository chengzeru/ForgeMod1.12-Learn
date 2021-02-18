package chengzeru.MC.Mod.command;


import chengzeru.MC.Mod.capability.CapabilityRegistryHandler;
import chengzeru.MC.Mod.capability.DirtBallPower;
import chengzeru.MC.Mod.network.NetworkRegistryHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class DirtBallPowerCommand extends CommandBase {
    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new DirtBallPowerCommand());
    }

    @Override
    public String getName() {
        return "power";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.power.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        DirtBallPower power = getCommandSenderAsPlayer(sender).getCapability(CapabilityRegistryHandler.DIRT_BALL_POWER, null);
        if (args.length >= 1) {
            if ("clear".equals(args[0])) {
                if (args.length == 1) {
                    power.setOrangePower(0.0f);
                    power.setGreenPower(0.0f);
                    power.setBluePower(0.0f);
                    //同步数据
                    NetworkRegistryHandler.Power.sendClientCustomPacket((EntityPlayer) sender);
                    TextComponentTranslation text = new TextComponentTranslation("tooltip.firstmod.clearPower");
                    sender.sendMessage(text);
                    return;
                }
                if (args.length == 2 || args.length == 3) {
                    int i = 0;
                    if (args.length == 2) {
                        i = 10;
                    }
                    if (args.length == 3) {
                        i = parseInt(args[2], 0);
                    }
                    if ("blue".equals(args[1])) {
                        power.setBluePower(power.getBluePower() - i);
                        if (power.getBluePower() < 0) {
                            power.setBluePower(0.0f);
                        }
                        //同步数据
                        NetworkRegistryHandler.Power.sendClientCustomPacket((EntityPlayer) sender);
                        notifyCommandListener(sender, this, "commands.power.clearBlue", i);
                        return;
                    } else if ("green".equals(args[1])) {
                        power.setGreenPower(power.getGreenPower() - i);
                        if (power.getGreenPower() < 0) {
                            power.setGreenPower(0.0f);
                        }
                        //同步数据
                        NetworkRegistryHandler.Power.sendClientCustomPacket((EntityPlayer) sender);
                        notifyCommandListener(sender, this, "commands.power.clearGreen", i);
                        return;
                    } else if ("orange".equals(args[1])) {
                        power.setOrangePower(power.getOrangePower() - i);
                        if (power.getOrangePower() < 0) {
                            power.setOrangePower(0.0f);
                        }
                        //同步数据
                        NetworkRegistryHandler.Power.sendClientCustomPacket((EntityPlayer) sender);
                        notifyCommandListener(sender, this, "commands.power.clearOrange", i);
                        return;
                    }
                }
            } else if ("add".equals(args[0])) {
                if (args.length == 3) {
                    int i;
                    i = parseInt(args[2], 0);
                    if ("blue".equals(args[1])) {
                        power.setBluePower(power.getBluePower() + i);
                        if (power.getBluePower() < 0) {
                            power.setBluePower(0.0f);
                        }
                        //同步数据
                        NetworkRegistryHandler.Power.sendClientCustomPacket((EntityPlayer) sender);
                        notifyCommandListener(sender, this, "commands.power.addBlue", i);
                        return;
                    } else if ("green".equals(args[1])) {
                        power.setGreenPower(power.getGreenPower() + i);
                        if (power.getGreenPower() < 0) {
                            power.setGreenPower(0.0f);
                        }
                        //同步数据
                        NetworkRegistryHandler.Power.sendClientCustomPacket((EntityPlayer) sender);
                        notifyCommandListener(sender, this, "commands.power.addGreen", i);
                        return;
                    } else if ("orange".equals(args[1])) {
                        power.setOrangePower(power.getOrangePower() + i);
                        if (power.getOrangePower() < 0) {
                            power.setOrangePower(0.0f);
                        }
                        //同步数据
                        NetworkRegistryHandler.Power.sendClientCustomPacket((EntityPlayer) sender);
                        notifyCommandListener(sender, this, "commands.power.addOrange", i);
                        return;
                    }
                }
            }
        }
        throw new WrongUsageException("commands.power.usage", new Object[0]);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
        // 0 代表任何人都能用 4(默认)管理员
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "clear", "add");
        } else if (args.length == 2) {
            return getListOfStringsMatchingLastWord(args, "blue", "green", "orange");
        } else {
            return args.length == 3 ? getListOfStringsMatchingLastWord(args, "10") : Collections.emptyList();
        }
    }
}
