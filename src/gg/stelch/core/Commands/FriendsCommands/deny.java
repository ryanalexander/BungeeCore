package gg.stelch.core.Commands.FriendsCommands;

import gg.stelch.core.PlayerUtil.GamePlayer;
import gg.stelch.core.Util.SQL;
import gg.stelch.core.Util.Text;
import gg.stelch.core.Util.en;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class deny {

        public static void get(ProxiedPlayer player, String[] args) {

            GamePlayer gamePlayer = GamePlayer.getGamePlayer(args[1]);
            GamePlayer gamePlayer1 = GamePlayer.getGamePlayer(player.getName());

            if (!gamePlayer.isStored()) {
                player.sendMessage(Text.build("&aFriends> " + en.general_player_offline));
                return;
            }

            if(gamePlayer1.hasPendingRequest(gamePlayer)){
                gamePlayer1.legacy().sendMessage(Text.build(String.format("&aFriends> &7You have declined &e%s&7's friend request.",gamePlayer.getRank().getColor()+gamePlayer.getUsername())));
            }else {
                player.sendMessage(Text.build("&aFriends> &7You have no pending friend requests from this user."));
                return;
            }

            SQL sql = new SQL("35.192.213.70", 3306, "root", "Garcia#02", "games");
            sql.query(String.format("DELETE FROM `relationships` WHERE `player`='%s' AND `target`='%s';", gamePlayer.getUuid(), gamePlayer1.getUuid()), true);
            sql.query(String.format("DELETE FROM `relationships` WHERE `target`='%s' AND `player`='%s';", gamePlayer.getUuid(), gamePlayer1.getUuid()), true);
        }
}