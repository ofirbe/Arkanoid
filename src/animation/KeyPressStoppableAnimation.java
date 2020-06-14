package animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the KeyPressStoppableAnimation class.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyborad;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;
    /**
     * constructoor.
     * @param sensor **the KeyboardSensor**
     * @param k **String object**
     * @param anim **Animation object**
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String k, Animation anim) {
        this.keyborad = sensor;
        this.key = k;
        this.animation = anim;
        this.isAlreadyPressed = true;
        this.stop = false;
    }
    /**
     * puts one frame on surface.
     * @param d **surface**
     */
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyborad.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }
    /**
     * stops Animation.
     * @return **boolean**
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
