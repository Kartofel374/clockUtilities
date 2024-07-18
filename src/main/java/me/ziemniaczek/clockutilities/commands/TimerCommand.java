package me.ziemniaczek.clockutilities.commands;

import me.ziemniaczek.clockutilities.ClockUtilities;
import me.ziemniaczek.clockutilities.threads.ScoreboardUpdateTime;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

public class TimerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String sMethodArg, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You can't execute this command!");
            return true;
        }
        Player p = (Player) sender;
        int h, m, s;
        h=m=s=0;
        try {
            switch (args.length) {
                case 3:
                    h += Integer.parseInt(args[2]);
                case 2:
                    m += Integer.parseInt(args[1]);
                case 1:
                    s += Integer.parseInt(args[0]);
                    break;
                default:
                    return false;
            }
        } catch (NumberFormatException e) {
            sender.sendMessage("Time must be an positive integer!");
            return true;
        }
        if ((h+m+s) <= 0 || h < 0 || m < 0 || s < 0) {
            sender.sendMessage("Time must be an positive integer!");
            return true;
        }
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard board = scoreboardManager.getNewScoreboard();
        new ScoreboardUpdateTime(p, board, h, m, s).start();
        return true;
    }
}
