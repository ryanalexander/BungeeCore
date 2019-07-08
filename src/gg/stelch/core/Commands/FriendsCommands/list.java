package gg.stelch.core.Commands.FriendsCommands;

import com.stelch.games2.core.BungeeCore;
import com.stelch.games2.core.PlayerUtils.ProxyGamePlayer;
import com.stelch.games2.core.PlayerUtils.ranks;
import com.stelch.games2.core.Utils.SQL;
import gg.stelch.core.Main;
import com.stelch.games2.core.Utils.Text;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import redis.clients.jedis.Jedis;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.UUID;

public class list {

    public static void get(ProxiedPlayer p, String[] args) throws SQLException {

        try (Jedis jedis = BungeeCore.pool.getResource()){
            Set<String> friends = jedis.smembers(String.format("PLAYER|%s|relationships",p.getUniqueId()));
            ComponentBuilder bc = new ComponentBuilder("");
            int count = 0;
            for(String s : friends){
                count++;
                ProxyGamePlayer target;
                UUID target_uuid=UUID.fromString(s);
                target=ProxyGamePlayer.getProxyGamePlayer(target_uuid);
                if(target.isonline()){
                    bc.append(Text.build("&a"+ ranks.valueOf(target.getRank().toString()).getColor()+target.getUsername()+"&r","Click to message","/msg "+target.getUsername()+" ", ClickEvent.Action.SUGGEST_COMMAND));
                    bc.append(Text.build(", "));
                }else {
                    bc.append(Text.build("&7"+ target.getUsername()+"&r",Text.format("&cUser is offline")));
                    bc.append(Text.build(", "));
                }
            }
            if(count==0){
                p.sendMessage(Text.build("&aFriends> &7You do not have any friends. Try &e/friends add&7!"));
            }else {
                String header = "&6-----------------------------------------------------\n\n&aFriend List\n";
                p.sendMessage(Text.build(header));
                p.sendMessage(Text.build(String.format("&aFriends (%s):",count)));
                p.sendMessage(bc.create());
                p.sendMessage(Text.build("&6-----------------------------------------------------"));
            }
        }
    }
}