package engine.game;

import engine.entities.Entity;
import engine.utils.Updateable;
import org.lwjgl.util.Renderable;

/**
 * Created by Aedan Smith.
 */

public abstract class GameObject extends Entity implements Updateable, Renderable {
    /**
     * Default GameObject constructor.
     *
     * @param x      The x position of the GameObject.
     * @param y      The y position of the GameObject.
     * @param width  The width of the GameObject.
     * @param height The height of the GameObject.
     */
    public GameObject(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
}
