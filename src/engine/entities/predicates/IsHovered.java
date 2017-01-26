package engine.entities.predicates;

import engine.entities.Entity;
import engine.utils.BoundingRectangle;
import engine.utils.PointConverter;
import org.lwjgl.input.Mouse;

import java.util.function.Predicate;

/**
 * Created by Aedan Smith.
 */

@SuppressWarnings("WeakerAccess")
public class IsHovered<T extends Entity> implements Predicate<T> {
    @Override
    public boolean test(T entity) {
        return testEntity(entity);
    }

    public static boolean testEntity(Entity entity) {
        return BoundingRectangle.contains(
                entity.getX(),
                entity.getY(),
                entity.getWidth()/2,
                entity.getHeight()/2,
                PointConverter.convertX(Mouse.getX()),
                PointConverter.convertY(Mouse.getY())
        );
    }
}
