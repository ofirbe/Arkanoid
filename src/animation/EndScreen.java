package animation;
import biuoop.DrawSurface;
import game.GameFlow;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the EndScreen class.
 */
public class EndScreen implements Animation {
    private boolean stop;
    private GameFlow game;
    /**
     * constructoor of EndScreen.
     * @param g **GameFlow**
     */
    public EndScreen(GameFlow g) {
        this.stop = false;
        this.game = g;
    }
    /**
     * doOneFrame(DrawSurface) is in charge of the logic.
     * @param d **surface**
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(java.awt.Color.darkGray);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(java.awt.Color.white);
        if (this.game.getLives().getValue() == 0) {
            d.drawText(d.getWidth() / 2 - 130, d.getHeight() / 2 - 50, "Game Over.", 32);
            d.drawText(d.getWidth() / 2 - 150, d.getHeight() / 2, "Your score is: " + game.getScore().getValue(), 32);
        } else {
            d.drawText(d.getWidth() / 2 - 130, d.getHeight() / 2 - 50, "You Win!", 32);
            d.drawText(d.getWidth() / 2 - 150, d.getHeight() / 2, "Your score is: " + game.getScore().getValue(), 32);
        }
        d.drawText(d.getWidth() / 2 - 200, d.getHeight() - 100, "Press space to continue", 30);
    }
    /**
     * shouldStop() is in charge of stopping condition.
     * @return boolean **should Stop or not**
     */
    public boolean shouldStop() {
        return this.stop;
    }
}