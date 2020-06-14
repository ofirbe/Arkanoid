package input;
import sprites.Block;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the BlockCreator interface.
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     * @param xpos **int**
     * @param ypos **int**
     * @return block **Bloc object**
     */
    Block create(int xpos, int ypos);
}