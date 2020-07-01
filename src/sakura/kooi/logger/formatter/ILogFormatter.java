package sakura.kooi.logger.formatter;

import sakura.kooi.logger.LogLevel;

public interface ILogFormatter {
    String format(LogLevel level, String module, String message);
}
