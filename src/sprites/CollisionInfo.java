package sprites;
import geometry.Point;

/**
 * @author Ofir Ben Ezra.
 *         Implementation of the CollisionInfo class.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;
    /**
     * CollisionInfo object constructor..
     * @param collisionP ** Point of the collision**
     * @param collisionO ** The object which the collision occurred with**
     */
    public CollisionInfo(Point collisionP, Collidable collisionO) {
        this.collisionPoint = collisionP;
        this.collisionObject = collisionO;
    }
    /**
     * the point at which the collision occurs.
     * @return collisionPoint **Point object**
     */
    public Point collisionPoint() {
        return collisionPoint;
    }
    /**
     * the collidable object involved in the collision.
     * @return collisionObject **Collidable object**
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}