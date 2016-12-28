package engine.entities;

import engine.renderer.resources.TexturedModel;
import engine.sprites.Sprite;
import engine.utils.Updateable;

/**
 * Created by Aedan Smith.
 *
 * Abstract class for Sprites that can be rendered by the renderer.
 */

@SuppressWarnings("WeakerAccess")
public abstract class Entity extends Sprite implements Updateable {
    /**
     * Default Entity constructor.
     *
     * @param x The x position of the Entity.
     * @param y The y position of the Entity.
     * @param texturedModel The TexturedModel of the Entity.
     */
    public Entity(float x, float y, TexturedModel texturedModel){
        super(x, y, texturedModel);
    }
}
