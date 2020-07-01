package sakura.kooi.logger.ansi.custom;

import static sakura.kooi.logger.ansi.custom.AnsiConstants.*;

@SuppressWarnings("unused")
public class AnsiFormat {
    public static final String RESET = ESC_PREFIX + "0" + SUFFIX;
    public static final String BOLD = ESC_PREFIX + "1" + SUFFIX;
    public static final String THIN = ESC_PREFIX + "2" + SUFFIX;
    public static final String ITALIC = ESC_PREFIX + "3" + SUFFIX;
    public static final String UNDERLINE = ESC_PREFIX + "4" + SUFFIX;
    public static final String BLINK = ESC_PREFIX + "5" + SUFFIX;
    public static final String RAPID_BLINK = ESC_PREFIX + "6" + SUFFIX;
    public static final String REVERSE = ESC_PREFIX + "7" + SUFFIX;
    public static final String INVISIBLE_TEXT = ESC_PREFIX + "8" + SUFFIX;
}
