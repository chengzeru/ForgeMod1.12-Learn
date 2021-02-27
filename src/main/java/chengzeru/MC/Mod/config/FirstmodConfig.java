package chengzeru.MC.Mod.config;

import chengzeru.MC.Mod.MyFirstMod;
import net.minecraftforge.common.config.Config;

@Config(modid = MyFirstMod.MODID)
@Config.LangKey("config.firstmod.general")
public final class FirstmodConfig {
    @Config.Comment("How long will a dirt burn?") // 有了这个就会多一个注释。
    @Config.LangKey("config.firstmod.general.dirtBurnTime")// 供配置 GUI 界面使用的本地化键
    @Config.Name("DirtBurnTime") // 默认配置选项名是字段名，如果需要别的名字就用这个。
    @Config.RangeInt(min = 0, max = 1000) // 整数值支持限定范围。
    //@Config.RequiresWorldRestart // meta 标记，代表需要重进存档才会生效
    //@Config.RequiresMcRestart // meta 标记，代表需要重启游戏才会生效
    public static int dirtBurnTime=100;
}
