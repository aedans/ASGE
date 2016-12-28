package engine.entities;

import engine.renderer.resources.TexturedModel;
import engine.components.Component;

/**
 * Created by Aedan Smith.
 *
 * Class for Component-based Sprites.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class ComponentEntity extends Entity {
    /**
     * The array of components that modify the ComponentEntity.
     */
    public final Component<Entity>[] components;

    /**
     * Default ComponentEntity constructor.
     *
     * @param x The x position of the ComponentEntity.
     * @param y The y position of the ComponentEntity.
     * @param texturedModel The TexturedModel of the ComponentEntity.
     * @param components The components that modify the ComponentEntity.
     */
    @SafeVarargs
    public ComponentEntity(float x, float y, TexturedModel texturedModel, Component<Entity>... components) {
        super(x, y, texturedModel);
        this.components = components;
    }

    @Override
    public void update(){
        for (Component<Entity> component : components){
            component.apply(this);
        }
    }
}
