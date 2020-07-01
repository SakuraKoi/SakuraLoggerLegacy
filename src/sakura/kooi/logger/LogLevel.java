package sakura.kooi.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class LogLevel {
    public static final LogLevel TRACE = new LogLevel("TRACE", "§7", false);
    public static final LogLevel DEBUG = new LogLevel("DEBUG", "§6", false);
    public static final LogLevel INFO = new LogLevel("INFO", "§f", true);
    public static final LogLevel WARN = new LogLevel("WARN", "§e", true);
    public static final LogLevel ERROR = new LogLevel("ERROR", "§c", true);
    public static final LogLevel CRIT = new LogLevel("CRIT", "§4", true);
    public static final LogLevel SUCC = new LogLevel("SUCC", "§a", true);

    @Getter
    private String tag;
    @Getter
    private String color;
    @Getter
    private boolean enableByDefault;
}
