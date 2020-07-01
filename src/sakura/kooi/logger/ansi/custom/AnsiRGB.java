package sakura.kooi.logger.ansi.custom;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

import static sakura.kooi.logger.ansi.custom.AnsiConstants.*;

@SuppressWarnings("unused")
@Getter
@Setter
public class AnsiRGB {
    private Color foreground;
    private Color background;

    public AnsiRGB(int rgb) {
        this(new Color(rgb));
    }

    public AnsiRGB(Color color) {
        setForeground(color);
    }

    public AnsiRGB(int r, int g, int b) {
        this(new Color(r, g, b));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(ESC_PREFIX);
        if (foreground != null) result.append(FOREGROUND).append(COLOR_MODE.format(foreground));
        if (background != null) result.append(BACKGROUND).append(COLOR_MODE.format(background));
        return result.append(SUFFIX).toString();
    }
}
