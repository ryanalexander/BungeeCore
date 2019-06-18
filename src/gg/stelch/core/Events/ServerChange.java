package gg.stelch.core.Events;

import gg.stelch.core.Main;
import gg.stelch.core.Party;
import gg.stelch.core.PartyUtil.PartyManager;
import gg.stelch.core.PartyUtil.PartyPlayer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerChange implements Listener {
    @EventHandler
    public void ServerChange(ServerSwitchEvent e) {
        Main.players.get(e.getPlayer()).setServer(e.getPlayer().getServer().getInfo());
        if (PartyManager.players.containsKey(e.getPlayer())) {
            PartyPlayer p = (PartyPlayer) PartyManager.players.get(e.getPlayer());
            if (p.getParty() == null)
                return;
            Party party = p.getParty();
            p.execute("partydata " + ((party.getLeader() != p) ? "join" : "leader") + " " + party.getLeader().getPlayer().getUniqueId());
            for (PartyPlayer pm : party.getMembers()) {
                if (pm.getServer().equals(p.getServer()))
                    continue;
                pm.send(p.getServer());
                pm.sendMessage(gg.stelch.core.Util.en.party_join_server.replaceAll("%server%", p.getServer().getName()));
            }
        }
    }
}
