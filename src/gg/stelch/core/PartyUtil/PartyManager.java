package gg.stelch.core.PartyUtil;


import gg.stelch.core.Party;
import gg.stelch.core.Util.Text;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PartyManager {
    public enum InviteState {
        PENDING, DECLINED, TIMEDOUT, ACCEPTED;
    }

    public static HashMap<ProxiedPlayer, PartyPlayer> players = new HashMap();

    public static HashMap<Party, PartyPlayer> parties = new HashMap();

    public static HashMap<Integer, PartyInvite> invites = new HashMap();

    public static void createInvite(final PartyPlayer invitee, final PartyPlayer sender, Party party) {
        if (invitee.hasParty()) {
            sender.sendMessage("&c" + invitee.getName() + " is already in a party.");
            return;
        }
        if (invitee.getPrivacy() != PartyPlayer.PartyPrivacy.PUBLIC) {
            sender.sendMessage("&c" + invitee.getName() + "'s privacy settings do not permit party requests from you.");
            return;
        }
        final PartyInvite invite = (new PartyInvite()).setParty(party).setReciever(invitee).setSender(sender);
        int id = invites.size() + 1;
        invites.put(Integer.valueOf(id), invite);
        invitee.sendMessage(Text.build(gg.stelch.core.Util.en.invite.replaceAll("%sender%", sender.getName()), "Click to accept  [id:" + id + "]", "/party join " + id, ClickEvent.Action.RUN_COMMAND));
        sender.sendMessage(Text.build(gg.stelch.core.Util.en.invite_sent.replaceAll("%player%", invitee.getName())));
        ProxyServer.getInstance().getScheduler().schedule(ProxyServer.getInstance().getPluginManager().getPlugin("server"), new Runnable() {
            public void run() {
                if (invite.getState() == PartyManager.InviteState.PENDING)
                    invitee.sendMessage("&cParty invite from " + sender.getName() + "&c has expired.");
            }
        },  1L, 1L, TimeUnit.MINUTES);
    }

    public static class PartyInvite {
        private PartyPlayer sender;

        private PartyPlayer reciever;

        private Party party;

        private PartyManager.InviteState state = PartyManager.InviteState.PENDING;

        public PartyPlayer getSender() { return this.sender; }

        public PartyInvite setSender(PartyPlayer p) { this.sender = p;
            return this; }

        public PartyPlayer getReciever() { return this.reciever; }

        public PartyInvite setReciever(PartyPlayer p) { this.reciever = p;
            return this; }

        public Party getParty() { return this.party; }

        public PartyInvite setParty(Party p) { this.party = p;
            return this; }

        public PartyManager.InviteState getState() { return this.state; }

        public PartyInvite setState(PartyManager.InviteState state) { this.state = state;
            return this; }

        public List getMembers() {
            List returndata = new ArrayList();
            for (Map.Entry<ProxiedPlayer, PartyPlayer> p : PartyManager.players.entrySet()) {
                if (((PartyPlayer)p.getValue()).getParty() == this.party)
                    returndata.add(p);
            }
            return returndata;
        }
    }
}
