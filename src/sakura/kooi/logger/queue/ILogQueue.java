package sakura.kooi.logger.queue;

import org.slf4j.helpers.MessageFormatter;
import sakura.kooi.logger.LogLevel;
import sakura.kooi.logger.SakuraLogger;
import sakura.kooi.logger.utils.LoggerUtils;

public interface ILogQueue {
    void log(String module, LogLevel level, String message);

    default void log(String module, LogLevel level, String s, Object... objects) {
        if (SakuraLogger.isLogLevelEnabled(level))
            log(module, level, MessageFormatter.arrayFormat(s, objects).getMessage());
    }


    default void log(String module, LogLevel level, Throwable throwable) {
        if (SakuraLogger.isLogLevelEnabled(level))
            log(module, level, LoggerUtils.getStackTraceAsString(throwable));
    }

    default void log(String module, LogLevel level, String s, Throwable throwable) {
        if (SakuraLogger.isLogLevelEnabled(level))
            log(module, level, MessageFormatter.format("{}\n{}", s, LoggerUtils.getStackTraceAsString(throwable)).getMessage());
    }
}
