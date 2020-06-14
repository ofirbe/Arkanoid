package animation;
import biuoop.DrawSurface;
import score.HighScoresTable;
import score.ScoreInfo;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the HighScoresAnimation class.
 */
public class HighScoresAnimation implements Animation {
    private boolean stop;
    private HighScoresTable scores;
    /**
     * constructoor.
     * @param s **HighScoresTable object**
     */
    public HighScoresAnimation(HighScoresTable s) {
        this.stop = false;
        this.scores = s;
    }
    /**
     * puts one frame on surface.
     * @param d **surface**
     */
    public void doOneFrame(DrawSurface d) {
        int i = 0;
        d.setColor(java.awt.Color.darkGray);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(java.awt.Color.yellow);
        d.drawText(50, 50, "High Scores", 30);
        d.setColor(java.awt.Color.green);
        d.drawText(100, 125, "Player Name", 25);
        d.drawText(450, 125, "Score", 25);
        d.drawLine(100, 130, 700, 130);
        d.setColor(java.awt.Color.white);
        for (ScoreInfo s : this.scores.getHighScores()) {
            i++;
            d.drawText(100, 130 + (20 * i), s.getName(), 25);
            d.drawText(450, 130 + (20 * i), Integer.toString(s.getScore()), 25);
        }
        d.setColor(java.awt.Color.white);
        d.drawText(200, 500, "Press space to continue", 40);
    }
    /**
     * stops Animation.
     * @return **boolean**
     */
    public boolean shouldStop() {
        return this.stop;
    }
}