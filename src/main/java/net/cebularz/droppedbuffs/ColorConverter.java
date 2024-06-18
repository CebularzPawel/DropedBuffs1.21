package net.cebularz.droppedbuffs;

public class ColorConverter {

    public static int convertToARGB(float alpha, float red, float green, float blue) {
        alpha = Math.max(0, Math.min(1, alpha));
        red = Math.max(0, Math.min(1, red));
        green = Math.max(0, Math.min(1, green));
        blue = Math.max(0, Math.min(1, blue));

        int a = (int) (alpha * 255);
        int r = (int) (red * 255);
        int g = (int) (green * 255);
        int b = (int) (blue * 255);

        return (a << 24) | (r << 16) | (g << 8) | b;
    }

}
