package engine.components;

/**
 * Created by Aedan Smith.
 *
 * Interface for creating component-based objects.
 */

@SuppressWarnings("WeakerAccess")
public interface Component<T> {
    void apply(T t);
}
