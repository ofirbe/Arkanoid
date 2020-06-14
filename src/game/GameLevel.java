package game;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Velocity;
import levels.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import sprites.Ball;
import sprites.Block;
import sprites.Collidable;
import sprites.Counter;
import sprites.GameEnvironment;
import sprites.LivesIndicator;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;
import java.util.List;
import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the GameLevel class.
 */
public class GameLevel implements Animation {
    private LevelInformation lvl;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter countOfBlocks;
    private BlockRemover blockRemover;
    private Counter countOfBalls;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;
    private LivesIndicator livesIndicator;
    private final int winWidth = 800; // windows size
    private final int winHight = 600;
    private final double frameWidth = 20; // the thick of the frame blocks
    private final int ballRad = 5; // The speed of the ball is determined by its size
    private final int paddleHight = 15;
    private final double ballStartX = (winWidth / 2);
    private final double ballStartY = winHight - ballRad - frameWidth - paddleHight - 0.0000001;
    private final java.awt.Color screenColor = java.awt.Color.lightGray;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    /**
     * Game object constructor..
     * @param level **information of the level*
     * @param ks **the keyboard**
     * @param ar **the AnimationRunner**
     * @param live **Counter of lives**
     * @param scoreListtener **Listtener of the score**
     * @param liveInd ** Indicator of lives**
     * @param scoreInd **Indicator of score**
     */
    public GameLevel(LevelInformation level, KeyboardSensor ks, AnimationRunner ar, Counter live,
            ScoreTrackingListener scoreListtener, LivesIndicator liveInd, ScoreIndicator scoreInd) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.countOfBlocks = new Counter(0);
        this.blockRemover = new BlockRemover(this, countOfBlocks);
        this.countOfBalls = new Counter(0);
        this.ballRemover = new BallRemover(this, countOfBalls);
        this.scoreTrackingListener = scoreListtener;
        this.scoreIndicator = scoreInd;
        this.livesIndicator = liveInd;
        this.runner = ar;
        this.running = false;
        this.keyboard = ks;
        this.lvl = level;
    }
    /**
     * add to the game environment the collidable object.
     * @param c **Collidable object.**
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * add to the Sprite collection the Sprite object.
     * @param s ** Sprite object.**
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * return Counter of Balls.
     * @return **Counter**
     */
    public Counter getBallCounter() {
        return this.countOfBalls;
    }
    /**
     * return Counter of Blocks.
     * @return **Counter**
     */
    public Counter getBlockCounter() {
        return this.countOfBlocks;
    }
    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them
     * to the game.
     */
    public void initialize() {
        this.addSprite(lvl.getBackground()); // add background of the levels to the game
        deathRegion(); // generate the deathRegion
        setFrame(); // set 4 blocks for boundaries.
        this.scoreIndicator.addToGame(this); // add scoreIndicator to the game
        this.livesIndicator.addToGame(this); // add livesIndicator to the game
        List<Block> lvlBlock = lvl.blocks();
        for (int i = 0; i < lvlBlock.size(); i++) {
            lvlBlock.get(i).addHitListener(blockRemover);
            lvlBlock.get(i).addHitListener(scoreTrackingListener);
            countOfBlocks.increase(1);
            lvlBlock.get(i).addToGame(this);
        }
    }
    /**
     * generate deathRegion Block function.
     */
    public void deathRegion() {
        Block deathRegion = new Block(0, winHight - frameWidth + 20, winWidth, frameWidth, screenColor, ballRad);
        deathRegion.setMaxHit(0);
        deathRegion.addClr(-1, java.awt.Color.GRAY);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);
    }
    /**
     * shouldStop() is in charge of stopping condition.
     * @return **boolean- should stop or not**
     */
    public boolean shouldStop() {
        return (!this.running);
    }
    /**
     * doOneFrame(DrawSurface) is in charge of the logic.
     * @param d **drawface**
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (countOfBlocks.getValue() <= 0 || countOfBalls.getValue() <= 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
    }
    /**
     * Run one game -- start the animation loop.
     */
    public void playOneTurn() {
        Point uLpaddle = new Point((winWidth / 2) - (lvl.paddleWidth() / 2), winHight - frameWidth - paddleHight);
        Paddle paddle = new Paddle(uLpaddle, lvl.paddleWidth(), paddleHight, java.awt.Color.ORANGE, keyboard, ballRad,
                lvl.paddleSpeed());
        paddle.addToGame(this); // add paddle to the game
        setBalls();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites)); // countdown before turn starts.
        this.running = true;
        this.runner.run(this);
        paddle.removeFromGame(this);
        return;
    }
    /**
     * generate 4 blocks for the frame of the game.
     */
    public void setFrame() {
        Block frameL = new Block(0, 20, frameWidth, winHight - 20, java.awt.Color.GRAY, ballRad);
        Block frameR = new Block(winWidth - frameWidth, 20, frameWidth, winHight - 20, java.awt.Color.GRAY, ballRad);
        Block frameU = new Block(0, 20, winWidth, frameWidth, java.awt.Color.GRAY, ballRad);
        frameL.setMaxHit(0);
        frameR.setMaxHit(0);
        frameU.setMaxHit(0);
        frameL.addClr(-1, java.awt.Color.GRAY);
        frameR.addClr(-1, java.awt.Color.GRAY);
        frameU.addClr(-1, java.awt.Color.GRAY);
        frameL.addToGame(this);
        frameR.addToGame(this);
        frameU.addToGame(this);
    }
    /**
     * generate balls for the game.
     * **the methoud return a list of the game balls for the paddle- it fix a problem i had- the balls stuck in the
     * padlle if they hit from the side when the padlle moving.**
     */
    public void setBalls() {
        List<Velocity> lvlBallVel = lvl.initialBallVelocities();
        for (int i = 0; i < lvl.numberOfBalls(); i++) {
            Ball ball1 = new Ball(ballStartX, ballStartY, ballRad, java.awt.Color.white); // new Ball1
            ball1.setVelocity(lvlBallVel.get(i));
            ball1.setGameEnvironment(environment);
            countOfBalls.increase(1);
            ball1.addToGame(this); // add ball1 to the game
        }
    }
    /**
     * remove from the game environment the collidable object.
     * @param c **Collidable object.**
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * remove from the Sprite collection the Sprite object.
     * @param s ** Sprite object.**
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}