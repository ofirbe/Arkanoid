package score;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the HighScoresTable class.
 */
public class HighScoresTable implements Serializable {
    private static final long serialVersionUID = 1L;
    private int tableSize;
    private List<ScoreInfo> scores;
    private final transient Comparator<ScoreInfo> comp = new Comparator<ScoreInfo>() {
        public int compare(ScoreInfo s1, ScoreInfo s2) {
            if (s1.getScore() > s2.getScore()) {
                return 1;
            }
            if (s1.getScore() < s2.getScore()) {
                return -1;
            }
            return 0;
        }
    };
    /**
     * constructor.
     * @param size **The size means that the table holds up to size top scores.**
     */
    public HighScoresTable(int size) {
        this.tableSize = size;
        this.scores = new ArrayList<ScoreInfo>(this.tableSize);
    }
    /**
     * Add a high-score.
     * @param s **ScoreInfo Object**
     */
    public void add(ScoreInfo s) {
        if (this.scores.size() < this.tableSize) {
            this.scores.add(s);
            return;
        }
        int rank = this.getRank(s.getScore());
        if (rank <= this.size()) {
            this.scores.remove(this.tableSize - 1);
            this.scores.add(s);
        }
    }
    /**
     * Return table size.
     * @return size **int**
     */
    public int size() {
        return (this.tableSize);
    }
    /**
     * Return the current high scores.
     * @return list **The list that sorted such that the highest scores come first.**
     */
    public List<ScoreInfo> getHighScores() {
        if (this.scores != null) {
            Collections.sort(this.scores, Collections.reverseOrder(comp));
        }
        return this.scores;
    }
    /**
     * return the rank of the current score: where will it be on the list if added?.
     * @param score **current score**
     * @return rank **1- highest on the list, size- lowest, rank>size - to low for the list**
     */
    public int getRank(int score) {
        int rank = 1;
        for (ScoreInfo s : this.getHighScores()) {
            if (s.getScore() >= score) {
                rank++;
            }
        }
        return rank;
    }
    /**
     * Clears the table.
     */
    public void clear() {
        this.scores.clear();
    }
    /**
     * Load table data from file.
     * @param filename ** File Object**
     * @throws IOException **Exception**
     */
    public void load(File filename) throws IOException {
        FileInputStream x = null;
        ObjectInputStream objStream = null;
        try {
            x = new FileInputStream(filename);
            objStream = new ObjectInputStream(x);
            HighScoresTable table = (HighScoresTable) objStream.readObject();
            this.scores = table.getHighScores();
            this.tableSize = table.size();
        } catch (IOException e) {
            throw e;
        } catch (ClassNotFoundException e) {
            System.out.println("Error- Class Not Found");
        } finally {
            if (objStream != null) {
                try {
                    objStream.close();
                } catch (IOException e) {
                    throw e;
                }
            }
            if (x != null) {
                try {
                    x.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }
    /**
     * Save table data to the specified file.
     * @param filename ** File Object**
     * @throws IOException **Exception**
     */
    public void save(File filename) throws IOException {
        FileOutputStream x = null;
        ObjectOutputStream objStream = null;
        try {
            x = new FileOutputStream(filename);
            objStream = new ObjectOutputStream(x);
            objStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objStream != null) {
                try {
                    objStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (x != null) {
                try {
                    x.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * Read a table from file and return it.
     * @param filename **File object**
     * @return t **HighScoresTable object**
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable t = new HighScoresTable(1);
        try { // try to load table from file.
            t.load(filename);
            return t;
        } catch (IOException e) {
            return t; // If the file does not exist, or there is a problem with reading it, an empty table is returned.
        }
    }
}
