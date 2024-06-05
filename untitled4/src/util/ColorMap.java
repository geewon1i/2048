package util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ColorMap {
    static Map<Integer, Color> colorMap = new HashMap<>();

    //todo: complete the method to add other color
    public static void InitialColorMap() {

        colorMap.put(2, new Color(118,110,102));
        colorMap.put(4, new Color(118,110,102));
        colorMap.put(8, Color.WHITE);
        colorMap.put(16, Color.WHITE);
        colorMap.put(32, Color.WHITE);
        colorMap.put(64, Color.WHITE);
        colorMap.put(128, Color.WHITE);
        colorMap.put(256, Color.WHITE);
        colorMap.put(512, Color.WHITE);
        colorMap.put(1024, Color.WHITE);
        colorMap.put(2048, Color.WHITE);

    }

    public static Color getColor(int i) {
        return colorMap.getOrDefault(i, Color.black);
    }
}
