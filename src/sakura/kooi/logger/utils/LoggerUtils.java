package sakura.kooi.logger.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class LoggerUtils {
    private static final SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

    private static final Pattern STRIP_ALL = Pattern.compile("(§[0-z]|\\033\\[[0-9;]*?m)");
    private static final Pattern STRIP_RGB = Pattern.compile("\\033\\[38;[25];.*?m");

    public static String currentTime() {
        return df.format(new Date());
    }

    public static String getStackTraceAsString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString().trim();
    }


    public static String strip(String message) {
        return STRIP_ALL.matcher(message).replaceAll("");
    }

    public static String stripRGB(String message) {
        return STRIP_RGB.matcher(message).replaceAll("");
    }

    public static boolean runningInIdea() {
        return System.getProperty("java.class.path").contains("idea_rt.jar");
    }
}
