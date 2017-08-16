package co.bugg.housingsaver.config;

import co.bugg.housingsaver.HousingSaver;
import net.minecraftforge.common.config.Configuration;

public class SaverConfig {

    // Config options
    public static int messageTick;
    public static final int messageTickDefault = 20;
    public static final String messageTickInfo = "How often in ticks the message buffer sends out messages (Default: 20 ticks)";

    public static String saveRegex;
    public static String saveRegexDefault = "^From (?:\\[[A-Z+]*] )?([A-Za-z0-9_]{1,16}): !save$";
    public static String saveRegexInfo = "Regular expression for what chat message triggers the save command";

    public static String ipMatch;
    public static String ipMatchDefault = ".hypixel.net";
    public static String ipMatchInfo = "What an IP must contain in order for the mod to be enabled";

    public static void sync() {

        // Creating Categories
        final String general = Configuration.CATEGORY_GENERAL;
        HousingSaver.config.addCustomCategoryComment(general, "General mod options");

        messageTick = HousingSaver.config.get(general, messageTickInfo, messageTickDefault).getInt(messageTickDefault);
        saveRegex = HousingSaver.config.get(general, saveRegexInfo, saveRegexDefault).getString();
        ipMatch = HousingSaver.config.get(general, ipMatchInfo, ipMatchDefault).getString();

        if(HousingSaver.config.hasChanged()) {
            HousingSaver.config.save();
        }
    }
}
