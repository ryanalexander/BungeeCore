package gg.stelch.core.Events;

import gg.stelch.core.Main;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyLeave implements Listener {

    @EventHandler
    public void ProxyLeave(PlayerDisconnectEvent e){
        Main.players.remove(e.getPlayer());
    }

}
