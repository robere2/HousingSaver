package co.bugg.housingsaver;

import co.bugg.housingsaver.commands.LoadCommand;
import co.bugg.housingsaver.commands.SaverToggleCommand;
import co.bugg.housingsaver.util.PublicMessageBuffer;
import co.bugg.housingsaver.util.json.JsonUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class HousingSaver {

    // Whether you're online hypixel or not
    public static boolean onHypixel = false;
    // Whether the saver is toggled on or not
    public static boolean toggle = false;

    // Path to the mods main directory
    public static final String path = "housingsaver/";
    // Path to the directory for this instance of Minecraft (corresponds to the user's UUID)
    public static final String fullPath = path + EntityPlayer.getUUID(Minecraft.getMinecraft().getSession().getProfile()) + "/";

    public static PublicMessageBuffer buffer = new PublicMessageBuffer();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        SaverEventHandler handler = new SaverEventHandler();

        // Register event handlers
        MinecraftForge.EVENT_BUS.register(handler);
        MinecraftForge.EVENT_BUS.register(buffer);

        // Try to create the directories
        JsonUtil.createDir(path);
        JsonUtil.createDir(fullPath);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // Register in-game slash commands
        ClientCommandHandler.instance.registerCommand(new SaverToggleCommand());
        ClientCommandHandler.instance.registerCommand(new LoadCommand());
    }
}
