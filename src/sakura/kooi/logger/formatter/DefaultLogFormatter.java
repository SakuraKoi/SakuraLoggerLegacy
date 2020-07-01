package sakura.kooi.logger.formatter;

import sakura.kooi.logger.LogLevel;
import sakura.kooi.logger.utils.LoggerUtils;

public class DefaultLogFormatter implements ILogFormatter {
    @Override
    public String format(LogLevel level, String module, String message) {
        StringBuilder sb = new StringBuilder()
                .append("§7")
                .append(LoggerUtils.currentTime())
                .append(" ")
                .append(level.getColor())
                .append("[")
                .append(level.getTag())
                .append("] \t");
        if (module != null) {
            sb.append("§b[").append(module).append("] ").append(level.getColor());
        }
        sb.append(message);
        return sb.toString();
    }
}
