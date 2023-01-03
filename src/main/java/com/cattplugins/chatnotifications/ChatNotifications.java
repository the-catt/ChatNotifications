package com.cattplugins.chatnotifications;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;

public final class ChatNotifications extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Chat Notifications is starting...");

        getServer().getPluginManager().registerEvents(this, this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label,String[] args)
    {
        // /cndisable - disables the plugin for you
        if (command.getName().equalsIgnoreCase("cndisable")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                PermissionAttachment pa = player.addAttachment(this);
                pa.setPermission("chatnotifs.block", true);
                player.sendMessage(ChatColor.GOLD + "Chat Notifications disabled.");
            }
        }
        // /cnenable - enables the plugin for you
        if (command.getName().equalsIgnoreCase("cnenable")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                PermissionAttachment pa = player.addAttachment(this);
                pa.setPermission("chatnotifs.block", false);
                player.sendMessage(ChatColor.GOLD + "Chat Notifications enabled.");
            }
        }
        return true;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerSpeak(AsyncPlayerChatEvent event){
        playSound();
    }
    public void playSound(){
    for(Player oPlayer : this.getServer().getOnlinePlayers()){
        if (!oPlayer.hasPermission("chatnotifs.block"))
        {
            oPlayer.playSound(oPlayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
        }
    }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Chat notifications is shutting down...");
    }
}
