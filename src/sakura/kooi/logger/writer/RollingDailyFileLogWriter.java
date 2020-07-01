package sakura.kooi.logger.writer;

import sakura.kooi.logger.LogLevel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class RollingDailyFileLogWriter extends SimpleFileLogWriter {

    private long currentDay;
    public RollingDailyFileLogWriter() throws IOException {
        this("logs", "Log");
    }

    public RollingDailyFileLogWriter(String prefix) throws IOException {
        this("logs", prefix);
    }

    public RollingDailyFileLogWriter(String dir, String prefix) throws IOException {
        this(dir, prefix, new SimpleDateFormat("yyyy-MM-dd"));
    }

    protected RollingDailyFileLogWriter(String dir, String prefix, SimpleDateFormat dateFormat) throws IOException {
        super(dir, prefix, dateFormat);
        currentDay = getCurrentDay();
    }

    private long getCurrentDay() {
        return System.currentTimeMillis() / TimeUnit.DAYS.toMillis(1);
    }

    @Override
    public void write(LogLevel level, String module, String message) {
        if (level == logLevel) return;
        if (getCurrentDay() != currentDay) {
           roll();
        }
        if (writer != null)
            super.write(level, module, message);
    }

    public void roll() {
        if (writer != null) {
            writer.flush();
            writer.close();
            writer = null;
        }
        try {
            newWriter();
            currentDay = getCurrentDay();
        } catch (IOException e) {
            logger.log(logLevel, "Error occurred rolling new log file.", e);
        }
    }
}
