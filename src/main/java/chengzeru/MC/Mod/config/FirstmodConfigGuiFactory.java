package chengzeru.MC.Mod.config;

import chengzeru.MC.Mod.MyFirstMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

import java.util.Collections;
import java.util.Set;

public class FirstmodConfigGuiFactory implements IModGuiFactory {
    private static final String FIRST_TITLE =I18n.format("config.firstmod.firstTitle");
    private static final String SECOND_TITLE =I18n.format("config.firstmod.secondTitle");
    @Override
    public void initialize(Minecraft minecraftInstance) {

    }

    @Override
    public boolean hasConfigGui() {
        return true;
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new GuiConfig(parentScreen, ConfigElement.from(FirstmodConfig.class).getChildElements(), MyFirstMod.MODID,false,false,FIRST_TITLE,SECOND_TITLE);
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return Collections.emptySet();
    }
}
