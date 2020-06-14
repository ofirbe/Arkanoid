package tasks;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Task interface.
 * @param <T> **generic type**
 */
public interface Task<T> {
    /**
     * run this task.
     * @return T **generic type**
     */
    T run();
}
