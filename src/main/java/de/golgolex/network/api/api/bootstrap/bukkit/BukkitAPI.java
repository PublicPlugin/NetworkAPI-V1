package de.golgolex.network.api.api.bootstrap.bukkit;

/*
===========================================================================================================================
# 
# Copyright (c) 2021 Pascal Kurz
# Class created at 01.09.2021, 21:23
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
import de.golgolex.network.api.api.events.Events;
import de.golgolex.network.api.api.events.spigot.SpigotEventManager;
import de.golgolex.network.api.api.listener.BukkitConnectionListener;
import org.bukkit.plugin.Plugin;

public class BukkitAPI extends NetworkAPI {
    private final Plugin plugin;

    public BukkitAPI(Plugin plugin) {
        super(new SpigotEventManager(plugin));
        this.plugin = plugin;

        new BukkitConnectionListener();
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
