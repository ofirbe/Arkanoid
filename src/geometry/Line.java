package geometry;
import java.util.ArrayList;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Line class.
 */
public class Line {
    private final double eps = 0.005;
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    /**
     * Line object constructor..
     * @param start ** start point coordinations**.
     * @param end ** end point coordinations**.
     */
    public Line(Point start, Point end) {
        this.x1 = start.getX();
        this.y1 = start.getY();
        this.x2 = end.getX();
        this.y2 = end.getY();
    }
    /**
     * Line object constructor initialized.
     * @param x1 ** double - x start coordination**.
     * @param y1 ** double - y start coordination**.
     * @param x2 ** double - x end coordination**.
     * @param y2 ** double - y end coordination**.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    /**
     * length of current Line method.
     * @return ** double- the length of the line**.
     */
    public double length() {
        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);
        return (start.distance(end));
    }
    /**
     * middle of current Line method.
     * @return middle ** Point-the middle point of the line**.
     */
    public Point middle() {
        Point middle = new Point(((x2 + x1) / 2), ((y2 + y1) / 2));
        return middle;
    }
    /**
     * returns starting Point of the current Line.
     * @return start **starting Point of the current Line**
     */
    public Point start() {
        Point start = new Point(x1, y1);
        return start;
    }
    /**
     * returns ending Point of the current Line.
     * @return end **ending Point of the current Line**
     */
    public Point end() {
        Point end = new Point(x2, y2);
        return end;
    }
    /**
     * calculates and returns the slope of the current Line.
     * @return **double - slope of the current Line**
     */
    public double slope() {
        return ((y2 - y1) / (x2 - x1));
    }
    /**
     * use start and end methods to compare between the current line and other line.
     * @param other **Line object**
     * @return **boolean- true is the lines are equal, false otherwise**
     */
    public boolean equals(Line other) {
        return ((this.start().equals(other.start())) && (this.end().equals(other.end())));
    }
    /**
     * check if point is on a line.
     * @param point **Point with x&y coordinations**
     * @return **boolean- true if point on the line, false otherwise**
     */
    public boolean isOnLine(Point point) {
        double x = point.getX(), y = point.getY();
        double minX = Math.min(x1, x2);
        double maxX = Math.max(x1, x2);
        double minY = Math.min(y1, y2);
        double maxY = Math.max(y1, y2);
        if (x1 == x2 && x == x1) {
            if (y >= minY && y <= maxY) {
                return true;
            }
            return false;
        }
        if (Math.abs(maxX - x) <= eps) {
            x = maxX;
        }
        if (Math.abs(minX + x) <= eps) {
            x = minX;
        }
        if (Math.abs(maxY - y) <= eps) {
            y = maxY;
        }
        if (Math.abs(minY + y) <= eps) {
            y = minY;
        }
        if (x >= minX && y >= minY && x <= maxX && y <= maxY) { // point is not at the range of the line
            return true;
        }
        return false;
    }
    /**
     * checks if two Lines intersect.
     * @param other **the Line we check intersection with**
     * @return **boolean - true if Lines intersect, otherwise false**
     */
    public boolean isIntersecting(Line other) { // if intersectionWith return null- there is no intersection point.
        if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;
    }
    /**
     * check if intersection point between two lines exist- if exist, returns the
     * Point, otherwise return null.
     * @param other **the Line which will be intersected with the current Line**
     * @return intersection **the intersection Point**
     */
    public Point intersectionWith(Line other) {
        Point inter = this.interCheck(other);
        if (inter != null && this.isOnLine(inter) && other.isOnLine(inter)) {
            return inter;
        }
        return null;
    }
    /**
     * check all the cases that can be when we looking for intersection point
     * between two lines. if intersection exist- return the point, otherwise return
     * null.
     * @param other **the line that we want to check with "this" line.
     * @return intersection **intersection point\ null if not exist**.
     */
    public Point interCheck(Line other) {
        double interX = 0, interY = 0; // case both Lines aren't functions (They collide or never intersect).
        if (this.equals(other)) { // if the lines equals return start point of this line.
            interX = x1;
            interY = y1;
            Point intersection = new Point(interX, interY);
            return intersection;
        }
        if (x1 == x2 && other.x1 == other.x2) { // special case, when Lines intersect at edge of segment.
            if (x1 == other.x1) {
                if (y1 == other.y1 || y1 == other.y2) {
                    Point intersection = new Point(x1, y1);
                    return intersection;
                }
                if (y2 == other.y1 || y2 == other.y2) {
                    Point intersection = new Point(x1, y2);
                    return intersection;
                }
                if (x1 == x2 && y1 == y2) { // case "this" Line is a Point.
                    Point intersection = new Point(x1, y1);
                    return intersection;
                }
                if (other.x1 == other.x2 && other.y1 == other.y2) { // case "other" Line is a Point.
                    Point intersection = new Point(other.x1, other.y1);
                    return intersection;
                }
            }
            return null;
        } // next two blocks deal with case that only one Line is a function.
        if (x1 == x2 && other.x1 != other.x2) {
            interX = x1; // Linear equation: y - y0 = m(x - x0) = y = mx + y0 -mx0, b is y0 -mx0.
            double m1 = other.slope();
            double b1 = other.y1 - (m1 * other.x1);
            interY = (m1 * interX) + b1;
            Point intersection = new Point(interX, interY);
            return intersection;
        } // case the 'other' Line isn't a function.
        if (x1 != x2 && other.x1 == other.x2) {
            interX = other.x1;
            double m1 = this.slope();
            double b1 = this.y1 - (m1 * this.x1);
            interY = (m1 * interX) + b1;
            Point intersection = new Point(interX, interY);
            return intersection;
        }
        if (this.slope() == other.slope()) { // equal slopes = none or infinite amount of intersection Points.
            if (y1 == y2 && other.y1 == other.y2) { // special case, when Lines intersect at edge of segment.
                if (y1 == other.y1) {
                    if (x1 == other.x1 || x1 == other.x2) {
                        Point intersection = new Point(x1, y1);
                        return intersection;
                    }
                    if (x2 == other.x1 || x2 == other.x2) {
                        Point intersection = new Point(x2, y1);
                        return intersection;
                    }
                }
                return null;
            }
        }
        double m1 = this.slope(); // slopes and start coordinations of 2 lines.
        double x11 = this.x1;
        double y11 = this.y1;
        double m2 = other.slope();
        double x22 = other.x1;
        double y22 = other.y1;
        interX = (((m1 * x11) - (m2 * x22) + y22 - y11) / (m1 - m2));
        interY = ((m1 * interX) - (m1 * x11) + y11);
        if (interX < 0 || interY < 0) { // error at calc
            return null;
        }
        Point intersection = new Point(interX, interY);
        return intersection;
    }
    /**
     * If this line does not intersect with the rectangle, return null. Otherwise,
     * return the closest intersection point to the start of the line.
     * @param rect **Rectangle Object**
     * @return interP **Point Object**
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        ArrayList<Point> interList = (ArrayList<Point>) rect.intersectionPoints(this);
        if (interList.isEmpty()) {
            return null;
        } else {
            double dist = this.start().distance(interList.get(0));
            int iter = 0;
            for (int i = 1; i < interList.size(); i++) {
                if (this.start().distance(interList.get(i)) <= dist) {
                    dist = this.start().distance(interList.get(i));
                    iter = i;
                }
            }
            return (interList.get(iter));
        }
    }
}
