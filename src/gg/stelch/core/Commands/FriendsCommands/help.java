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

package gg.stelch.core.Commands.FriendsCommands;

import com.stelch.games2.core.Utils.Text;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class help {

    public static void get(ProxiedPlayer p, String[] args) {
        String help = "&6-----------------------------------------------------\n\n&aFriend Commands\n";
        BaseComponent[] invite = Text.build("&e/friends help &7-&b Display help menu", "Click to auto-fill", "/friends help", ClickEvent.Action.SUGGEST_COMMAND);
        BaseComponent[] friends = Text.build("&e/friends list &7-&b List all friends", "Click to auto-fill", "/friends help", ClickEvent.Action.SUGGEST_COMMAND);
        p.sendMessage(Text.build(help));
        p.sendMessage(invite);
        p.sendMessage(friends);
        p.sendMessage(Text.build("&6-----------------------------------------------------"));
    }
}