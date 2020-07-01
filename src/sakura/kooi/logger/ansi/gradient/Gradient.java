package sakura.kooi.logger.ansi.gradient;

import sakura.kooi.logger.ansi.custom.AnsiFormat;
import sakura.kooi.logger.ansi.custom.AnsiRGB;

import java.awt.*;

public class Gradient {
    public static final MultiPointLinearGradient RAINBOW = new MultiPointLinearGradient(
            new Color(255, 0, 0),
            new MultiPointLinearGradient.GradientPoint(new Color(255, 0, 255), 15),
            new MultiPointLinearGradient.GradientPoint(new Color(0, 0, 255), 19),
            new MultiPointLinearGradient.GradientPoint(new Color(0, 255, 255), 15),
            new MultiPointLinearGradient.GradientPoint(new Color(0, 255, 0), 18),
            new MultiPointLinearGradient.GradientPoint(new Color(255, 255, 0), 17),
            new MultiPointLinearGradient.GradientPoint(new Color(255, 0, 0), 16)
    );
    public static final MultiPointLinearGradient BLUE_ORANGE_PINK = new MultiPointLinearGradient(
            new Color(64, 224, 208),
            new Color(255, 140, 0),
            new Color(255, 0, 128)
    );
    public static final MultiPointLinearGradient BLUE_PURPLE_RED = new MultiPointLinearGradient(
            new Color(18, 194, 233),
            new Color(196, 113, 237),
            new Color(246, 79, 89)
    );


    public static String format(String text, Color color1, Color color2, Color... colors) {
        return format(text, new MultiPointLinearGradient(color1, color2, colors));
    }

    public static String format(String text, MultiPointLinearGradient gradient) {
        char[] chars = text.toCharArray();
        AnsiRGB[] colors = gradient.getColors(chars.length);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            result.append(colors[i].toString()).append(chars[i]);
        }

        return result.toString();
    }

    public static String[] format(MultiPointLinearGradient gradient, int degrees, String... text) {
        char[][] chars = toCharArray(text);
        int yMax = chars.length - 1;
        int xMax = chars[0].length - 1;

        double slope = Math.tan(Math.toRadians(degrees)); // y = slope * x

        int offset = (int) (slope * xMax);
        int yWithOffset = yMax + offset;
        AnsiRGB[][] newColors = new AnsiRGB[yWithOffset + 1][xMax + 1];
        AnsiRGB[] verticalColors = gradient.getColors(yWithOffset + 1);
        for (int sourceY = 0; sourceY < verticalColors.length; sourceY++) {
            AnsiRGB color = verticalColors[sourceY];

            for (int x = 0; x < xMax + 1; x++) {
                // 从斜度(slope)用X获取实际点
                int newY = sourceY + (int) (slope * x);

                try {
                    newColors[newY][x] = color;
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
        }

        String[] result = new String[text.length];

        for (int y = 0; y < yMax + 1; y++) {
            char[] sentence = chars[y];
            AnsiRGB[] colors = newColors[y + offset];
            StringBuilder oneResult = new StringBuilder();
            for (int x = 0; x < xMax + 1; x++) {
                char charInASentence = sentence[x];
                AnsiRGB colorInASentence = colors[x];
                if (charInASentence == '\u0000') continue;
                if (colorInASentence != null) oneResult.append(colorInASentence);
                else oneResult.append(AnsiFormat.RESET);
                oneResult.append(charInASentence);
            }
            result[y] = oneResult.toString();
        }
        return result;
    }

    private static char[][] toCharArray(String[] text) {
        char[][] array = new char[text.length][];
        for (int i = 0; i < text.length; i++) {
            array[i] = text[i].toCharArray();
        }
        return array;
    }
}
