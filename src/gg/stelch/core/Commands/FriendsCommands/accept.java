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

import com.stelch.games2.core.PlayerUtils.ProxyGamePlayer;
import com.stelch.games2.core.Utils.SQL;
import gg.stelch.core.Main;
import com.stelch.games2.core.Utils.Text;
import gg.stelch.core.Util.en;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class accept {

    public static void get(ProxiedPlayer player, String[] args) {

        ProxyGamePlayer gamePlayer = ProxyGamePlayer.getProxyGamePlayer(args[1]);
        ProxyGamePlayer gamePlayer1 = ProxyGamePlayer.getProxyGamePlayer(player.getName());

        if (!gamePlayer.isStored()) {
            player.sendMessage(Text.build("&aFriends> " + en.general_player_offline));
            return;
        }

        if(gamePlayer1.hasPendingRequest(gamePlayer)){
            gamePlayer1.legacy().sendMessage(Text.build(String.format("&aFriends> &7You are now friends with &e%s&7!",gamePlayer.getRank().getColor()+gamePlayer.getUsername())));
            gamePlayer.legacy().sendMessage(Text.build(String.format("&aFriends> &e%s&7 has accepted your friend request!",gamePlayer1.getRank().getColor()+gamePlayer1.getUsername())));
        }else {
            player.sendMessage(Text.build("&aFriends> &7You have no pending friend requests from this user."));
            return;
        }

        SQL sql = new SQL("35.192.213.70", 3306, "root", "Garcia#02", "games");
        sql.query(String.format("UPDATE `relationships` SET `state`='1' WHERE `player`='%s' AND `target`='%s';", gamePlayer.getUuid(), gamePlayer1.getUuid()), true);
        sql.query(String.format("INSERT INTO `relationships` (`player`,`target`,`state`) VALUES ('%s','%s','1')", gamePlayer1.getUuid(), gamePlayer.getUuid()), true);
    }
}