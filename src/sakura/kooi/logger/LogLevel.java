package sakura.kooi.logger;

import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;

public class LogLevel {
    private static HashMap<String, LogLevel> registry = new HashMap<>();

    public static final LogLevel TRACE = register("TRACE", "§7", false);
    public static final LogLevel DEBUG = register("DEBUG", "§6", false);
    public static final LogLevel INFO = register("INFO", "§f", true);
    public static final LogLevel WARN = register("WARN", "§e", true);
    public static final LogLevel ERROR = register("ERROR", "§c", true);
    public static final LogLevel CRIT = register("CRIT", "§4", true);
    public static final LogLevel SUCC = register("SUCC", "§a", true);

    @Getter
    private String tag;
    @Getter
    private String color;
    @Getter
    private boolean enableByDefault;

    private LogLevel(String tag, String color, boolean enableByDefault) {
        this.tag = tag;
        this.color = color;
        this.enableByDefault = enableByDefault;
    }

    public static LogLevel register(@NonNull String tag, @NonNull String color, boolean enableByDefault) {
        if (registry.containsKey(tag))
            throw new IllegalArgumentException("Duplicate tag");
        LogLevel logLevel = new LogLevel(tag, color, enableByDefault);
        registry.put(tag, logLevel);
        return logLevel;
    }
}
