package com.mcprohosting.plugins.dynamicbukkit.utils;

import com.mcprohosting.plugins.dynamicbukkit.data.NetTask;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class DynamicUtils {

    public static void sendPlayerToServer(Player player, String server) {
        NetTask.withName("send")
                .withArg("player", player.getName())
                .withArg("server", server).send("dynamicbungee");
    }
    
    public static String getIp() throws Exception {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine();
            return ip;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
