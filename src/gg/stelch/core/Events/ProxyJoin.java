package gg.stelch.core.Events;

import gg.stelch.core.Main;
import gg.stelch.core.PlayerUtil.GamePlayer;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyJoin implements Listener {

    @EventHandler
    public void ProxyJoin(ServerConnectedEvent e){
        GamePlayer player;
        if(!(Main.players.containsKey(e.getPlayer()))){
            player = new GamePlayer(e.getPlayer().getUniqueId().toString());
            Main.players.put(e.getPlayer(),player);
        }else {
            player=Main.players.get(e.getPlayer());
        }

        player.setServer(e.getServer().getInfo());
    }

}
