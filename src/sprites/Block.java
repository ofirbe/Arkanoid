package sprites;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listeners.HitListener;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the Block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private java.awt.Color strokeColor;
    private final Point upperLeft;
    private final double width;
    private final double height;
    private int maxHit;
    private int countHit;
    private final double difference;
    private HashMap<Integer, java.awt.Color> colors;
    private HashMap<Integer, java.awt.Image> images;
    /**
     * Block object constructor.
     * @param upperLeft **The upper left point of the block**
     * @param width **width of the block**
     * @param height **height of the block**
     * @param color **color of the block**
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.strokeColor = color;
        this.maxHit = 0;
        this.countHit = maxHit;
        this.difference = 0;
        this.hitListeners = new ArrayList<HitListener>();
        this.colors = new HashMap<Integer, java.awt.Color>();
        this.images = new HashMap<Integer, java.awt.Image>();
    }
    /**
     * Block object constructor.
     * @param x **x coordination of the upper left point**
     * @param y **y coordination of the upper left point**
     * @param width **width of the block**
     * @param height **height of the block**
     * @param color **color of the block**
     * @param difference **radios of the ball**
     */
    public Block(double x, double y, double width, double height, java.awt.Color color, double difference) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
        this.strokeColor = color;
        this.maxHit = 0;
        this.countHit = 0;
        this.difference = difference;
        this.hitListeners = new ArrayList<HitListener>();
        this.colors = new HashMap<Integer, java.awt.Color>();
        this.images = new HashMap<Integer, java.awt.Image>();
    }
    /**
     * create new Rectangle object.
     * @return BlockRec **Rectangle object**
     */
    public Rectangle getCollisionRectangle() {
        Point recUpperLeft = new Point(upperLeft.getX() - difference, upperLeft.getY() - difference);
        return new Rectangle(recUpperLeft, width + (2 * difference), height + (2 * difference));
    }
    /**
     * set the maximum number of hit for this block.
     * @param num **int**
     */
    public void setMaxHit(int num) {
        this.maxHit = num;
        this.countHit = num;
    }
    /**
     * notifies to all the listeners that this Block has been Hit.
     * @param hitter **Ball**
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * check what "wall" of the block the Ball hit and change the Velocity in accordance.
     * @param hitter **Ball**
     * @param collisionPoint ** Point object**
     * @param currentVelocity **current Velocity object**
     * @return new Velocity object.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Line[] blockRecLine = this.getCollisionRectangle().genRecLine();
        for (int i = 0; i < blockRecLine.length; i++) {
            if (blockRecLine[i].isOnLine(collisionPoint) && (i == 0 || i == 2)) { // upper/bottom
                dy *= (-1);
            }
            if (blockRecLine[i].isOnLine(collisionPoint) && (i == 1 || i == 3)) { // right/left
                dx *= (-1);
            }
        }
        this.countHit--;
        this.notifyHit(hitter);
        return (new Velocity(dx, dy));
    }
    /**
     * Draw the Block on the given DrawSurface.
     * @param surface **DrawSurface - surface of the Block**
     */
    public void drawOn(DrawSurface surface) {
        if (this.colors.containsKey(this.countHit)) {
            surface.setColor(this.colors.get(this.countHit));
            surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
        } else if (this.images.containsKey(this.countHit)) {
            surface.drawImage((int) upperLeft.getX(), (int) upperLeft.getY(), this.images.get(this.countHit));
        } else if (this.images.containsKey(-1)) { // -1 is default "fill" value.
            surface.drawImage((int) upperLeft.getX(), (int) upperLeft.getY(), this.images.get(-1));
        } else {
            surface.setColor(this.colors.get(-1));
            surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
        }
        if (this.strokeColor != null) {
            surface.setColor(this.strokeColor);
            surface.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
        }
    }
    /**
     * Need To Update!.
     */
    public void timePassed() {
        // Need To Update!
    }
    /**
     * add this block to the game.
     * @param game **Game object**
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
    /**
     * remove this block from the game.
     * @param game **Game object**
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * add hl to listeners List.
     * @param hl **HitListener**
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * remove hl from listeners List.
     * @param hl **HitListener**
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * get the number of Hits.
     * @return countHit **Counter**
     */
    public int getHitPoints() {
        return (this.countHit);
    }
    /**
     * get the width of the block.
     * @return width **double**
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * adds image to image map.
     * @param key **integer**
     * @param img **java.awt.Image**
     */
    public void addImg(int key, java.awt.Image img) {
        this.images.put(key, img);
    }
    /**
     * adds color to color map.
     * @param key **integer**
     * @param clr **java.awt.Color**
     */
    public void addClr(int key, java.awt.Color clr) {
        this.colors.put(key, clr);
    }
    /**
     * sets colors map to inputed one.
     * @param m **numbers to colors map**
     */
    public void setMapClr(HashMap<Integer, java.awt.Color> m) {
        this.colors = m;
    }
    /**
     * sets images map to inputed one.
     * @param m **numbers to images map**
     */
    public void setMapImg(HashMap<Integer, java.awt.Image> m) {
        this.images = m;
    }
}
