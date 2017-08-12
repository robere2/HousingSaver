package co.bugg.housingsaver;

import co.bugg.housingsaver.commands.SaverToggleCommand;
import co.bugg.housingsaver.util.PublicMessageBuffer;
import co.bugg.housingsaver.util.json.JsonUtil;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.IOException;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class HousingSaver {

    // Whether you're online hypixel or not
    public static boolean onHypixel = false;
    // Whether the saver is toggled on or not
    public static boolean toggle = false;

    public static PublicMessageBuffer buffer = new PublicMessageBuffer();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        SaverEventHandler handler = new SaverEventHandler();

        MinecraftForge.EVENT_BUS.register(handler);
        MinecraftForge.EVENT_BUS.register(buffer);

        JsonUtil.createDir(JsonUtil.path);
        JsonUtil.createDir(JsonUtil.fullPath);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new SaverToggleCommand());
    }
}
