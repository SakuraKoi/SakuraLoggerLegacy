package sakura.kooi.logger.writer;

import sakura.kooi.logger.LogLevel;

public class ConsoleLogWriter implements ILogWriter {
    @Override
    public void write(LogLevel level, String module, String message) {
        System.out.println(format(message));
    }
}
