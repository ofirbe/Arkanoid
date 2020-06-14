package sprites;
import biuoop.DrawSurface;
import game.GameLevel;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the LivesIndicator class.
 */
public class LivesIndicator implements Sprite {
    private final Counter lives;
    private final int fontSize = 15;
    /**
     * LivesIndicator object constructor.
     * @param c **Counter**
     */
    public LivesIndicator(Counter c) {
        this.lives = c;
    }
    /**
     * draws LivesIndicator on surface.
     * @param surface **surface to draw on**
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(java.awt.Color.BLACK);
        surface.drawText(5, 18, "Lives: " + String.valueOf(lives.getValue()), fontSize);
    }
    /**
     * adds LivesIndicator to the game.
     * @param g **game**
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**
     * initiates objects "behavior".
     */
    public void timePassed() {
        // to be update..
    }
}