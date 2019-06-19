package gg.stelch.core.Commands.FriendsCommands;

import gg.stelch.core.Main;
import gg.stelch.core.PlayerUtil.GamePlayer;
import gg.stelch.core.Util.SQL;
import gg.stelch.core.Util.Text;
import gg.stelch.core.Util.en;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class add {

    public static void get(ProxiedPlayer player, String[] args){

        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[1]);

        GamePlayer target_player = GamePlayer.getGamePlayer(target.getName());

        GamePlayer gamePlayer = GamePlayer.getGamePlayer(args[1]);


        if(!gamePlayer.isStored()){player.sendMessage(Text.build("&aFriends> "+en.general_player_offline));return;}

        if(gamePlayer.hasPendingRequest(target_player)){player.sendMessage(Text.build("&aFriends> &7You already have a pending friend request for that user."));}

        if(target_player.isFriends(gamePlayer)){player.sendMessage(Text.build("&aFriends> &7You are already friends with that user."));return;}

        if(gamePlayer.isonline()){
            target.sendMessage(Text.build(String.format("&aFriends> &7You have a new friend request from &e%s\n\n",player.getName())));
            target.sendMessage(new ComponentBuilder("    ").reset().append("ACCEPT").color(ChatColor.GREEN).event(
                    new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                            new ComponentBuilder(Text.format("&aClick to accept.")).create())
            ).event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/friend accept "+player.getName())).append("     ").reset()
                    .append("DECLINE").color(ChatColor.RED).event(
                            new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                    new ComponentBuilder(Text.format("&cClick to deny.")).create())
                    ).event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/friend deny "+player.getName())).create());
            player.sendMessage(Text.build("&aFriends> &7Your friend request has been delivered"));
        }else {
            player.sendMessage(Text.build(String.format("&aFriends> &7The player %s&7 is currently offline, they will receive the request next time they join.",gamePlayer.getRank().getColor()+gamePlayer.getUsername())));
        }
        SQL sql = new SQL("35.192.213.70",3306,"root","Garcia#02","games");
        sql.query(String.format("INSERT INTO `relationships` (`player`,`target`,`state`) VALUES ('%s','%s','0')",player.getUniqueId(),gamePlayer.getUuid()),true);

    }

}
