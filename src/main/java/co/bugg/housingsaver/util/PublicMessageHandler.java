package co.bugg.housingsaver.util;

import net.minecraft.client.Minecraft;

public class PublicMessageHandler {
    public static void send(String message) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(message);
    }

    public static void sendPM(String user, String message) {
        send("/msg " + user + " " + message);
    }
}
