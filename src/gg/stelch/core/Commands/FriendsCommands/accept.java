package gg.stelch.core.Commands.FriendsCommands;

import gg.stelch.core.Main;
import gg.stelch.core.PlayerUtil.GamePlayer;
import gg.stelch.core.Util.SQL;
import gg.stelch.core.Util.Text;
import gg.stelch.core.Util.en;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class accept {

    public static void get(ProxiedPlayer player, String[] args) {

        GamePlayer gamePlayer = GamePlayer.getGamePlayer(args[1]);
        GamePlayer gamePlayer1 = GamePlayer.getGamePlayer(player.getName());

        if (!gamePlayer.isStored()) {
            player.sendMessage(Text.build("&aFriends> " + en.general_player_offline));
            return;
        }

        if(gamePlayer1.hasPendingRequest(gamePlayer)){
            player.sendMessage("Request has been accepted");
        }else {
            player.sendMessage("No pending requests from this user.");
        }

        SQL sql = new SQL("35.192.213.70", 3306, "root", "Garcia#02", "games");
        sql.query(String.format("INSERT INTO `relationships` (`player`,`target`,`state`) VALUES ('%s','%s','0')", player.getUniqueId(), gamePlayer.getUuid()), true);
    }
}