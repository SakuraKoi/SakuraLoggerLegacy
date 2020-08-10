package sakura.kooi.logger.queue;

import sakura.kooi.logger.LogLevel;
import sakura.kooi.logger.SakuraLogger;

public class BlockingLogQueue implements ILogQueue {
    private final Object lock = new Object();
    @Override
    public void log(String module, LogLevel level, String message) {
        for (String msg : message.split("\n")) {
            String formatted = SakuraLogger.getFormatter().format(level, module, msg);
            if (formatted == null) continue;
            synchronized (lock) {
                SakuraLogger.getWriter().write(level, module, formatted);
            }
        }
    }
}
