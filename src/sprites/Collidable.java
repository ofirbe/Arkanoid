package sprites;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Collidable interface.
 */
public interface Collidable {
    /**
     * returns a Rectangle (all Collidable objects in our Project are Rectangular)
     * of the Collidable object.
     * @return **corresponding Rectangle**
     */
    Rectangle getCollisionRectangle();
    /**
     * returns a new Velocity adjusted to the collision of the moving object with
     * the Collidable object.
     * @param hitter **Ball**
     * @param collisionPoint **collision Point between the objects**
     * @param currentVelocity **current Velocity of the moving object**
     * @return **Velocity - adjusted Velocity after collision**
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}