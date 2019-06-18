package gg.stelch.core;

import gg.stelch.core.Commands.CommandBungee;
import gg.stelch.core.Commands.CommandFriends;
import gg.stelch.core.Commands.CommandParty;
import gg.stelch.core.Commands.CommandServer;
import gg.stelch.core.Events.ProxyJoin;
import gg.stelch.core.Events.ProxyPing;
import gg.stelch.core.Events.ServerKick;
import gg.stelch.core.PlayerUtil.GamePlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.HashMap;

public class Main extends Plugin {

    public static HashMap<ProxiedPlayer, GamePlayer> players = new HashMap<>();

    public void onEnable() {

        getProxy().getPluginManager().registerCommand(this, new CommandBungee());
        getProxy().getPluginManager().registerCommand(this, new CommandServer());
        getProxy().getPluginManager().registerCommand(this, new CommandParty());
        getProxy().getPluginManager().registerCommand(this, new CommandFriends());

        getProxy().getPluginManager().registerListener(this,new ProxyPing());
        getProxy().getPluginManager().registerListener(this,new ProxyJoin());
        getProxy().getPluginManager().registerListener(this,new ServerKick());

    }
}
