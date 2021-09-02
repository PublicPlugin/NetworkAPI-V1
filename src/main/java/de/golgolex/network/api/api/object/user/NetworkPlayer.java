package de.golgolex.network.api.api.object.user;

/*
===========================================================================================================================
# 
# Copyright (c) 2021 Pascal Kurz
# Class created at 01.09.2021, 21:27
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

import de.golgolex.network.api.api.NetworkAPI;
import net.md_5.bungee.api.ProxyServer;
import org.bukkit.Bukkit;

import java.util.UUID;

public class NetworkPlayer implements INetworkPlayer {

    private final String name;
    private final UUID uniqueId;
    private int coins;
    private boolean notify;
    private boolean autoGG;

    public NetworkPlayer(String name, UUID uniqueId) {
        this.name = name;
        this.uniqueId = uniqueId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    public Object getPlayer() {
        if (NetworkAPI.getInstance().isProxyInstance()) {
            return ProxyServer.getInstance().getPlayer(this.uniqueId);
        } else {
            return Bukkit.getPlayer(this.uniqueId);
        }
    }

    @Override
    public int getCoins() {
        return coins;
    }

    @Override
    public void setCoins(int coins) {
        this.coins = coins;
    }

    @Override
    public void addCoins(int coins) {
        this.coins = (this.coins + coins);
    }

    @Override
    public void removeCoins(int coins) {
        this.coins = (this.coins - coins);
    }

    @Override
    public void resetCoins() {
        this.coins = 0;
    }

    @Override
    public boolean isNotify() {
        return notify;
    }

    @Override
    public boolean isAutoGG() {
        return autoGG;
    }

    @Override
    public void setNotify(boolean bol) {
        this.notify = bol;
    }

    @Override
    public void setAutoGG(boolean bol) {
        this.autoGG = bol;
    }
}
