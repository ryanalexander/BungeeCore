package gg.stelch.core.Commands.PartyCommands;

import gg.stelch.core.PartyUtil.PartyManager;
import gg.stelch.core.PartyUtil.PartyPlayer;
import com.stelch.games2.core.Utils.Text;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class join {
    public static boolean req_party = true;

    public static void get(ProxiedPlayer p, String[] args) {
        if (args.length < 2) {
            p.sendMessage(Text.build(gg.stelch.core.Util.en.party_join_usage));
            return;
        }
        if (!Text.isNumeric(args[1]) || !PartyManager.invites.containsKey(Integer.valueOf(Integer.parseInt(args[1])))) {
            p.sendMessage(Text.build(gg.stelch.core.Util.en.party_join_invalid_player));
            return;
        }
        PartyManager.PartyInvite invite = (PartyManager.PartyInvite)PartyManager.invites.get(Integer.valueOf(Integer.parseInt(args[1])));
        if (invite.getState() == PartyManager.InviteState.PENDING) {
            PartyPlayer player = (PartyPlayer)PartyManager.players.get(p);
            invite.setState(PartyManager.InviteState.ACCEPTED);
            player.setParty(invite.getParty());
            for (PartyPlayer pl : invite.getParty().getMembers())
                pl.sendMessage(invite.getReciever().getName() + " has joined your party");
            invite.getParty().addMember(player);
            p.sendMessage(Text.build(gg.stelch.core.Util.en.party_join_done.replaceAll("%player%", invite.getParty().getLeader().getName())));
        } else {
            p.sendMessage(Text.build(gg.stelch.core.Util.en.party_join_invalid_player));
            return;
        }
    }
}
