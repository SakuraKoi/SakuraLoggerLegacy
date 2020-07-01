package sakura.kooi.logger.ansi.custom;

import static sakura.kooi.logger.ansi.custom.AnsiConstants.ESC_PREFIX;
import static sakura.kooi.logger.ansi.custom.AnsiConstants.SUFFIX;

@SuppressWarnings("unused")
public class AnsiBackgroundColor {
    public static final String BLACK = ESC_PREFIX + 40 + SUFFIX;
    public static final String RED = ESC_PREFIX + 41 + SUFFIX;
    public static final String GREEN = ESC_PREFIX + 42 + SUFFIX;
    public static final String YELLOW = ESC_PREFIX + 43 + SUFFIX;
    public static final String BLUE = ESC_PREFIX + 44 + SUFFIX;
    public static final String PURPLE = ESC_PREFIX + 45 + SUFFIX;
    public static final String CYAN = ESC_PREFIX + 46 + SUFFIX;
    public static final String WHITE = ESC_PREFIX + 47 + SUFFIX;

    public static final String LIGHT_BLACK = ESC_PREFIX + 100 + SUFFIX;
    public static final String LIGHT_RED = ESC_PREFIX + 101 + SUFFIX;
    public static final String LIGHT_GREEN = ESC_PREFIX + 102 + SUFFIX;
    public static final String LIGHT_YELLOW = ESC_PREFIX + 103 + SUFFIX;
    public static final String LIGHT_BLUE = ESC_PREFIX + 104 + SUFFIX;
    public static final String LIGHT_PURPLE = ESC_PREFIX + 105 + SUFFIX;
    public static final String LIGHT_CYAN = ESC_PREFIX + 106 + SUFFIX;
    public static final String LIGHT_WHITE = ESC_PREFIX + 107 + SUFFIX;
}
