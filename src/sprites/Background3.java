package sprites;
import biuoop.DrawSurface;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Background3 class.
 */
public class Background3 implements Sprite {
    private final int winWidth = 800; // windows size
    private final int winHight = 600;
    private String levelname;
    /**
     * constructor.
     * @param lvlName **the levels name**
     */
    public Background3(String lvlName) {
        this.levelname = lvlName;
    }
    /**
     * draws this Background on surface.
     * @param d **surface to draw on**
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.getHSBColor(0.4f, 0.9f, 0.6f)); // screen color
        d.fillRectangle(20, 40, winWidth - 40, winHight - 40);
        d.setColor(java.awt.Color.BLACK);
        d.drawText(580, 18, "Level Name: " + this.levelname, 15); // level name
        // level draw
        d.setColor(java.awt.Color.BLACK);
        d.fillRectangle(50, 400, 110, 200);
        d.setColor(java.awt.Color.LIGHT_GRAY);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(60 + (j * 20), 410 + (i * 38), 10, 28);
            }
        }
        d.setColor(java.awt.Color.DARK_GRAY);
        d.fillRectangle(90, 330, 30, 70);
        d.setColor(java.awt.Color.GRAY);
        d.fillRectangle(100, 160, 10, 170);
        d.setColor(java.awt.Color.yellow);
        d.fillCircle(105, 148, 12);
        d.setColor(java.awt.Color.RED);
        d.fillCircle(105, 148, 8);
        d.setColor(java.awt.Color.WHITE);
        d.fillCircle(105, 148, 4);
    }
    /**
     * initiates objects "behavior".
     */
    public void timePassed() {
        // TODO Auto-generated method stub
    }
}
