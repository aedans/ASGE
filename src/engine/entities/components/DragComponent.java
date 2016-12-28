package engine.entities.components;

import engine.components.PredicateComponent;
import engine.input.MouseButton;
import engine.entities.predicates.IsHeld;
import engine.sprites.Sprite;
import engine.utils.PointConverter;
import org.lwjgl.input.Mouse;

/**
 * Created by Aedan Smith.
 *
 * Class for creating draggable Sprite.
 */

@SuppressWarnings("unused")
public class DragComponent extends PredicateComponent<Sprite> {
    public DragComponent(MouseButton toDrag) {
        super(new IsHeld(toDrag));
    }

    @Override
    protected void whenTrue(Sprite sprite) {
        sprite.setPosition(
                PointConverter.convertX(Mouse.getX()),
                PointConverter.convertY(Mouse.getY())
        );
    }

    @Override
    protected void whenFalse(Sprite sprite) {

    }
}
