package engine.sprites;

import engine.components.Component;
import engine.utils.Updateable;
import org.lwjgl.util.Renderable;

import java.util.ArrayList;

/**
 * Created by Aedan Smith.
 *
 * Class for managing groups of sprites.
 */

@SuppressWarnings("WeakerAccess")
public class SpriteManager implements Updateable, Renderable {
    /**
     * The list of sprites for the SpriteManager to manage.
     */
    public final ArrayList<Sprite> sprites;
    public final Component<SpriteManager>[] components;

    /**
     * Default SpriteManager constructor.
     *
     * @param sprites The list of sprites for the SpriteManager to manager.
     * @param components The components that make up the SpriteManager.
     */
    @SuppressWarnings("unused")
    @SafeVarargs
    public SpriteManager(ArrayList<Sprite> sprites, Component<SpriteManager>... components){
        this.sprites = sprites;
        this.components = components;
    }

    @Override
    public void update() {
        for (Component<SpriteManager> component : this.components) {
            component.apply(this);
        }
    }

    @Override
    public void render() {
        for (Sprite sprite : this.sprites){
            sprite.render();
        }
    }
}
