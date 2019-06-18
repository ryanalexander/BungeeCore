package gg.stelch.core.Commands;

import gg.stelch.core.Util.Text;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Command;

import java.util.Map;

public class CommandServer extends Command {
    public CommandServer() { super("server", ""); }

    public void execute(CommandSender sender, String[] args) {
        Map<String, ServerInfo> servers = ProxyServer.getInstance().getServers();
        if (args.length == 0) {
            if (sender instanceof ProxiedPlayer)
                sender.sendMessage(Text.build(String.format("&aPortal> &7You are connected to &e%s&7.",((ProxiedPlayer) sender).getServer().getInfo().getName())));
        } else {
            if (!(sender instanceof ProxiedPlayer))
                return;
            ProxiedPlayer player = (ProxiedPlayer)sender;
            ServerInfo server = (ServerInfo)servers.get(args[0]);
            if (server == null) {
                sender.sendMessage(Text.build(String.format("&cPortal> &7The server &e%s&7 does not exist.",args[0])));
            } else if (!server.canAccess(player)) {
                sender.sendMessage(Text.build(String.format("&cPortal> &7You may not connect to &e%s&7.",args[0])));
            } else {
                player.connect(server, ServerConnectEvent.Reason.COMMAND);
                sender.sendMessage(Text.build(String.format("&aPortal> &7You are now connected to &e%s&7.",server.getName())));
            }
        }
    }
}
