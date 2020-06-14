package animation;
import biuoop.DrawSurface;
import sprites.Counter;
import sprites.SpriteCollection;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the CountdownAnimation class.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private double constNumOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Counter counter;
    private final biuoop.Sleeper sleeper;
    /**
     * constructoor.
     * @param num **number of sec to display**
     * @param count **what number start to count from**
     * @param game **SpriteCollection object*
     */
    public CountdownAnimation(double num, int count, SpriteCollection game) {
        this.numOfSeconds = 0;
        this.countFrom = count;
        this.gameScreen = game;
        this.constNumOfSeconds = num;
        this.counter = new Counter(this.countFrom);
        this.sleeper = new biuoop.Sleeper();
    }
    /**
     * getter for numOfSeconds.
     * @return **double**
     */
    public double getSec() {
        return this.numOfSeconds;
    }
    /**
     * play one frame.
     * @param d **surface**
     */
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(java.awt.Color.BLUE);
        if (this.counter.getValue() != 0) {
            d.drawText(d.getWidth() / 2 - 50, d.getHeight() / 2, Integer.toString(this.counter.getValue()), 50);
        } else {
            d.drawText(d.getWidth() / 2 - 50, d.getHeight() / 2, "GO", 45);
        }
        this.sleeper.sleepFor((1000 * (long) this.numOfSeconds / (long) this.countFrom));
        numOfSeconds = constNumOfSeconds;
        this.counter.decrease(1);
    }
    /**
     * stop condition.
     * @return **boolean**
     */
    public boolean shouldStop() {
        return (this.counter.getValue() == -2);
    }
}