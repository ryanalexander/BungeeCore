package gg.stelch.core.Commands;

import gg.stelch.core.Commands.FriendsCommands.accept;
import gg.stelch.core.Commands.FriendsCommands.add;
import gg.stelch.core.Commands.FriendsCommands.help;
import gg.stelch.core.Commands.FriendsCommands.list;
import gg.stelch.core.Util.Text;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Map;

public class CommandFriends extends Command {
    public CommandFriends() { super("friends", "", new String[] { "f","friend" }); }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            help.get((ProxiedPlayer) sender,args);
        } else {
            if (!(sender instanceof ProxiedPlayer))
                return;
            switch(args[0].toLowerCase()){
                case "help":
                    help.get((ProxiedPlayer)sender,args);
                    break;
                case "add":
                    add.get((ProxiedPlayer)sender,args);
                    break;
                case "accept":
                    accept.get((ProxiedPlayer)sender,args);
                    break;
                case "list":
                    try {
                        list.get((ProxiedPlayer)sender,args);
                    }catch (Exception e){
                        e.printStackTrace();
                        sender.sendMessage(Text.build("&aFriends> &cFailed to execute command. Try again later."));
                    }
                    break;
                default:
                    help.get((ProxiedPlayer)sender,args);
                    break;
            }
        }
    }
}