package sakura.kooi.logger.queue;

import org.slf4j.helpers.MessageFormatter;
import sakura.kooi.logger.LogLevel;
import sakura.kooi.logger.SakuraLogger;
import sakura.kooi.logger.utils.LoggerUtils;

public interface ILogQueue {
    void log(SakuraLogger backend, String module, LogLevel level, String message);

    default void log(SakuraLogger backend, String module, LogLevel level, String s, Object... objects) {
        log(backend, module, level, MessageFormatter.arrayFormat(s, objects).getMessage());
    }


    default void log(SakuraLogger backend, SakuraLogger logger, String module, LogLevel level, Throwable throwable) {
        log(backend, module, level, LoggerUtils.getStackTraceAsString(throwable));
    }

    default void log(SakuraLogger backend, SakuraLogger logger, String module, LogLevel level, String s, Throwable throwable) {
        log(backend, module, level, MessageFormatter.format("{}\n{}", s, LoggerUtils.getStackTraceAsString(throwable)).getMessage());
    }
}
