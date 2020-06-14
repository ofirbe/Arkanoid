package input;
import java.util.HashMap;
import java.util.Map;
import sprites.Block;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the BlocksFromSymbolsFactory class.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;
    /**
     * constructor.
     */
    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new HashMap<String, Integer>();
        this.blockCreators = new HashMap<String, BlockCreator>();
    }
    /**
     * add Spacer to map.
     * @param key **String**
     * @param value **Integer**
     */
    public void addSpacer(String key, Integer value) {
        this.spacerWidths.put(key, value);
    }
    /**
     * add BlockCreator to map.
     * @param key **String**
     * @param value **BlockCreator**
     */
    public void addBlockCreator(String key, BlockCreator value) {
        this.blockCreators.put(key, value);
    }
    /**
     * returns true if 's' is a valid space symbol.
     * @param s **String**
     * @return boolean **true-the key is exist, otherwise- false.**
     */
    public boolean isSpaceSymbol(String s) {
        return (spacerWidths.containsKey(s));
    }
    /**
     * returns true if 's' is a valid block symbol.
     * @param s **String**
     * @return boolean **true-the key is exist, otherwise- false.**
     */
    public boolean isBlockSymbol(String s) {
        return (blockCreators.containsKey(s));
    }
    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     * @param s **key of spacer**
     * @return value of spacer.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
    /**
     * Return a block according to the definitions associated with symbol s. The block will be located at position
     * @param s **key of block creator**
     * @param x **x position**
     * @param y ** y position**
     * @return Block object.
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }
}