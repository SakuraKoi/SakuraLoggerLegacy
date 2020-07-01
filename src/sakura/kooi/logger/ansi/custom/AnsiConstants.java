package sakura.kooi.logger.ansi.custom;

@SuppressWarnings("unused")
public class AnsiConstants {
    public static final String ESC_PREFIX = "\033[";
    public static final String SUFFIX = "m";

    public static final String FORMAT_PREFIX = "&";

    public static final String FOREGROUND = "38;";
    public static final String BACKGROUND = "48;";

    public static AnsiColorMode COLOR_MODE = AnsiColorMode.COLOR_8BIT;
}
