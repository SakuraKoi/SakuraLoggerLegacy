package sakura.kooi.logger.writer;

import lombok.Getter;
import sakura.kooi.logger.LogLevel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class ComplexLogWriter implements ILogWriter {
    @Getter
    private final HashSet<ILogWriter> logWriters = new HashSet<>();
    public ComplexLogWriter(Collection<ILogWriter> logWriters) {
        this.logWriters.addAll(logWriters);
        this.logWriters.remove(null);
    }

    public ComplexLogWriter(ILogWriter... logWriters) {
        Collections.addAll(this.logWriters, logWriters);
        this.logWriters.remove(null);
    }

    @Override
    public void write(LogLevel level, String module, String message) {
        for (ILogWriter logWriter : logWriters) {
            logWriter.write(level, module, message);
        }
    }
}
