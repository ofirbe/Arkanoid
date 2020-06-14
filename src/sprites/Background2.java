package sprites;
import biuoop.DrawSurface;
import geometry.Point;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Background2 class.
 */
public class Background2 implements Sprite {
    private final int winWidth = 800; // windows size
    private final int winHight = 600;
    private String levelname;
    private int dif = 60;
    private final Point center = new Point(150, 110);
    /**
     * constructor.
     * @param lvlName **the levels name**
     */
    public Background2(String lvlName) {
        this.levelname = lvlName;
    }
    /**
     * draws this Background on surface.
     * @param d **surface to draw on**
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK); // screen color
        d.fillRectangle(20, 40, winWidth - 40, winHight - 40);
        d.setColor(java.awt.Color.BLACK);
        d.drawText(580, 18, "Level Name: " + this.levelname, 15); // level name
        // level draw
        d.setColor(java.awt.Color.getHSBColor(0.28f, 62f, 0.4f));
        d.fillCircle((int) center.getX(), (int) center.getY(), dif);
        dif = dif - 10;
        d.setColor(java.awt.Color.getHSBColor(0.3f, 62f, 0.9f));
        d.fillCircle((int) center.getX(), (int) center.getY(), dif);
        dif = dif - 10;
        d.setColor(java.awt.Color.YELLOW);
        d.fillCircle((int) center.getX(), (int) center.getY(), dif);
        dif = dif + 20;
        for (int i = 2; i < dif; i++) {
            d.drawLine((int) center.getX(), (int) center.getY(), i * 12, 200);
        }
    }
    /**
     * initiates objects "behavior".
     */
    public void timePassed() {
        // TODO Auto-generated method stub
    }
}
