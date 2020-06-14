package score;
import java.io.Serializable;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the ScoreInfo class.
 */
public class ScoreInfo implements Serializable, Comparable<ScoreInfo> {
    private static final long serialVersionUID = 1L;
    private String name;
    private int score;
    /**
     * Constructor.
     * @param name **String- the player name**
     * @param score **Int- the player score**
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }
    /**
     * get the player name.
     * @return name **String- the player name**
     */
    public String getName() {
        return this.name;
    }
    /**
     * get the player score.
     * @return score **Int- the player score**
     */
    public int getScore() {
        return this.score;
    }
    /**
     * compares current ScoreInfo to inputed one.
     * @param other **ScoreInfo**
     * @return **integer**
     */
    public int compareTo(ScoreInfo other) {
        if (this.getScore() > other.getScore()) {
            return 1;
        }
        if (this.getScore() < other.getScore()) {
            return -1;
        }
        return 0;
    }
}