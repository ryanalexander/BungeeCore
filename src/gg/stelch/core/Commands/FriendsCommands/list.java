package gg.stelch.core.Commands.FriendsCommands;

import gg.stelch.core.Main;
import gg.stelch.core.PlayerUtil.GamePlayer;
import gg.stelch.core.Util.SQL;
import gg.stelch.core.Util.Text;
import gg.stelch.core.Varables.ranks;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class list {

    public static void get(ProxiedPlayer p, String[] args) throws SQLException {
        SQL sql = new SQL("35.192.213.70",3306,"root","Garcia#02","games");

        ResultSet results = sql.query(String.format("SELECT * FROM `relationships` WHERE `player` = '%s' AND `state`='1';",p.getUniqueId()));
        ComponentBuilder bc = new ComponentBuilder("");
        int friends = 0;
        while(results.next()){
            friends++;
            GamePlayer target;
            UUID target_uuid=UUID.fromString(results.getString("target"));
            if(Main.players.containsKey(ProxyServer.getInstance().getPlayer(target_uuid))){
                target=Main.players.get(ProxyServer.getInstance().getPlayer(target_uuid));
            }else {
                target=new GamePlayer(results.getString("target"));
            }
            if(target.isonline()){
                bc.append(Text.build("&a"+ranks.valueOf(target.getRank().toString()).getColor()+target.getUsername()+"&r","Click to message","/msg "+target.getUsername()+" ", ClickEvent.Action.SUGGEST_COMMAND));
                bc.append(Text.build(", "));
            }else {
                bc.append(Text.build("&7"+ target.getUsername()+"&r",Text.format("&cUser is offline")));
                bc.append(Text.build(", "));
            }
        }

        if(friends==0){
            p.sendMessage(Text.build("&aFriends> &7You do not have any friends. Try &e/friends add&7!"));
        }else {
            String header = "&6-----------------------------------------------------\n\n&aFriend List\n";
            p.sendMessage(Text.build(header));
            p.sendMessage(Text.build(String.format("&aFriends (%s):",friends)));
            p.sendMessage(bc.create());
            p.sendMessage(Text.build("&6-----------------------------------------------------"));
        }
    }
}