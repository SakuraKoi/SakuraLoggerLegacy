package sakura.kooi.logger.queue;

import sakura.kooi.logger.LogLevel;
import sakura.kooi.logger.SakuraLogger;

import java.util.concurrent.LinkedBlockingQueue;

public class AsyncLogQueue implements ILogQueue {
    private static final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    static {
        AsyncLogThread thread = new AsyncLogThread();
        thread.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            thread.interrupt();
            for (Runnable log : queue) {
                log.run();
            }
        }));
    }

    @Override
    public void log(String module, LogLevel level, String message) {
        queue.add(() -> {
            for (String msg : message.split("\n")) {
                String formatted = SakuraLogger.getFormatter().format(level, module, msg);
                SakuraLogger.getWriter().write(level, module, formatted);
            }
        });
    }

    private static class AsyncLogThread extends Thread {
        private AsyncLogThread() {
            this.setName("SakuraLogger - Async Log Thread");
            this.setDaemon(true);
        }

        @Override
        public void run() {
            while (true) {
                Runnable logEntry;
                try {
                    logEntry = queue.take();
                } catch (InterruptedException e) {
                    return;
                }
                logEntry.run();
            }
        }
    }
}
