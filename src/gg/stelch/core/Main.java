package gg.stelch.core;

import gg.stelch.core.Commands.CommandBungee;
import gg.stelch.core.Commands.CommandFriends;
import gg.stelch.core.Commands.CommandParty;
import gg.stelch.core.Commands.CommandServer;
import gg.stelch.core.Events.*;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

    public void onEnable() {

        getProxy().getPluginManager().registerCommand(this, new CommandBungee());
        getProxy().getPluginManager().registerCommand(this, new CommandServer());
        getProxy().getPluginManager().registerCommand(this, new CommandParty());
        getProxy().getPluginManager().registerCommand(this, new CommandFriends());

        getProxy().getPluginManager().registerListener(this,new ServerKick());
        getProxy().getPluginManager().registerListener(this,new ServerChange());

    }
}
