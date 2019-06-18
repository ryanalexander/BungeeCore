package gg.stelch.core.PlayerUtil;

import gg.stelch.core.Util.SQL;
import gg.stelch.core.Util.Text;
import gg.stelch.core.Varables.ranks;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.ResultSet;
import java.util.UUID;

public class GamePlayer {

    private ProxiedPlayer player;

    private ServerInfo server;

    private String uuid;

    private String username=null;

    private ranks rank;

    private boolean stored = false;

    private boolean retrying = false;

    public GamePlayer(String UUID){
        ProxiedPlayer p = ProxyServer.getInstance().getPlayer(java.util.UUID.fromString(UUID));
        this.uuid=UUID;
        if(p!=null) {
            this.username=p.getName();
            this.player=p;
        }
        resolvePlayer();
    }

    public void resolvePlayer() {
        SQL sql = new SQL("35.192.213.70",3306,"root","Garcia#02","games");
        ResultSet results = sql.query(String.format("SELECT * FROM `players` WHERE `uuid` = '%s'",this.uuid));
        try {
            while (results.next()) {
                this.username=results.getString("username");
                this.rank=ranks.valueOf(results.getString("rank").toUpperCase());
                this.stored=true;
            }
        } catch (Exception e){
            if(retrying){
                this.username="undefined";
                e.printStackTrace();
                this.retrying=false;
            }else {
                createPlayer();
                this.retrying=true;
                resolvePlayer();
            }
        }
        if(!this.stored){
            createPlayer();
            resolvePlayer();
        }
    }

    private void createPlayer() {
        SQL sql = new SQL("35.192.213.70",3306,"root","Garcia#02","games");
        sql.query(String.format("INSERT INTO `games`.`players` (`username`, `uuid`, `rank`) VALUES ('%s', '%s', '%s')",this.player.getName(),this.uuid,ranks.MEMBER),true);
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }

    public ranks getRank() {
        return rank;
    }

    public ServerInfo getServer() {
        return server;
    }

    public String getUsername() {
        return username;
    }

    public String getUuid() {
        return uuid;
    }

    public void setPlayer(ProxiedPlayer player) {
        this.player = player;
    }

    public void setRank(ranks rank) {
        this.rank = rank;
    }

    public void setServer(ServerInfo server) {
        this.server = server;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public boolean isonline() {return ((player!=null)&&player.isConnected());}

}
