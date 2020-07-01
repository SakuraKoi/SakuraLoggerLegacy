package sakura.kooi.logger.ansi.custom;

import lombok.AllArgsConstructor;

import java.awt.*;

@AllArgsConstructor
public enum AnsiColorMode {
    COLOR_RGB((r, g, b) -> String.format("2;%s;%s;%s", r, g, b)),
    COLOR_8BIT((r, g, b) -> String.format("5;%s", rgbTo8Bit(r, g, b)));

    private final AnsiColorFormatter formatter;

    private interface AnsiColorFormatter {
        String format(int r, int g, int b);
    }

    public String format(Color color) {
        return format(color.getRed(), color.getGreen(), color.getBlue());
    }

    public String format(int r, int g, int b) {
        return formatter.format(r, g, b);
    }

    private static int rgbTo8Bit(int r, int g, int b) {
        boolean grayPossible = true;
        boolean gray = false;
        float sep = 42.5f;
        while (grayPossible) {
            if (r < sep || g < sep || b < sep) {
                gray = r < sep && g < sep && b < sep;
                grayPossible = false;
            }
            sep += 42.5;
        }

        if (gray) {
            return Math.round(232f + (r + g + b) / 33f);
        } else {
            return 16 + ((int) (r / 256f * 6f)) * 36 + ((int) (g / 256f * 6f)) * 6 + ((int) (b / 256f * 6f));
        }
    }
}