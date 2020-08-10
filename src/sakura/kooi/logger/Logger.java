package sakura.kooi.logger;

import sakura.kooi.logger.utils.Formatter;
import sakura.kooi.logger.utils.LoggerUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Logger {
    private static ConcurrentMap<String, Logger> loggerMap = new ConcurrentHashMap<>();
    private final String module;

    private Logger(final String module) {
        this.module = module;
    }

    public static Logger of(final String module) {
        return loggerMap.computeIfAbsent(module, Logger::new);
    }

    //region Basic logging
    public void trace(String s) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.TRACE))
            log(LogLevel.TRACE, s);
    }

    public void debug(String s) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.DEBUG))
            log(LogLevel.DEBUG, s);
    }

    public void info(String s) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.INFO))
            log(LogLevel.INFO, s);
    }

    public void warn(String s) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.WARN))
            log(LogLevel.WARN, s);
    }

    public void error(String s) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.ERROR))
            log(LogLevel.ERROR, s);
    }

    public void success(String s) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.SUCC))
            log(LogLevel.SUCC, s);
    }

    public void crit(String s) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.CRIT))
            log(LogLevel.CRIT, s);
    }
    //endregion
    
    //region Array format
    public void trace(String s, Object... objects) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.TRACE))
            trace(Formatter.arrayFormat(s, objects));
    }

    public void debug(String s, Object... objects) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.DEBUG))
            debug(Formatter.arrayFormat(s, objects));
    }

    public void info(String s, Object... objects) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.INFO))
            info(Formatter.arrayFormat(s, objects));
    }

    public void warn(String s, Object... objects) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.WARN))
            warn(Formatter.arrayFormat(s, objects));
    }

    public void error(String s, Object... objects) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.ERROR))
            error(Formatter.arrayFormat(s, objects));
    }

    public void crit(String s, Object... objects) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.CRIT))
            crit(Formatter.arrayFormat(s, objects));
    }

    public void success(String s, Object... objects) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.SUCC))
            success(Formatter.arrayFormat(s, objects));
    }

    //endregion
    //region Exception only
    public void trace(Throwable throwable) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.TRACE))
            trace(LoggerUtils.getStackTraceAsString(throwable));
    }

    public void debug(Throwable throwable) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.DEBUG))
            debug(LoggerUtils.getStackTraceAsString(throwable));
    }

    public void info(Throwable throwable) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.INFO))
            info(LoggerUtils.getStackTraceAsString(throwable));
    }

    public void warn(Throwable throwable) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.WARN))
            warn(LoggerUtils.getStackTraceAsString(throwable));
    }

    public void error(Throwable throwable) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.ERROR))
            error(LoggerUtils.getStackTraceAsString(throwable));
    }

    public void crit(Throwable throwable) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.CRIT))
            crit(LoggerUtils.getStackTraceAsString(throwable));
    }

    //endregion

    //region Custom logLevel
    public void log(LogLevel logLevel, String s) {
        if (SakuraLogger.isLogLevelEnabled(logLevel))
            SakuraLogger.getLogQueue().log(module, logLevel, s);
    }

    public void log(LogLevel logLevel, String s, Object... objects) {
        if (SakuraLogger.isLogLevelEnabled(logLevel))
            log(logLevel, Formatter.arrayFormat(s, objects));
    }

    public void log(LogLevel logLevel, Throwable throwable) {
        if (SakuraLogger.isLogLevelEnabled(logLevel))
            log(logLevel, LoggerUtils.getStackTraceAsString(throwable));
    }
    //endregion
}
