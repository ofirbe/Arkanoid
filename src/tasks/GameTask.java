package tasks;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import game.GameFlow;
import input.LevelSpecificationReader;
import levels.LevelInformation;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the GameTask class.
 */
public class GameTask implements Task<Void> {
    private GameFlow gamef;
    private String nameOfFile;

    /**
     * constuctor.
     * @param g **Game Flow**
     * @param n **name of file**
     */
    public GameTask(GameFlow g, String n) {
        this.gamef = g;
        this.nameOfFile = n;
    }
    /**
     * run the game.
     * @return **null**
     */
    public Void run() {
        LevelSpecificationReader fileLevels = new LevelSpecificationReader();
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        try {
            InputStream file = null;
            if (nameOfFile != null) {
                file = ClassLoader.getSystemClassLoader().getResourceAsStream(this.nameOfFile);
            }
            if (file != null) { // if the first argument is a file name.
                java.io.Reader reader = new InputStreamReader(file);
                levels = fileLevels.fromReader(reader);
                reader.close();
            } else {
                System.out.println("EROOR!");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gamef.runLevels(levels);
        gamef.endScreen();
        return null;
    }
}
