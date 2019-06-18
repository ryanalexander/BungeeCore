package gg.stelch.core.Events;

import gg.stelch.core.Util.Text;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyPing implements Listener {

    @EventHandler
    public void onPing(ProxyPingEvent e){
        ServerPing serverPing = e.getResponse();
        serverPing.setDescription(Text.format("&aStelch Games &c[testing]"));
        serverPing.getPlayers().setMax(ProxyServer.getInstance().getOnlineCount()+1);
        e.setResponse(serverPing);
    }
}