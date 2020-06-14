package game;
import java.io.File;
import java.io.IOException;
import animation.Animation;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.Menu;
import animation.MenuAnimation;
import biuoop.GUI;
import score.HighScoresTable;
import tasks.ExitTask;
import tasks.ShowHiScoresTask;
import tasks.SubMenuTask;
import tasks.Task;
import biuoop.KeyboardSensor;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Ass7 class.
 */
public class Ass7Game {
    /**
     * generate a HighScoreTable.
     * @return HighScoreAnimation for the Table.
     */
    public static HighScoresAnimation genScT() {
        int tableSize = 10;
        HighScoresTable h = null;
        File f = new File("highscores");
        try { // case file already exists.
            if (f.isFile()) {
                h = HighScoresTable.loadFromFile(f);
                if (h.size() > 1) { // case table loaded successfully.
                    return new HighScoresAnimation(h);
                }
            } else {
                f.createNewFile();
                h = new HighScoresTable(tableSize);
                h.save(f);
                return new HighScoresAnimation(h);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        h = new HighScoresTable(tableSize);
        return new HighScoresAnimation(h);
    }
    /**
     * run The Game.
     * @param g **GUI**
     * @param r **AnimationRunner**
     * @param k **KeyboardSensor**
     * @param a **arguments from main**
     */
    public static void runTheGame(GUI g, AnimationRunner r, KeyboardSensor k, String[] a) {
        Menu<Task<Void>> menu;
        Animation table;
        while (true) {
            menu = new MenuAnimation<Task<Void>>(k);
            menu.addSelection("q", "Quit", new ExitTask(g));
            table = new KeyPressStoppableAnimation(k, KeyboardSensor.SPACE_KEY, genScT());
            menu.addSelection("h", "High Scores", new ShowHiScoresTask(r, table));
            menu.addSelection("s", "Start Game", new SubMenuTask(g, r, k, a));
            r.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }
    /**
     * the main method.
     * @param args **arguments from the user**
     */
    public static void main(String[] args) {
        int winWidth = 800; // windows size
        int winHight = 600;
        GUI gui = new GUI("Arkanoid", winWidth, winHight);
        AnimationRunner runner = new AnimationRunner(gui);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        runTheGame(gui, runner, keyboard, args);
        System.exit(1);
    }
}