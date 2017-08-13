package co.bugg.housingsaver.commands;

import co.bugg.housingsaver.HousingSaver;
import co.bugg.housingsaver.util.MessageBuilder;
import co.bugg.housingsaver.util.json.Coordinates;
import co.bugg.housingsaver.util.json.JsonUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.client.Minecraft.getMinecraft;

/**
 * In-game slash command that loads user save data
 */
public class LoadCommand implements ICommand {
    @Override
    public String getCommandName() {
        return "load";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/load <username>";
    }

    @Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("hload");
        aliases.add("hl");

        return aliases;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if(HousingSaver.onHypixel) {
            String loadDir = HousingSaver.fullPath;
            String usernameRegex = "^[a-zA-Z0-9_]{1,16}$";

            // Throw error if not only 1 argument is provided
            if (args.length != 1) {
                MessageBuilder.send(MessageBuilder.buildError("Invalid syntax: " + getCommandUsage(sender)));
                throw new CommandException("Invalid syntax");
            }

            String playerName = args[0];

            // Throw error if the username isn't valid
            if (!playerName.matches(usernameRegex)) {
                MessageBuilder.send(MessageBuilder.buildError("Invalid username."));
                throw new CommandException("Invalid username");
            }

            // Convert the username to the user's UUID (must be in the housing world)
            EntityPlayer player = getMinecraft().theWorld.getPlayerEntityByName(playerName);

            // Check if the player is in the Housing world (otherwise I can't get their UUID without querying Mojang,
            // which isn't really that necessary).
            if(player != null) {
                Coordinates coords = JsonUtil.read(EntityPlayer.getUUID(player.getGameProfile()));
                if(coords != null) {
                    MessageBuilder.send(MessageBuilder.build(playerName + "'s Save: " + coords.x + " / " + coords.y + " / " + coords.z, EnumChatFormatting.AQUA));
                } else {
                    MessageBuilder.send(MessageBuilder.buildError("Failed loading save for user " + playerName));
                }
            } else {
                MessageBuilder.send(MessageBuilder.buildError("Unable to find player " + playerName + "!"));
            }
        } else {
            MessageBuilder.send(MessageBuilder.buildError("You are not on the Hypixel Network!"));
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        List<EntityPlayer> players = Minecraft.getMinecraft().theWorld.playerEntities;
        List<String> playerNames = new ArrayList<>();

        for(EntityPlayer player : players) {
            playerNames.add(player.getName());
        }

        return playerNames;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(ICommand o) {
        return 0;
    }
}
