package sakura.kooi.logger;

import lombok.Getter;
import lombok.Setter;
import org.fusesource.jansi.Ansi;
import sakura.kooi.logger.preprocesser.IPreprocessor;
import sakura.kooi.logger.utils.Formatter;
import sakura.kooi.logger.utils.LoggerUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@SuppressWarnings("unused")
public class Logger {
    private static ConcurrentMap<String, Logger> loggerMap = new ConcurrentHashMap<>();

    @Getter @Setter
    private SakuraLogger backend = SakuraLogger.INSTANCE;
    static {
        SakuraLogger dummy = SakuraLogger.INSTANCE; // force initialize SakuraLogger class
    }

    private final HashMap<LogLevel, Boolean> logLevel = new HashMap<>();
    @Getter
    private LinkedList<IPreprocessor> preprocessors = new LinkedList<>();

    private final String module;

    private Logger(final String module) {
        this.module = module;
        //noinspection ConstantConditions
        if (backend != null) {
            logLevel.putAll(backend.logLevel);
            preprocessors.addAll(backend.preprocessors);
        }
    }

    public static Logger of(final String module) {
        return loggerMap.computeIfAbsent(module, Logger::new);
    }

    public static Logger create(final String module) {
        return new Logger(module);
    }

    public void overlap() {
        if (backend.isSupportOverlap())
            System.out.print(Ansi.ansi().cursorUpLine().eraseLine(Ansi.Erase.ALL).toString());
    }

    //region Basic logging
    public void trace(String s) {
        if (isLogLevelEnabled(LogLevel.TRACE))
            log(LogLevel.TRACE, s);
    }

    public void debug(String s) {
        if (isLogLevelEnabled(LogLevel.DEBUG))
            log(LogLevel.DEBUG, s);
    }

    public void info(String s) {
        if (isLogLevelEnabled(LogLevel.INFO))
            log(LogLevel.INFO, s);
    }

    public void warn(String s) {
        if (isLogLevelEnabled(LogLevel.WARN))
            log(LogLevel.WARN, s);
    }

    public void error(String s) {
        if (isLogLevelEnabled(LogLevel.ERROR))
            log(LogLevel.ERROR, s);
    }

    public void success(String s) {
        if (isLogLevelEnabled(LogLevel.SUCC))
            log(LogLevel.SUCC, s);
    }

    public void crit(String s) {
        if (isLogLevelEnabled(LogLevel.CRIT))
            log(LogLevel.CRIT, s);
    }
    //endregion
    
    //region Array format
    public void trace(String s, Object... objects) {
        if (isLogLevelEnabled(LogLevel.TRACE))
            trace(Formatter.arrayFormat(s, objects));
    }

    public void debug(String s, Object... objects) {
        if (isLogLevelEnabled(LogLevel.DEBUG))
            debug(Formatter.arrayFormat(s, objects));
    }

    public void info(String s, Object... objects) {
        if (isLogLevelEnabled(LogLevel.INFO))
            info(Formatter.arrayFormat(s, objects));
    }

    public void warn(String s, Object... objects) {
        if (isLogLevelEnabled(LogLevel.WARN))
            warn(Formatter.arrayFormat(s, objects));
    }

    public void error(String s, Object... objects) {
        if (isLogLevelEnabled(LogLevel.ERROR))
            error(Formatter.arrayFormat(s, objects));
    }

    public void crit(String s, Object... objects) {
        if (isLogLevelEnabled(LogLevel.CRIT))
            crit(Formatter.arrayFormat(s, objects));
    }

    public void success(String s, Object... objects) {
        if (isLogLevelEnabled(LogLevel.SUCC))
            success(Formatter.arrayFormat(s, objects));
    }

    //endregion
    //region Exception only
    public void trace(Throwable throwable) {
        if (isLogLevelEnabled(LogLevel.TRACE))
            trace(LoggerUtils.getStackTraceAsString(throwable));
    }

    public void debug(Throwable throwable) {
        if (isLogLevelEnabled(LogLevel.DEBUG))
            debug(LoggerUtils.getStackTraceAsString(throwable));
    }

    public void info(Throwable throwable) {
        if (isLogLevelEnabled(LogLevel.INFO))
            info(LoggerUtils.getStackTraceAsString(throwable));
    }

    public void warn(Throwable throwable) {
        if (isLogLevelEnabled(LogLevel.WARN))
            warn(LoggerUtils.getStackTraceAsString(throwable));
    }

    public void error(Throwable throwable) {
        if (isLogLevelEnabled(LogLevel.ERROR))
            error(LoggerUtils.getStackTraceAsString(throwable));
    }

    public void crit(Throwable throwable) {
        if (isLogLevelEnabled(LogLevel.CRIT))
            crit(LoggerUtils.getStackTraceAsString(throwable));
    }

    //endregion

    //region Custom logLevel
    public void log(LogLevel logLevel, String s) {
        if (isLogLevelEnabled(logLevel)) {
            for (IPreprocessor preprocessor : preprocessors) {
                s = preprocessor.preprocess(s);
            }
            if (s == null || s.isEmpty()) return;
            backend.getLogQueue().log(backend, module, logLevel, s);
        }
    }

    public void log(LogLevel logLevel, String s, Object... objects) {
        if (isLogLevelEnabled(logLevel)) {
            for (IPreprocessor preprocessor : preprocessors) {
                s = preprocessor.preprocess(s);
            }
            if (s == null || s.isEmpty()) return;
            log(logLevel, Formatter.arrayFormat(s, objects));
        }
    }

    public void log(LogLevel logLevel, Throwable throwable) {
        if (isLogLevelEnabled(logLevel)) {
            String s = LoggerUtils.getStackTraceAsString(throwable);
            for (IPreprocessor preprocessor : preprocessors) {
                s = preprocessor.preprocess(s);
            }
            if (s == null || s.isEmpty()) return;
            log(logLevel, s);
        }
    }
    //endregion
    
    public boolean isLogLevelEnabled(LogLevel level) {
        return logLevel.computeIfAbsent(level, LogLevel::isEnableByDefault);
    }

    public void setLogLevelEnabled(LogLevel level, boolean enabled) {
        logLevel.put(level, enabled);
    }
}
