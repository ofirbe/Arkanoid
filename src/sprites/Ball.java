package sprites;
import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Ball class.
 */
public class Ball implements Sprite {
    private GameEnvironment game;
    private Point center;
    private final int rad;
    private final java.awt.Color color;
    private Velocity v;
    private double xMax;
    private double yMax;
    private double xMin;
    private double yMin;
    /**
     * Ball object constructor..
     * @param center ** Point object**.
     * @param r ** radios of the ball**.
     * @param color ** color of the ball**.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.rad = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        xMax = 0;
        xMin = 0;
        yMin = 0;
        yMax = 0;
    }
    /**
     * Ball object constructor..
     * @param x ** x coordination of the center**.
     * @param y ** y coordination of the center**.s
     * @param r ** radios of the ball**.
     * @param color ** color of the ball**.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.rad = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        xMax = 0;
        xMin = 0;
        yMin = 0;
        yMax = 0;
    }
    // setters
    /**
     * Setter for the velocity of the current Ball.
     * @param newV **Velocity for the current Ball**
     */
    public void setVelocity(Velocity newV) {
        this.v = newV;
    }
    /**
     * Setter for the velocity of the current Ball.
     * @param dx **x coordination of the velocity for the current Ball**
     * @param dy **y coordination of the velocity for the current Ball**
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }
    /**
     * Setter for the boundary of the current Ball.
     * @param xMin1 **x coordination of left wall**
     * @param yMin1 **y coordination of lower wall**
     * @param xMax1 **x coordination of right wall**
     * @param yMax1 **y coordination of upper wall**
     */
    public void setFrame(double xMin1, double yMin1, double xMax1, double yMax1) {
        this.xMin = xMin1;
        this.yMin = yMin1;
        this.xMax = xMax1;
        this.yMax = yMax1;
    }
    /**
     * set the game environment of the ball.
     * @param game1 **GameEnvironment object**
     */
    public void setGameEnvironment(GameEnvironment game1) {
        this.game = game1;
    }
    // getters
    /**
     * Getter for the X field of the center Point.
     * @return x **double - X value of center Point**
     */
    public int getX() {
        return ((int) this.center.getX());
    }
    /**
     * Getter for the Y field of the center Point.
     * @return y **double - y value of center Point**
     */
    public int getY() {
        return ((int) this.center.getY());
    }
    /**
     * Getter for the radios field of the current Ball.
     * @return rad **double - radios length of the current Ball**
     */
    public int getSize() {
        return (this.rad);
    }
    /**
     * Getter for the color field of the current Ball.
     * @return color **java.awt.Color - color of the Ball**
     */
    public java.awt.Color getColor() {
        return (this.color);
    }
    /**
     * Getter for the velocity field of the current Ball.
     * @return v **Velocity - velocity of the Ball**
     */
    public Velocity getVelocity() {
        return (this.v);
    }
    /**
     * Move the ball to the next frame.
     */
    public void moveOneStep() {
        if (xMax > xMin && yMax > yMin) { // case frame boundaries were set.
            if ((center.getX() + v.getDx() + rad) > xMax) { // right x wall
                this.setVelocity(-v.getDx(), v.getDy());
            }
            if ((center.getX() + v.getDx() - rad) < xMin) { // left x wall
                this.setVelocity(-v.getDx(), v.getDy());
            }
            if ((center.getY() + v.getDy() + rad) > yMax) { // down y wall
                this.setVelocity(v.getDx(), -v.getDy());
            }
            if ((center.getY() + v.getDy() - rad) < yMin) { // up y wall
                this.setVelocity(v.getDx(), -v.getDy());
            }
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(center));
        CollisionInfo col = game.getClosestCollision(trajectory);
        if (col == null) {
            this.center = this.getVelocity().applyToPoint(center);
            return;
        } else {
            double epsX = 0;
            double epsY = 0;
            Point collisionPoint = col.collisionPoint();
            if (this.center.getX() <= collisionPoint.getX()) { // "left or right" collision.
                epsX = -1.000000000001;
            } else {
                epsX = 1.000000000001;
            }
            if (this.center.getY() <= collisionPoint.getY()) { // "upper or bottom" collision.
                epsY = -1.000000000001;
            } else {
                epsY = 1.000000000001;
            } // moving the Ball and adjusting the Balls Velocity to the "hit".
            Velocity velHit = col.collisionObject().hit(this, col.collisionPoint(), v);
            this.setVelocity(velHit);
            this.center = new Point(collisionPoint.getX() + epsX, collisionPoint.getY() + epsY);
            return;
        }
    }
    /**
     * move one more step.
     */
    public void timePassed() {
        this.moveOneStep();
    }
    /**
     * Draw the ball on the given DrawSurface.
     * @param surface **DrawSurface - surface of the Ball**
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), rad);
    }
    /**
     * return the speed by the Ball weight.
     * @return speed **double - speed of the Ball**
     */
    public double speedByWeight() {
        if (this.getSize() >= 50) {
            return 0.5;
        } else {
            return ((1 / (double) this.getSize()) * 50);
        }
    }
    /**
     * add this ball to the game.
     * @param theGame **Game object**
     */
    public void addToGame(GameLevel theGame) {
        theGame.addSprite(this);
    }
    /**
     * remove this ball from the game.
     * @param theGame **Game object**
     */
    public void removeFromGame(GameLevel theGame) {
        theGame.removeSprite(this);
    }
}