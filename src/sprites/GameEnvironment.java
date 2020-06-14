package sprites;
import java.util.ArrayList;
import java.util.List;
import geometry.Line;
import geometry.Point;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the GameEnvironment class.
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    /**
     * GameEnvironment object constructor..
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }
    /**
     * add the given collidable to the game environment.
     * @param c **Collidable object**
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    /**
     * Remove the given collidable from the game environment.
     * @param c **Collidable object**
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }
    /**
     * Assume an object moving from line.start() to line.end().
     * @param trajectory **Line object**
     * @return ** If this object will not collide with any of the collidables // in
     *         this collection, return null. Else, return the information // about
     *         the closest collision that is going to occur.**
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (collidables.isEmpty()) {
            // ERROR
            System.out.println("Error - collidables is Empty- No Blocks Found!");
            return null;
        }
        Point closetPoint = null;
        Collidable closetCol = null;
        Point otherPoint;
        for (Collidable c : collidables) {
            if (closetPoint == null) {
                closetPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
                closetCol = c;
                continue;
            }
            otherPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (otherPoint == null || closetPoint == null) {
                continue;
            }
            if (closetPoint.distance(trajectory.start()) >= otherPoint.distance(trajectory.start())) {
                closetPoint = otherPoint;
                closetCol = c;
            }
        }
        if (closetPoint == null) { // If this object will not collide with any of the collidables in this
                                   // collection.
            return null;
        }
        return (new CollisionInfo(closetPoint, closetCol));
    }
}