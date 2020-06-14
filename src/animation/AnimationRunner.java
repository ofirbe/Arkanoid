package animation;
import biuoop.DrawSurface;
import biuoop.GUI;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the AnimationRunner class.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private int millisecondsPerFrame;
    private final biuoop.Sleeper sleeper;
    /**
     * constructoor.
     * @param g **GUI**
     */
    public AnimationRunner(GUI g) {
        this.gui = g;
        this.framesPerSecond = 60;
        this.millisecondsPerFrame = 1000 / framesPerSecond;
        this.sleeper = new biuoop.Sleeper();
    }
    /**
     * run the animation.
     * @param animation **Animation object**
     */
    public void run(Animation animation) {
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}