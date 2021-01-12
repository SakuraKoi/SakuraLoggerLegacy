package sakura.kooi.logger.writer;

import sakura.kooi.logger.LogLevel;
import sakura.kooi.logger.Logger;
import sakura.kooi.logger.utils.LoggerUtils;
import sakura.kooi.logger.utils.MinecraftColorFormatter;

public interface ILogWriter {
    Logger logger = Logger.of("LogWriter");
    LogLevel logLevel = LogLevel.register("LOGGER", "§e", true);

    void write(LogLevel level, String module, String message);

    default String format(String message) {
        return MinecraftColorFormatter.format(message);
    }

    default String strip(String message) {
        return LoggerUtils.strip(message);
    }

    default String stripRGB(String message) {
        return LoggerUtils.stripRGB(message);
    }
}
