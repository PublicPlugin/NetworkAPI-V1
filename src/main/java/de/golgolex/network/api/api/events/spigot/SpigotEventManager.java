package de.golgolex.network.api.api.events.spigot;

/*
===========================================================================================================================
# 
# Copyright (c) 2021 Pascal Kurz
# Class created at 03.09.2021, 14:16
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
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.jsoup.select.Evaluator;

import java.util.function.Consumer;

public class SpigotEventManager implements Events {

    private final Plugin plugin;

    public SpigotEventManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void call(Event event) {
        this.plugin.getServer().getPluginManager().callEvent(event);
    }

    public <T extends Event> Listener register(Class<T> clazz, Consumer<T> consumer) {
        Listener listener = new Listener() {
        };
        this.plugin.getServer().getPluginManager().registerEvent(clazz, listener, EventPriority.NORMAL, (l, use) -> {
            if (clazz.isInstance(use)) consumer.accept((T) use);
        }, this.plugin, false);
        return listener;
    }

    @Override
    public Object getManager() {
        return this;
    }

}
