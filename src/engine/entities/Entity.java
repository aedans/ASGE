package engine.entities;

/**
 * Created by Aedan Smith.
 *
 * Abstract class for Entities that can be rendered by the renderer.
 */

@SuppressWarnings("WeakerAccess")
public abstract class Entity {
    /**
     * The position of the Entity.
     */
    private float x, y;

    /**
     * The dimensions of the Entity.
     */
    private final float width, height;

    /**
     * The scale of the Entity.
     */
    private float xScale = 1, yScale = 1;

    /**
     * Default Entity constructor.
     *
     * @param x The x position of the Entity.
     * @param y The y position of the Entity.
     * @param width The width of the Entity.
     * @param height The height of the Entity.
     */
    public Entity(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Translates the Entity.
     */
    @SuppressWarnings("unused")
    public void translate(float x, float y) {
        this.x += x;
        this.y += y;
    }

    /**
     * Sets the position of the Entity.
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Scales the Entity by the given amount.
     */
    @SuppressWarnings("unused")
    public void scale(float xScale, float yScale){
        this.xScale *= xScale;
        this.yScale *= yScale;
    }

    /**
     * Sets the scale of the Entity to the given amount.
     */
    @SuppressWarnings("unused")
    public void setScale(float xScale, float yScale){
        this.xScale = xScale;
        this.yScale = yScale;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width*xScale;
    }

    public float getHeight() {
        return height*yScale;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "("
                + "x = " + getX() + ", "
                + "y = " + getY() + ", "
                + "width = " + getWidth() + ", "
                + "height = " + getHeight() + ")";
    }
}
