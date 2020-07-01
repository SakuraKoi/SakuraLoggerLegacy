package sakura.kooi.logger;

import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import sakura.kooi.logger.utils.LoggerUtils;

public class Logger extends MarkerIgnoringBase {
    private final String module;
    private Logger(final String module) {
        this.module = module;
    }

    public static Logger of(final String module) {
        return new Logger(module);
    }

    //region Basic logging
    @Override
    public void trace(String s) {
        if (isTraceEnabled())
            log(LogLevel.TRACE, s);
    }

    @Override
    public void debug(String s) {
        if (isDebugEnabled())
            log(LogLevel.DEBUG, s);
    }

    @Override
    public void info(String s) {
        if (isInfoEnabled())
            log(LogLevel.INFO, s);
    }

    @Override
    public void warn(String s) {
        if (isWarnEnabled())
            log(LogLevel.WARN, s);
    }

    @Override
    public void error(String s) {
        if (isErrorEnabled())
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
    @Override
    public void trace(String s, Object... objects) {
        if (isTraceEnabled())
            trace(MessageFormatter.arrayFormat(s, objects).getMessage());
    }

    @Override
    public void debug(String s, Object... objects) {
        if (isDebugEnabled())
            debug(MessageFormatter.arrayFormat(s, objects).getMessage());
    }

    @Override
    public void info(String s, Object... objects) {
        if (isInfoEnabled())
            info(MessageFormatter.arrayFormat(s, objects).getMessage());
    }

    @Override
    public void warn(String s, Object... objects) {
        if (isWarnEnabled())
            warn(MessageFormatter.arrayFormat(s, objects).getMessage());
    }

    @Override
    public void error(String s, Object... objects) {
        if (isErrorEnabled())
            error(MessageFormatter.arrayFormat(s, objects).getMessage());
    }

    public void crit(String s, Object... objects) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.CRIT))
            crit(MessageFormatter.arrayFormat(s, objects).getMessage());
    }

    public void success(String s, Object... objects) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.SUCC))
            success(MessageFormatter.arrayFormat(s, objects).getMessage());
    }
    //endregion

    //region Exception only
    public void trace(Throwable throwable) {
        if (isTraceEnabled())
            trace(LoggerUtils.getStackTraceAsString(throwable));
    }

    public void debug(Throwable throwable) {
        if (isDebugEnabled())
            debug(LoggerUtils.getStackTraceAsString(throwable));
    }

    public void info(Throwable throwable) {
        if (isInfoEnabled())
            info(LoggerUtils.getStackTraceAsString(throwable));
    }

    public void warn(Throwable throwable) {
        if (isWarnEnabled())
            warn(LoggerUtils.getStackTraceAsString(throwable));
    }

    public void error(Throwable throwable) {
        if (isErrorEnabled())
            error(LoggerUtils.getStackTraceAsString(throwable));
    }

    public void crit(Throwable throwable) {
        if (SakuraLogger.isLogLevelEnabled(LogLevel.CRIT))
            crit(LoggerUtils.getStackTraceAsString(throwable));
    }
    //endregion

    //region Exception format
    @Override
    public void trace(String s, Throwable throwable) {
        if (throwable == null)
            trace(s);
        else if (isTraceEnabled())
            trace(MessageFormatter.format("{}\n{}", s, LoggerUtils.getStackTraceAsString(throwable)).getMessage());
    }

    @Override
    public void debug(String s, Throwable throwable) {
        if (throwable == null)
            debug(s);
        else if (isDebugEnabled())
            debug(MessageFormatter.format("{}\n{}", s, LoggerUtils.getStackTraceAsString(throwable)).getMessage());
    }

    @Override
    public void info(String s, Throwable throwable) {
        if (throwable == null)
            info(s);
        else if (isInfoEnabled())
            info(MessageFormatter.format("{}\n{}", s, LoggerUtils.getStackTraceAsString(throwable)).getMessage());
    }

    @Override
    public void warn(String s, Throwable throwable) {
        if (throwable == null)
            warn(s);
        else if (isWarnEnabled())
            warn(MessageFormatter.format("{}\n{}", s, LoggerUtils.getStackTraceAsString(throwable)).getMessage());
    }

    @Override
    public void error(String s, Throwable throwable) {
        if (throwable == null)
            error(s);
        else if (isErrorEnabled())
            error(MessageFormatter.format("{}\n{}", s, LoggerUtils.getStackTraceAsString(throwable)).getMessage());
    }

    public void crit(String s, Throwable throwable) {
        if (throwable == null)
            crit(s);
        else if (SakuraLogger.isLogLevelEnabled(LogLevel.CRIT))
            crit(MessageFormatter.format("{}\n{}", s, LoggerUtils.getStackTraceAsString(throwable)).getMessage());
    }
    //endregion

    //region Custom exception format
    public void warnEx(String s, Throwable ex, Object... o) {
        if (isWarnEnabled())
            warn(MessageFormatter.arrayFormat(s, o).getMessage() + "\n"+ LoggerUtils.getStackTraceAsString(ex));
    }

    public void errorEx(String s, Throwable ex, Object... o) {
        if (isErrorEnabled())
            error(MessageFormatter.arrayFormat(s, o).getMessage() + "\n"+ LoggerUtils.getStackTraceAsString(ex));
    }
    //endregion

    //region Custom logLevel
    public void log(LogLevel logLevel, String s) {
        if (SakuraLogger.isLogLevelEnabled(logLevel))
            SakuraLogger.getLogQueue().log(module, logLevel, s);
    }

    public void log(LogLevel logLevel, String s, Object... objects) {
        if (SakuraLogger.isLogLevelEnabled(logLevel))
            log(logLevel, MessageFormatter.arrayFormat(s, objects).getMessage());
    }

    public void log(LogLevel logLevel, Throwable throwable) {
        if (SakuraLogger.isLogLevelEnabled(logLevel))
            log(logLevel, LoggerUtils.getStackTraceAsString(throwable));
    }

    public void log(LogLevel logLevel, String s, Throwable throwable) {
        if (throwable == null)
            log(logLevel, s);
        else if (SakuraLogger.isLogLevelEnabled(logLevel))
            log(logLevel, MessageFormatter.format("{}\n{}", s, LoggerUtils.getStackTraceAsString(throwable)).getMessage());
    }

    public void log(LogLevel logLevel, String s, Throwable ex, Object... o) {
        if (SakuraLogger.isLogLevelEnabled(logLevel))
            log(logLevel, MessageFormatter.arrayFormat(s, o).getMessage() + "\n" + LoggerUtils.getStackTraceAsString(ex));
    }
    //endregion

    //region slf4j format method
    @Override
    public void trace(String s, Object o) {
        if (isTraceEnabled())
            trace(MessageFormatter.format(s, o).getMessage());
    }

    @Override
    public void trace(String s, Object o, Object o1) {
        if (isTraceEnabled())
            trace(MessageFormatter.format(s, o, o1).getMessage());
    }

    @Override
    public void debug(String s, Object o) {
        if (isDebugEnabled())
            debug(MessageFormatter.format(s, o).getMessage());
    }

    @Override
    public void debug(String s, Object o, Object o1) {
        if (isDebugEnabled())
            debug(MessageFormatter.format(s, o, o1).getMessage());
    }

    @Override
    public void info(String s, Object o) {
        if (isInfoEnabled())
            info(MessageFormatter.format(s, o).getMessage());
    }

    @Override
    public void info(String s, Object o, Object o1) {
        if (isInfoEnabled())
            info(MessageFormatter.format(s, o, o1).getMessage());
    }

    @Override
    public void warn(String s, Object o) {
        if (isWarnEnabled())
            warn(MessageFormatter.format(s, o).getMessage());
    }

    @Override
    public void warn(String s, Object o, Object o1) {
        if (isWarnEnabled())
            warn(MessageFormatter.format(s, o, o1).getMessage());
    }

    @Override
    public void error(String s, Object o) {
        if (isErrorEnabled())
            error(MessageFormatter.format(s, o).getMessage());
    }

    @Override
    public void error(String s, Object o, Object o1) {
        if (isErrorEnabled())
            error(MessageFormatter.format(s, o, o1).getMessage());
    }
    //endregion

    //region slf4j isLevelEnabled
    @Override
    public boolean isTraceEnabled() {
        return SakuraLogger.isLogLevelEnabled(LogLevel.TRACE);
    }

    @Override
    public boolean isDebugEnabled() {
        return SakuraLogger.isLogLevelEnabled(LogLevel.DEBUG);
    }

    @Override
    public boolean isInfoEnabled() {
        return SakuraLogger.isLogLevelEnabled(LogLevel.INFO);
    }

    @Override
    public boolean isWarnEnabled() {
        return SakuraLogger.isLogLevelEnabled(LogLevel.WARN);
    }

    @Override
    public boolean isErrorEnabled() {
        return SakuraLogger.isLogLevelEnabled(LogLevel.ERROR);
    }
    //endregion
}
