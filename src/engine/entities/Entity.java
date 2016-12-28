package engine.entities;

import engine.utils.MatrixMath;

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
    private float width, height;

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
     *
     * @param x The x amount to translate.
     * @param y The y amount to translate.
     */
    @SuppressWarnings("unused")
    public void translate(float x, float y) {
        this.x += x;
        this.y += y;
    }

    /**
     * Sets the position of the Entity.
     *
     * @param x The x position of the Entity.
     * @param y The y position of the Entity.
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
