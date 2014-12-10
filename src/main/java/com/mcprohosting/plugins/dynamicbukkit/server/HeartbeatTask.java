package com.mcprohosting.plugins.dynamicbukkit.server;

import com.mcprohosting.plugins.dynamicbukkit.DynamicBukkit;
import com.mcprohosting.plugins.dynamicbukkit.data.NetTask;
import com.mcprohosting.plugins.dynamicbukkit.utils.DynamicUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HeartbeatTask implements Runnable {

    /**
     * Will send a single heartbeat.
     */
    @Override
    public void run() {
        String externalIP = DynamicBukkit.getPlugin().getConf().server_externalip;
        
        try {
        if(externalIP.equals("auto")){
         externalIP = DynamicUtils.getIp();
        }
        
        List<String> players = new ArrayList<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            players.add(player.getName());
        }

        NetTask.withName("heartbeat")
                .withArg("name", DynamicBukkit.getPlugin().getConf().server_name)
                .withArg("ip", externalIP.equals("") ? Bukkit.getIp() : externalIP)
                .withArg("port", Bukkit.getPort())
                .withArg("players", players)
                .send("heartbeat");
        } catch (Exception e){
            e.printStackTrace();
        }
        schedule();
    }

    /**
     * Reschedule the heartbeat task to be sent again in 40 ticks (2 seconds)
     */
    private void schedule() {
        Bukkit.getScheduler().runTaskLaterAsynchronously(DynamicBukkit.getPlugin(), this, 40);
    }
}
