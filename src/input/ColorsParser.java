package input;
import java.awt.Color;
import java.util.HashMap;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the ColorsParser class.
 */
public class ColorsParser {
    /**
     * parse color definition and return the specified color.
     * @param s **String**
     * @return **java.awt.Color**
     */
    public java.awt.Color colorFromString(String s) {
        if (s.startsWith("RGB(")) { // If RGB format.
            int r = 0, g = 0, b = 0;
            s = s.substring(4, s.length() - 1);
            String[] rgb = s.split(",");
            try {
                r = Integer.valueOf(rgb[0]);
            } catch (NumberFormatException e) {
                return null;
            }
            try {
                g = Integer.valueOf(rgb[1]);
            } catch (NumberFormatException e) {
                return null;
            }
            try {
                b = Integer.valueOf(rgb[2]);
            } catch (NumberFormatException e) {
                return null;
            }
            return (new java.awt.Color(r, g, b));
        }
        HashMap<String, java.awt.Color> colors = this.genColors();
        if (colors.containsKey(s)) {
            return colors.get(s);
        }
        return null;
    }
    /**
     * generates Colors map.
     * @return **Color map**
     */
    private HashMap<String, Color> genColors() {
        HashMap<String, java.awt.Color> colors = new HashMap<String, java.awt.Color>();
        colors.put("red", java.awt.Color.RED);
        colors.put("RED", java.awt.Color.RED);
        colors.put("white", java.awt.Color.WHITE);
        colors.put("WHITE", java.awt.Color.WHITE);
        colors.put("yellow", java.awt.Color.YELLOW);
        colors.put("YELLOW", java.awt.Color.YELLOW);
        colors.put("pink", java.awt.Color.PINK);
        colors.put("PINK", java.awt.Color.PINK);
        colors.put("orange", java.awt.Color.ORANGE);
        colors.put("ORANGE", java.awt.Color.ORANGE);
        colors.put("green", java.awt.Color.GREEN);
        colors.put("GREEN", java.awt.Color.GREEN);
        colors.put("lightGray", java.awt.Color.LIGHT_GRAY);
        colors.put("LIGHTGRAY", java.awt.Color.LIGHT_GRAY);
        colors.put("gray", java.awt.Color.GRAY);
        colors.put("GRAY", java.awt.Color.GRAY);
        colors.put("cyan", java.awt.Color.CYAN);
        colors.put("CYAN", java.awt.Color.CYAN);
        colors.put("blue", java.awt.Color.BLUE);
        colors.put("BLUE", java.awt.Color.BLUE);
        colors.put("black", java.awt.Color.BLACK);
        colors.put("BLACK", java.awt.Color.BLACK);
        return colors;
    }
}