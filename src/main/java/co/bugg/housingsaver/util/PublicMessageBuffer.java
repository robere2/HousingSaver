package co.bugg.housingsaver.util;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;

public class PublicMessageBuffer {

    // How many ticks between running commands
    public int tickDelay = 20;
    // How many ticks have gone by
    public int tickCount = 0;

    public ArrayList<String> buffer;

    public void send(String message) {
        buffer.add(message);
    }

    public void sendPM(String user, String message) {
        send("/msg " + user + " " + message);
    }

    @SubscribeEvent
    public void onGameTick(TickEvent.ClientTickEvent event) {
        tickCount++;
        // Runs every tickDelay ticks
        if(tickCount > tickDelay) {
            tickCount = 0;
            String nextMessage = buffer.get(0);
            Minecraft.getMinecraft().thePlayer.sendChatMessage(nextMessage);

            // Remove the message from the buffer
            buffer.remove(0);
        }
    }

}
