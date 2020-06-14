package game;
import java.io.File;
import java.io.IOException;
import java.util.List;
import animation.AnimationRunner;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import listeners.ScoreTrackingListener;
import score.HighScoresTable;
import score.ScoreInfo;
import sprites.Counter;
import sprites.LivesIndicator;
import sprites.ScoreIndicator;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the GameFlow class.
 */
public class GameFlow {
    private final int numOfLives = 7;
    private final GUI gui;
    private ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;
    private LivesIndicator livesIndicator;
    private Counter lives = new Counter(numOfLives);
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;
    private final int tableSize = 10; // default table, for cases no high scores file is present.
    private HighScoresTable h;
    /**
     * constructor.
     * @param ar **AnimationRunner**
     * @param ks **KeyboardSensor**
     * @param g **GUI**
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI g) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.score = new Counter(0);
        this.scoreTrackingListener = new ScoreTrackingListener(score);
        this.scoreIndicator = new ScoreIndicator(score);
        this.livesIndicator = new LivesIndicator(lives);
        this.gui = g;
        this.h = new HighScoresTable(tableSize);
    }
    /**
     * run the levels from the list.
     * @param levels **list of levels to run**
     */
    public void runLevels(List<LevelInformation> levels) {
        File f = new File("highscores");
        try { // case file already exists.
            if (f.isFile()) {
                HighScoresTable tmp = HighScoresTable.loadFromFile(f);
                if (tmp.size() > 1) { // case table loaded successfully.
                    this.h = tmp;
                }
            } else {
                f.createNewFile();
                this.h.save(f);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.lives,
                    this.scoreTrackingListener, this.livesIndicator, this.scoreIndicator);
            level.initialize();
            while (lives.getValue() > 0 && level.getBlockCounter().getValue() > 0) {
                level.playOneTurn();
                if (!(level.getBallCounter().getValue() > 0)) {
                    lives.decrease(1);
                }
                if (lives.getValue() <= 0) {
                    break;
                }
            }
            if (level.getBlockCounter().getValue() == 0) {
                score.increase(100);
            }
        }
    }
    /**
     * run the EndScreen.
     */
    public void endScreen() {
        File f = new File("highscores"); // at that point, this file exist for sure!.
        this.animationRunner.run(
                new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY, new EndScreen(this)));
        if (this.h.getRank(this.score.getValue()) <= this.h.size()) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "Player");
            ScoreInfo player = new ScoreInfo(name, this.score.getValue());
            h.add(player);
            try { // because file already exists.
                h.save(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(h)));
    }
    /**
     * return the lives counter.
     * @return **Counter object**
     */
    public Counter getLives() {
        return this.lives;
    }
    /**
     * return the score counter.
     * @return **Counter object**
     */
    public Counter getScore() {
        return this.score;
    }
}
