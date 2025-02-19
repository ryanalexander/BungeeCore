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

package gg.stelch.core.Commands;

import com.stelch.games2.core.Utils.Text;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

public class CommandBungee extends Command {
    public CommandBungee() { super("bungee", ""); }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        commandSender.sendMessage(Text.build("\u00A79This server is running BungeeCord version Stelch by md_5 and Aspire"));

    }
}
