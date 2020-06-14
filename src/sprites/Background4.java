package sprites;
import biuoop.DrawSurface;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Background4 class.
 */
public class Background4 implements Sprite {
    private final int winWidth = 800; // windows size
    private final int winHight = 600;
    private String levelname;
    /**
     * constructor.
     * @param lvlName **the levels name**
     */
    public Background4(String lvlName) {
        this.levelname = lvlName;
    }
    /**
     * draws this Background on surface.
     * @param d **surface to draw on**
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.getHSBColor(0.6f, 0.9f, 0.8f)); // screen color
        d.fillRectangle(20, 40, winWidth - 40, winHight - 40);
        d.setColor(java.awt.Color.BLACK);
        d.drawText(580, 18, "Level Name: " + this.levelname, 15); // level name
        // level draw
        d.setColor(java.awt.Color.WHITE);
        for (int i = 0; i < 9; i++) {
            d.drawLine(130 + (i * 8), 400, 50 + (i * 10), winHight);
        }
        d.setColor(java.awt.Color.LIGHT_GRAY);
        d.fillCircle(110, 390, 30);
        d.fillCircle(125, 420, 32);
        d.setColor(java.awt.Color.GRAY);
        d.fillCircle(160, 385, 35);
        d.fillCircle(210, 400, 35);
        d.fillCircle(175, 430, 28);
        d.setColor(java.awt.Color.WHITE);
        for (int i = 0; i < 9; i++) {
            d.drawLine(580 + (i * 8), 500, 530 + (i * 10), winHight);
        }
        d.setColor(java.awt.Color.LIGHT_GRAY);
        d.fillCircle(560, 490, 30);
        d.fillCircle(575, 520, 32);
        d.setColor(java.awt.Color.GRAY);
        d.fillCircle(610, 485, 35);
        d.fillCircle(660, 500, 35);
        d.fillCircle(625, 530, 28);
    }
    /**
     * initiates objects "behavior".
     */
    public void timePassed() {
        // TODO Auto-generated method stub
    }
}
