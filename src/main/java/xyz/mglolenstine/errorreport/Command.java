package xyz.mglolenstine.errorreport;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        Main plugin = Main.mainGet();
        if(strings.length == 0) {
            plugin.report(Bukkit.getPlayer(commandSender.getName()), 10, "");
        }else if(strings.length == 1){
            plugin.report(Bukkit.getPlayer(commandSender.getName()), Integer.parseInt(strings[0]), "");
        }else if(strings.length == 2){
            plugin.report(Bukkit.getPlayer(commandSender.getName()), Integer.parseInt(strings[0]), strings[1]);
        }
        return true;
    }
}
