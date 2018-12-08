package util;

public class ColorConverter {

    public static String hexToRGB(String hex, boolean alpha) {

        int r = Integer.parseInt(hex.substring(1, 3), 16),
                g = Integer.parseInt(hex.substring(3, 5), 16),
                b = Integer.parseInt(hex.substring(5, 7), 16);

        if (alpha) {
            return "rgba(" + r + ", " + g + ", " + b + ", 1)";
        } else {
            return "rgb(" + r + ", " + g + ", " + b + ")";
        }
    }
}
