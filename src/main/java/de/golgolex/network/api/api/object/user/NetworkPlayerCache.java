package de.golgolex.network.api.api.object.user;

/*
===========================================================================================================================
# 
# Copyright (c) 2021 Pascal Kurz
# Class created at 01.09.2021, 21:32
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

import java.util.ArrayList;
import java.util.UUID;

public class NetworkPlayerCache extends ArrayList<INetworkPlayer> implements INetworkPlayerCache {

    @Override
    public void registerNetworkPlayer(INetworkPlayer<?> iNetworkPlayer) {
        add(iNetworkPlayer);
    }

    @Override
    public void unregisterNetworkPlayer(INetworkPlayer<?> iNetworkPlayer) {
        remove(iNetworkPlayer);
    }

    @Override
    public void unregisterNetworkPlayer(String name) {
        remove( stream().filter(iNetworkPlayer -> iNetworkPlayer.getName().equalsIgnoreCase(name)).findFirst().orElse(null) );
    }

    @Override
    public void unregisterNetworkPlayer(UUID uuid) {
        remove( stream().filter(iNetworkPlayer -> iNetworkPlayer.getUniqueId().equals(uuid)).findFirst().orElse(null) );
    }

    @Override
    public INetworkPlayer<?> getINetworkPlayer(String name) {
        return stream().filter(iNetworkPlayer -> iNetworkPlayer.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    @Override
    public INetworkPlayer<?> getINetworkPlayer(UUID uuid) {
        return stream().filter(iNetworkPlayer -> iNetworkPlayer.getUniqueId().equals(uuid)).findFirst().orElse(null);
    }

    @Override
    public ArrayList<INetworkPlayer<?>> getINetworkPlayers() {
        return new ArrayList<>(this);
    }

}
