package de.golgolex.network.api.api.utils.scoreboard;

import com.google.common.collect.Maps;
import de.golgolex.network.api.api.object.user.INetworkPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Map;

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

public class CustomScoreboard {

    private final String[] codes = new String[]{"a", "b", "c", "d", "e", "f"};
    private final INetworkPlayer<?> iNetworkPlayer;
    private final Map<Integer, String> integerStringConcurrentMap = Maps.newConcurrentMap();

    public CustomScoreboard(final INetworkPlayer<?> iNetworkPlayer) {
        this.iNetworkPlayer = iNetworkPlayer;
    }

    public CustomScoreboard setScore(int score, String string) {
        String[] split = this.getBoardName(string);
        this.integerStringConcurrentMap.put(score, split[0] + ";" + split[1]);
        return this;
    }

    public String getColorTeam(int number) {
        if(number > 9 && number < 16) return this.codes[number-10];
        return "z";
    }

    public CustomScoreboard updateScore(int score, String display) {
        Team team = ((Player) iNetworkPlayer.getPlayer()).getScoreboard().getTeam("x" + score);
        String[] split = this.getBoardName(display);
        setTeamDisplay(team, split);
        return this;
    }

    private String[] getBoardName(String display){
        if(display.length() > 30) return new String[]{" ", " "};
        if(display.length() > 16){
            String lastColors = ChatColor.getLastColors(display.substring(0,16));
            String left = display.substring(0, 16);
            String right = display.substring(16);
            if(left.endsWith("§")) {
                left = left.substring(0, 15);
                right = "§" + right;
            }
            return new String[]{left, lastColors + right};
        }
        else return new String[]{display, " "};
    }


    public Scoreboard getPlayerScoreboard(){
        return ((Player) iNetworkPlayer.getPlayer()).getScoreboard();
    }

    public CustomScoreboard updateBoard(String display) {
        getPlayerScoreboard().getObjective("aaa").setDisplayName(display);
        return this;
    }

    public void build(final String display) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("aaa", "bbb");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(display);

        for(int i = 0; i < 20; ++i) {
            if (this.integerStringConcurrentMap.get(i) != null) {
                Team team = scoreboard.registerNewTeam("x" + i);
                setTeamDisplay(team, (this.integerStringConcurrentMap.get(i)).split(";"));
                team.addEntry((i < 10 ? "§" + i : "§" + this.getColorTeam(i)));
                objective.getScore((i < 10 ? "§" + i : "§" + this.getColorTeam(i))).setScore(i);
                ((Player) iNetworkPlayer.getPlayer()).setScoreboard(scoreboard);
            }
        }
    }

    public void setTeamDisplay(Team team, String[] display){
        team.setPrefix(display[0]);
        team.setSuffix(display[1]);
    }

    public String[] getCodes() {
        return codes;
    }

    public INetworkPlayer<?> getINetworkPlayer() {
        return iNetworkPlayer;
    }

    public Map<Integer, String> getIntegerStringConcurrentMap() {
        return integerStringConcurrentMap;
    }
}
