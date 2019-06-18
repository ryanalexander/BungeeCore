package gg.stelch.core.Commands.PartyCommands;

import gg.stelch.core.Util.Text;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class help {
    public static boolean req_party = false;

    public static void get(ProxiedPlayer p, String[] args) {
        String help = "&6-----------------------------------------------------\n\n&aParty Commands\n";
        BaseComponent[] invite = Text.build("&e/party invite <player> &7-&b Invite a player to your party.", "Click to auto-fill", "/party invite", ClickEvent.Action.SUGGEST_COMMAND);
        BaseComponent[] chat = Text.build("&e/party chat &7-&b Change chat mode to party only.", "Click to execute", "/party chat", ClickEvent.Action.RUN_COMMAND);
        BaseComponent[] join = Text.build("&e/party join &7-&b Join a players party.", "Click to auto-fill", "/party join", ClickEvent.Action.SUGGEST_COMMAND);
        BaseComponent[] list = Text.build("&e/party list &7-&b Show a list of party members.", "Click to execute", "/party list", ClickEvent.Action.RUN_COMMAND);
        BaseComponent[] warp = Text.build("&e/party warp &7-&b Show a list of party members.", "Click to execute", "/party warp", ClickEvent.Action.RUN_COMMAND);
        BaseComponent[] kick = Text.build("&e/party kick <player> &7-&b Kick a member from the party.", "Click to auto-fill", "/party kick", ClickEvent.Action.SUGGEST_COMMAND);
        BaseComponent[] leave = Text.build("&e/party leave &7-&b Leave your current party.", "Click to execute", "/party leave", ClickEvent.Action.RUN_COMMAND);
        p.sendMessage(Text.build(help));
        p.sendMessage(invite);
        p.sendMessage(chat);
        p.sendMessage(join);
        p.sendMessage(list);
        p.sendMessage(warp);
        p.sendMessage(kick);
        p.sendMessage(leave);
        p.sendMessage(Text.build("&6-----------------------------------------------------"));
    }
}