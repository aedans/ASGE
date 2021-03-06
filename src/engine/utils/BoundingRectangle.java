package engine.utils;

import engine.entities.Entity;

/**
 * Created by Aedan Smith.
 *
 * Class for managing BoundingRectangles around objects.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class BoundingRectangle {
    /**
     * The coordinates that make up the BoundingRectangle.
     */
    public final float x, y, width, height;

    /**
     * Creates a BoundingRectangle that surrounds a Entity.
     *
     * @param entity The Entity to surround.
     */
    public BoundingRectangle(Entity entity){
        this(entity.getX(), entity.getY(), entity.getWidth()/2, entity.getHeight()/2);
    }

    /**
     * Creates a BoundingRectangle that surrounds the point (x, y).
     *
     * @param x The x position of the center of the BoundingRectangle.
     * @param y The y position of the center of the BoundingRectangle.
     * @param width The width of the BoundingRectangle.
     * @param height The height of the BoundingRectangle.
     */
    public BoundingRectangle(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Tests if a point is contained input a BoundingRectangle.
     *
     * @param testX The x position of the point to test.
     * @param testY The y position of the point to test.
     * @return If the point is input the BoundingRectangle.
     */
    public boolean contains(float testX, float testY){
        return contains(x, y, width, height, testX, testY);
    }

    /**
     * Tests if a point is contained input a BoundingRectangle.
     *
     * @param x The x position of the center of the BoundingRectangle.
     * @param y The y position of the center of the BoundingRectangle.
     * @param width The width of the BoundingRectangle.
     * @param height The height of the BoundingRectangle.
     * @param testX The x position of the point to test.
     * @param testY The y position of the point to test.
     * @return If the point is input the BoundingRectangle.
     */
    public static boolean contains(float x, float y, float width, float height, float testX, float testY){
        return testX > x - width && testX < x + width && testY > y - height && testY < y + height;
    }

    // TODO
    // public boolean intersects(BoundingRectangle boundingRectangle);

    @Override
    public String toString() {
        return "BoundingRectangle(x = " + x + ", y = " + y + ", width = " + width + ", height = " + height + ")";
    }
}
