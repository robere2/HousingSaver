package co.bugg.housingsaver.config;

import co.bugg.housingsaver.HousingSaver;
import co.bugg.housingsaver.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ConfigGui extends GuiConfig {
    public ConfigGui(GuiScreen screen) {
        super(screen, new ConfigElement(HousingSaver.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                Reference.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(HousingSaver.config.toString()));
    }
}
