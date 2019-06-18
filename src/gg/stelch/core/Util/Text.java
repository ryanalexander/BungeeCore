package gg.stelch.core.Util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;

public class Text {
  public static BaseComponent[] build(String str) { return (new ComponentBuilder("").appendLegacy(str.replaceAll("&","§")).create()); }

  public static BaseComponent[] build(String text, String hover) { return (new ComponentBuilder("").appendLegacy(text.replaceAll("&","§")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(hover)).create())).create()); }

  public static BaseComponent[] build(String text, String hover, String command, ClickEvent.Action action) { return (new ComponentBuilder("").appendLegacy(text.replaceAll("&","§"))).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(hover)).create())).event(new ClickEvent(action, command)).create(); }

  public static String format(String str) { return (str).replaceAll("&","§"); }
  
  public static boolean isNumeric(String str) { return str.matches("-?\\d+(\\.\\d+)?"); }
}
