package co.bugg.housingsaver.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

/**
 * Class for building chat messages
 * Complicated messages require their own function ideally
 */
public class MessageBuilder {
    public static IChatComponent buildError(String error) {
        return build(error, EnumChatFormatting.RED);
    }

    public static IChatComponent buildSuccess(String msg) {
        return build(msg, EnumChatFormatting.GREEN);
    }

    /**
     * Basic simple message constructor
     * @param msg Message to be sent
     * @param code Color code for the message.
     */
    public static IChatComponent build(String msg, EnumChatFormatting code) {
        IChatComponent component = new ChatComponentText(msg);
        ChatStyle style = new ChatStyle();

        style.setColor(code);
        component.setChatStyle(style);

        return component;
    }

    /**
     * Send a chat message to the player
     * @param component IChatComponent to be sent
     */
    public static void send(IChatComponent component) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(component);
    }
}
