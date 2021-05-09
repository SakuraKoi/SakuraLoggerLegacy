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
    public static final SakuraLogger INSTANCE = new SakuraLogger();

    protected HashMap<LogLevel, Boolean> logLevel;
    @Getter
    protected LinkedList<IPreprocessor> preprocessors;

    @Getter @Setter
    private ILogFormatter formatter;
    @Getter @Setter
    private ILogWriter writer;
    @Getter
    private ILogQueue logQueue;

    @Getter @Setter
    private boolean supportOverlap = false;

    private static final LogLevel LEVEL_CONTROL = LogLevel.register("CTRL", "", true);


    public SakuraLogger() {
        logLevel = new HashMap<>();
        preprocessors = new LinkedList<>();
        formatter = new DefaultLogFormatter();
        writer = new ConsoleLogWriter();
        logQueue = new BlockingLogQueue();
    }

    public SakuraLogger(SakuraLogger from) {
        this();
        this.logLevel.putAll(from.logLevel);
        this.preprocessors.addAll(from.preprocessors);
        this.formatter = from.formatter;
        this.writer = from.writer;
        this.logQueue = from.logQueue;
        this.supportOverlap = from.supportOverlap;
    }

    public void enableColorSupport() {
        if (!LoggerUtils.runningInIdea()) {
            AnsiConsole.systemInstall();
        }
    }

    public void enableAsyncPrinting() {
        if (logQueue instanceof AsyncLogQueue) return;
        logQueue = new AsyncLogQueue();
    }

    public void clearScreen() {
        logQueue.log(this, "Logger", LEVEL_CONTROL, Ansi.ansi().eraseScreen().toString());
    }

    public boolean isLogLevelEnabled(LogLevel level) {
        return logLevel.computeIfAbsent(level, LogLevel::isEnableByDefault);
    }

    public void setLogLevelEnabled(LogLevel level, boolean enabled) {
        logLevel.put(level, enabled);
    }

    public SakuraLogger copy() {
        return new SakuraLogger(this);
    }
}
