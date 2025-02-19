/*
 *
 * *
 *  *
 *  * © Stelch Games 2019, distribution is strictly prohibited
 *  *
 *  * Changes to this file must be documented on push.
 *  * Unauthorised changes to this file are prohibited.
 *  *
 *  * @author Ryan Wood
 *  * @since 14/7/2019
 *
 */

package gg.stelch.core.Commands.PartyCommands;

import gg.stelch.core.Party;
import gg.stelch.core.PartyUtil.PartyManager;
import gg.stelch.core.PartyUtil.PartyPlayer;
import com.stelch.games2.core.Utils.Text;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class leave {
    public static boolean req_party = true;

    public static void get(ProxiedPlayer p, String[] args) {
        PartyPlayer pp = (PartyPlayer) PartyManager.players.get(p);
        Party party = pp.getParty();
        if (pp.getParty() == null) {
            pp.sendMessage(Text.format("&cYou are not in a party."));
            return;
        }
        for (PartyPlayer pm : party.getMembers())
            pm.sendMessage("&c" + p.getDisplayName() + "&c has left your party.");
        pp.getParty().kickMember(pp);
        pp.sendMessage(Text.format("&eYou have left &6" + pp.getParty().getLeader().getName() + "&e's party."));
    }
}
