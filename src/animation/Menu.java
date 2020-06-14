package animation;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Menu interface.
 * @param <T> **generic Type**
 */
public interface Menu<T> extends Animation {
    /**
     * adds selection to Menu.
     * @param key **the key we waiting for**
     * @param message ** massage to print on the screen**
     * @param returnVal **return object**
     */
    void addSelection(String key, String message, T returnVal);
    /**
     * gets current status of Menu.
     * @return T **the Object that selected**
     */
    T getStatus();
    }
