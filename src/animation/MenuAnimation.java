package animation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the MenuAnimation class.
 * @param <T> **generic type**
 */
public class MenuAnimation<T> implements Menu<T> {
    private boolean stop;
    private final biuoop.KeyboardSensor k;
    private final HashMap<String, T> tasks;
    private final HashMap<String, String> names;
    private String status = "?";
    /**
     * constructor.
     * @param keyboard **Sensor**
     */
    public MenuAnimation(KeyboardSensor keyboard) {
        this.k = keyboard;
        this.tasks = new HashMap<String, T>();
        this.names = new HashMap<String, String>();
    }
    /**
     * puts one frame on surface.
     * @param d **surface**
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(java.awt.Color.DARK_GRAY);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(java.awt.Color.CYAN);
        d.drawText(50, 50, "Arkanoid", 30);
        d.setColor(java.awt.Color.green);
        List<String> nameKey = new ArrayList<String>();
        nameKey.addAll(this.names.keySet());
        for (int i = 0; i < nameKey.size(); i++) {
            d.drawText(100, 100 + (i * 30), "(" + nameKey.get(i) + ") " + this.names.get(nameKey.get(i)), 20);
        }
        for (int i = 0; i < nameKey.size(); i++) {
            if (this.k.isPressed(nameKey.get(i))) {
                this.status = nameKey.get(i);
                this.stop = true;
                break;
            }
        }
    }
    /**
     * stops Animation.
     * @return **boolean**
     */
    public boolean shouldStop() {
        return this.stop;
    }
    /**
     * adds selection to Menu.
     * @param key **the key we waiting for**
     * @param message ** massage to print on the screen**
     * @param returnVal **return object**
     */
    public void addSelection(String key, String message, T returnVal) {
        this.names.put(key, message);
        this.tasks.put(key, returnVal);
    }
    /**
     * gets current status of Menu.
     * @return T **the Object that selected**
     */
    public T getStatus() {
        if (status != "?") {
            return this.tasks.get(status);
        }
        return null;
    }
}
