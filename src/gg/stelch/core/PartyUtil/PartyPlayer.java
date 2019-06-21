package gg.stelch.core.PartyUtil;

import com.stelch.games2.core.PlayerUtils.ProxyGamePlayer;
import gg.stelch.core.Party;
import com.stelch.games2.core.Utils.Text;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PartyPlayer {
    ProxiedPlayer player;

    public enum PartyPrivacy {
        PRIVATE, FRIENDS, PUBLIC;
    }

    ProxyGamePlayer gamePlayer;

    PartyPrivacy privacy = PartyPrivacy.PUBLIC;

    Party party = null;

    public PartyPlayer PartyPlayer(ProxiedPlayer player) { this.player = player;
        PartyManager.players.put(player, this);
        return this; }

    public PartyPlayer send(ServerInfo server) { this.player.connect(server);
        return this; }

    public PartyPlayer execute(String command) { this.player.chat("/" + command);
        return this; }

    public String getName() { return this.player.getDisplayName(); }

    public PartyPlayer setParty(Party p) { this.party = p;
        return this; }

    public ServerInfo getServer() { return this.player.getServer().getInfo(); }

    public PartyPrivacy getPrivacy() { return this.privacy; }

    public PartyPlayer setPrivacy(PartyPrivacy option) { this.privacy = option;
        return this; }

    public Party getParty() { return this.party; }

    public boolean hasParty() { return (this.party != null); }

    public ProxiedPlayer getPlayer() { return this.player; }

    public ProxyGamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(ProxyGamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public PartyPlayer sendMessage(String message) { this.player.sendMessage(Text.build(message));
        return this; }

    public PartyPlayer sendMessage(BaseComponent[] message) { this.player.sendMessage(message);
        return this; }
}
