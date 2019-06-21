package gg.stelch.core.Commands.PartyCommands;

import gg.stelch.core.Party;
import gg.stelch.core.PartyUtil.PartyManager;
import gg.stelch.core.PartyUtil.PartyPlayer;
import com.stelch.games2.core.Utils.Text;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class kick {
    public static boolean req_party = true;

    public static void get(ProxiedPlayer p, String[] args) {
        if (args.length < 2)
            p.sendMessage(Text.build("&cUsage: /party kick <player>"));
        PartyPlayer pp = (PartyPlayer)PartyManager.players.get(p);
        PartyPlayer victom = (PartyPlayer) PartyManager.players.get(ProxyServer.getInstance().getPlayer(args[1]));
        Party party = pp.getParty();
        if (pp.getParty() == null) {
            pp.sendMessage(Text.format("&cYou are not in a party."));
            return;
        }
        if (pp.getParty().getLeader() == victom) {
            pp.sendMessage(Text.format("&cYou may not kick the party leader."));
            return;
        }
        if (pp == party.getLeader()) {
            pp.sendMessage(Text.format("&cYou have kicked " + victom.getName() + " from your party."));
        } else {
            pp.sendMessage(Text.format("&cYou have kicked " + victom.getName() + " from &6" + pp.getParty().getLeader().getName() + "&c's party."));
        }
        victom.sendMessage(Text.format("&cYou have been kicked from &6" + pp.getParty().getLeader().getName() + "&c's party."));
        pp.getParty().kickMember(victom);
        for (PartyPlayer pm : party.getMembers()) {
            if (pm != pp)
                pm.sendMessage("&c" + victom.getName() + "&c has been kicked from your party.");
        }
        if (!PartyManager.players.containsKey(p)) {
            pp = new PartyPlayer();
            pp.PartyPlayer(p);
        } else {
            pp = (PartyPlayer)PartyManager.players.get(p);
        }
    }
}
