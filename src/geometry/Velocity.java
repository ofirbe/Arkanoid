package geometry;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Velocity class.
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private final double dx;
    private final double dy;
    /**
     * Velocity object constructor..
     * @param dx ** x coordination**.
     * @param dy ** y coordination**.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * Velocity object constructor..
     * @param v **Velocity object**.
     */
    Velocity(Velocity v) {
        this.dx = v.getDx();
        this.dy = v.getDy();
    }
    /**
     * Convert angel and speed to coordination (x & y).
     * @param angle ** double - launch angle**.
     * @param speed ** double - launch speed**.
     * @return v **velocity object**
     */
    public Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx1 = Math.cos(Math.toRadians(angle - 90)) * speed;
        double dy1 = Math.sin(Math.toRadians(angle - 90)) * speed;
        return new Velocity(dx1, dy1);
    }
    /**
     * Getter for dx field.
     * @return **current dx**
     */
    public double getDx() {
        return dx;
    }
    /**
     * Getter for dy field.
     * @return **current dy**
     */
    public double getDy() {
        return dy;
    }
    /**
     * Take a point with position (x,y) and return a new point with position (x+dx,
     * y+dy).
     * @param p ** Point object**.
     * @return newPoint ** Point object**.
     */
    public Point applyToPoint(Point p) {
        Point newPoint = new Point(p.getX() + dx, p.getY() + dy);
        return newPoint;
    }
}