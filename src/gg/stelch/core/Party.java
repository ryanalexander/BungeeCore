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

package gg.stelch.core;

import gg.stelch.core.PartyUtil.PartyPlayer;

import java.util.Collection;
import java.util.HashMap;

public class Party {
    private enum AccessLevel {
        LEADER, ADMIN, MEMBER;
    }

    private HashMap<PartyPlayer, AccessLevel> Members = new HashMap();

    private PartyPlayer leader;

    public Party Party(PartyPlayer owner) { this.Members.put(owner, AccessLevel.LEADER);
        owner.setParty(this);
        return this; }

    public Party addMember(PartyPlayer player) { addMember(player, AccessLevel.MEMBER);
        player.setParty(this);
        return this; }

    public Party addMember(PartyPlayer player, AccessLevel level) { this.Members.put(player, level);
        return this; }

    public Party kickMember(PartyPlayer player) { this.Members.remove(player);
        player.setParty(null);
        return this; }

    public PartyPlayer getLeader() { return this.leader; }

    public Party setLeader(PartyPlayer player) { this.leader = player;
        return this; }

    public AccessLevel getAccess(PartyPlayer player) { return (AccessLevel)this.Members.get(player); }

    public Party setAccess(PartyPlayer player, AccessLevel level) { this.Members.replace(player, level);
        return this; }

    public Collection<PartyPlayer> getMembers() { return this.Members.keySet(); }
}