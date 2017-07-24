package xyz.mglolenstine.errorreport;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class Main extends JavaPlugin {
    private static Main plugin;
    public void onEnable(){
        plugin = this;
        this.getCommand("erreport").setExecutor(new Command());
    }

    static Main mainGet(){
        return plugin;
    }

    @SuppressWarnings("unused")
    void report(Player p, int n, String s){
        File log = new File(getDataFolder().getParentFile().getParentFile(), "logs/latest.log");
        int counter = 0;
        String tmp;
        int tmpn;
        try {
            //noinspection deprecation
            ReversedLinesFileReader object = new ReversedLinesFileReader(log);
            if((tmpn = countLines(log)) < n){
                n = tmpn;
            }
            while(!(tmp = object.readLine()).equals("")  && counter < n) {
                if(tmp.contains(s)) {
                    p.sendMessage(tmp);
                }
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int countLines(File f) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(f));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }
}
