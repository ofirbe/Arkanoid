package geometry;
import java.util.ArrayList;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Point class.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    // Create a new rectangle with location and width/height.
    /**
     * Rectangle object constructor..
     * @param upperLeft **upperLeft Point location**
     * @param width **width of rectangle**
     * @param height **height of rectangle**
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * generate array of all the Rectangle Lines by using the upperLeft Point, width
     * and height.
     * @return recLine **Array of Lines**
     */
    public Line[] genRecLine() {
        Line[] recLine = new Line[4];
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point underLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point underRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        recLine[0] = new Line(upperLeft, upperRight);
        recLine[1] = new Line(upperRight, underRight);
        recLine[2] = new Line(underRight, underLeft);
        recLine[3] = new Line(underLeft, upperLeft);
        return (recLine);
    }
    /**
     * check if the point exist at the list.
     * @param interList **List of Points**
     * @param interPoint **Point object**
     * @return boolean **true if exist and false otherwise.**
     */
    public boolean checkExist(ArrayList<Point> interList, Point interPoint) {
        return (interList.contains(interPoint));
    }
    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line ** the line we want to check intersection with**.
     * @return interList **List of intersection points**.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> interList = new ArrayList<Point>();
        Point interPoint = null;
        Line[] recLine = genRecLine();
        for (int i = 0; i < recLine.length; i++) { // 0-U. 1-R. 2-D. 3-L.
            interPoint = line.intersectionWith(recLine[i]);
            if (interPoint != null && !checkExist(interList, interPoint)) {
                interList.add(interPoint);
            }
        }
        return (interList);
    }
    /**
     * Return the width of the rectangle.
     * @return **double**
     */
    public double getWidth() {
        return (this.width);
    }
    /**
     * Return the hight of the rectangle.
     * @return **double**
     */
    public double getHeight() {
        return (this.height);
    }
    /**
     * Returns the upper-left point of the rectangle.
     * @return **Point object**
     */
    public Point getUpperLeft() {
        return (this.upperLeft);
    }
}