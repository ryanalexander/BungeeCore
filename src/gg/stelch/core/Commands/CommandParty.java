package gg.stelch.core.Commands;

import gg.stelch.core.Commands.PartyCommands.*;
import gg.stelch.core.PartyUtil.PartyManager;
import gg.stelch.core.Util.Text;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Command;

import java.util.Map;

public class CommandParty extends Command {
    public CommandParty() { super("party", "", new String[] { "p" }); }

    public void execute(CommandSender sender, String[] args) {
        Map<String, ServerInfo> servers = ProxyServer.getInstance().getServers();
        if (args.length == 0) {
            help.get((ProxiedPlayer)sender,args);
        } else {
            if (!(sender instanceof ProxiedPlayer))
                return;
            switch(args[0].toLowerCase()){
                case "help":
                    if (help.req_party && !PartyManager.players.containsKey((ProxiedPlayer)sender)) {sender.sendMessage(Text.build("&cYou must be in a party to issue that command."));}
                    help.get((ProxiedPlayer)sender,args);
                    break;
                case "invite":
                    if (invite.req_party && !PartyManager.players.containsKey((ProxiedPlayer)sender)) {sender.sendMessage(Text.build("&cYou must be in a party to issue that command."));}
                    invite.get((ProxiedPlayer)sender,args);
                    break;
                case "join":
                    if (join.req_party && !PartyManager.players.containsKey((ProxiedPlayer)sender)) {sender.sendMessage(Text.build("&cYou must be in a party to issue that command."));}
                    join.get((ProxiedPlayer)sender,args);
                    break;
                case "kick":
                    if (kick.req_party && !PartyManager.players.containsKey((ProxiedPlayer)sender)) {sender.sendMessage(Text.build("&cYou must be in a party to issue that command."));}
                    kick.get((ProxiedPlayer)sender,args);
                    break;
                case "leave":
                    if (leave.req_party && !PartyManager.players.containsKey((ProxiedPlayer)sender)) {sender.sendMessage(Text.build("&cYou must be in a party to issue that command."));}
                    leave.get((ProxiedPlayer)sender,args);
                    break;
                case "warp":
                    if (warp.req_party && !PartyManager.players.containsKey((ProxiedPlayer)sender)) {sender.sendMessage(Text.build("&cYou must be in a party to issue that command."));}
                    warp.get((ProxiedPlayer)sender,args);
                    break;
                default:
                    help.get((ProxiedPlayer)sender,args);
                    break;
            }
        }
    }
}
