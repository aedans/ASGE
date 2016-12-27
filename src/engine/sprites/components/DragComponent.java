package engine.sprites.components;

import engine.components.PredicateComponent;
import engine.input.MouseButton;
import engine.sprites.Sprite;
import engine.sprites.predicates.IsHeld;
import engine.utils.PointConverter;
import org.lwjgl.input.Mouse;

/**
 * Created by Aedan Smith.
 *
 * Class for creating draggable sprites.
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
