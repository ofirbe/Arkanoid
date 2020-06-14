package sprites;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Paddle class which implements the Collidable and Sprite interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private java.awt.Color color;
    private Point upperLeft;
    private final double width;
    private final double height;
    private final double speed;
    private final double xLlim = 20; // left X coordination boundary.(the frame width).
    private final double xRlim; // right X coordination boundary....(right boundary-width-frame width).
    private final double difference; // radius of the moving Ball.
    private final double eps = 0.000000000001;
    /**
     * constructor of Paddle.
     * @param upperLeft **upper left point of the paddle**
     * @param w **width of the paddle**
     * @param h **height of the paddle**
     * @param c **color of the paddle**
     * @param k **keyboard sensor**
     * @param d **radios of the ball**
     * @param s **speed of the paddle**
     */
    public Paddle(Point upperLeft, double w, double h, java.awt.Color c, biuoop.KeyboardSensor k, double d, double s) {
        this.upperLeft = upperLeft;
        this.width = w;
        this.height = h;
        this.color = c;
        this.keyboard = k;
        this.speed = s;
        this.difference = d;
        this.xRlim = 800 - xLlim - width;
    }
    /**
     * move the Paddle Left.
     */
    public void moveLeft() {
        Point leftStep = new Point(upperLeft.getX() - speed, upperLeft.getY());
        if (leftStep.getX() < xLlim) { // if the padddle is at the left wall.
            this.upperLeft = new Point(xLlim, this.upperLeft.getY());
        } else {
            this.upperLeft = leftStep;
        }
    }
    /**
     * move the Paddle Right.
     */
    public void moveRight() {
        Point rightStep = new Point(upperLeft.getX() + speed, upperLeft.getY());
        if (rightStep.getX() > xRlim) { // if the padddle is at the right wall.
            this.upperLeft = new Point(xRlim, this.upperLeft.getY());
        } else {
            this.upperLeft = rightStep;
        }
    }
    // Sprite
    /**
     * check the next step of the paddle.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * draw the paddle on the surface.
     * @param surface **Surface object**
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
        surface.setColor(java.awt.Color.BLACK);
        surface.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
    }
    // Collidable
    /**
     * return the paddle shape.
     * @return paddleRec **Rectangle object**
     */
    public Rectangle getCollisionRectangle() {
        Point recUpperLeft = new Point(upperLeft.getX() - difference, upperLeft.getY() - difference);
        Rectangle paddleRec = new Rectangle(recUpperLeft, width + (2 * difference), height);
        return (paddleRec);
    }
    /**
     * check what "wall" and "part"(0-4) of the paddle the Ball hit and change the
     * Velocity in accordance.
     * @param ball **Ball**
     * @param collisionPoint ** Point object**
     * @param currentVelocity **current Velocity object**
     * @return new Velocity object.
     */
    public Velocity hit(Ball ball, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double ballSpeed = Math.sqrt((dx * dx) + (dy * dy));
        Line[] blockRecLine = this.getCollisionRectangle().genRecLine();
        for (int i = 0; i < blockRecLine.length; i++) {
            if ((i == 1 || i == 3) && blockRecLine[i].isOnLine(collisionPoint)) { // right/left of the paddle
                dx *= (-1);
            }
            if (blockRecLine[i].isOnLine(collisionPoint) && (i == 0)) { // upper line
                Line[] paddleUpLine = splitTheLineToFive(blockRecLine[0]); // 0-left,2-middle,4-right
                for (int j = 0; j < paddleUpLine.length; j++) {
                    if ((j == 2) && paddleUpLine[j].isOnLine(collisionPoint)) { // middle
                        return (new Velocity(dx, -dy));
                    }
                    if ((j == 0) && paddleUpLine[j].isOnLine(collisionPoint)) { // most left
                        return (currentVelocity.fromAngleAndSpeed(330, ballSpeed));
                    }
                    if ((j == 1) && paddleUpLine[j].isOnLine(collisionPoint)) {
                        return (currentVelocity.fromAngleAndSpeed(300, ballSpeed));
                    }
                    if ((j == 3) && paddleUpLine[j].isOnLine(collisionPoint)) {
                        return (currentVelocity.fromAngleAndSpeed(30, ballSpeed));
                    }
                    if ((j == 4) && paddleUpLine[j].isOnLine(collisionPoint)) { // most right
                        return (currentVelocity.fromAngleAndSpeed(60, ballSpeed));
                    }
                }
            }
        }
        return (new Velocity(dx, dy));
    }
    /**
     * Add this paddle to the game.
     * @param game **Game object**
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
    /**
     * Remove this paddle from the game.
     * @param game **Game object**
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
    /**
     * split the line of the paddle to 5 equal lines.
     * @param line **Line object**
     * @return splitLine **Array of Lines**
     */
    public Line[] splitTheLineToFive(Line line) {
        Line[] splitLine = new Line[5];
        double y = line.start().getY();
        double xStart = line.start().getX();
        double xEnd = line.end().getX();
        double splitDis = ((xEnd - xStart) / 5);
        for (int i = 0; i < 5; i++) {
            splitLine[i] = new Line(xStart + (i * splitDis), y, xStart + ((i + 1) * splitDis) - eps, y);
        }
        return splitLine;
    }
}