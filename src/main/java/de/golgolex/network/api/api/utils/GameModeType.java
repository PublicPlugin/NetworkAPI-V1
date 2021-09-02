package de.golgolex.network.api.api.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.GameMode;

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

@Getter
@AllArgsConstructor
public enum GameModeType {

    CREATIVE(1, GameMode.CREATIVE, "CREATIVE"),
    SURVIVAL(0, GameMode.SURVIVAL, "SURVIVAL"),
    SPECTATOR(3, GameMode.SPECTATOR, "SPECTATOR"),
    ADVENTURE(2, GameMode.ADVENTURE, "ADVENTURE"),
    ;

    private final int i;
    private final GameMode bukkitGameMode;
    private final String name;

}
