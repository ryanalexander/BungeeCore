package gg.stelch.core.Events;


import com.stelch.games2.core.Utils.Text;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyPing implements Listener {

    @EventHandler
    public void onPing(ProxyPingEvent e){
        ServerPing serverPing = e.getResponse();
        serverPing.setDescriptionComponent(Text.build("&aStelch Games &c[testing]")[0]);
        serverPing.getPlayers().setMax(ProxyServer.getInstance().getOnlineCount()+1);
        e.setResponse(serverPing);
    }
}