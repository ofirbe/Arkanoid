package tasks;
import animation.Animation;
import animation.AnimationRunner;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the ShowHiScoresTask class.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;
    /**
     * constuctor.
     * @param runner **AnimationRunner object**
     * @param highScoresAnimation **Animation object**
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }
    /**
     * run the animation.
     * @return **null**
     */
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}