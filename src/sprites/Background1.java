package sprites;
import biuoop.DrawSurface;
import geometry.Point;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Background1 class.
 */
public class Background1 implements Sprite {
    private final int winWidth = 800; // windows size
    private final int winHight = 600;
    private String levelname;
    private final Point center = new Point(winWidth / 2, winHight / 3);
    /**
     * constructor.
     * @param lvlName **the levels name**
     */
    public Background1(String lvlName) {
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
        d.setColor(java.awt.Color.BLUE);
        d.drawCircle((int) center.getX(), (int) center.getY(), 80);
        d.drawCircle((int) center.getX(), (int) center.getY(), 110);
        d.drawCircle((int) center.getX(), (int) center.getY(), 140);
        d.drawLine(winWidth / 2, 50, winWidth / 2, 350);
        d.drawLine(250, winHight / 3, 550, winHight / 3);
    }
    /**
     * initiates objects "behavior".
     */
    public void timePassed() {
        // TODO Auto-generated method stub
    }
}
