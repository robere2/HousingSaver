package co.bugg.housingsaver.util;

import co.bugg.housingsaver.HousingSaver;
import co.bugg.housingsaver.config.SaverConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;

/**
 * Class for handling messages sent publicly to avoid getting shut down by the spam filter
 */
public class PublicMessageBuffer {

    // How many ticks between running commands
    // Multiplied by 2 because for some reason
    // 1 second is 40 ticks and I haven't been
    // able to figure it out.
    public int tickDelay = SaverConfig.messageTick * 2;
    // How many ticks have gone by
    public int tickCount = 0;

    // The current buffer of chat messages to be sent out
    public ArrayList<String> buffer = new ArrayList<>();

    /**
     *  Add the passed message to the chat buffer
     * @param message Message to be added
     */
    public void send(String message) {
        buffer.add(message);
        System.out.println("New bufffer: " + buffer.toString());
    }

    /**
     * Basic function to simplify sending PMs
     * @param user User the PM should be sent to (Name or UUID)
     * @param message Message to be sent
     */
    public void sendPM(String user, String message) {
        send("/msg " + user + " " + message);
    }

    @SubscribeEvent
    public void onGameTick(TickEvent.ClientTickEvent event) {
        if (HousingSaver.onHypixel) {
            tickCount++;
            // Runs every tickDelay ticks
            if (tickCount > tickDelay) {
                tickCount = 0;
                // Only continue with emptying the buffer if it isn't already empty
                if(!buffer.isEmpty()) {
                    String nextMessage = buffer.get(0);
                    System.out.println("Sending message: " + nextMessage);
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(nextMessage);

                    // Remove the message from the buffer
                    buffer.remove(0);
                }
            }
        }
    }
}
