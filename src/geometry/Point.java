package geometry;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Point class.
 */
public class Point {
    private final double x;
    private final double y;
    /**
     * Point object constructor..
     * @param x ** double - x coordination**.
     * @param y ** double - y coordination**.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Returns the distance between the current Point and another Point.
     * @param other **Point Object**.
     * @return dis **double- the distance between the Points**
     */
    public double distance(Point other) {
        double x1 = other.getX();
        double y1 = other.getY();
        double x2 = this.x;
        double y2 = this.y;
        double dis = Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
        return (dis);
    }
    /**
     * Compare between other coordinations to the current coordinations.
     * @param other **Point Object**.
     * @return true/false **return true if the points are equal, false otherwise**
     */
    public boolean equals(Point other) {
        return ((other.getX() == this.x) && (other.getY() == this.y));
    }
    /**
     * Getter for the X field of the current Point.
     * @return x **double - X value of current Point**
     */
    public double getX() {
        return x;
    }
    /**
     * Getter for the Y field of the current Point.
     * @return y **double - Y value of current Point**
     */
    public double getY() {
        return y;
    }
}