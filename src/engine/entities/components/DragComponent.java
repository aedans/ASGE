package engine.entities.components;

import engine.components.PredicateComponent;
import engine.entities.Entity;
import engine.entities.predicates.IsHeld;
import engine.input.MouseButton;
import engine.utils.PointConverter;
import org.lwjgl.input.Mouse;

/**
 * Created by Aedan Smith.
 *
 * Class for creating draggable Entities.
 */

@SuppressWarnings("unused")
public class DragComponent<T extends Entity> extends PredicateComponent<T> {
    public DragComponent(MouseButton toDrag) {
        super(new IsHeld<>(toDrag));
    }

    @Override
    protected void whenTrue(T entity) {
        entity.setPosition(
                PointConverter.convertX(Mouse.getX()),
                PointConverter.convertY(Mouse.getY())
        );
    }

    @Override
    protected void whenFalse(T entity) {

    }
}
