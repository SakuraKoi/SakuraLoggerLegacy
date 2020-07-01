package sakura.kooi.logger.ansi.custom;

import static sakura.kooi.logger.ansi.custom.AnsiConstants.ESC_PREFIX;
import static sakura.kooi.logger.ansi.custom.AnsiConstants.SUFFIX;

@SuppressWarnings("unused")
public class AnsiTextColor {
    public static final String BLACK = ESC_PREFIX + 30 + SUFFIX;
    public static final String RED = ESC_PREFIX + 31 + SUFFIX;
    public static final String GREEN = ESC_PREFIX + 32 + SUFFIX;
    public static final String YELLOW = ESC_PREFIX + 33 + SUFFIX;
    public static final String BLUE = ESC_PREFIX + 34 + SUFFIX;
    public static final String PURPLE = ESC_PREFIX + 35 + SUFFIX;
    public static final String CYAN = ESC_PREFIX + 36 + SUFFIX;
    public static final String WHITE = ESC_PREFIX + 37 + SUFFIX;

    public static final String LIGHT_BLACK = ESC_PREFIX + 30 + ";1" + SUFFIX;
    public static final String LIGHT_RED = ESC_PREFIX + 31 + ";1" + SUFFIX;
    public static final String LIGHT_GREEN = ESC_PREFIX + 32 + ";1" + SUFFIX;
    public static final String LIGHT_YELLOW = ESC_PREFIX + 33 + ";1" + SUFFIX;
    public static final String LIGHT_BLUE = ESC_PREFIX + 34 + ";1" + SUFFIX;
    public static final String LIGHT_PURPLE = ESC_PREFIX + 35 + ";1" + SUFFIX;
    public static final String LIGHT_CYAN = ESC_PREFIX + 36 + ";1" + SUFFIX;
    public static final String LIGHT_WHITE = ESC_PREFIX + 37 + ";1" + SUFFIX;
}
