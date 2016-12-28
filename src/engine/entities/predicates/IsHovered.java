package engine.entities.predicates;

import engine.sprites.Sprite;
import engine.utils.BoundingRectangle;
import engine.utils.PointConverter;
import org.lwjgl.input.Mouse;

import java.util.function.Predicate;

/**
 * Created by Aedan Smith.
 */

@SuppressWarnings("WeakerAccess")
public class IsHovered implements Predicate<Sprite> {
    @Override
    public boolean test(Sprite sprite) {
        return testSprite(sprite);
    }

    public static boolean testSprite(Sprite sprite) {
        return BoundingRectangle.contains(
                sprite.getX(),
                sprite.getY(),
                sprite.getWidth()/2,
                sprite.getHeight()/2,
                PointConverter.convertX(Mouse.getX()),
                PointConverter.convertY(Mouse.getY())
        );
    }
}
