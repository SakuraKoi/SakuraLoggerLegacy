package sakura.kooi.logger.writer;

import sakura.kooi.logger.LogLevel;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SimpleFileLogWriter implements ILogWriter {
    private final File logDirectory;
    private final String prefix;
    private final SimpleDateFormat dateFormat;

    protected PrintWriter writer;

    public SimpleFileLogWriter() throws IOException {
        this("logs", "Log");
    }

    public SimpleFileLogWriter(String prefix) throws IOException {
        this("logs", prefix);
    }

    public SimpleFileLogWriter(String dir, String prefix) throws IOException {
        this(dir, prefix, new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss"));
    }

    protected SimpleFileLogWriter(String dir, String prefix, SimpleDateFormat dateFormat) throws IOException {
        this.prefix = prefix;
        this.dateFormat = dateFormat;
        logDirectory = new File(dir);
        if (!logDirectory.exists()) Files.createDirectories(logDirectory.toPath());
        newWriter();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                writer.flush();
            } finally {
                writer.close();
            }
        }));
    }

    protected void newWriter() throws IOException {
        File logFile = new File(logDirectory, this.prefix + "-" + dateFormat.format(new Date()) + ".log");
        int i = 1;
        while (logFile.exists()) {
            logFile = new File(logDirectory, this.prefix + "-" + dateFormat.format(new Date()) + "-" + i + ".log");
            i++;
        }
        writer = new PrintWriter(Files.newBufferedWriter(logFile.toPath(), StandardCharsets.UTF_8));
    }

    @Override
    public void write(LogLevel level, String module, String message) {
        if (level == logLevel) return;

        writer.println(strip(message));
        writer.flush();
    }

    public void cleanupLog() {
        long expire = TimeUnit.DAYS.toMillis(30);
        if (logDirectory.exists()) {
            if (logDirectory.isDirectory()) {
                File[] logs = logDirectory.listFiles();
                if (logs != null) {
                    long current = System.currentTimeMillis();
                    int deleted = 0;
                    for (File log : logs) {
                        if (log.getName().endsWith(".log") && log.getName().startsWith(prefix)) {
                            try {
                                BasicFileAttributes attr = Files.readAttributes(log.toPath(), BasicFileAttributes.class);
                                if (current > attr.lastModifiedTime().toMillis() + expire) {
                                    if (log.delete())
                                        ++deleted;
                                }
                            } catch (IOException var12) {
                                logger.log(logLevel, "Error occurred while cleaning up log files.", var12);
                            }
                        }
                    }
                    if (deleted > 0)
                        logger.log(logLevel, "Removed {} old log files", deleted);
                }
            }
        }
    }
}
