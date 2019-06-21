package gg.stelch.core.Events;

import com.stelch.games2.core.Utils.Text;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerKick implements Listener {

    @EventHandler
    public void onKick(ServerKickEvent e){
        e.setState(ServerKickEvent.State.CONNECTED);
        e.setCancelled(true);
        ServerInfo server = ProxyServer.getInstance().getServers().get("hub01");
        e.setCancelServer(server);
        e.getPlayer().sendMessage(Text.build("&aPortal> &7Returned to lobby as you were kicked."));
    }
}