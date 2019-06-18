package gg.stelch.core.Commands.PartyCommands;

import gg.stelch.core.Party;
import gg.stelch.core.PartyUtil.PartyManager;
import gg.stelch.core.PartyUtil.PartyPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class warp {
    public static boolean req_party = true;

    public static void get(ProxiedPlayer p, String[] args) {
        PartyPlayer pp = (PartyPlayer) PartyManager.players.get(p);
        Party party = pp.getParty();
        int moved = 0;
        for (PartyPlayer pm : party.getMembers()) {
            if (pm.getServer().equals(pp.getServer()))
                continue;
            pm.send(pp.getServer());
            pm.sendMessage(gg.stelch.core.Util.en.party_join_server.replaceAll("%server%", pp.getServer().getName()));
            moved++;
        }
        if (moved == 0)
            pp.sendMessage("&cNo one needs to be moved.");
    }
}
