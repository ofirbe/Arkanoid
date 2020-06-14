package sprites;
import biuoop.DrawSurface;
import game.GameLevel;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the ScoreIndicator class.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    /**
     * constructor of ScoreIndicator.
     * @param s **Counter that pass from the game**
     */
    public ScoreIndicator(Counter s) {
        this.score = s;
    }
    /**
     * draws ScoreIndicator on surface.
     * @param surface **surface to draw on**
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(java.awt.Color.BLACK);
        surface.drawText(300, 18, "Score: " + String.valueOf(score.getValue()), 15);
    }
    /**
     * initiates objects "behavior".
     */
    public void timePassed() {
        // to be update..
    }
    /**
     * adds ScoreIndicator to game.
     * @param game **Game**
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
