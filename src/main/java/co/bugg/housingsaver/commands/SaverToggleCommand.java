package co.bugg.housingsaver.commands;

import co.bugg.housingsaver.HousingSaver;
import co.bugg.housingsaver.util.MessageBuilder;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.*;

import java.util.ArrayList;
import java.util.List;

public class SaverToggleCommand implements ICommand {

    @Override
    public String getCommandName() {
        return "savertoggle";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/savertoggle";
    }

    @Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("st");
        aliases.add("ht");

        return aliases;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if(sender instanceof EntityPlayer) {

            if(HousingSaver.onHypixel) {
                // Swap the toggle.
                HousingSaver.toggle = !HousingSaver.toggle;
            } else {
                MessageBuilder.send(MessageBuilder.buildError("You are currently not on the Hypixel Network!"));
                return;
            }

            IChatComponent component;
            String toggleMessage = "Housing Saver toggled ";

            if(HousingSaver.toggle) {
                component = MessageBuilder.buildSuccess(toggleMessage + "on");
            } else {
                component = MessageBuilder.buildError(toggleMessage + "off");
            }

            sender.addChatMessage(component);
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return null;
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
