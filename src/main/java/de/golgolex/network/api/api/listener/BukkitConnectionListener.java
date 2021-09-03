package de.golgolex.network.api.api.listener;

/*
===========================================================================================================================
# 
# Copyright (c) 2021 Pascal Kurz
# Class created at 01.09.2021, 21:35
# Class created by: Pascal
# 
# Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation 
# files (the "Software"),
# to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, 
# distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software 
# is furnished to do so, subject to the following conditions:
# 
# The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
# 
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
# INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE 
# AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
#  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
# 
===========================================================================================================================
*/

import de.dytanic.cloudnetcore.api.event.network.CloudInitEvent;
import de.golgolex.network.api.api.NetworkAPI;
import de.golgolex.network.api.api.events.EventState;
import de.golgolex.network.api.api.events.Events;
import de.golgolex.network.api.api.events.spigot.SpigotEventManager;
import de.golgolex.network.api.api.events.spigot.event.NetworkPlayerJoinEvent;
import de.golgolex.network.api.api.object.user.NetworkPlayer;
import de.golgolex.network.api.api.object.user.INetworkPlayer;
import de.golgolex.network.api.api.utils.BukkitRunTaskHelper;
import de.golgolex.network.api.cloud.CloudSimplifier;
import de.golgolex.network.api.cloud.manager.IPlayerManager;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BukkitConnectionListener {

    private final Events<?> events;
    private final SpigotEventManager spigotEventManager;

    public BukkitConnectionListener() {
        this.events = NetworkAPI.getInstance().getEvents();
        this.spigotEventManager = (SpigotEventManager) events.getManager();
        registerLogin();
        registerQuit();
    }

    public void registerLogin() {
        this.spigotEventManager.register(PlayerLoginEvent.class, event -> {
            if (NetworkAPI.getInstance().getiNetworkPlayerCache().getINetworkPlayer(event.getPlayer().getName()) == null) {
                NetworkAPI.getInstance().getiNetworkPlayerCache().registerNetworkPlayer(new NetworkPlayer(event.getPlayer().getName(),
                        event.getPlayer().getUniqueId()));
            }

            BukkitRunTaskHelper.runDelayAsync(() -> {
                final INetworkPlayer<?> iNetworkPlayer = NetworkAPI.getInstance().getiNetworkPlayerCache().getINetworkPlayer(event.getPlayer().getName());
                iNetworkPlayer.fetchData();
                this.spigotEventManager.call(new NetworkPlayerJoinEvent(iNetworkPlayer, EventState.SUCCESS));
            }, 0);
        });

    }

    public void registerQuit() {
        this.spigotEventManager.register(PlayerQuitEvent.class, event -> {
            final INetworkPlayer<?> iNetworkPlayer = NetworkAPI.getInstance().getiNetworkPlayerCache().getINetworkPlayer(event.getPlayer().getName());
            NetworkAPI.getInstance().getiNetworkPlayerCache().unregisterNetworkPlayer(iNetworkPlayer);
        });
    }

}
