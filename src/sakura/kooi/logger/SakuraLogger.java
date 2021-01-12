package sakura.kooi.logger;

import lombok.Getter;
import lombok.Setter;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import sakura.kooi.logger.formatter.DefaultLogFormatter;
import sakura.kooi.logger.formatter.ILogFormatter;
import sakura.kooi.logger.preprocesser.IPreprocessor;
import sakura.kooi.logger.queue.AsyncLogQueue;
import sakura.kooi.logger.queue.BlockingLogQueue;
import sakura.kooi.logger.queue.ILogQueue;
import sakura.kooi.logger.utils.LoggerUtils;
import sakura.kooi.logger.writer.ConsoleLogWriter;
import sakura.kooi.logger.writer.ILogWriter;

import java.util.HashMap;
import java.util.LinkedList;

public class SakuraLogger {
    protected static final HashMap<LogLevel, Boolean> logLevel = new HashMap<>();
    @Getter
    protected static LinkedList<IPreprocessor> preprocessors = new LinkedList<>();

    @Getter @Setter
    private static ILogFormatter formatter = new DefaultLogFormatter();
    @Getter @Setter
    private static ILogWriter writer = new ConsoleLogWriter();
    @Getter
    private static ILogQueue logQueue = new BlockingLogQueue();

    @Getter @Setter
    private static boolean supportOverlap = false;

    private static final LogLevel LEVEL_CONTROL = LogLevel.register("CTRL", "", true);

    public static void enableColorSupport() {
        if (!LoggerUtils.runningInIdea()) {
            AnsiConsole.systemInstall();
        }
    }

    public static void enableAsyncPrinting() {
        if (logQueue instanceof AsyncLogQueue) return;
        logQueue = new AsyncLogQueue();
    }

    public static void clearScreen() {
        logQueue.log("Logger", LEVEL_CONTROL, Ansi.ansi().eraseScreen().toString());
    }

    public static boolean isLogLevelEnabled(LogLevel level) {
        return logLevel.computeIfAbsent(level, LogLevel::isEnableByDefault);
    }

    public static void setLogLevelEnabled(LogLevel level, boolean enabled) {
        logLevel.put(level, enabled);
    }
}
