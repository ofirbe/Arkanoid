package sprites;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Counter class.
 */
public class Counter {
    private int count;
    /**
     * constructor of the Counter.
     * @param num **int**
     */
    public Counter(int num) {
        this.count = num;
    }
    /**
     * add number to current count.
     * @param number **int**
     */
    public void increase(int number) {
        this.count += number;
    }
    /**
     * subtract number from current count.
     * @param number **int**
     */
    public void decrease(int number) {
        this.count -= number;
    }
    /**
     * get current count.
     * @return count **int**
     */
    public int getValue() {
        return this.count;
    }
}