package engine.sprites.transformations;

/**
 * Created by Aedan Smith.
 *
 * Interface for transforming objects.
 */

public interface Transformation<T> {
    T transform(T t);
}
