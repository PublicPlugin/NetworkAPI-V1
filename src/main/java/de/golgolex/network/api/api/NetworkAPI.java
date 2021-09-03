package de.golgolex.network.api.api;

/*
===========================================================================================================================
# 
# Copyright (c) 2021 Pascal Kurz
# Class created at 01.09.2021, 21:07
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

import de.golgolex.network.api.api.events.Events;
import de.golgolex.network.api.api.object.user.NetworkPlayerCache;
import de.golgolex.network.api.api.object.user.INetworkPlayerCache;
import de.golgolex.network.api.database.mongod.IMongoConnector;
import de.golgolex.network.api.database.mongod.IMongoFetcher;
import de.golgolex.network.api.database.mongod.MongoConnector;
import de.golgolex.network.api.database.mongod.SimpleMongoFetcher;
import de.golgolex.network.api.api.bootstrap.bukkit.BukkitAPI;
import de.golgolex.network.api.api.bootstrap.bungeecord.ProxyAPI;
import de.golgolex.network.api.database.DatabaseAPI;

import javax.xml.crypto.Data;

public abstract class NetworkAPI {

    private static NetworkAPI instance;

    protected final INetworkPlayerCache iNetworkPlayerCache;
    protected final IMongoConnector networkPlayerMongoConnector;
    protected final IMongoFetcher networkPlayerMongoPlayer;
    protected final Events<?> events;

    protected NetworkAPI(Events<?> events) {
        instance = this;
        this.iNetworkPlayerCache = new NetworkPlayerCache();
        this.networkPlayerMongoConnector = DatabaseAPI.getService().getIMongoConnector();
        this.networkPlayerMongoConnector.connect("", "", "", "networkPlayers");
        this.networkPlayerMongoPlayer = DatabaseAPI.getService().getIMongoFetcher("networkPlayers");
        this.events = events;
    }

    public Events<?> getEvents() {
        return events;
    }

    public BukkitAPI getBukkitAPI() {
        return (BukkitAPI) this;
    }

    public ProxyAPI getProxyAPI() {
        return (ProxyAPI) this;
    }

    public IMongoConnector getNetworkPlayerMongoConnector() {
        return networkPlayerMongoConnector;
    }

    public IMongoFetcher getNetworkPlayerMongoPlayer() {
        return networkPlayerMongoPlayer;
    }

    public static NetworkAPI getInstance() {
        return instance;
    }

    public INetworkPlayerCache getiNetworkPlayerCache() {
        return iNetworkPlayerCache;
    }

    public boolean isProxyInstance() {
        return (this instanceof ProxyAPI);
    }

}
