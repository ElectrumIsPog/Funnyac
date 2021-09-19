package me.electrum.plugin.listener;

import me.electrum.plugin.util.MathUtli;
import net.minecraft.server.v1_8_R3.PacketPlayOutExplosion;
import net.minecraft.server.v1_8_R3.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Collections;

// Flags 3.01 reach and any speed.

public class MoveListener implements Listener {
    private int ticks = 0;

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        double x = event.getTo().getX();
        double z = event.getTo().getZ();
        double x1 = event.getFrom().getX();
        double z2 = event.getFrom().getZ();
        double dX = Math.abs(x - x1);
        double dZ = Math.abs(z - z2);
        double hypot = MathUtli.hypot(dX * dX + dZ * dZ);
        int ping = ((CraftPlayer) event.getPlayer()).getHandle().ping;
        if (ping > 500) {
            ((CraftPlayer) event.getPlayer()).getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(
                    Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE,
                    Float.MAX_VALUE, Collections.EMPTY_LIST,
                    new Vec3D(Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE)));
        }
        if (hypot > 1 && ticks++ > 5 ) {
            event.getPlayer().kickPlayer(String.format("FLAGGED L %s", hypot));
        }
    }
}
