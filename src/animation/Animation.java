package animation;
import biuoop.DrawSurface;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Animation interface.
 */
public interface Animation {
    /**
     * doOneFrame(DrawSurface) is in charge of the logic.
     * @param d **surface**
     */
    void doOneFrame(DrawSurface d);
    /**
     * shouldStop() is in charge of stopping condition.
     * @return **boolean- stop or not**
     */
    boolean shouldStop();
}