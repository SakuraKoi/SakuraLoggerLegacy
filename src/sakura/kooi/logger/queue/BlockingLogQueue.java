package sakura.kooi.logger.queue;

import sakura.kooi.logger.LogLevel;
import sakura.kooi.logger.SakuraLogger;

public class BlockingLogQueue implements ILogQueue {
    private final Object lock = new Object();
    @Override
    public void log(SakuraLogger backend, String module, LogLevel level, String message) {
        synchronized (lock) {
            for (String msg : message.split("\n")) {
                String formatted = backend.getFormatter().format(level, module, msg);
                if (formatted == null) continue;
                backend.getWriter().write(level, module, formatted);
            }
        }
    }
}
