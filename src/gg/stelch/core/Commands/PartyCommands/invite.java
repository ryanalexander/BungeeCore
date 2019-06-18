package gg.stelch.core.Commands.PartyCommands;

import gg.stelch.core.Party;
import gg.stelch.core.PartyUtil.PartyManager;
import gg.stelch.core.PartyUtil.PartyPlayer;
import gg.stelch.core.Util.Text;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class invite {
    public static boolean req_party = false;

    public static void get(ProxiedPlayer p, String[] args) {
        Party party;
        PartyPlayer invitee, pp;
        if (args.length < 2){
            p.sendMessage(Text.build("&cUsage: /party invite <player>"));
            return;
        }
        if (!PartyManager.players.containsKey(p)) {
            pp = new PartyPlayer();
            pp.PartyPlayer(p);
        } else {
            pp = (PartyPlayer)PartyManager.players.get(p);
        }
        if (ProxyServer.getInstance().getPlayer(args[1]) == null) {
            pp.sendMessage(gg.stelch.core.Util.en.party_invite_invalid_player);
            return;
        }
        if (!pp.hasParty()) {
            party = new Party();
            party.Party(pp);
            party.setLeader(pp);
        } else {
            party = pp.getParty();
        }
        if (!PartyManager.players.containsKey(ProxyServer.getInstance().getPlayer(args[1]))) {
            invitee = new PartyPlayer();
            invitee.PartyPlayer(ProxyServer.getInstance().getPlayer(args[1]));
        } else {
            invitee = (PartyPlayer)PartyManager.players.get(ProxyServer.getInstance().getPlayer(args[1]));
        }
        if (pp == invitee) {
            pp.sendMessage(Text.format(gg.stelch.core.Util.en.party_invite_self));
            return;
        }
        PartyManager.createInvite(invitee, pp, party);
    }
}
