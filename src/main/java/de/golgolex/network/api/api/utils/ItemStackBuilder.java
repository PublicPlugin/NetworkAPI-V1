package de.golgolex.network.api.api.utils;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;

/*
===========================================================================================================================
#
# Copyright (c) 2021 Pascal Kurz
# Class created at 12.08.2021, 04:14
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

public class ItemStackBuilder {

    private final ItemStack stack;
    private Consumer<PlayerInteractEvent> eventConsumer;

    private static final List<ItemStackBuilder> clickableItems = Lists.newArrayList();

    public ItemStackBuilder(Material material) {
        this(material, 1);
    }

    public ItemStackBuilder(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStackBuilder(Material material, int amount) {
        this.stack = new ItemStack(material, amount);
    }

    public ItemStackBuilder(Material material, int amount, short id) {
        this.stack = new ItemStack(material, amount, id);
    }

    public ItemStackBuilder setFireworkEffectMeta(Color color) {
        final ItemMeta itemMeta = this.stack.getItemMeta();
        final FireworkEffectMeta fireworkEffectMeta = (FireworkEffectMeta) itemMeta;
        final FireworkEffect.Builder builder = FireworkEffect.builder();

        builder.withColor(color);
        fireworkEffectMeta.setEffect(builder.build());
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
        stack.setItemMeta(fireworkEffectMeta);
        return this;
    }

    public ItemStackBuilder(Material material, short id) {
        this(material, 1, id);
    }

    public ItemStackBuilder(Material material, byte durability) {
        this(material, 1, durability);
    }

    public ItemStackBuilder(Material material, int amount, byte durability) {
        this.stack = new ItemStack(material, amount, durability);
    }

    public ItemStackBuilder(String link, int amount) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        try {
            Field profileField = Class.forName("org.bukkit.craftbukkit." + org.bukkit.Bukkit.getServer().getClass().getPackage().
                    getName().split("\\.")[3] + ".inventory.CraftMetaSkull").getDeclaredField("profile");
            profileField.setAccessible(true);
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            String base64encoded = Base64.getEncoder().encodeToString(("{textures:{SKIN:{url:\"" + "http://textures.minecraft.net/texture/" + link + "\"}}}").getBytes());
            profile.getProperties().put("textures", new Property("textures", base64encoded));
            profileField.set(meta, profile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        skull.setItemMeta(meta);
        stack = skull;

    }

    public ItemStackBuilder(String link) {
        this(link, 1);
    }

    public ItemStackBuilder setDurability(short dur) {
        stack.setDurability(dur);
        return this;
    }

    public ItemStackBuilder setAmount(Integer amount) {
        stack.setAmount(amount);
        return this;
    }

    public ItemStackBuilder addItemFlag(ItemFlag flag) {
        ItemMeta itemMeta = this.stack.getItemMeta();
        itemMeta.addItemFlags(flag);
        this.stack.setItemMeta(itemMeta);
        return this;
    }

    public ItemStackBuilder setGlow(boolean glow) {
        if (glow) {
            addEnchant(Enchantment.KNOCKBACK, 1);
            addItemFlag(ItemFlag.HIDE_ENCHANTS);
        } else {
            ItemMeta meta = this.stack.getItemMeta();
            for (Enchantment enchantment : meta.getEnchants().keySet()) {
                meta.removeEnchant(enchantment);
            }
        }
        return this;
    }

    public ItemStackBuilder setName(String name) {
        ItemMeta im = stack.getItemMeta();
        im.setDisplayName(name);
        stack.setItemMeta(im);
        return this;
    }

    public ItemStackBuilder setUnbreakable() {
        ItemMeta im = stack.getItemMeta();
        im.spigot().setUnbreakable(true);
        stack.setItemMeta(im);
        return this;
    }

    public ItemStackBuilder addUnsafeEnchantment(Enchantment enchant, int level) {
        stack.addUnsafeEnchantment(enchant, level);
        return this;
    }

    public ItemStackBuilder removeEnchantment(Enchantment enchantment) {
        stack.removeEnchantment(enchantment);
        return this;
    }

    public ItemStackBuilder setSkullOwner(String owner) {
        SkullMeta im = (SkullMeta) stack.getItemMeta();
        im.setOwner(owner);
        stack.setItemMeta(im);
        return this;
    }

    public ItemStackBuilder addEnchant(Enchantment enchantment, int level) {
        ItemMeta im = stack.getItemMeta();
        im.addEnchant(enchantment, level, true);
        stack.setItemMeta(im);
        return this;
    }

    public ItemStackBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
        stack.addEnchantments(enchantments);
        return this;
    }

    public ItemStackBuilder setInfinityDurability() {
        stack.setDurability(Short.MAX_VALUE);
        return this;
    }

    public ItemStackBuilder setLore(String... lore) {
        ItemMeta im = stack.getItemMeta();
        im.setLore(Arrays.asList(lore));
        stack.setItemMeta(im);
        return this;
    }

    public ItemStackBuilder setLore(List<String> lore) {
        ItemMeta im = stack.getItemMeta();
        im.setLore(lore);
        stack.setItemMeta(im);
        return this;
    }

    public ItemStackBuilder removeLoreLine(String line) {
        ItemMeta im = stack.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        if (!lore.contains(line)) return this;
        lore.remove(line);
        im.setLore(lore);
        stack.setItemMeta(im);
        return this;
    }

    public ItemStackBuilder removeLoreLine(int index) {
        ItemMeta im = stack.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        if (index < 0 || index > lore.size()) return this;
        lore.remove(index);
        im.setLore(lore);
        stack.setItemMeta(im);
        return this;
    }

    public ItemStackBuilder addLoreLine(String line) {
        ItemMeta im = stack.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (im.hasLore()) lore = new ArrayList<>(im.getLore());
        lore.add(line);
        im.setLore(lore);
        stack.setItemMeta(im);
        return this;
    }

    public ItemStackBuilder addLoreLine(String line, int pos) {
        ItemMeta im = stack.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        lore.set(pos, line);
        im.setLore(lore);
        stack.setItemMeta(im);
        return this;
    }

    public ItemStackBuilder setFlags() {
        ItemMeta im = stack.getItemMeta();
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        im.addItemFlags(ItemFlag.HIDE_DESTROYS);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        im.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        stack.setItemMeta(im);
        return this;
    }

    public ItemStackBuilder setDyeColor(DyeColor color) {
        this.stack.setDurability(color.getDyeData());
        return this;
    }

    /*public ItemBuilder setWoolColor(DyeColor color) {
        if(!stack.getType().equals(Material.WOOL))return this;
        this.stack.setDurability(color.getData());
        return this;
    }*/

    public ItemStackBuilder setLeatherArmorColor(Color color) {
        LeatherArmorMeta im = (LeatherArmorMeta) stack.getItemMeta();
        im.setColor(color);
        stack.setItemMeta(im);
        return this;
    }


    /*public ItemBuilder setEventConsumer(Consumer<PlayerInteractEvent> eventConsumer) {
        this.eventConsumer = eventConsumer;
        clickableItems.add(this);
        return this;
    }*/

    public ItemStack buildItemStack() {
        return stack;
    }

    public ItemStackBuilder clone() {
        return new ItemStackBuilder(stack);
    }

    /*public static List<ItemBuilder> getClickableItems() {
        return clickableItems;
    }*/

    /*public Consumer<PlayerInteractEvent> getEventConsumer() {
        return eventConsumer;
    }*/

    /*public ItemStack getStack() {
        return stack;
    }*/
}
