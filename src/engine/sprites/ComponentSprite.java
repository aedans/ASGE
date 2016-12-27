package engine.sprites;

import engine.renderer.resources.TexturedModel;
import engine.components.Component;

/**
 * Created by Aedan Smith.
 *
 * Class for Component-based Sprites.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class ComponentSprite extends Sprite {
    /**
     * The array of components that modify the ComponentSprite.
     */
    public final Component<Sprite>[] components;

    /**
     * Default ComponentSprite constructor.
     *
     * @param x The x position of the ComponentSprite.
     * @param y The y position of the ComponentSprite.
     * @param texturedModel The TexturedModel of the ComponentSprite.
     * @param components The components that modify the ComponentSprite.
     */
    @SafeVarargs
    public ComponentSprite(float x, float y, TexturedModel texturedModel, Component<Sprite>... components) {
        super(x, y, texturedModel);
        this.components = components;
    }

    @Override
    public void update(){
        for (Component<Sprite> component : components){
            component.apply(this);
        }
    }
}
